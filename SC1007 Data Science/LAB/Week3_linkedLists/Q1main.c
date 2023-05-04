#include <stdio.h>
#include <stdlib.h>

typedef struct _listnode{
    int item;
    struct _listnode *next;
} ListNode;


void printList(ListNode *cur);
ListNode * findNode(ListNode *cur, int index);
int insertNode(ListNode **ptrHead, int index, int item);
void deleteList(ListNode **ptrHead);

int removeNode(ListNode **ptrHead,int index);

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

    while(1){
        printf("Enter the index of the node to be removed: ");
        scanf("%d",&index);

        if(removeNode(&head,index))
            size--;
        else{
            printf("The node cannot be removed.\n");
            break;
        }
        printf("After the removal operation,\n");
        printList(head);
    }

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
    while (removeNode(ptrHead,0));
}

int removeNode(ListNode **ptrHead,int index)
{
    // If linked list is empty
    if (*ptrHead == NULL)
        return 0;

    // Store head node
    ListNode* temp = *ptrHead;

    if (index == 0)
    {
       *ptrHead = temp->next;
       free(temp);
       return 1;
    }

    else
    {
        // Find previous node of the node to be deleted
        for (int i = 0; temp != NULL && i < index - 1; i++)
            temp = temp->next;
        // If position is more than number of nodes
        if (temp == NULL || temp->next == NULL)
            return 0;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        ListNode* next = temp->next->next;

        // Unlink the node from linked list
        free(temp->next); // Free memory

        // Unlink the deleted node from list
        temp->next = next;

    }

}
