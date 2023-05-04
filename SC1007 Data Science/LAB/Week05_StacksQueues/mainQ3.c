#include <stdio.h>
#include <stdlib.h>

#define SIZE 1000 //The limit of expression length

typedef struct _stackNode{
    double item;
    struct _stackNode *next;
}StackNode;

typedef struct _stack{
   int size;
   StackNode *head;
}Stack;

void push(Stack *sPtr, double item);
int pop(Stack *sPtr);
double peek(Stack s);
int isEmptyStack(Stack s);

double exePostfix(char*);

int main()
{
    char postfix[SIZE];

    printf("Enter a postfix expression:\n");
    scanf("%[^\n]%*c",postfix);

    printf("The answer is %.2lf.\n",exePostfix(postfix));

    return 0;
}

void push(Stack *sPtr, double item){
    StackNode *newNode;
    newNode = (StackNode *) malloc(sizeof(StackNode));
    newNode->item = item;
    newNode->next = sPtr->head;
    sPtr->head = newNode;
    sPtr->size++;
}

int pop(Stack *sPtr){
    if(sPtr == NULL || sPtr->head == NULL){
        return 0;
    }
    else{
       StackNode *temp = sPtr->head;
       sPtr->head = sPtr->head->next;
       free(temp);
       sPtr->size--;
       return 1;
    }
}

double peek(Stack s){
    return s.head->item;
}

int isEmptyStack(Stack s){
     if(s.size == 0) return 1;
     else return 0;
}

double exePostfix(char* postfix)
{
 //Write your code here
 // 987-654-/3+*+
 Stack st;
 int i=0;
 double j=0,k=0,total=0;

 st.head=NULL;
 st.size=0;

 while(postfix[i]!=NULL){

    if(postfix[i]>='0' && postfix[i]<='9'){
        push(&st, (double)(postfix[i])-48 );
        //printf("num %c %.2lf \n",postfix[i], (double)(postfix[i])-48 );
    }
    else {

        switch(postfix[i]){
        case '*':
            j = peek(st);
            pop(&st);
            k = peek(st) * j;
            pop(&st);
            push(&st,k);
            //printf("*    j %.2f  k %.2f \n",j,k);
            break;
        case '+':
            j = peek(st);
            pop(&st);
            k = peek(st) + j;
            pop(&st);
            push(&st,k);
            //printf("+    j %.2f  k %.2f \n",j,k);
            break;
        case '-':
            j = peek(st);
            pop(&st);
            k = peek(st) - j;
            pop(&st);
            push(&st,k);
            //printf("-    j %.2f  k %.2f \n",j,k);
            break;
        case '/':
            j = peek(st);
            pop(&st);
            k = peek(st) / j;
            pop(&st);
            push(&st,k);
            //printf("/    j %.2f  k %.2f \n",j,k);
            break;
        default:
            //printf("?????????\n");
            break;

        }
    }
    i++;
    j=0;
    k=0;
 }

  if(st.size==1){
    total = (float)peek(st);
    pop(&st);
  }

  return total;


}
