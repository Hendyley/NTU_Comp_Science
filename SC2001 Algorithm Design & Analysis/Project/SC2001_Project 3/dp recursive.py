# Python3 program to find maximum
# achievable value with a knapsack
# of weight W and multiple instances allowed.
import time


def timer(func):
    def wrap(*args, **kwargs):
        start_time = time.perf_counter()
        func(*args, **kwargs)
        end_time = time.perf_counter()
        comp_time = end_time - start_time
        print("*" * 10)
        print("Computation Time:", comp_time)

    return wrap


def unboundedKnapsack(Capacity, index, profit, weight):
    # Base case of recursion when only one element is there.
    if index == 0: return (Capacity // weight[0]) * profit[0]
    # If the element with referred by index is doesn't occur even once in the required solution
    notTake = 0 + unboundedKnapsack(Capacity, index - 1, profit, weight)
    # If the element is occur atleast once in the required solution
    take = -100000
    if weight[index] <= Capacity:
        take = profit[index] + unboundedKnapsack(Capacity - weight[index], index, profit, weight)
    return max(take, notTake)

Capacity = 14
profit = [7, 6, 9]
weight = [4, 6, 8]
n = 3
start_time = time.perf_counter()
print("Maximum profit: ",unboundedKnapsack(Capacity, n - 1, profit, weight))

end_time = time.perf_counter()

comp_time = end_time - start_time
print("Computation Time:", comp_time)
