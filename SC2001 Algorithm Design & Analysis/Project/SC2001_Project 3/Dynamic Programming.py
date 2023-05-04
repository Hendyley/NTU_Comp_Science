from timeit import default_timer as timer



# A Dynamic Programming based Python bottom-up manner
def knapSack2(Capacity, weight, profit, n):
    K = [[0 for x in range(Capacity + 1)] for x in range(n + 1)]
    # Build table K[][] in bottom up manner
    for i in range(n + 1):
        for w in range(Capacity + 1):
            if i == 0 or w == 0:
                K[i][w] = 0
            elif weight[i-1] <= w:
                K[i][w] = max(profit[i-1]
                              + K[i-1][w-weight[i-1]],
                              K[i-1][w])
            else:
                K[i][w] = K[i-1][w]
    return K[n][Capacity]
weight = [5,6,8]
profit = [7,6,9]
Capacity = 14
n = 3
start = timer()
print("Maximum profit: ",knapSack2(Capacity, weight, profit, n))
end = timer()
print("Time Taken: ",end - start)

# Complexity Analysis:
#
# Time Complexity: O(N*W).
# where ‘N’ is the number of weight element and ‘W’ is capacity. As for every weight element we traverse through all weight capacities 1<=w<=W.
# Auxiliary Space: O(N*W).
# The use of 2-D array of size ‘N*W’.


# Recursion by Brute-Force algorithm OR Exhaustive Search.
# A naive recursive implementation
# of 0-1 Knapsack Problem

# Returns the maximum value that
# can be put in a knapsack of
# capacity W


def knapSack1(Capacity, weight, profit, n):
    # Base Case
    if n == 0 or Capacity == 0:
        return 0
    # If weight of the nth item is
    # more than Knapsack of capacity,
    # then this item cannot be included
    # in the optimal solution
    if (weight[n-1] > Capacity):
        return knapSack1(Capacity, weight, profit, n-1)
    # return the maximum of two cases: (1) nth item included (2) not included
    else:
        return max(
            profit[n-1] + knapSack1(
                Capacity-weight[n-1], weight, profit, n-1),
            knapSack1(Capacity, weight, profit, n-1))
weight = [4,6,8]
profit = [7,6,9]
Capacity = 14
n = 3
start = timer()
#print("Maximum profit: ",knapSack1(Capacity, weight, profit, n))
end = timer()
#print("Time Taken: ",end - start)


# Complexity Analysis:
#
# Time Complexity: O(2n).
# As there are redundant subproblems.
# Auxiliary Space :O(1) + O(N).
# As no extra data structure has been used for storing values but O(N) auxiliary stack space(ASS) has been used for recursion stack.





#https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/





