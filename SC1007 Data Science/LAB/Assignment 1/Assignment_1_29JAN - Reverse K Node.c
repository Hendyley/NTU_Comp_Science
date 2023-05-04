#include <stdio.h>
#include <stdlib.h>

struct _listNode{
    int item;
    struct _listNode* next;
};
typedef struct _listNode ListNode;

void printList(ListNode *head);
void deleteList(ListNode **ptrHead);

void reverseKNodes(ListNode** head, int K);

int main()
{
    ListNode *head = NULL, *temp;
	int i = 0;
	int K = 0;

	scanf("%d",&K);

	while (scanf("%d", &i)){
		if (head == NULL){
			head = (ListNode*) malloc(sizeof(ListNode));
			temp = head;
		}
		else{
			temp->next = (ListNode*) malloc(sizeof(ListNode));
			temp = temp->next;
		}
		temp->item = i;
	}
	temp->next = NULL;


	reverseKNodes(&head, K);
	printList(head);
    deleteList(&head);
	return 0;
}

void printList(ListNode *head){
    while(head !=NULL){
        printf("%d ",head->item);
        head = head->next;
    }
    printf("\n");
}
void deleteList(ListNode **ptrHead){
    ListNode *cur = *ptrHead;
    ListNode *temp;
    while (cur!= NULL) {
		temp=cur->next;
		free(cur);
		cur=temp;
	}
	*ptrHead=NULL;
}

ListNode *findNode(ListNode* cur, int index)
{
   if (cur==NULL || index<0)
      return NULL;
   while(index>0){
      cur=cur->next;
      if (cur==NULL)
         return NULL;
      index--;
   }
   return cur;
}

void reverseKNodes(ListNode** head, int K){
//Write your code here
//1 2 3 4 5 6 7 8 9 10 a
ListNode *temp = *head, *pre=NULL, *temph=NULL, *nw=NULL, *nwh=NULL;
int count=0, r=0;


while(temp != NULL){
    count++;
    temp = temp->next;
}
//printf("Count %d \n",count);
temp = *head;

if(K>1 && K<count){
    for (int i=0; i<count ; i+=K){

        if((count-i)<K){
            break;
        }

        //printf("i = %d of %d \n",i,count);
        for(int x=(i+K); x>i; x--){
            pre = findNode(temp, (x-1));
            //printf("Chk i= %d  x= %d preval= %d \n",i,x,pre->item);
            if(nw==NULL){
                nw = (ListNode *) malloc(sizeof(ListNode));
                nwh = nw;
            }
            else{
                nw->next = (ListNode *) malloc(sizeof(ListNode));
                nw = nw->next;
            }
            nw->item = pre->item;
            r++;
        }
    }
    while(r<count){
         pre = findNode(temp, r);
         nw->next = (ListNode *) malloc(sizeof(ListNode));
         nw = nw->next;
         nw->item = pre->item;
         r++;
    }
    nw->next = NULL;

    *head = nwh;
    //printList(nwh);
}

}
