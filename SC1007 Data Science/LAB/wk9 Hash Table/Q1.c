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
    int i;
    ListNode *temp;

    if(Q3Hash.hSize!=0){
        //printf("s Index: %d \n",Hash(key,Q3Hash.hSize));
    }
    else{
        return NULL;
    }

    for(i=0;i<Q3Hash.hSize;i++)
    {
        temp = Q3Hash.Table[i].head;
        //printf("%d:  ",i);
        while(temp !=NULL)
        {
            //printf("%d -> ", temp->key);
            if( temp->key == key ){
                return temp;
            }
            temp = temp->next;
        }
        //printf("\n");
    }

    return NULL;


}

int HashInsert(HashTable* Q3HashPtr, int key)
{
    //write your code here
    int i=0,j=0,index=0,nsz=key,hsz=Q3HashPtr->hSize;
    HashTableNode temp;

    if(HashSearch(*Q3HashPtr, key)!=NULL)   //duplicate
        return 0;

    if(Q3HashPtr->hSize!=0)
        index  = Hash(key,Q3HashPtr->hSize);
        //printf("i index: %d\n",index);

    if( Q3HashPtr->Table[nsz % hsz].head == NULL ){

        Q3HashPtr->Table[nsz % hsz].head = (ListNode *) malloc(sizeof(ListNode));
        Q3HashPtr->Table[nsz % hsz].head->key = key;
        Q3HashPtr->Table[nsz % hsz].head->next = NULL;
        Q3HashPtr->nSize++;
    }
    else {
        temp.head = (ListNode *) malloc(sizeof(ListNode));
        temp.head->key = key;
        temp.head->next = Q3HashPtr->Table[nsz % hsz].head;
        Q3HashPtr->Table[nsz % hsz].head = temp.head;
        Q3HashPtr->nSize++;
    }


    return 1;

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
9
3
10
4
5
*/
