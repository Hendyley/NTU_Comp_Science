#include <stdio.h>
int main()
{
char ar[20] = "User M 19 1.85";

char str[10];
char ch;
int i;
float f;

/* Calling sscanf() to read multiple values from a char[] array and store each value in matching variable */
sscanf(ar, "%s %c %d %f", &str, &ch, &i, &f);

printf("ar: %s\n",ar);
printf("The value in string is : %s ", str);
printf("\n");

printf("The value in char is : %c ", ch);
printf("\n");

printf("The value in int is : %d ", i);
printf("\n");

printf("The value in float is : %f ", f);

sscanf(ar, "%s %c %d", &str, &ch, &i);

printf("\nar: %s\n",ar);
return 0;
}
