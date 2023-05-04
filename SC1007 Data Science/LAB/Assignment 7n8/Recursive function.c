#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

int *M;

int top_down_dp(int n)
{
    //write your code here
    if(n==0){
        M[0] = 0;
        return 0;
    }
    if(n==1){
        M[1] = 1;
        return 1;
    }
    if(n==2){
        M[2] = 2;
        return 2;
    }

    if(M[n-1]==-1){
        M[n-1] = top_down_dp(n-1);
    }
    if(M[n-2]==-1){
        M[n-2] = top_down_dp(n-2);
    }
    if(M[n-3]==-1){
        M[n-3] = top_down_dp(n-3);
    }
    M[n] = M[n-1] + 2*M[n-2] - 3*M[n-3];
    return M[n];

}



int main ()
{
    int n;
    int function;
    int i;
    printf("Enter the value of n:\n");
    scanf("%d",&n);

    M = malloc(sizeof(int) * (n+1));
    for (i=0; i<=n; i++){
        M[i] = -1;
    }

    printf("The value of F(%d) is: %d \n", n, top_down_dp(n));

}
