#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

//memory
int *r;

int cr_recursive(int *p, int n)
{
    if (n == 0)
        return 0;

    int i;
    int q = -1;     //current max value
    for (i=1;i<=n;i++)
    {
        int temp = p[i] + cr_recursive(p, n-i);
        if (temp > q)
            q = temp;
    }

    return q;
}

int cr_top_down_dp(int *p, int n)
{
    if (n == 0)
        return 0;

    //check memory
    if (r[n] > 0)
        return r[n];

    int i;
    int q = -1;     //current max value
    for (i=1;i<=n;i++)
    {
        int temp = p[i] + cr_top_down_dp(p, n-i);
        if (temp > q)
            q = temp;
    }

    //write to the memory
    r[n] = q;
    return q;
}

int cr_bottom_up_dp(int *p, int n)
{
    int i,j;
    r[0] = 0;
    for (j=1;j<=n;j++)
        for (i=1;i<=j;i++)
        {
            int temp = p[i] + r[j-i];
            if (temp > r[j])
                r[j] = temp;
        }
    return r[n];
}

void main ()
{
    int n;      //length of  rod
    int function;
    int i;
    int *p;     //price list

    int price_list[10] = {0,1,4,8,9,10,17,17,20,24}; //declare one more value to cover the index 0;

    n = sizeof(price_list) / sizeof(int) - 1;
    p = price_list;


    //allocate the memory array
    r = malloc(sizeof(int) * (n+1));

    printf("Choose a function (1 for recursive, 2 for top-down DP, 3 for bottom-up DP, 0 to stop): \n");
    scanf("%d",&function);
    while (function != 0)
    {
        if (function == 1)
        {
            printf("The maximun value is: %d \n", cr_recursive(p,n));
        }
        else if (function == 2)
        {
            //set all memory elements to -1
            for (i=0; i<=n; i++)
                r[i] = -1;

            printf("The maximun value is: %d \n", cr_top_down_dp(p,n));
        }
        else if (function == 3)
        {
            //set all memory elements to -1
            for (i=0; i<=n; i++)
                r[i] = -1;

            printf("The maximun value is: %d \n", cr_bottom_up_dp(p,n));
        }
        printf("Choose a function (1 for recursive, 2 for top-down DP, 3 for bottom-up DP, 0 to stop): \n");
        scanf("%d",&function);
    }
}
