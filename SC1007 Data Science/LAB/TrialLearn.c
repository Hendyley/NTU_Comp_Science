# include <stdio.h >
# include <stdlib.h >

struct person {
    char first [15];
    char last [15];
    struct {
        int age ;
        float height ;
        float weight ;
    } Info ,* InfoPtr ;
} student1 ;
typedef struct person person_t ;

int main ()
{

int v=0;
int *x=&v;
v=2;
*x = 1;

printf("%d %d \n",v,*x);

return 0;
}
