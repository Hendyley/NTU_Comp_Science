#include <stdio.h>
#include <stdlib.h>

struct _listNode{
    int item;
    struct _listNode* next;
};
typedef struct _listNode ListNode;

typedef struct _linkedList{
    ListNode *head;
    int size;
}LinkedList;

void printList(LinkedList ll);
void printListN(ListNode *head);
void deleteList(LinkedList* llptr);

ListNode *realreverse (ListNode *temp, int count, int c);

LinkedList rearrange (LinkedList ll);

int main()
{
    LinkedList ll;

    ll.head = NULL;
    ll.size = 0;
    ListNode* temp;

	int i = 0;

	while (scanf("%d", &i)){
		if (ll.head == NULL){
			ll.head = (ListNode*) malloc(sizeof(ListNode));
			temp = ll.head;
		}
		else{
			temp->next = (ListNode*) malloc(sizeof(ListNode));
			temp = temp->next;
		}
		temp->item = i;
		ll.size++;
	}
	temp->next = NULL;

	ll=rearrange(ll);

	printList(ll);
    deleteList(&ll);
	return 0;
}

void printList(LinkedList ll){
    ListNode* temp = ll.head;

    while(temp !=NULL){
        printf("%d ",temp->item);
        temp = temp->next;
    }
    printf("\n");
}
void deleteList(LinkedList* llptr){
    ListNode *cur = llptr->head;
    ListNode *temp;
    while (cur!= NULL) {
		temp=cur->next;
		free(cur);
		cur=temp;
	}
	llptr->head=NULL;
	llptr->size =0;
}

void printListN(ListNode *head){
    while(head != NULL){
        printf("%d ",head->item);
        head = head->next;
    }
    printf("\n");
}

ListNode *realreverse (ListNode *temp, int count, int c){
//printf("char %d\n",c);
ListNode *oddg=NULL, *eveng=NULL, *oddh=NULL, *evenh=NULL, *temph;

for(int i=0; i<(count/2) ; i++){
    if(oddg == NULL){
        oddg = (ListNode *) malloc(sizeof(ListNode));
        oddh = oddg;
        oddg->item = temp->item;
    }
    else{
        oddg->next = (ListNode *) malloc(sizeof(ListNode));
        oddg = oddg->next;
        temp = temp->next;
        oddg->item = temp->item;
    }
    //printf("O : %d \n",temp->item);
    //printf("item: %d next val %d\n",oddg->item,(oddg->next));
    //oddg = oddg->next;
    //temp = temp->next;
}
oddg->next = NULL;
temp = temp->next;
//printf("%d\n",temp->next->item);

while (temp!=NULL){
    //printf("%d\n",temp->next->item);
    if(eveng == NULL){
        eveng = (ListNode *) malloc(sizeof(ListNode));
        evenh = eveng;
        eveng->item = temp->item;
    }
    else {
        eveng->next = (ListNode *) malloc(sizeof(ListNode));
        eveng = eveng->next;

        eveng->item = temp->item;
    }
    temp = temp->next;
    //printf("E : %d \n",temp->item);
    //printf("item: %d next val %d\n",eveng->item,(eveng->next));
}
eveng->next = NULL;


oddg = oddh;
eveng = evenh;
//printListN(oddg);
//printListN(eveng);

temp = NULL;


for(int i=0; i<count; i++){

    int k;
    if(c==1){
        k = i;
    }else{
        k = i+1;
    }

    if(temp==NULL){
        temp = (ListNode *) malloc(sizeof(ListNode));
        temph = temp;
        if(c==1){
            temp->item = eveng->item;
            eveng = eveng->next;
        }else{
            temp->item = oddg->item;
            oddg = oddg->next;;
        }
    }
    else{
        if(((k%2)!=0) && (oddg!=NULL)){
            //printf("odd\n");
            temp->next = (ListNode *) malloc(sizeof(ListNode));
            temp = temp->next;
            temp->item = oddg->item;
            oddg = oddg->next;
        }
        else{
            //printf("even\n");
            temp->next = (ListNode *) malloc(sizeof(ListNode));
            temp = temp->next;
            temp->item = eveng->item;
            eveng = eveng->next;
        }
    }
    //printf("i %d temp %d \n",i,temp->item);
}
temp->next = NULL;

return temph;

}

LinkedList rearrange (LinkedList ll){
// Write your code here
ListNode *oddg=NULL, *eveng=NULL, *oddh=NULL, *evenh=NULL, *temp, *temph;
int count = 0;

temp = ll.head;
while (temp!=NULL){
    count++;
    //printf("%d C:%d\n",temp->item,count);
    temp = temp->next;
}
//printf("Count : %d \n",count);
temp = ll.head;

if(count<=1){
}
else{
    //1 2 3 4 5 6 7 8 9 10 11 a
    temph = realreverse(temp,count,1);
    //printListN(temph);
    temph = realreverse(temph,count,2);
    //printListN(temph);

    ll.head = temph;
}


return ll;

}
