#include <stdio.h>
#include <stdlib.h>

typedef struct _btnode{
	int item;
	struct _btnode *left;
	struct _btnode *right;
} BTNode;

typedef struct _stackNode{
	BTNode *data;
	struct _stackNode *next;
}StackNode;

typedef struct _stack
{
    int size;
	StackNode *top;
}Stack;

//Prototypes of Interface functions for Stack structure
void push(Stack *sPtr, BTNode *data);
int pop(Stack *sPtr);
BTNode* peek(Stack s);
int isEmptyStack(Stack s);


BTNode* insertBTNode(BTNode* cur, int item);
void removeAll(BTNode **node);
void printBTNode(BTNode *root, int space,int left);


void preOrderIterative(BTNode *root);

int main()
{
    BTNode* root=NULL;
    int item;

    printf("Enter a list of numbers for a Binary Tree, terminated by any non-digit character: \n");
    while(scanf("%d",&item))
        //printf("\t\t COME %d\n",item);
        root = insertBTNode(root, item);
    scanf("%*s");

    printBTNode(root,0,0);
    preOrderIterative(root);

    removeAll(&root);

    return 0;
}

void push(Stack *sPtr, BTNode *data){
    StackNode *newNode;
    newNode= (StackNode *)malloc(sizeof(StackNode));
    newNode->data = data;
    newNode->next = sPtr->top;
    sPtr->top = newNode;
    sPtr->size++;
}

int pop(Stack *sPtr){
    if(sPtr==NULL || sPtr->top==NULL){
        return 0;
    }
    else{
       StackNode *temp = sPtr->top;
       sPtr->top = sPtr->top->next;
       free(temp);
       sPtr->size--;
       return 1;
    }
}

BTNode* peek(Stack s){
    return s.top->data;
}

int isEmptyStack(Stack s){
     if(s.size==0) return 1;
     else return 0;
}

BTNode* insertBTNode(BTNode* cur, int item){
    if (cur == NULL){
	BTNode* node = (BTNode*) malloc(sizeof(BTNode));
	node->item = item;
	node->left = node->right = NULL;
	//printf("     (%d)     \n",item);
	return node;
	}

	int x=rand();
	//printf("Rand : %d  %d  Item %d \n",x,x%2,item);
    if (x%2){
        //printf("\t left item %d cur %d\n",item,cur->item);
        cur->left  = insertBTNode (cur->left, item);
    }
    else{
        //printf("\t right item %d cur %d\n",item,cur->item);
        cur->right = insertBTNode (cur->right, item);
    }

    //printf("cur item    %d     \n",cur->item);
    return cur;
}

void removeAll(BTNode **node)
{
	if (*node != NULL)
	{
		removeAll(&((*node)->left));
		removeAll(&((*node)->right));
		free(*node);
		*node = NULL;
	}
}

void printBTNode(BTNode *root,int space,int left){
      if (root != NULL)
      {

          int i;
          for (i = 0; i < space-1; i++)
                 printf("|\t");


          if(i<space){
            if(left==1)
              printf("|---");
            else
              printf("|___");
          }

          printf("%d\n", root->item);

          space++;
          printBTNode(root->left, space,1);
          printBTNode(root->right, space,0);
      }
}

void preOrderIterative(BTNode *root)
{
//Write your code here    20 15 10 18 50 25 80 a    20 15 50 10 18 25 80 a   4 2 5 1 3 6 a    20 15 10 50 18 80 25 a
Stack sp;

sp.top = NULL;
sp.size = 0;

BTNode *temp=root;

push(&sp,temp);

while(isEmptyStack(sp)==0){


    temp = peek(sp);
    pop(&sp);
    printf("%d ",temp->item);

    if(temp->right!=NULL){
        push(&sp,temp->right);
    }
    if(temp->left!=NULL){
        push(&sp,temp->left);
    }



}



}
