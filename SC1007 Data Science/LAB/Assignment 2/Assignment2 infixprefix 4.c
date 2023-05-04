#include <stdio.h>
#include <stdlib.h>

#define SIZE 1000 //The size of the array

enum ExpType {OPT,OPERAND};

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

typedef struct _listnode
{
    int  item;
    enum ExpType type;
	struct _listnode *next;
} ListNode;

typedef struct _linkedlist{
   int size;
   ListNode *head;
} LinkedList;

void insertNode(LinkedList *llPtr, int item,enum ExpType type);
int deleteNode(LinkedList *llPtr);
void removeAllNodes(LinkedList *llPtr);
int isEmptyLinkedList (LinkedList ll);


void in2PreLL(char* infix, LinkedList *inExpLL);

void printExpLL(LinkedList inExp);

int main()
{
    char infix[SIZE];

    //printf("Enter an infix expression:\n");
    scanf("%[^\n]%*c",infix);

    LinkedList inExpLL;
    inExpLL.head = NULL;
    inExpLL.size = 0;

    in2PreLL(infix, &inExpLL);

    printExpLL(inExpLL);

    removeAllNodes(&inExpLL);
    return 0;
}

void printExpLL(LinkedList inExpLL)
{
    ListNode* temp = NULL;
    temp = inExpLL.head;
    while(temp!= NULL){
        if(temp->type == OPERAND)
            printf(" %d ",temp->item);
        else
            printf(" %c ",(char)(temp->item));
        temp = temp->next;
    }
    printf("\n");
}

void insertNode(LinkedList *LLPtr, int item, enum ExpType type) {
    ListNode *newNode;
    newNode = malloc(sizeof(ListNode));
    if(newNode==NULL) exit(0);

    newNode->item = item;
    newNode->type = type;
    newNode->next = LLPtr->head;
    LLPtr->head=newNode;
    LLPtr->size++;
}


int deleteNode(LinkedList *LLPtr) {
    if(LLPtr==NULL || LLPtr->size==0){
        return 0;
    }
    else{
       ListNode *temp = LLPtr->head;
       LLPtr->head = LLPtr->head->next;

       free(temp);
       LLPtr->size--;
       return 1;
    }
}

int isEmptyLinkedList (LinkedList ll) {
    if(ll.size==0) return 1;
    else return 0;
}

void removeAllNodes(LinkedList *LLPtr)
{
	while(deleteNode(LLPtr));
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



int getprio(char x){
    if(x=='+' || x=='-'){
        return 1;
    } else if (x=='*' || x=='/'){
        return 2;
    } else if (x=='^'){
        return 3;
    }
    return 0;
}

//24/18*4-6+7/8/(204-32) ((12+24)*(45+34)) 99+(88-77)*(66/(55-44)+33)
void in2PreLL(char* infix, LinkedList *inExpLL) {
  //Write your code here
  //ListNode *t=NULL,*rv=NULL;
  Stack nu;
  int i=0,j=0,a=0,dec=0;
  char al[SIZE], rev[SIZE];

  nu.head=NULL;
  nu.size=0;

  i=0;
  //////reverse the thing
  while(infix[i]!=NULL){
      i++;
  };


  for(int x=(i-1); x>=0 ; x--){
      //printf("x %d i %d item x %c item i %c \n",x,i,infix[x],infix[i]);

      if(infix[x]>='0' && infix[x]<='9'){
            al[j]=infix[x];
            while( (x-1)>=0 && (infix[x-1]>='0' && infix[x-1] <= '9') ){
                //rv=rv->next;
                //printf("Loop j=%d x-1=%d\n",j,x-1);
                x--;
                j++;
                al[j] =infix[x];
            }
            for(int y=0; y<=j; y++){
                rev[y] = al[j-y];
                //printf("rev %s al %s rev[y] %c    y %d j-y %d j%d \n",rev,al,rev[y],y,(j-y),j);
            }
            insertNode(inExpLL,atoi(rev),OPERAND);
            //printf("     OPRAND %s   %s\n",rev,al);
            memset(al, 0, SIZE);
            memset(rev, 0, SIZE);
            j=0;
      }
      else if(infix[x]!='(' && infix[x]!=')'){
        if(isEmptyStack(nu)!=1){
            //rm=peek(nu);
            a=getprio(peek(nu));
            //printf("rm %c with %d VS item %c with %d \n",peek(nu),a,infix[x],(getprio(infix[x])) );
            if(getprio(infix[x]) >= a){
                push(&nu,infix[x]);
                //printf("     to nu stack %c \n",peek(nu));
            } else if(getprio(infix[x]) < a){
                while(isEmptyStack(nu)==0 && getprio(infix[x]) < getprio(peek(nu)) ){
                    insertNode(inExpLL,peek(nu),OPT);
                    //printf("     OPT %c \n",peek(nu));
                    pop(&nu);
                }
                push(&nu,infix[x]);
                //printf("     to nu stack %c \n",peek(nu));
            }
        }
        else if (isEmptyStack(nu)==1){
            push(&nu,infix[x]);
            //printf("     to nu stack %c \n",peek(nu));
        }
      }
      else if(infix[x]=='('){
            while( peek(nu)!=')' ){
                //if(peek(nu)=='+' || peek(nu)=='-' || peek(nu)=='/' || peek(nu)=='*'){
                insertNode(inExpLL,peek(nu),OPT);
                pop(&nu);
                //}
            }
            pop(&nu);
      }
      else {
        push(&nu,infix[x]);
      }

      //rv=rv->next;
      //x--;
  }

  while(isEmptyStack(nu)==0){
    insertNode(inExpLL,peek(nu),OPT);
    //printf("     OPT %c \n",peek(nu));
    pop(&nu);
  }


}
