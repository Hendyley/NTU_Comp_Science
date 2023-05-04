
/*
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

//memory
int *r;

int cr_recursive(int *p, int n)
{
    if(n==0){
        return 0;
    }

    int i;
    int q = -1;
    for(i=1;i<=n;i++){
        int temp = p[i] + cr_recursive(p, n-i);
        if (temp > q)
            q = temp;
    }
    return q;

}

int cr_top_down_dp(int *p, int n)
{
    if(n==0){
        return 0;
    }

    if(r[n]<0){
        return r[n];
    }

    int i;
    int q = -1;
    for(i=1;i<=n;i++){
        int temp = p[i] + cr_top_down_dp(p, n-i);
        if (temp > q)
            q = temp;
    }

    r[n] = q;
    return q;
}

int cr_bottom_up_dp(int *p, int n)
{

    r[0] = 0;
    int i,j;

    for(i=1;i<=n;i++){
        for(j=1;j<=i;i++){
            int temp = p[i] + r[j-i];
            if (temp > r[j])
                r[j] = temp;
        }
    }

    return r[n];

}

void main ()
{
    int n;      //length of  rod
    int function;
    int i;
    int *p;     //price list

    int price_list[10] = {0,1,4,8,9,10,17,17,20,24}; //declare one more value to cover the index 0;

    n = sizeof(price_list) / sizeof(int) - 1;
    p = price_list;


    //allocate the memory array
    r = malloc(sizeof(int) * (n+1));

    printf("Choose a function (1 for recursive, 2 for top-down DP, 3 for bottom-up DP, 0 to stop): \n");
    scanf("%d",&function);
    while (function != 0)
    {
        if (function == 1)
        {
            printf("The maximun value is: %d \n", cr_recursive(p,n));
        }
        else if (function == 2)
        {
            //set all memory elements to -1
            for (i=0; i<=n; i++)
                r[i] = -1;

            printf("The maximun value is: %d \n", cr_top_down_dp(p,n));
        }
        else if (function == 3)
        {
            //set all memory elements to -1
            for (i=0; i<=n; i++)
                r[i] = -1;

            printf("The maximun value is: %d \n", cr_bottom_up_dp(p,n));
        }
        printf("Choose a function (1 for recursive, 2 for top-down DP, 3 for bottom-up DP, 0 to stop): \n");
        scanf("%d",&function);
    }
}


/*
//Q1 n ASSG 7n8 Q1

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int *M;

int fib_recursive(int n)
{
    //write your code here
   if(n==0){
       return 0;
   }
   if(n==1){
       return 1;
   }
   if(n==2){
       return 2;
   }

   return fib_recursive(n-1) + 2*fib_recursive(n-2) - 3*fib_recursive(n-3);

}

int top_down_dp(int n)
{
    //write your code here
    if(n==0){
        M[0] = 0;
        return 0;
    }
    if(n==1){
        M[1] = 1;
        return 1;
    }
    if(n==2){
        M[2] = 2;
        return 2;
    }

    if(M[n-1]==-1){
        M[n-1] = top_down_dp(n-1);
    }
    if(M[n-2]==-1){
        M[n-2] = top_down_dp(n-2);
    }
    if(M[n-3]==-1){
        M[n-3] = top_down_dp(n-3);
    }

    M[n] = M[n-1]+2*M[n-2]-3*M[n-3];
    return M[n];
}

int bottom_up_dp(int n)
{
    //write your code here
    M[0] = 0;
    M[1] = 1;
    M[2] = 2;

    for(int i=3; i<=n; i++){
        M[i] = M[i-1]+2*M[i-2]-3*M[i-3];
    }
    return M[n];

}

void main ()
{
    int n;
    int function;
    int i;
    printf("Enter the value of n:\n");
    scanf("%d",&n);

    //allocate the memory array
    M = malloc(sizeof(int) * (n+1));

    printf("Choose a function (1 for recursive, 2 for top-down DP, 3 for bottom-up DP, 0 to stop): \n");
    scanf("%d",&function);
    while (function != 0)
    {
        if (function == 1)
        {
            clock_t begin = clock();
            printf("The result of Fib(%d) is: %d \n", n, fib_recursive(n));
            clock_t end = clock();
            double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
            printf("Running time is: %f seconds \n", time_spent);
        }
        else if (function == 2)
        {
            //set all memory elements to -1
            for (i=0; i<=n; i++)
                M[i] = -1;

            clock_t begin = clock();
            printf("The result of Fib(%d) is: %d \n", n, top_down_dp(n));
            clock_t end = clock();
            double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
            printf("Running time is: %f seconds \n", time_spent);
        }
        else if (function == 3)
        {
            //set all memory elements to -1
            for (i=0; i<=n; i++)
                M[i] = -1;

            clock_t begin = clock();
            printf("The result of Fib(%d) is: %d \n", n, bottom_up_dp(n));
            clock_t end = clock();
            double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
            printf("Running time is: %f seconds \n", time_spent);
        }
        printf("Choose a function (1 for recursive, 2 for top-down DP, 3 for bottom-up DP, 0 to stop): \n");
        scanf("%d",&function);
    }
}

//LAB 7

/*
//Q3
#include <stdio.h>
#include <stdlib.h>

int nQueensAll(int** board, int N, int col);
int isSafe(int** board,int N, int row, int col);
void printSolution(int** board, int N);
int count = 0;

int main()
{
    int **board;
    int BSize;

    int i,j;

    printf("Enter the size of chessBoard:\n");
    scanf("%d",&BSize);

    board = (int **)malloc(BSize*sizeof(int *));
    for(i=0;i<BSize;i++)
        board[i] = (int *)malloc(BSize*sizeof(int));

    for(i=0;i<BSize;i++)
       for(j=0;j<BSize;j++)
           board[i][j] = 0;

    nQueensAll(board, BSize, 0);
    if (count == 0)
    	printf("Solution does not exist");
    else
    	printf("There are total %d solutions",count);

    return 0;
}


int nQueensAll(int** board, int BSize, int col)
{
     // write your code here
     if(col == BSize){
        count++;
        return 1;
     }

     int found = 0;
     for(int b=0; b<BSize; b++){
        if(isSafe(board,BSize,b,col) == 1){

            board[b][col] = 1;

            found = nQueensAll(board,BSize,col+1) || found;

            board[b][col] = 0;
        }

     }

     return found;


}

//Safe checking
int isSafe(int** board,int BSize, int row, int col)
{
    int i, j;

    // Horicatal check
    for (i = 0; i < col; i++)
        if (board[row][i])
            return 0;

    // Upper left diagonal check
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
        if (board[i][j])
            return 0;

    // Lower left diagonal check
    for (i = row, j = col; j >= 0 && i < BSize; i++, j--)
        if (board[i][j])
            return 0;

    return 1;
}

void printSolution(int** board, int BSize)
{
    int i,j;
    for (i = 0; i < BSize; i++) {
        for (j = 0; j < BSize; j++){
            if(board[i][j]==1)
                printf(" Q ");
            else
                printf(" + ");
        }
        printf("\n");
    }
    printf("\n");
}


/*
//Q3
#include <stdio.h>
#include <stdlib.h>

int nQueens(int** board, int N, int col);
int isSafe(int** board,int N, int row, int col);
void printSolution(int** board, int N);

int main()
{
    int **board;
    int BSize;

    int i,j;

    printf("Enter the size of chessBoard:\n");
    scanf("%d",&BSize);

    board = (int **)malloc(BSize*sizeof(int *));
    for(i=0;i<BSize;i++)
        board[i] = (int *)malloc(BSize*sizeof(int));

    for(i=0;i<BSize;i++)
       for(j=0;j<BSize;j++)
           board[i][j] = 0;

    if (!nQueens(board, BSize, 0))
    	printf("Solution does not exist");

    return 0;
}

int nQueens(int** board, int BSize, int col)
{
     // write your code here
     if(col == BSize){
        printSolution(board, BSize);
        return 1;
     }

     for(int b=0; b<BSize ; b++){
        if(isSafe(board,BSize,b,col)==1 ){
            board[b][col] = 1;

            if(nQueens(board,BSize,col+1)==1 ){
                return 1;
            }

            board[b][col] = 0;
        }
     }

}

//Safe checking
int isSafe(int** board,int BSize, int row, int col)
{
    int i, j;

    // Horicatal check
    for (i = 0; i < col; i++)
        if (board[row][i])
            return 0;

    // Upper left diagonal check
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
        if (board[i][j])
            return 0;

    // Lower left diagonal check
    for (i = row, j = col; j >= 0 && i < BSize; i++, j--)
        if (board[i][j])
            return 0;

    return 1;
}

void printSolution(int** board, int BSize)
{
    int i,j;
    for (i = 0; i < BSize; i++) {
        for (j = 0; j < BSize; j++){
            if(board[i][j]==1)
                printf(" Q ");
            else
                printf(" + ");
        }
        printf("\n");
    }
    printf("\n");
}

/*
//Q1
#include <stdint.h>
#include <stdio.h>

// Number of vertices in the graph
#define V 4

int graphColoring(int graph[V][V], int , int color[], int);
int isSafe(int, int graph[V][V], int color[], int);
void printSolution(int color[]);


int main()
{

    int graph[V][V] = {
        { 0, 1, 1, 1 },
        { 1, 0, 1, 0 },
        { 1, 1, 0, 1 },
        { 1, 0, 1, 0 },
    };
    int m = 3; // Number of colors

    // Initialize all color values as 0.
    int color[V];
    for (int i = 0; i < V; i++)
        color[i] = 0;

    if (!graphColoring(graph, m, color, 0))
        printf("Solution does not exist");
    else
        printSolution(color);
    return 0;
}


void printSolution(int color[]);


int isSafe(int v, int graph[V][V], int color[], int c)
{
    for (int i = 0; i < V; i++)
        if (graph[v][i] && c == color[i])
            return 0;
    return 1;
}

// A backtracking function
//to solve m coloring problem
int graphColoring(int graph[V][V], int m, int color[], int v)
{
    //write your code here
    if(v == V){
        return 1;
    }

     // Consider this vertex v and
     //  try different colors
    for(int c=1; c<=m; c++){

         // Check if assignment of color
         //  c to v is fine
        if( isSafe(v,graph,color,c) == 1 ){
           color[v] = c;
            // recur to assign colors to
            //   rest of the vertices
           if( graphColoring(graph,m,color,v+1) == 1 ){
                return 1;
           }

           // If assigning color c doesn't
           //    lead to a solution then remove it
            color[v] = 0;
        }

    }
    return 0;
}


// A utility function to print solution
void printSolution(int color[])
{
    printf(
           "Solution Exists:"
           " Following are the assigned colors \n");
    for (int i = 0; i < V; i++)
        printf(" %d ", color[i]);
    printf("\n");
}


/*
//Q3
#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;

typedef struct _graph{
    int V;
    int E;
    int *visited;
    int **matrix;
}Graph;

////GRAPH////////////////////////////////////////////
void printGraphMatrix(Graph );
////////////////////////////////////////////

void DFS_R(Graph , int );

int main()
{
    Graph g;
    int i,j;

    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.matrix = (int **)malloc(g.V*sizeof(int *));
    for(i=0;i<g.V;i++)
        g.matrix[i] = (int *)malloc(g.V*sizeof(int));

    for(i=0;i<g.V;i++)
       for(j=0;j<g.V;j++)
           g.matrix[i][j] = 0;

    g.visited = (int *) malloc(sizeof(int)*g.V);
    for(i=0;i<g.V;i++) g.visited[i] = 0;

    int V1, V2;
    printf("Enter two vertices which are adjacent to each other:\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            g.matrix[V1-1][V2-1] = 1;
            g.matrix[V2-1][V1-1] = 1;
            g.E++;
        }
        else
            break;
        printf("Enter two vertices which are adjacent to each other:\n");
    }
    scanf("%*c");
    printf("Enter a start vertex for DFS:\n");
    scanf("%d", &i);
    printGraphMatrix(g);
    DFS_R(g,i);

    return 0;
}

void DFS_R(Graph g, int v)
{
    // Write your code here
    int i,j,c;

    printf("%d ",v);
    g.visited[v-1]=1;

    for(i=0;i<g.V;i++){
        if(g.matrix[v-1][i] == 1 && g.visited[i] == 0){
            g.visited[i]=1;
            DFS_R(g,i+1);
        }
    }

}

void printGraphMatrix(Graph g)
{
    int i,j;

    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            printf("%d\t",g.matrix[i][j]);
        printf("\n");
    }

}
/*
7
1 2
1 3
2 3
2 4
3 5
3 6
6 7
a
1
*/

/*
//Q2
#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;
typedef ListNode StackNode;

typedef struct _graph{
    int V;
    int E;
    int *visited; //store the visited vertices
    int **matrix;
}Graph;

typedef struct _linkedlist
{
	int size;
	ListNode *head;
} Stack;

// You should not change the prototypes of these functions
//////STACK///////////////////////////////////////////
void push(Stack *sPtr, int vertex);
int pop(Stack *sPtr);
int peek(Stack s);
int isEmptyStack(Stack s);
void removeAllItemsFromStack(Stack *sPtr);
////GRAPH////////////////////////////////////////////
void printGraphMatrix(Graph );
////////////////////////////////////////////

void DFS_I (Graph , int );

int main()
{
    Graph g;
    int i,j;


    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.matrix = (int **)malloc(g.V*sizeof(int *));
    for(i=0;i<g.V;i++)
        g.matrix[i] = (int *)malloc(g.V*sizeof(int));

    for(i=0;i<g.V;i++)
       for(j=0;j<g.V;j++)
           g.matrix[i][j] = 0;

	g.visited = (int *) malloc(sizeof(int)*g.V);
    for(i=0;i<g.V;i++) g.visited[i] = 0;


    int V1, V2;
    printf("Enter two vertices which are adjacent to each other:\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            g.matrix[V1-1][V2-1] = 1;
            g.matrix[V2-1][V1-1] = 1;
            g.E++;
        }
        else
            break;
        printf("Enter two vertices which are adjacent to each other:\n");
    }
    scanf("%*c");
    printf("Enter a start vertex for DFS:\n");
    scanf("%d", &i);

    printGraphMatrix(g);
    DFS_I(g,i);

    return 0;
}

void DFS_I (Graph g, int v)
{
    // Write your code here
    int i,j,k,c=0;
    Stack s;
    s.size=0;

    printf("%d ",v);
    push(&s,v);
    g.visited[v-1]=1;

    while(isEmptyStack(s)==0){
        k = peek(s);
        c = 0;
        for(j=0;j<g.V;j++){
            if( g.matrix[k-1][j] ==1 && g.visited[j] ==0){
                push(&s,j+1);
                printf("%d ",j+1);
                g.visited[j]=1;
                c=1;
                break;
            }

        }

        if(c==0){
            pop(&s);
        }



    }

}

void printGraphMatrix(Graph g)
{
    int i,j;

    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            printf("%d\t",g.matrix[i][j]);
        printf("\n");
    }

}

void push(Stack *sPtr, int vertex)
{
	StackNode *newNode;
    newNode= malloc(sizeof(StackNode));
    newNode->vertex = vertex;
    newNode->next = sPtr->head;
    sPtr->head = newNode;
    sPtr->size++;
}

int pop(Stack *sPtr)
{
    if(sPtr==NULL || sPtr->head==NULL){
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

int isEmptyStack(Stack s)
{
     if(s.size==0) return 1;
     else return 0;
}

int peek(Stack s){
    return s.head->vertex;
}

void removeAllItemsFromStack(Stack *sPtr)
{
	while(pop(sPtr));
}
/*
7
1 2
1 3
2 3
2 4
3 5
3 6
6 7
a
1
*/


/*
//Q1
#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;

typedef struct _graph{
    int V;
    int E;
    int *visited;
    int **matrix;
}Graph;

typedef ListNode QueueNode;

typedef struct _queue{
   int size;
   QueueNode *head;
   QueueNode *tail;
} Queue;

void BFS (Graph G, int v);

void printGraphMatrix(Graph );

// You should not change the prototypes of these functions
void enqueue(Queue *qPtr, int item);
int dequeue(Queue *qPtr);
int getFront(Queue q);
int isEmptyQueue(Queue q);
void removeAllItemsFromQueue(Queue *qPtr);

int main()
{
    Graph g;
    int i,j;

    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.matrix = (int **)malloc(g.V*sizeof(int *));
    for(i=0;i<g.V;i++)
        g.matrix[i] = (int *)malloc(g.V*sizeof(int));

    for(i=0;i<g.V;i++)
       for(j=0;j<g.V;j++)
           g.matrix[i][j] = 0;

    g.visited = (int *) malloc(sizeof(int)*g.V);
    for(i=0;i<g.V;i++) g.visited[i] = 0;

    int V1, V2;
    printf("Enter two vertices which are adjacent to each other:\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            g.matrix[V1-1][V2-1] = 1;
            g.matrix[V2-1][V1-1] = 1;
            g.E++;
        }
        else
            break;
        printf("Enter two vertices which are adjacent to each other:\n");
    }
    scanf("%*c");
    printf("Enter a start vertex for BFS:\n");
    scanf("%d", &i);
    printGraphMatrix(g);
    BFS(g,i);

    return 0;
}

void BFS(Graph g, int v){
    // Write your code here
    int i,j,k;
    Queue q;
    q.size = 0;

    enqueue(&q,v);
    g.visited[v-1]=1;
    printf("%d ",v);

    while(isEmptyQueue(q)==0){
        k=dequeue(&q);
        for(j=0;j<g.V;j++){
            if(g.matrix[k-1][j]==1 && g.visited[j]!=1){
                enqueue(&q,j+1);
                g.visited[j]=1;
                printf("%d ",j+1);
            }
        }
    }

}

void printGraphMatrix(Graph g)
{
    int i,j;

    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            printf("%d\t",g.matrix[i][j]);
        printf("\n");
    }

}


void enqueue(Queue *qPtr, int item) {
    QueueNode *newNode;
    newNode = malloc(sizeof(QueueNode));
    if(newNode==NULL) exit(0);

    newNode->vertex = item;
    newNode->next = NULL;

    if(isEmptyQueue(*qPtr))
        qPtr->head=newNode;
    else
        qPtr->tail->next = newNode;

    qPtr->tail = newNode;
    qPtr->size++;
}

int dequeue(Queue *qPtr) {
    if(qPtr==NULL || qPtr->head==NULL){ //Queue is empty or NULL pointer
        return 0;
    }
    else{
       int headValue = qPtr->head->vertex;
       QueueNode *temp = qPtr->head;
       qPtr->head = qPtr->head->next;
       if(qPtr->head == NULL) //Queue is emptied
           qPtr->tail = NULL;

       free(temp);
       qPtr->size--;
       return headValue;
    }
}

int getFront(Queue q){
    return q.head->vertex;
}

int isEmptyQueue(Queue q) {
    if(q.size==0) return 1;
    else return 0;
}

void removeAllItemsFromQueue(Queue *qPtr)
{
	while(dequeue(qPtr));
}
/*
7
1 2
1 3
2 3
2 4
3 5
3 6
6 7
a
1
*/


// LAB 5

/*
#include <stdio.h>
#include <stdlib.h>

enum GraphType {ADJ_MATRIX, ADJ_LIST}; // Types of Graph Representation

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;

union GraphForm{
    int **matrix;
    ListNode **list;
};

typedef struct _graph{
    int V;
    int E;
    enum GraphType type;
    union GraphForm adj;
}Graph;

void printGraphMatrix(Graph );
void adjM2adjL(Graph *);
void printGraphList(Graph );
void calDegreeV(Graph,int *);
void printDegreeV(int *,int );

int main()
{
    Graph g;
    int i,j;

    int* degreeV;

    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.adj.matrix = (int **)malloc(g.V*sizeof(int *));
    for(i=0;i<g.V;i++)
        g.adj.matrix[i] = (int *)malloc(g.V*sizeof(int));

    for(i=0;i<g.V;i++)
        for(j=0;j<g.V;j++)
            g.adj.matrix[i][j] = 0;
    g.type = ADJ_MATRIX;

    degreeV = (int *) malloc(g.V*sizeof(int));
    for(i=0;i<g.V;i++)
        degreeV[i]=0;

    int V1, V2;
    printf("Enter two vertices which are adjacent to each other: (enter a to stop)\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            g.adj.matrix[V1-1][V2-1] = 1;
            g.adj.matrix[V2-1][V1-1] = 1;
            g.E++;
        }
        else
            break;
        printf("Enter two vertices which are adjacent to each other: (enter a to stop)\n");
    }


    printGraphMatrix(g);

    adjM2adjL(&g);

    printGraphList(g);

    calDegreeV(g,degreeV);

    printDegreeV(degreeV,g.V);

    return 0;
}

void printGraphMatrix(Graph g)
{
    int i,j;
    printf("Print graph matrix \n");
    if(g.type == ADJ_LIST) {printf("Error"); return;}

    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            //printf("i%d j%d : %d %d\n",i,j,g.adj.matrix[i][j],g.V);
            printf("%d\t",g.adj.matrix[i][j]);
        printf("\n");
    }
    printf("\n");

}

void adjM2adjL(Graph *g)
{
	//g->type = ADJ_LIST; //change representation form
	int x,i,j,num;
	ListNode **list;
    ListNode *temp;

    if(g->type == ADJ_LIST) {printf("Error"); return;}
    if(g->V<=0){printf("Empty graph!"); return;}

    list = (ListNode **) malloc(g->V*sizeof(ListNode *));
    for(i=0;i<g->V;i++)
        list[i] = NULL;

	for(i=0;i<g->V;i++){
    //num = 1;
    //temp=g->adj.list[i];
        for(j=0;j<g->V;j++){
            //printf("i%d j%d : %d %d\n",i,j,g->adj.matrix[i][j],g->V);
            if(g->adj.matrix[i][j] == 1){
                if(list[i]==NULL){
                    list[i] = (ListNode *)malloc(sizeof(ListNode));
                    list[i]->vertex = j+1;
                    //list[i]->next = NULL;
                    temp = list[i];
                }
                else{
                   temp->next=(ListNode*) malloc(sizeof(ListNode));
                   temp = temp->next;
                   temp->vertex = (j+1);
                }
            }
        //num++;
        }
    temp->next = NULL;

    }

    g->type = ADJ_LIST; //change representation form

    //free adjMatrix

    for(i=0;i<g->V;i++)
        free(g->adj.matrix[i]);
    free(g->adj.matrix);

    g->adj.list = list;



}


void printGraphList(Graph g){
    int i;
    ListNode* temp;
    printf("Print graph list \n");
    if(g.type == ADJ_MATRIX) {printf("Error"); return;}

    for(i=0;i<g.V;i++)
    {
        printf("%d:\t",i+1);
        temp = g.adj.list[i];
        while(temp!=NULL){
            printf("%d -> ",temp->vertex);
            temp = temp->next;
        }
        printf("\n");
    }
    printf("\n");
}

void calDegreeV(Graph g, int *degreeV)
{
    // Question 3
    // Write your code here
    int i,j,c;
    ListNode* temp = NULL;

    if(g.type == ADJ_MATRIX) {printf("Error"); return;}

    for(i=0;i<g.V;i++){
        temp = g.adj.list[i];
        c=0;
        while(temp!=NULL){
            c++;
            temp=temp->next;
        }
        degreeV[i] = c;
    }

}

void printDegreeV(int *degreeV,int V)
{
    int i;
    for(i=0;i<V;i++)
        printf("%d: %d degree\n",i+1,degreeV[i]);
}

/*
7
1 2
1 3
2 3
2 4
4 1
4 3
4 6
5 3
5 7
6 1
6 3
7 4
7 5
a
*/

/*
//Q1
#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode{
    int key;
    struct _listnode *next;
} ListNode;

typedef struct _linkedlist{
   int size;
   ListNode *head;
} HashTableNode;

typedef struct _hashTable{
   int hSize; //size of hash table
   int nSize; //number of inserted keys
   HashTableNode *Table;
} HashTable;

int Hash(int,int);

ListNode* HashSearch(HashTable, int);
int HashInsert(HashTable *, int);

//In Practice, we will not do it
void HashPrint(HashTable);

int main()
{
    int opt;
    int size;

    int i;
    int key;

    //Create a HashTable
    HashTable Q3Hash;
    Q3Hash.hSize = 0;
    Q3Hash.nSize = 0;
    Q3Hash.Table = NULL;

    printf("============= Hash Table ============\n");
    printf("|1. Create a hash table             |\n");
    printf("|2. Insert a key to the hash table  |\n");
    printf("|3. Search a key in the hash table  |\n");
    printf("|4. Print the hash table            |\n");
    printf("|5. Quit                            |\n");
    printf("=====================================\n");

    printf("Enter selection: ");
    scanf("%d",&opt);
    while(opt>=1 && opt <=4){
        switch(opt){
        case 1:
            printf("Enter the size of hash table:\n");
            scanf("%d",&Q3Hash.hSize);

            Q3Hash.nSize = 0;

            Q3Hash.Table = (HashTableNode *) malloc(sizeof(HashTableNode)*(Q3Hash.hSize));

            for(i=0;i<Q3Hash.hSize;i++){
               Q3Hash.Table[i].head = NULL;
               Q3Hash.Table[i].size = 0;
            }
            printf("HashTable is created.\n");
            break;
        case 2: //Insertion
            printf("Enter a key to be inserted:\n");
            scanf("%d",&key);

            if(HashInsert(&Q3Hash,key))
                printf("%d is inserted.\n",key);
            else
                printf("%d is a duplicate. No key is inserted.\n",key);
            break;
        case 3: //Search
            printf("Enter a key for searching in the HashTable:\n");
            scanf("%d",&key);
            ListNode* node = HashSearch(Q3Hash, key);

            if(node!=NULL)
                printf("%d is found.\n",key);
            else
                printf("%d is not found.\n",key);
            break;
        case 4:
            HashPrint(Q3Hash);
            break;
        }

    printf("Enter selection: ");
    scanf("%d",&opt);
    }

    for(i=0;i<Q3Hash.hSize;i++)
    {
        while(Q3Hash.Table[i].head)
        {
            ListNode *temp;
            temp = Q3Hash.Table[i].head;
            Q3Hash.Table[i].head = Q3Hash.Table[i].head->next;
            free(temp);
        }
    }
    free(Q3Hash.Table);

    return 0;

}

int Hash(int key,int hSize)
{
    return key%hSize;
}

ListNode* HashSearch(HashTable Q3Hash, int key)
{
    //write your code here
    int i,j,k;
    ListNode *temp;
    k=Hash(key,Q3Hash.hSize);

    temp=Q3Hash.Table[k].head;

    while(temp!=NULL){
        if(key == temp->key){
            return temp;
        }
        temp = temp->next;
    }


}

int HashInsert(HashTable* Q3HashPtr, int key)
{
    //write your code here
    int i,j,k;
    ListNode *temp;

    temp = (ListNode*) malloc(sizeof(ListNode));
    temp->key = key;

    k=Hash(key,Q3HashPtr->hSize);

    temp->next = Q3HashPtr->Table[k].head;
    Q3HashPtr->Table[k].head = temp;




}

void HashPrint(HashTable Q3Hash)
{
    int i,j,k;
    ListNode *temp;

    for(i=0;i<Q3Hash.hSize;i++){
        temp = Q3Hash.Table[i].head;
        while(temp!=NULL){
            printf("%d -> ",temp->key);
            temp=temp->next;
        }
        printf("\n");

    }
}
/*
1
3
2
1
2
2
2
3
2
4
2
5
2
6
2
7
2
8
2
9
3
9
3
10
4
5
*/




//ASSIGNMENT 6

/*
#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;

typedef struct _graph{
    int V;
    int E;
    int *visited;
    int **matrix;
}Graph;

typedef ListNode QueueNode;

typedef struct _queue{
   int size;
   QueueNode *head;
   QueueNode *tail;
} Queue;

int SD (Graph G, int v, int w);

void printGraphMatrix(Graph );

// You should not change the prototypes of these functions
void enqueue(Queue *qPtr, int item);
int dequeue(Queue *qPtr);
int getFront(Queue q);
int isEmptyQueue(Queue q);
void removeAllItemsFromQueue(Queue *qPtr);

int main()
{
    Graph g;
    int i,j;

    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.matrix = (int **)malloc(g.V*sizeof(int *));
    for(i=0;i<g.V;i++)
        g.matrix[i] = (int *)malloc(g.V*sizeof(int));

    for(i=0;i<g.V;i++)
       for(j=0;j<g.V;j++)
           g.matrix[i][j] = 0;

    g.visited = (int *) malloc(sizeof(int)*g.V);
    for(i=0;i<g.V;i++) g.visited[i] = 0;

    int V1, V2;
    printf("Enter two vertices which are adjacent to each other:\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            g.matrix[V1-1][V2-1] = 1;
            g.matrix[V2-1][V1-1] = 1;
            g.E++;
        }
        else
            break;
        printf("Enter two vertices which are adjacent to each other: (press a to stop)\n");
    }
    scanf("%*c");
    // printGraphMatrix(g);

    printf("Enter two vertices for finding their shortest distance: (press a to stop)\n");
    scanf("%d %d", &i, &j);

    int d = SD(g,i,j);
    if(d==-1)
        printf("%d and %d are unconnected.\n",i,j);
    else
        printf("The shortest distance is %d\n",d);
    return 0;
}

int SD(Graph g, int v, int w){

    // Write your code here
    int i,j,k,c;
    Queue q;
    q.size = 0;
    q.head = NULL;
    int d[100];

    if(v==w){
        return 0;
    }

    for(k=0;k<g.V;k++){
        d[k] = 0;
    }
    k=0;
    enqueue(&q,v);
    g.visited[v-1] = 1;

    //printf("FFF");
    while(isEmptyQueue(q)==0){

        k = dequeue(&q);

        for(i=0;i<g.V;i++){
            if(g.matrix[k-1][i]==1 && g.visited[i]==0){
               enqueue(&q, i+1 );
               g.visited[i] = 1;
               d[i] = d[k-1] + 1;
               if(i == (w-1) ){
                    break;
               }
            }
        }

    }
    //printf("GGG");

    if(d[w-1] != 0){
        return d[w-1];
    }

    return -1;

}

void printGraphMatrix(Graph g)
{
    int i,j;

    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            printf("%d\t",g.matrix[i][j]);
        printf("\n");
    }

}


void enqueue(Queue *qPtr, int item) {
    QueueNode *newNode;
    newNode = malloc(sizeof(QueueNode));
    if(newNode==NULL) exit(0);

    newNode->vertex = item;
    newNode->next = NULL;

    if(isEmptyQueue(*qPtr))
        qPtr->head=newNode;
    else
        qPtr->tail->next = newNode;

    qPtr->tail = newNode;
    qPtr->size++;
}

int dequeue(Queue *qPtr) {
    if(qPtr==NULL || qPtr->head==NULL){ //Queue is empty or NULL pointer
        return 0;
    }
    else{
       int headValue = qPtr->head->vertex;
       QueueNode *temp = qPtr->head;
       qPtr->head = qPtr->head->next;
       if(qPtr->head == NULL) //Queue is emptied
           qPtr->tail = NULL;

       free(temp);
       qPtr->size--;
       return headValue;
    }
}

int getFront(Queue q){
    return q.head->vertex;
}

int isEmptyQueue(Queue q) {
    if(q.size==0) return 1;
    else return 0;
}

void removeAllItemsFromQueue(Queue *qPtr)
{
	while(dequeue(qPtr));
}

/*
5
1 2
1 4
2 3
3 4
4 5
a
1 5
*/
/*
5
1 2
2 3
3 4
a
1 4
*/


/*
#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;
typedef ListNode StackNode;

typedef struct _graph{
    int V;
    int E;
    int *visited; //store the visited vertices
    int **matrix;
}Graph;

typedef struct _linkedlist
{
	int size;
	ListNode *head;
} Stack;

// You should not change the prototypes of these functions
//////STACK///////////////////////////////////////////
void push(Stack *sPtr, int vertex);
int pop(Stack *sPtr);
int peek(Stack s);
int isEmptyStack(Stack s);
void removeAllItemsFromStack(Stack *sPtr);
////GRAPH////////////////////////////////////////////
void printGraphMatrix(Graph );
////////////////////////////////////////////

int Connected (Graph);

int main()
{
    Graph g;
    int i,j;


    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.matrix = (int **)malloc(g.V*sizeof(int *));
    for(i=0;i<g.V;i++)
        g.matrix[i] = (int *)malloc(g.V*sizeof(int));

    for(i=0;i<g.V;i++)
       for(j=0;j<g.V;j++)
           g.matrix[i][j] = 0;

	g.visited = (int *) malloc(sizeof(int)*g.V);
    for(i=0;i<g.V;i++) g.visited[i] = 0;


    int V1, V2;
    printf("Enter a directed edge: (press a to stop)\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            g.matrix[V1-1][V2-1] = 1;
            g.E++;
        }
        else
            break;
        printf("Enter a directed edge: (press a to stop)\n");
    }
    scanf("%*c");


    int res = Connected(g);
    if(res == 1)
        printf("The graph is strongly connected.\n");
    else
        printf("The graph is not strongly connected.\n");

    return 0;
}



int Connected (Graph g)
{
    int i,j,k,c;
    Graph gt=g;
    Stack s;

    for(i=0;i<g.V;i++){
        gt=g;
        removeAllItemsFromStack(&s);
        s.size = 0;
        push(&s,i+1);
        gt.visited[i]=1;

        while(isEmptyStack(s)==0){
            k=peek(s);
            c=0;

            for(j=0;j<g.V;j++){
                if(gt.matrix[k-1][j]==1 && gt.visited[j]==0){
                    push(&s,j+1);
                    gt.visited[j] = 1;
                    c=1;
                    break;
                }
            }

            if(c==0){
                pop(&s);
            }

        }

        for(int x=0;x<g.V;x++){
            if(gt.visited[x]==0){
                return 0;
            }
        }

    }

    return 1;


}


void printGraphMatrix(Graph g)
{
    int i,j;

    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            printf("%d\t",g.matrix[i][j]);
        printf("\n");
    }

}

void push(Stack *sPtr, int vertex)
{
	StackNode *newNode;
    newNode= malloc(sizeof(StackNode));
    newNode->vertex = vertex;
    newNode->next = sPtr->head;
    sPtr->head = newNode;
    sPtr->size++;
}

int pop(Stack *sPtr)
{
    if(sPtr==NULL || sPtr->head==NULL){
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

int isEmptyStack(Stack s)
{
     if(s.size==0) return 1;
     else return 0;
}

int peek(Stack s){
    return s.head->vertex;
}

void removeAllItemsFromStack(Stack *sPtr)
{
	while(pop(sPtr));
}
/*
5
1 2
1 4
2 3
3 4
4 1
4 5
a
*/



//ASS5

/*
#include <stdio.h>
#include <stdlib.h>

enum GraphType {ADJ_MATRIX, ADJ_LIST}; // Types of Graph Representation

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;

struct GraphForm{
    int **matrix;
    ListNode **list;
};

typedef struct _graph{
    int V;
    int E;
    enum GraphType type;
    struct GraphForm adj;
}Graph;

void printGraphMatrix(Graph );
void adjL2adjM(Graph *);
void printGraphList(Graph );
void calDegreeV(Graph,int *);
void printDegreeV(int *,int );

int main()
{
    Graph g;
    int i,j;

    int* degreeV;
    ListNode *temp;

    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.adj.list = (ListNode **) malloc(g.V*sizeof(ListNode *));

    g.type = ADJ_LIST;

    degreeV = (int *) malloc(g.V*sizeof(int));
    for(i=0;i<g.V;i++)
        degreeV[i]=0;

    int V1, V2;
    printf("Enter two vertices which are adjacent to each other (enter a to stop):\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            i = V1-1;
            j = V2-1;
            if(g.adj.list[i]==NULL){
                    g.adj.list[i] = (ListNode *)malloc(sizeof(ListNode));
                    g.adj.list[i]->vertex = j+1;
                    g.adj.list[i]->next = NULL;
			}
			else{
				temp = (ListNode *)malloc(sizeof(ListNode));
				temp->vertex = j+1;
				temp->next =g.adj.list[i];
				g.adj.list[i] = temp;
			}
			if(g.adj.list[j]==NULL){
                    g.adj.list[j] = (ListNode *)malloc(sizeof(ListNode));
                    g.adj.list[j]->vertex = i+1;
                    g.adj.list[j]->next = NULL;
			}
			else{
				temp = (ListNode *)malloc(sizeof(ListNode));
				temp->vertex = i+1;
				temp->next =g.adj.list[j];
				g.adj.list[j] = temp;
			}
            g.E++;
        }
        else
            break;
        printf("Enter two vertices which are adjacent to each other: (enter a to stop)\n");
    }

    //printGraphList(g);

    adjL2adjM(&g);

    printGraphMatrix(g);

    return 0;
}

void printGraphMatrix(Graph g)
{
    int i,j;
    if(g.type == ADJ_LIST) {printf("Error"); return;}
    printf("Adjacency matrix:\n");
    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            printf("%d\t",g.adj.matrix[i][j]);
        printf("\n");
    }

}

void adjL2adjM(Graph *g){

    // add your code here
    int i,j,c,k;
    ListNode *temp = NULL;
    int **matrix;

    if(g->type == ADJ_MATRIX) {printf("Error"); return;}
    if(g->V<=0){printf("Empty graph!"); return;}

    //printf("\n AAA %d\n",g->E);


    g->adj.matrix = (int**) malloc(g->V*sizeof(int*));
    for(i=0;i<g->V;i++){
        g->adj.matrix[i] = (int*) malloc(g->V*sizeof(int));
    }

    //printf("\n BBB \n");

    for(i=0;i<g->V;i++){
        for(j=0;j<g->V;j++){
            g->adj.matrix[i][j]= 0;
        }
    }

    printf("\n CCC \n");

    g->type = ADJ_MATRIX;

    for(i=0;i<g->V;i++){
        temp = g->adj.list[i];
        while(temp != NULL && temp->vertex <= (g->V)+1){
            if(temp==NULL){
                break;
            }
            printf("[%d] %d \n",i,temp->vertex);
            g->adj.matrix[i][(temp->vertex)-1] = 1;
            temp = temp->next;
        }
    }
    printf("\n GGG \n");


}


void printGraphList(Graph g){
    int i;
    ListNode* temp;

    if(g.type == ADJ_MATRIX) {printf("Error"); return;}
    printf("Adjacency list:\n");
    for(i=0;i<g.V;i++)
    {
        printf("%d:\t",i+1);
        temp = g.adj.list[i];
        while(temp!=NULL){
            printf("%d -> ",temp->vertex);
            temp = temp->next;
        }
        printf("\n");
    }
}
/*
7
1 2
1 3
2 3
2 4
4 1
4 3
4 6
5 3
5 7
6 1
6 3
7 4
7 5
a
*/


/*
#include <stdio.h>
#include <stdlib.h>

enum GraphType {ADJ_MATRIX, ADJ_LIST}; // Types of Graph Representation

typedef struct _listnode
{
    int vertex;
	struct _listnode *next;
} ListNode;

struct GraphForm{
    int **matrix;
    ListNode **list;
};

typedef struct _graph{
    int V;
    int E;
    enum GraphType type;
    struct GraphForm adj;
}Graph;

void printGraphMatrix(Graph );
void calDegreeV(Graph,int *);
void printDegreeV(int *,int );

int main()
{
    Graph g;
    int i,j;

    int* degreeV;

    printf("Enter the number of vertices:\n");
    scanf("%d",&g.V);

    g.E = 0;
    g.adj.matrix = (int **)malloc(g.V*sizeof(int *));
    for(i=0;i<g.V;i++)
        g.adj.matrix[i] = (int *)malloc(g.V*sizeof(int));

    for(i=0;i<g.V;i++)
        for(j=0;j<g.V;j++)
            g.adj.matrix[i][j] = 0;
    g.type = ADJ_MATRIX;

    degreeV = (int *) malloc(g.V*sizeof(int));
    for(i=0;i<g.V;i++)
        degreeV[i]=0;

    int V1, V2;
    printf("Enter two vertices which are adjacent to each other: (enter a to stop)\n");
    while(scanf("%d %d",&V1,&V2)==2)
    {
        if(V1>0 && V1<=g.V && V2>0 && V2<=g.V)
        {
            g.adj.matrix[V1-1][V2-1] = 1;
            g.adj.matrix[V2-1][V1-1] = 1;
            g.E++;
        }
        else
            break;
        printf("Enter two vertices which are adjacent to each other: (enter a to stop)\n");
    }


    printGraphMatrix(g);

    calDegreeV(g,degreeV);

    printDegreeV(degreeV,g.V);

    return 0;
}

void printGraphMatrix(Graph g)
{
    int i,j;
    printf("Print graph matrix \n");
    if(g.type == ADJ_LIST) {printf("Error"); return;}

    for(i=0;i<g.V;i++){
        for(j=0;j<g.V;j++)
            printf("%d\t",g.adj.matrix[i][j]);
        printf("\n");
    }
    printf("\n");

}

void calDegreeV(Graph g, int *degreeV)
{
    // Write your code here
    int i,j,c=0;
    if(g.type == ADJ_LIST) {printf("Error"); return;}

    for(i=0;i<g.V;i++){
    c=0;
        for(j=0;j<g.V;j++){
            if(g.adj.matrix[i][j] == 1){
                c++;
            }
        }
    degreeV[i] = c;
    }

}

void printDegreeV(int *degreeV,int V)
{
    int i;
    printf("Print vertex's degree \n");
    for(i=0;i<V;i++)
        printf("%d: %d degree\n",i+1,degreeV[i]);
}
/*
7
1 2
1 3
2 3
2 4
4 1
4 3
4 6
5 3
5 7
6 1
6 3
7 4
7 5
a
*/


/*
#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode{
    int key;
    struct _listnode *next;
} ListNode;

typedef struct _linkedlist{
   int size;
   ListNode *head;
} HashTableNode;

typedef struct _hashTable{
   int hSize; //size of hash table
   int nSize; //number of inserted keys
   HashTableNode *Table;
} HashTable;

int Hash(int,int);

ListNode* HashSearch(HashTable, int);
int HashInsert(HashTable *, int);
int HashDelete(HashTable*, int);

//In Practice, we will not do it
void HashPrint(HashTable);

int main()
{
    int opt;
    int size;

    int i;
    int key;

    //Create a HashTable
    HashTable Q3Hash;
    Q3Hash.hSize = 0;
    Q3Hash.nSize = 0;
    Q3Hash.Table = NULL;

    printf("============= Hash Table ============\n");
    printf("|1. Create a hash table             |\n");
    printf("|2. Insert a key to the hash table  |\n");
    printf("|3. Search a key in the hash table  |\n");
    printf("|4. Delete a key in the hash table  |\n");
    printf("|5. Print the hash table            |\n");
    printf("|6. Quit                            |\n");
    printf("=====================================\n");

    printf("Enter selection: ");
    scanf("%d",&opt);
    while(opt>=1 && opt <=5){
        switch(opt){
        case 1:
            printf("Enter the size of hash table:\n");
            scanf("%d",&Q3Hash.hSize);

            Q3Hash.nSize = 0;

            Q3Hash.Table = (HashTableNode *) malloc(sizeof(HashTableNode)*(Q3Hash.hSize));

            for(i=0;i<Q3Hash.hSize;i++){
               Q3Hash.Table[i].head = NULL;
               Q3Hash.Table[i].size = 0;
            }
            printf("HashTable is created.\n");
            break;
        case 2: //Insertion
            printf("Enter a key to be inserted:\n");
            scanf("%d",&key);

            if(HashInsert(&Q3Hash,key))
                printf("%d is inserted.\n",key);
            else
                printf("%d is a duplicate. No key is inserted.\n",key);
            break;
        case 3: //Search
            printf("Enter a key for searching in the HashTable:\n");
            scanf("%d",&key);
            ListNode* node = HashSearch(Q3Hash, key);

            if(node!=NULL)
                printf("%d is found.\n",key);
            else
                printf("%d is not found.\n",key);
            break;
        case 4: //Deletion
            printf("Enter a key to be deleted:\n");
            scanf("%d",&key);
            if(HashDelete(&Q3Hash,key))
                printf("%d is deleted.\n",key);
            else
                printf("%d is not existing.\n",key);
            break;
        case 5:
            HashPrint(Q3Hash);
            break;
        }

    printf("Enter selection: ");
    scanf("%d",&opt);
    }

    for(i=0;i<Q3Hash.hSize;i++)
    {
        while(Q3Hash.Table[i].head)
        {
            ListNode *temp;
            temp = Q3Hash.Table[i].head;
            Q3Hash.Table[i].head = Q3Hash.Table[i].head->next;
            free(temp);
        }
    }
    free(Q3Hash.Table);

    return 0;

}

int Hash(int key,int hSize)
{
    return key%hSize;
}

ListNode* HashSearch(HashTable Q3Hash, int key)
{
    int x;
    ListNode *temp;

    x = Hash(key,Q3Hash.hSize);

    temp =Q3Hash.Table[x].head;

    while(temp != NULL){
        if (temp->key == key){
            return temp;
        }
        temp = temp->next;
    }

    return NULL;

}

int HashInsert(HashTable* Q3HashPtr, int key)
{

    int x;
    x=Hash(key,Q3HashPtr->hSize);
    ListNode *temp;

    temp = (ListNode *) malloc(sizeof(ListNode));
    temp->key = key;
    temp->next = Q3HashPtr->Table[x].head;

    Q3HashPtr->Table[x].head = temp;
    Q3HashPtr->nSize++;

}

int HashDelete(HashTable* Q3HashPtr, int key)
{
    // Write your code here.
    int i,j,x,t;
    ListNode *temp;

    x=Hash(key,Q3HashPtr->hSize);
    temp = Q3HashPtr->Table[x].head;

    if(temp==NULL){
        return 0;
    }

    if(Q3HashPtr->Table[x].head->key==key){
        Q3HashPtr->Table[x].head = Q3HashPtr->Table[x].head->next;
        return 1;
    }

    while(temp->next!=NULL){
        if (temp->next->key == key){
            if(temp->next->next == NULL){
                temp->next = NULL;
                return 1;
            }
            temp->next = temp->next->next;
            return 1;
        }
        temp = temp->next;
    }

    return 0;
}

void HashPrint(HashTable Q3Hash)
{
    int i;
    ListNode *temp;
    for(i=0;i<Q3Hash.hSize;i++)
    {
        temp =Q3Hash.Table[i].head;
        printf("%d:  ",i);
        while(temp !=NULL)
        {
            printf("%d -> ", temp->key);
            temp = temp->next;
        }
        printf("\n");
    }
}
/*
1
3
2
1
2
2
2
3
2
4
2
5
2
6
2
7
2
8
2
9
3
5
4
5
3
5
3
4
3
6
6
*/
