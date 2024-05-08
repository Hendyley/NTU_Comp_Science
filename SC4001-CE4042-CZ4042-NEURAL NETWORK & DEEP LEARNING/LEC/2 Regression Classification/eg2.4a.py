import tensorflow as tf
import numpy as np
import pylab as plt
import multiprocessing as mp
from functools import partial

import os
if not os.path.isdir('figures'):
	os.makedirs('figures')
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

print(tf.__version__)

no_data = 16
no_iters = 500
SEED = 10
np.random.seed(SEED)

## training data
X = np.random.rand(no_data,2)
Y = 1.0 +3.3*X[:,0]**2-2.5*X[:,1]+0.2*X[:,0]*X[:,1]
Y = Y.reshape(no_data,1)

# a class for the preceptron
class Perceptron():
  def __init__(self):
    self.w = tf.Variable(0.01*np.random.rand(2), dtype=tf.float64)
    self.b = tf.Variable(0., dtype=tf.float64)

  def __call__(self, x):
    u = tf.tensordot(x ,self.w, axes=1) + self.b
    y = 6.0*tf.sigmoid(u)-1.5
    return y

lr = 0.0001
model = Perceptron()

def loss(predicted_y, d):
  return tf.reduce_sum(tf.square(predicted_y - d))

def train(model, inputs, outputs, learning_rate):
  with tf.GradientTape() as t:
    current_loss = loss(model(inputs), outputs)
  dw, db = t.gradient(current_loss, [model.w, model.b])
  model.w.assign_sub(learning_rate * dw)
  model.b.assign_sub(learning_rate * db)

def my_train(alpha):

  cost = []
  idx = np.arange(no_data)
  XX, YY = X, Y
  for i in range(no_iters):
    np.random.shuffle(idx)
    XX, YY = XX[idx], YY[idx]
    cost_ = []
    for p in range(len(XX)):
      train(model, XX[p], YY[p], alpha)
      cost_.append(loss(model(XX[p]), YY[p]))
    cost.append(np.sum(cost_)/no_data)

    if not i%10:
      print(i, cost[i])

  return cost


if __name__ == '__main__':

        rates = [0.005, 0.01, 0.05, 0.1]

        

        no_threads = mp.cpu_count()
        p = mp.Pool(processes = no_threads)
        costs = p.map(my_train, rates)

        plt.figure()
        for r in range(len(rates)):
          plt.plot(range(no_iters), costs[r], label='lr = {}'.format(rates[r]))
        plt.xlabel('iterations')
        plt.ylabel('cost')
        plt.title('stochastic gradient descent')
        plt.legend()
        plt.savefig('./figures/2.4a_1.png')

        # plt.show()


