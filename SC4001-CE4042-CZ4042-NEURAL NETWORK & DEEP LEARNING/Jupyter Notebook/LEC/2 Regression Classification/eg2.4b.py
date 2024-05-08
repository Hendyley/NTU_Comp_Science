import tensorflow as tf
import numpy as np
import pylab as plt
import multiprocessing as mp

import os
if not os.path.isdir('figures'):
	print('creating the figures folder')
	os.makedirs('figures')
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'
    
no_data = 16
no_iters = 500
SEED = 10
np.random.seed(SEED)

# training data
X = np.random.rand(no_data,2)
Y = 1.0 +3.3*X[:,0]**2-2.5*X[:,1]+0.2*X[:,0]*X[:,1]
Y = Y.reshape(no_data,1)

# a class for the preceptron
class Perceptron():
  def __init__(self):
    self.w = tf.Variable(0.01*np.random.rand(2,1), dtype=tf.float64)
    self.b = tf.Variable([0.], dtype=tf.float64)

  def __call__(self, x):
    u = tf.matmul(x, self.w) + self.b
    y = 6.0*tf.sigmoid(u)-1.5
    return y

def loss(predicted_y, d):
  return tf.reduce_sum(tf.square(predicted_y - d))

def train(model, inputs, outputs, learning_rate):
  with tf.GradientTape() as t:
    current_loss = loss(model(inputs), outputs)
  dw, db = t.gradient(current_loss, [model.w, model.b])
  model.w.assign_sub(learning_rate * dw)
  model.b.assign_sub(learning_rate * db)


model = Perceptron()

def my_train(rate):
 
  print(rate)

  err = []
  for i in range(no_iters):
    train(model, X, Y, rate)
    loss_= loss(model(X), Y)
    err.append(loss_.numpy())
    
    if not i%10:
      print(i, err[i])

  return err
  

if __name__ == '__main__':
        
    
        no_threads = mp.cpu_count()
        rates = [0.005, 0.01, 0.05, 0.1]

        p = mp.Pool(processes = no_threads)
        results = p.map(my_train, rates)

        plt.figure()
        for i in range(len(rates)):
          plt.plot(range(no_iters), results[i], label='lr = {}'.format(rates[i]))
        plt.xlabel('epochs')
        plt.ylabel('cost')
        plt.title('gradient descent')
        plt.legend()
        plt.savefig('./figures/2.4b_1.png')

        #plt.show()


