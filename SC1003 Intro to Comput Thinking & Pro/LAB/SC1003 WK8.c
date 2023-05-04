#include <stdio.h>
int main()
{
//First Part
int sel;

printf("Select test case: \n");
scanf("%d",&sel);

int studentNumber = 0, mark;

int total, count, lines, input;
double average;


int height, val, i,j;
int num = 0;

int n, denominator = 1;
float x, result = 1.0, numerator = 1.0;

switch(sel){
    case 1:
//part 1

       printf("Enter Student ID: \n");
       scanf("%d", &studentNumber);
       while (studentNumber != -1)
       {
          printf("Enter Mark: ");
          scanf("%d", &mark);
          if (mark >= 75)
             printf("Grade = A\n");
          else if (mark >= 65)
            printf("Grade = B\n");

          else if (mark >= 55)
             printf("Grade = C\n");
          else if (mark >= 45)
             printf("Grade = D\n");
          else
             printf("Grade = F\n");
          printf("Enter Student ID: ");
          scanf("%d", &studentNumber);
       }
       break;
    case 2:
//2nd part

       printf("Enter number of lines: \n");
       scanf("%d", &lines);
       for (i = 0; i < lines; i++) {
          total=0; count=0;
          printf("Enter line %d (end with -1): \n", i+1);
          scanf("%d",&input);
          while (input != -1)
          {
             total += input;
             count++;
             scanf("%d",&input);
          }
          average = (double)total/(double)count;
          printf("Average = %.2f\n", average);
       }
       break;
    case 3:
//3rd Part

        printf("Enter Height :\n");
        scanf("%d",&height);
        for(i=0; i < height; i++){
            for(j=0; j<i+1; j++){
                printf("%d",num+1);
            }
            num = (num +1)%3;
            printf("\n");
        }
        break;
    case 4:
//4th Part

       printf("Enter x: \n");
       scanf("%f", &x);
       for (n = 1; n <= 10; n++)
       {
          denominator *= n;
          numerator *= x;
          result += numerator/denominator;
       }
       printf("Result = %.2f\n",  result);
       break;
    default: printf("Redo! n");

}

}
