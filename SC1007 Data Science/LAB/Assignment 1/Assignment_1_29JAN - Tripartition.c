#include <stdio.h>
#include <stdlib.h>

struct _listNode{
    int item;
    struct _listNode* next;
};
typedef struct _listNode ListNode;

void printList(ListNode *head);
void deleteList(ListNode **ptrHead);

void triPartition(ListNode** head, int pivot);

int main()
{
    ListNode *head = NULL, *temp;
	int i = 0;
	int pivot = 0;

	scanf("%d",&pivot);

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


	triPartition(&head, pivot);
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



void triPartition(ListNode** head, int pivot){
    // Write your code here
    //8 1 5 5 7 2 5 a
    ListNode *refer=*head, *referh=NULL, *sort=NULL, *sorth=NULL, *sortl=NULL, *sortlh=NULL;
    int count=0, piv=0;

    refer = *head;
    while(refer!=NULL){
        count++;
        if(refer->item == pivot){
            piv++;
        }
        refer = refer->next;
    }
    refer = *head;

    if(count<0){
    }
    else{

        referh = refer;
        while(refer != NULL){
            if(refer->item < pivot){
                if(sort==NULL){
                   sort = (ListNode *) malloc(sizeof(ListNode));
                   sorth = sort;
                   sort->item = refer->item;
                }
                else{
                    sort->next = (ListNode *) malloc(sizeof(ListNode));
                    sort = sort->next;
                    sort->item = refer->item;
                }
            }
            refer = refer->next;
        }
        sort->next = NULL;



        refer = referh;
        //printList(refer);
        while(refer != NULL){
            if(refer->item > pivot){
                if(sortl==NULL){
                   sortl = (ListNode *) malloc(sizeof(ListNode));
                   sortlh = sortl;
                   sortl->item = refer->item;
                }
                else{
                    sortl->next = (ListNode *) malloc(sizeof(ListNode));
                    sortl = sortl->next;
                    sortl->item = refer->item;
                }
            }
            refer = refer->next;
        }
        sortl->next = NULL;


        refer = *head;
        referh = refer;
        //printList(refer);
        //printList(sorth);
        //printList(sortlh);
        while(sorth!=NULL){
            refer->item = sorth->item;
            refer = refer->next;
            sorth = sorth->next;
        }
        for(int i=0;i<piv;i++){
            refer->item = pivot;
            refer = refer->next;
        }

        while(sortlh!=NULL && refer!=NULL){
            refer->item = sortlh->item;
            refer = refer->next;
            sortlh = sortlh->next;

        }

        //printList(referh);
        *head = referh;


    }


}
