### THIS FILE CONTAINS COMMON FUNCTIONS, CLASSSES

import tqdm
import time
import random 
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

import torch
from torch import nn
from torch.utils.data import Dataset
from torch.utils.data import DataLoader

from scipy.io import wavfile as wav

from sklearn import preprocessing
from sklearn.model_selection import KFold
from sklearn.model_selection import train_test_split
from sklearn.metrics import f1_score, precision_score, recall_score, confusion_matrix



def split_dataset(df, columns_to_drop, test_size, random_state):
    label_encoder = preprocessing.LabelEncoder()

    df['label'] = label_encoder.fit_transform(df['label'])

    df_train, df_test = train_test_split(df, test_size=test_size, random_state=random_state)

    df_train2 = df_train.drop(columns_to_drop,axis=1)
    y_train2 = df_train['label'].to_numpy()

    df_test2 = df_test.drop(columns_to_drop,axis=1)
    y_test2 = df_test['label'].to_numpy() 

    return df_train2, y_train2, df_test2, y_test2

def preprocess_dataset(df_train, df_test):

    standard_scaler = preprocessing.StandardScaler()
    df_train_scaled = standard_scaler.fit_transform(df_train)

    df_test_scaled = standard_scaler.transform(df_test)

    return df_train_scaled, df_test_scaled

def set_seed(seed = 0):
    '''
    set random seed
    '''
    random.seed(seed)
    torch.manual_seed(seed)
    np.random.seed(seed)
    torch.cuda.manual_seed_all(seed)
    torch.backends.cudnn.deterministic = True


# early stopping obtained from tutorial
class EarlyStopper:
    def __init__(self, patience=3, min_delta=0):
        self.patience = patience
        self.min_delta = min_delta
        self.counter = 0
        self.min_validation_loss = np.inf

    def early_stop(self, validation_loss):
        if validation_loss < self.min_validation_loss:
            self.min_validation_loss = validation_loss
            self.counter = 0
        elif validation_loss > (self.min_validation_loss + self.min_delta):
            self.counter += 1
            if self.counter >= self.patience:
                return True
        return False
    
    
# Defined in Part_A_1.ipynb 

class MLP(nn.Module):

    def __init__(self, no_features, no_hidden, no_labels):
        super().__init__()
        self.mlp_stack = nn.Sequential(
            # YOUR CODE HERE
            nn.Linear(no_features, no_hidden),
            nn.ReLU(),
            nn.Dropout(0.2),
            
            nn.Linear(no_hidden, no_hidden),
            nn.ReLU(),
            nn.Dropout(0.2),
            
            nn.Linear(no_hidden, no_hidden),
            nn.ReLU(),
            nn.Dropout(0.2),
            
            nn.Linear(no_hidden, no_labels),
            nn.Sigmoid()       
        )

    # YOUR CODE HERE
    def forward(self, x):
        return self.mlp_stack(x)

# New Class to take in different depth and width. Defined in Part_A_3.ipynb

class Part_A3_MLP(nn.Module):

    def __init__(self, no_features, hidden_units, no_labels):
        super().__init__()

        depth = len(hidden_units)
        
        layers = []
        
        # Adding hidden layers
#         print("1 hidden \n",no_features, hidden_units[0])
        layers.append(nn.Linear(no_features, hidden_units[0]))
        layers.append(nn.ReLU())
        layers.append(nn.Dropout(0.2))
        
        if depth > 1: 
            for i in range(depth - 1):
#                 print("1 hidden \n",hidden_units[i], hidden_units[i+1])
                layers.append(nn.Linear(hidden_units[i], hidden_units[i+1]))
                layers.append(nn.ReLU())
                layers.append(nn.Dropout(0.2))

        # Adding output layer
        layers.append(nn.Linear(hidden_units[-1], no_labels))
        layers.append(nn.Sigmoid())
        
        self.mlp_stack = nn.Sequential(*layers)

    def forward(self, x):
        return self.mlp_stack(x)
    
    
class CustomDataset(Dataset):
    # YOUR CODE HERE
    def __init__(self, X, y):
        self.X =torch.tensor(X, dtype=torch.float)
        self.y =torch.tensor(y, dtype=torch.float).unsqueeze(1)
    def __len__(self):
        return len(self.y)
    def __getitem__(self,idx):
        return self.X[idx], self.y[idx]
    
loss_fn = nn.BCELoss()

def train_loop(dataloader, model, loss_fn, optimizer):
    model.train()
    size = len(dataloader.dataset)
    num_batches = len(dataloader)
    train_loss, correct = 0, 0
    for batch, (X, y) in enumerate(dataloader):
        # Compute prediction and loss
        pred = model(X)
        loss = loss_fn(pred, y)
        
        train_loss += loss.item()
        correct += ((pred > 0.5).type(torch.float) == y).type(torch.float).sum().item()


        # Backpropagation
        optimizer.zero_grad()
        loss.backward()
        
        # Update Weight
        optimizer.step()
    
        if batch % 100 == 0:
                loss, current = loss.item(), (batch + 1) * len(X)
                print(f"loss: {loss:>7f}  [{current:>5d}/{size:>5d}]")
    
    train_loss /= num_batches
    correct /= size
    print(f"Train Error: \n Accuracy: {(correct*100):>0.1f}%, Avg loss: {train_loss:>8f} \n")
    
    return train_loss, correct
    

def test_loop(dataloader, model, loss_fn):
    model.eval()
    size = len(dataloader.dataset)
    num_batches = len(dataloader)
    test_loss, correct = 0, 0

    with torch.no_grad():
        for X, y in dataloader:
            pred = model(X)
            test_loss += loss_fn(pred, y).item()
            correct += ((pred > 0.5).type(torch.float) == y).type(torch.float).sum().item()

    test_loss /= num_batches
    correct /= size
    print(f"Test Error: \n Accuracy: {(100*correct):>0.1f}%, Avg loss: {test_loss:>8f} \n")
    
    return test_loss, correct

def train(model, X_train_scaled, y_train2, X_val_scaled, y_val2, batch_size):

    # YOUR CODE HERE
    
    train_data = CustomDataset(X_train_scaled, y_train2)
    val_data = CustomDataset(X_val_scaled, y_val2)

    train_dataloader = DataLoader(train_data, batch_size=batch_size, shuffle=True)
    val_dataloader = DataLoader(val_data, batch_size=batch_size, shuffle=True)
    num_batches = len(train_dataloader)
    
    loss_fn = nn.BCELoss()
    optimizer = torch.optim.Adam(model.parameters(), lr=0.001)
    Early_stopper = EarlyStopper(patience=3, min_delta=0)


    train_losses, train_accuracies = [], [] 
    test_losses, test_accuracies = [], []
    times, acc_ = [], []
    t = 0

    for epoch in range(epochs):

        print(f"Epoch {epoch+1}\n-------------------------------")

        start = time.time()

        train_loss, train_accuracy = train_loop(train_dataloader, model, loss_fn, optimizer)
        test_loss, test_accuracy = test_loop(val_dataloader, model, loss_fn)

        t += time.time() - start

        train_losses.append(train_loss), train_accuracies.append(train_accuracy)
        test_losses.append(test_loss), test_accuracies.append(test_accuracy)

        # Early Stopper
        if Early_stopper.early_stop(test_loss): 
            print("Done!")
            break

        # Accuracy for the current Fold at last Epoch
        acc_.append(test_accuracies[-1])

        # Time taken for the current Fold at last Epoch
        #t_.append(epoch_times[-1])
        times.append(t*1000 / (epochs) )

        print(f"Batch size: {batch_size}, Time: {times[-1]:>3f}s , Accuracy: {acc_[-1]:>2f}")       


    return train_accuracies, train_losses, test_accuracies, test_losses, times
