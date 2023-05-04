#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode{
    int item;
    struct _listnode *next;
} ListNode;

int moveMaxToFront(ListNode **ptrHead);

void printList(ListNode *cur);
ListNode * findNode(ListNode *cur, int index);
int insertNode(ListNode **ptrHead, int index, int item);
void deleteList(ListNode **ptrHead);

int main()
{
    ListNode *head=NULL;
    int size =0;
    int item;
    int index;

    printf("Enter a list of numbers, terminated by any non-digit character: \n");
    while(scanf("%d",&item))
        if(insertNode(&head,size, item)) size++;
    scanf("%*s");

    printList(head);

    moveMaxToFront(&head);

    printList(head);

    deleteList(&head);
    return 0;
}

void printList(ListNode *cur){
    printf("Current List: ");
    while (cur != NULL){
        printf("%d ", cur->item);
        cur = cur->next;
    }
    printf("\n");
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

int insertNode(ListNode **ptrHead, int index, int item){
    ListNode  *pre, *newNode;
    // If empty list or inserting first node, update head pointer
    if (index == 0){
        newNode = malloc(sizeof(ListNode));
        newNode->item = item;
        newNode->next = *ptrHead;
        *ptrHead = newNode;
        return 1;
    }
    // Find the nodes before and at the target position
    // Create a new node and reconnect the links
    else if ((pre = findNode(*ptrHead, index-1)) != NULL){
        newNode = malloc(sizeof(ListNode));
        newNode->item = item;
        newNode->next = pre->next;
        pre->next = newNode;
        return 1;
    }
    return 0;
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

int moveMaxToFront(ListNode **ptrHead){
    ListNode *pre, *cur;
	ListNode *preMax = NULL;
	int maxItem;
	// Empty list
	if (*ptrHead == NULL) return -1;
	// Only one node in the list
	if ( (*ptrHead)->next == NULL )
		return 0;
	cur = (*ptrHead);
	maxItem = (*ptrHead)->item;
	// preMax gets set to the address of the ListNode with the max value
	// If preMax is still NULL at the end, first node had the max value
	while (cur != NULL){
		if (cur->item > maxItem){
			maxItem = cur->item;
			preMax = pre;
			}
		pre = cur;
		cur = cur->next;
	}
	// Never saw a value higher than the first node, nothing to move
	if (preMax == NULL)
		return 0;
	// Move node with max value to first position
	cur = preMax->next;
	preMax->next = preMax->next->next;
	// Update head pointer
	cur->next = *ptrHead;
	*ptrHead = cur;
	return 0;



}
