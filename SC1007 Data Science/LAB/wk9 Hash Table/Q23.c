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
