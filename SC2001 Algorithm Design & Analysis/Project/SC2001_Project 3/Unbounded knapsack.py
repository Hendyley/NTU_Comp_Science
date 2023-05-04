from timeit import default_timer as timer


def unboundedKnapsack(W, index, val, wt):

    #Base case of recursion when only one element is there.
    if index==0 :
        return (W//wt[0])*val[0]
    #If the element with referred by index is doesn't occur even once in the required solution
    notTake= 0 + unboundedKnapsack(W,index-1,val,wt)
    #If the element is occur atleast once in the required solution
    take=-100000;
    if wt[index]<=W:
        take=val[index]+unboundedKnapsack(W-wt[index],index,val,wt)
    return max(take,notTake)


weight = [5,6,8]
profit = [7,6,9]
Capacity = 14
n = 3
start = timer()
print("Maximum profit: "+unboundedKnapsack(Capacity, weight, profit, n))
end = timer()
print("Time Taken: "+end - start)
