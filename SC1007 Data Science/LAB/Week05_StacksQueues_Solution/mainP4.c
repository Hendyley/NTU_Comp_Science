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

void in2Pre(char*, char*);
int precedence(char);

int main()
{
    char infix[SIZE];
    char prefix[SIZE];

    printf("Enter an infix expression:\n");
    scanf("%[^\n]%*c",infix);

    in2Pre(infix,prefix);
    printf("The prefix expression is \n");
    printf("%s\n",prefix);

    return 0;
}

void in2Pre(char* infix, char* prefix)
{
   Stack s;
   s.head = NULL;
   s.size = 0;

   char revInfix[SIZE];
   char revPrefix[SIZE];
   int i=0;
   int j = 0;
   char c;
   int count =0;

   //reverse the string
   while(infix[count]!='\0')count++;
   for(i=count-1;i>=0;i--)
    revInfix[count-1-i] = infix[i];
   revInfix[count]='\0';

   i=0;
   while(i<SIZE && revInfix[i]!='\0')
    {
        c = revInfix[i++];
        switch(c)
        {
        case '*': //operators
        case '/':
        case '+':
        case '-':
            while(isEmptyStack(s)==0 && peek(s) != ')' && precedence(peek(s)) > precedence(c) ) //note the precedence comparison
            {
                revPrefix[j++] = peek(s);
                pop(&s);
            }
            push(&s,c);
            break;
        case ')':
            push(&s,c);
            break;
        case '(':
            while(isEmptyStack(s)==0)
            {
                if(peek(s)!=')')
                {
                    revPrefix[j++]=peek(s);
                    pop(&s);
                }
                else
                {
                    pop(&s);
                    break;
                }
            }
            break;
        default: //operand
            revPrefix[j++] = c;
        }
    }

    while(isEmptyStack(s)==0)
    {
        revPrefix[j++] = peek(s);
        pop(&s);
    }
    revPrefix[j] = '\0';

   //reverse the string
  count=0;
  while(revPrefix[count]!='\0')count++;
    for(i=count-1;i>=0;i--)
    {
        prefix[count-1-i] = revPrefix[i];
    }
    prefix[count]='\0';

}

int precedence(char op)
{
    if(op == '*' || op == '/')
        return 1;
    else return 0;
}

void push(Stack *sPtr, char item){
    StackNode *newNode;
    newNode = malloc(sizeof(StackNode));
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
