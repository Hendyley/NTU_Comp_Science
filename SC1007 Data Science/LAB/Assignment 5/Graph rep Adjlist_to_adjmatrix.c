
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
    if(g->type == ADJ_MATRIX) {printf("Error"); return;}

    int i,j,k;
    ListNode *temp=g->adj.list[i];
    int **matrix;

    matrix = (int **)malloc(g->V*sizeof(int *));
    for(i=0;i<g->V;i++){
        for(j=0;j<g->V;j++){
            matrix[i] = (int *)malloc(g->V*sizeof(int));
        }
    }

    for(i=0;i<g->V;i++){
        for(j=0;j<g->V;j++){
            matrix[i][j] = 0;
        }
    }

    for(i=0;i<g->V;i++){
       temp=g->adj.list[i];
       //k=0;
       while(temp!=NULL){
            k = temp->vertex;
            matrix[i][k-1] = 1;
            temp=temp->next;
            //k++;
       }

    }


    printf("I M HERE LOL \n");
    g->type = ADJ_MATRIX;

    //free adjList

    for(i=0;i<g->V;i++)
        free(g->adj.list[i]);
    free(g->adj.list);

    g->adj.matrix = matrix;



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
