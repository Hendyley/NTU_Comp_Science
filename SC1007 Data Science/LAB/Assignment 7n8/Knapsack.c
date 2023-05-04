#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

int **M;
int max(int a, int b) { return (a > b)? a : b; }
int bottom_up_dp(int n, int *s, int *v, int C)
{
    //write your code here
    int i,j;
    M[1][1] = 0;

    for (i=0;i<=n;i++){
        for (j=0;j<=C;j++)
        {
           if (i==0 || j==0)
               M[i][j] = 0;
           else if (s[i] <= j)
                 M[i][j] = max( v[i] + M[i-1][j-s[i]],  M[i-1][j] );
           else
                 M[i][j] = M[i-1][j];

        //printf("M[%d,%d] %d %d\n",i,j,M[i][j]);
        }
    }
    return M[n][C];
}


int main ()
{
    int n,C;
    int function;
    int *s;
    int *v;
    int i,j;
    printf("Enter the number of items n:\n");
    scanf("%d",&n);
    printf("Enter the capacity C:\n");
    scanf("%d",&C);
    s = (int *) malloc(sizeof(int) * (n+1));
    v = (int *) malloc(sizeof(int) * (n+1));

    printf("Enter the sizes of items (as a list, separate by spacing:\n");
    for (i=1;i<=n;i++)
        scanf("%d",&s[i]);

    printf("Enter the values of items (as a list, separate by spacing:\n");
    for (i=1;i<=n;i++)
        scanf("%d",&v[i]);

    M = (int **)malloc((n+1)*sizeof(int *));
    //malloc(sizeof(int) * (n+1));

    for(i=0;i<=n;i++)
        M[i] = (int *)malloc((C+1)*sizeof(int));

    for (i=0;i<=n;i++){
        for (j=0;j<=C;j++){
            M[i][j] = 0;
            //printf("Test\n");
        }
    }
    //printf("Dones\n");

    printf("The maximum value is: %d \n", bottom_up_dp(n,s,v,C));

}

/*
4
5
2 1 3 2
12 10 20 15
*/

/*
M(i,j) = max {M(i-1,j), M(i-1, j-s1) + vl}
37
*/
