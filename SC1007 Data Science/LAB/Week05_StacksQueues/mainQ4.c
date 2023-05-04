#include <stdio.h>
#include <stdlib.h>

#define SIZE 1000 //The limit of expression length

typedef struct _stackNode{
    char item;
    struct _stackNode *next;
}StackNode;

typedef struct _stack{
   int size;
   StackNode *head;
}Stack;

void push(Stack *sPtr, char item);
int pop(Stack *sPtr);
char peek(Stack s);
int isEmptyStack(Stack s);

void in2Post(char*, char*);

int main()
{
    char infix[SIZE];
    char postfix[SIZE];

    printf("Enter an infix expression:\n");
    scanf("%[^\n]%*c",infix);

    in2Post(infix,postfix);
    printf("The postfix expression is \n");
    printf("%s\n",postfix);
    return 0;
}

void push(Stack *sPtr, char item){
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

char peek(Stack s){
    return s.head->item;
}

int isEmptyStack(Stack s){
     if(s.size == 0) return 1;
     else return 0;
}

void in2Post(char* infix, char* postfix)
{
 //Write your code here
//  a+b*c-d*(e/f)    => abc*+def/*-
//Infix: (A+B)+(C-D)     Postfix: AB+CD-+
//Infix: 9+(8-7)*(6/(5-4)+3)    Postfix: 987-654-/3+*+
 int i=0,j=0;
 Stack ck;

 ck.head=NULL;
 ck.size=0;

 while(infix[i]!=NULL){
    if (infix[i]==')'){
        while(ck.size!=0 && peek(ck)!='(' ){
            postfix[j++] = peek(ck);
            pop(&ck);
        }
        pop(&ck);
    }
    else if (infix[i]=='('){
        push(&ck,infix[i]);
    }
    else if (infix[i]=='+' || infix[i]=='-' || infix[i]=='/' || infix[i]=='*'){
        while(ck.size!=0 && peek(ck)!='(' && peek(ck) >= infix[i] ){
            postfix[j++] = peek(ck);
            pop(&ck);
        }
        push(&ck,infix[i]);
    } else {
        postfix[j++] = infix[i];
    }


    i++;
 }

 while(isEmptyStack(ck)!=1){
    postfix[j++] = peek(ck);
    pop(&ck);
 }

}
