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


def unboundedKnapsack(Capacity, n, profit, weight):
    # dp[i] is going to store maximum
    # value with knapsack capacity i.
    dp = [0 for i in range(Capacity + 1)]
    ans = 0
    # Fill dp[] using above recursive formula
    for i in range(Capacity + 1):
        for j in range(n):
            if (weight[j] <= i):
                dp[i] = max(dp[i], dp[i - weight[j]] + profit[j])

    return dp[Capacity]
Capacity = 14
profit = [7, 6, 9]
weight = [5, 6, 8]
n = 3

start_time = time.perf_counter()
print("Maximum profit: ",unboundedKnapsack(Capacity, n, profit, weight))
end_time = time.perf_counter()

comp_time = end_time - start_time
print("Computation Time:", comp_time)

# This code is contributed by Anant Agarwal.
