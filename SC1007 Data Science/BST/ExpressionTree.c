#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 200 //The number digit limitation

typedef struct _btnode{
	int item;
	struct _btnode *left;
	struct _btnode *right;
} BTNode;   // You should not change the definition of BTNode

typedef struct _node{
     BTNode* item;
     struct _node *next;
}StackNode;

typedef struct _stack{
   int size;
   StackNode *head;
}Stack;

void deleteTree(BTNode **root);

void createExpTree(BTNode** root,char* prefix);
void printTree(BTNode *node);
void printTreePostfix(BTNode *node);
double computeTree(BTNode *node);

void push(Stack *sPtr,  BTNode* item);
int pop(Stack *sPtr);
BTNode *peek(Stack s);
int isEmptyStack(Stack s);

int main()
{
    char prefix[SIZE];
    BTNode* root=NULL;

    //printf("Enter an prefix expression:\n");
    gets(prefix);

    createExpTree(&root, prefix);

    // printf("The Infix Expression:\n");
    printTree(root);
    printf("\n");
    //printf("The Postfix (Reverse Polish) Expression:\n");
    printTreePostfix(root);
    printf("\n");
    //printf("Answer ");
    printf("%.2f\n", computeTree(root));
    deleteTree(&root);
    return 0;
}

void push(Stack *sPtr, BTNode *item){
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

BTNode *peek(Stack s){
    return s.head->item;
}

int isEmptyStack(Stack s){
     if(s.size == 0) return 1;
     else return 0;
}

void deleteTree(BTNode **root){
    BTNode* temp;
    if(*root !=NULL)
    {
        temp = (*root)->right;
        deleteTree(&((*root)->left));
        free(*root);
        *root = NULL;
        deleteTree(&temp);
    }
}

//+ 99 * - 88 77 + / 66 - 55 44 33
void createExpTree(BTNode** root,char* prefix)
{
    *root = malloc(sizeof(BTNode));
    char * opt = "+-*/()";
    char *token = strtok(prefix, " ");
    printf("\t token %s\n",token);
    if(strchr(opt,token[0]) == NULL) {
        printf("n  %s   %c\n",strchr(opt,token[0]),token[0]);
        (*root)->item = atoi(token);
        (*root)->left = NULL;
        (*root)->right = NULL;
    }
    else {
        (*root)->item = token[0];
        createExpTree(&(*root)->left,NULL);
        createExpTree(&(*root)->right, NULL);
    }
}

void printTree(BTNode *node){
    //Write your code here
    if (node == NULL) return;
    if (node->left == NULL && node->right == NULL)
        printf("%d ",node->item);
    else {
        printTree(node->left);
        printf("%c ",node->item);
        printTree(node->right);
    }
}


void printTreePostfix(BTNode *node){
    //Write your code here
    if (node == NULL) return;
    printTreePostfix(node->left);
    printTreePostfix(node->right);
    (node->left == NULL && node->right == NULL ) ? printf("%d ",node->item) : printf("%c ",node->item);

}

double computeTree(BTNode *node){
    //Write your code here
    if (node == NULL) return 0;
    if (node->left == NULL && node->right == NULL)
        return node->item;

    double left = computeTree(node->left);

    double right = computeTree(node->right);

    char opt = node->item;

    switch (opt) {
        case '+':
            return left+right;
        case '-':
            return left-right;
        case '*':
            return left*right;
        case '/':
            return left/right;
        default:
            break;
    }
    return 0;
}

