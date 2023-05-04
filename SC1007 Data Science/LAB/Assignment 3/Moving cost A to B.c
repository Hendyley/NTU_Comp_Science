#include <stdio.h>
#include <stdlib.h>

typedef struct _btnode{
    int nodeV;
    struct _btnode *left;
    struct _btnode *right;
} BTNode;

typedef struct _queuenode{
    BTNode* node;
    struct _queuenode *next;
}QueueNode;

typedef struct _queue{
   int size;
   QueueNode *head;
   QueueNode *tail;
} Queue;

void printBTNode(BTNode *root, int space,int left);
//Prototypes of Interface functions for Queue structure
void enqueue(Queue *qPtr, BTNode * node);
int dequeue(Queue *qPtr);
BTNode* getFront(Queue q);
int isEmptyQueue(Queue q);

int twoNodesCost(BTNode* node, int nodeV1,int nodeV2);

int main()
{
    BTNode* root = (BTNode*) malloc(sizeof(BTNode));

    Queue q;
    q.head = NULL;
    q.tail = NULL;
    q.size = 0;

    BTNode* node;
    enqueue(&q,root);

    int nodeV;
    char opL, opR;

    while(!isEmptyQueue(q)){
            scanf("%d %c %c",&nodeV,&opL,&opR);
            node = getFront(q);dequeue(&q);
            node->nodeV = nodeV;

            if(opL != '@'){
                node->left = (BTNode*) malloc(sizeof(BTNode));
                enqueue(&q,node->left);
            }
            else node->left =NULL;
            if(opR != '@'){
                node->right = (BTNode*) malloc(sizeof(BTNode));
                enqueue(&q,node->right);
            }
            else
                node->right = NULL;
    }

    int v1,v2;
    scanf("%d %d",&v1,&v2);
    int cost = twoNodesCost(root,v1,v2);

    printBTNode(root,0,0);

    printf("Distance is %d\n",cost);
    return 0;
}

void enqueue(Queue *qPtr, BTNode *node){
    QueueNode *newNode;
    newNode = malloc(sizeof(QueueNode));
    newNode->node = node;
    newNode->next = NULL;

    if(isEmptyQueue(*qPtr))
        qPtr->head=newNode;
    else
        qPtr->tail->next = newNode;

    qPtr->tail = newNode;
    qPtr->size++;
}

int dequeue(Queue *qPtr){
    if(qPtr==NULL || qPtr->head==NULL){ //Queue is empty or NULL pointer
        return 0;
    }
    else{
       QueueNode *temp = qPtr->head;
       qPtr->head = qPtr->head->next;
       if(qPtr->head == NULL) //Queue is emptied
           qPtr->tail = NULL;

       free(temp);
       qPtr->size--;
       return 1;
    }
}

BTNode* getFront(Queue q){
    return q.head->node;
}

int isEmptyQueue(Queue q){
    if(q.size==0) return 1;
    else return 0;
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

          printf("%d\n", root->nodeV);

          space++;
          printBTNode(root->left, space,1);
          printBTNode(root->right, space,0);
      }
}


/*
1 a a
2 a a
3 a @
4 a a
7 @ @
8 a @
5 @ @
6 @ @
9 @ @
5 7
*/

int diff(BTNode *root, int point,int *val) {


    if (root==NULL){
        return 0;
    }
    if (root->nodeV == point) {
        *val += point;
        return 1;
    }

    *val += root->nodeV;

    if (diff(root->left,point,val) || diff(root->right,point,val)  ){
        return 1;
    }

    *val -= root->nodeV;

    return 0;
}

BTNode *findclose(BTNode * root, int a,int b) {

    if (root==NULL){
        return NULL;
    }

    if (root->nodeV == a || root->nodeV == b){
        return root;
    }

    BTNode *L = findclose(root->left,a,b);
    BTNode *R = findclose(root->right,a,b);

    if (L && R){
        return root;
    }

    if(L!=NULL){
        return L;
    }
    else{
        return R;
    }
}

int twoNodesCost(BTNode* node, int nodeV1,int nodeV2)
{
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
//*/

// Write your code here
BTNode *close=NULL, *temp=node;
int val=0;

close = findclose(temp,nodeV1,nodeV2);
diff(close,nodeV1,&val);
diff(close,nodeV2,&val);
val = val - close->nodeV;
return val;


}
