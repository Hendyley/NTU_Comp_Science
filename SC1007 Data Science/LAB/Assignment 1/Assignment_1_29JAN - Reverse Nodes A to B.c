#include <stdio.h>
#include <stdlib.h>

struct _listNode{
    float item;
    struct _listNode* next;
};
typedef struct _listNode ListNode;

void printList(ListNode *head);
void deleteList(ListNode **ptrHead);

ListNode *reverseSegment(ListNode* head, int start, int end);

int main()
{
    ListNode *head = NULL, *temp;
	float f = 0.0;
	int S, E;

	scanf("%d %d",&S, &E);

	while (scanf("%f", &f)){
		if (head == NULL){
			head = (ListNode*) malloc(sizeof(ListNode));
			temp = head;
		}
		else{
			temp->next = (ListNode*) malloc(sizeof(ListNode));
			temp = temp->next;
		}
		temp->item = f;
	}
	temp->next = NULL;

	head = reverseSegment(head,S,E);
	printList(head);
	deleteList(&head);
	return 0;

}

void printList(ListNode *head){
    while(head !=NULL){
        printf("%.2f ",head->item);
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

ListNode *reverseSegment(ListNode* head, int start, int end)
{
// Write your code here
ListNode *tempA, *tempB, *temp;
float tempval;
int s1 = start,s2 = start+1, count=0, lk;

temp = head;
while(temp!=NULL){
    count++;
    temp = temp->next;
}
temp = head;

lk = end-start;

while (lk>=0 && (end>=0 && end<count) && (start>=0 && start<count ) ){
    //printf("\n Check lk %d %d\n",lk,start);
    tempA = findNode(head, start);
    tempB = findNode(head, start+1);
    for(int i=0; i<=(lk-1); i++){
        //printf("Loop %d\n",lk);
        tempval = tempA->item;
        tempA->item = tempB->item;
        tempB->item = tempval;
        //printf("tempA %.2f tempB %.2f tempval %.2f \n",tempA->item,tempB->item,tempval);

        tempA = tempA->next;
        tempB = tempB->next;
        //printf("2_ _tempA %.2f tempB %.2f tempval %.2f \n",tempA->item,tempB->item,tempval);
        //printList(head);
    }
    lk--;
}

return head;

}
