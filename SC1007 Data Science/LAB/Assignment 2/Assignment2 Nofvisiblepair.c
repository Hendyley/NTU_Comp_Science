#include <stdio.h>
#include <stdlib.h>

typedef struct _dbllistnode
{
    int  item;
	struct _dbllistnode *next;
	struct _dbllistnode *pre;
} CDblListNode;

typedef struct _dbllinkedlist{
   int size;
   CDblListNode *head;
} CDblLinkedList;

void insertNode_AtFront(CDblLinkedList *ptrCDLL, int value);
void deleteList(CDblLinkedList *ptrCDLL);
void printList(CDblLinkedList CDLL);

int numMountainPairs(CDblLinkedList CDLL);

int main()
{
    CDblLinkedList himalayas;
    himalayas.head = NULL;
    himalayas.size = 0;

    int item;
    int i,cases;
    int numP;
    scanf("%d",&cases);
    for(i=0; i<cases; i++)
    {
        while(scanf("%d",&item))
            insertNode_AtFront(&himalayas, item);
        scanf("%*s");

        numP = numMountainPairs(himalayas);
        printList(himalayas);
        printf("%d Pairs.\n",numP);

        deleteList(&himalayas);
    }
    return 0;
}

void insertNode_AtFront(CDblLinkedList *ptrCDLL, int value){
      //create a new node
      CDblListNode* newNode;
      newNode = (CDblListNode *) malloc(sizeof(CDblListNode));
      newNode->item = value;

      if(ptrCDLL->size==0) //first node
      {
          newNode->next = newNode;
          newNode->pre = newNode;
          ptrCDLL->head = newNode;
      }
      else{
        newNode->next = ptrCDLL->head;
        newNode->pre = ptrCDLL->head->pre;

        newNode->pre->next = newNode; //update last node next link;
        ptrCDLL->head->pre = newNode;

        ptrCDLL->head = newNode;
      }
      ptrCDLL->size++;
}

void printList(CDblLinkedList CDLL){

    if(CDLL.head==NULL) return;

    CDblListNode* temp = CDLL.head;
    printf("Current List: ");

    while (temp->next != CDLL.head){
        printf("%d ", temp->item);
        temp = temp->next;

    }
    printf("%d\n",temp->item);
}

void deleteList(CDblLinkedList *ptrCDLL){
    if(ptrCDLL->head==NULL) return;
    CDblListNode *cur = ptrCDLL->head;
    CDblListNode *temp;

    while (cur->next!= ptrCDLL->head) {
        temp=cur->next;
        free(cur);
        cur=temp;
    }

    free(cur);
    ptrCDLL->head =NULL;
    ptrCDLL->size =0;

}



int numMountainPairs(CDblLinkedList CDLL)
{
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

// Write your code here
    float mount=CDLL.size;
    int tp=0 , t=0 ,d=0 ,min=0;


    CDblListNode *temp=CDLL.head, *th=NULL, *tt=NULL, *a=NULL;

    if(mount<=1){
        return 0;
    } else if (mount==2){
        return 1;
    }

    for(int i=0; i<CDLL.size; i++ ){
        //printf("m %.2f \n",mount);
        //printf("%d \n",temp->item);
        th=temp->next;
        tt=temp->pre;
        tp=th->item;

        while(temp!=th){
            tt=temp->pre;
            //printf("temp %d th %d  tp %d mount %.2f \n",temp->item,th->item,tp,mount);
            //not besides

            if(temp->item > th->item){
                min = th->item;
            }
            else {
                min = temp->item;
            }
            //tp = min;

            if(th!=temp->next && th!=temp->pre){
                d=0;
                //next<-
                a=th;
                tp = th->next->item;
                while(a!=temp && min >= tp){
                    if(a->next==temp){
                        //printf(" <- temp %d th %d tp %d a %d\n",temp->item,th->item,tp,a->item);
                        d=1;
                    }
                    a = a->next;
                    if(tp < a->item){
                        tp = a->item;
                    }
                }
                //pre->
                a=th;
                tp = th->pre->item;
                while(a!=temp && min >= tp){
                    if(a->pre==temp){
                        //printf(" <- temp %d th %d tp %d a %d\n",temp->item,th->item,tp,a->item);
                        d=1;
                    }
                    a = a->pre;
                    if(tp < a->item){
                        tp = a->item;
                    }
                }

                //det
                if(d==1){
                    //printf("  Mount \n");
                    mount+=0.5;
                }

            }



            th=th->next;
            tt=temp->pre;
        }


        temp = temp->next;
    }
    //mount = mount - CDLL.size;


    return (int)mount;
}
