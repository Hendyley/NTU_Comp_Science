#include <stdio.h>
#include <string.h>
#define MAX 5

typedef struct
{
 int nameCardID;
 char personName[20];
 char companyName[20];
} NameCard;

void listNameCards(NameCard *nm, int size);
int addNameCard(NameCard *nm, int size);
int removeNameCard(NameCard *nm, int *size, char *target);
int getNameCard(NameCard *nm, int size, char *target);
void Sort (NameCard *nm, int size);


int main()
{
    NameCard nm[MAX];
    char target[80], *p;
    int size=0,choice,result,counter;
    char dummychar;

    printf("NTU NAME CARD HOLDER MANAGEMENT PROGRAM:  \n");
    printf("1: listNameCards() \n");
    printf("2: addNameCard() \n");
    printf("3: removeNameCard() \n");
    printf("4: getNameCard() \n");
    printf("5: quit \n");
    do{
        printf("Enter your choice: \n");
        scanf("%d",&choice);

        switch (choice){
            case 1:
                scanf("%c", &dummychar);
                printf("listNameCards(): \n");
                listNameCards(nm,size);
                break;
            case 2:
                scanf("%c", &dummychar);
                printf("addNameCard(): \n");
                Sort(nm,size);
                size = addNameCard(nm,size);
                Sort(nm,size);
                break;
                //if(p=strchr(target,'\n')) *p = '\0';
            case 3:
                scanf("%c", &dummychar);
                printf("removeNameCard(): \n");
                printf("Enter personName: \n");
                fgets(target, 80, stdin);
                if (p=strchr(target,'\n')) *p = '\0';
                Sort(nm,size);
                result = removeNameCard(nm,&size,target);
                Sort(nm,size);

                if (result == 0) {
                }
                else if (result == 1) {
                    printf("The name card holder is empty");
                    printf("\n");
                }
                else if (result == 2) {
                    printf("The target person name is not in the name card holder");
                    printf("\n");
                }
                else {
                    printf("An error has occurred");
                    printf("\n");
                }
                break;

            case 4:
                scanf("%c", &dummychar);
                printf("getNameCard(): \n");
                printf("Enter personName: \n");
                fgets(target, 80, stdin);
                if (p=strchr(target,'\n')) *p = '\0';

                result = getNameCard(nm,size,target);

                if (result == 0) {
                    //Success
                }
                else if (result == 1) {
                    printf("The name card holder is empty \n");
                }
                else if (result == 2) {
                    printf("The target person name is not found \n");
                }
                else {
                    printf("An error has occurred \n");
                }
                break;
        }
    } while (choice <= 4);
    return 0;
}

void listNameCards(NameCard*nm,int size)
{
    int i;

    if (size==0){
        printf("The name card holder is empty \n");
    }
    else {
        for(i=0;i<size;i++){
            //printf("Size : %d  i = %d \n",size,i);
            printf("nameCardID1: %d \n",nm[i].nameCardID);
            printf("personName1: %s \n",nm[i].personName);
            printf("companyName: %s \n",nm[i].companyName);
        }
    }
}


int addNameCard(NameCard *nm, int size)
{
    char *p, dummy[80];
    int i;
    int temp;
    char temp_name[80], temp_comp[80];

    printf("Enter nameCardID: \n");
    scanf("%d",&temp);

    fgets(dummy,80,stdin);

    printf("Enter personName: \n");
    fgets(temp_name,80,stdin);


    printf("Enter companyName: \n");
    fgets(temp_comp,80,stdin);


    for(i=0;i<size;i++){
        if(nm[i].nameCardID == temp){
            printf("The nameCardID has already existed \n");
            return size;
        }
    }

    if(size>=MAX){
        printf("The name card holder is full \n");
        return size;
    }

    nm[size].nameCardID = temp;
    memcpy(nm[size].personName,temp_name,strlen(temp_name)+1);
    if (p=strchr(nm[size].personName,'\n')) *p = '\0';
    memcpy(nm[size].companyName,temp_comp,strlen(temp_comp)+1);
    if (p=strchr(nm[size].companyName,'\n')) *p = '\0';
    nm++;
    size++;
    printf("The name card has been added successfully \n");

    return size;

}



int removeNameCard(NameCard*nm, int *size, char *target)
{
    int i,j,k;
    int temp;
    char temp_name[80], temp_comp[80], temp_word[80], *p;

    if ((*size) == 0){
        return 1;
    }

    i=0;
    j=-1;

    for(k=0;k<strlen(target);k++){
        target[k] = tolower(target[k]);
    }

    while ( (j == -1) && (i < (*size) )){

        for(k=0;k<strlen(nm[i].personName)+1;k++){
            temp_word[k] = tolower(nm[i].personName[k]);
        }
        //printf("Target: %s   Temp_word: %s \n",target,temp_word);
        if ( strcmp(target,temp_word) == 0 ){
            j = i;
            temp = nm[i].nameCardID;
            memcpy(temp_name,nm[i].personName,strlen(nm[i].personName)+1);
            if (p=strchr(temp_name,'\n')) *p = '\0';
            memcpy(temp_comp,nm[i].companyName,strlen(nm[i].companyName)+1);
            if (p=strchr(temp_comp,'\n')) *p = '\0';
        }
        else {
            i++;
        }
    }

    if (j == -1){  //not exist
        return 2;
    }
    else {
        for(i=j;i<(*size-1);i++){
            strcpy(nm[i].personName,nm[i+1].personName);
            nm[i].nameCardID = nm[i+1].nameCardID;
            strcpy(nm[i].companyName,nm[i+1].companyName);
        }
    --(*size);
    }

    printf("The name card is removed \n");
    printf("nameCardID: %d \n",temp);
    printf("personName: %s \n",temp_name);
    printf("companyName: %s \n",temp_comp);
    return 0; //successful
}



int getNameCard(NameCard *nm, int size, char *target)
{

    int i,k;
    char temp_word[80];

    if(size==0){
        return 1;
    }

    for(k=0;k<(strlen(target));k++){
        target[k] = tolower(target[k]);
    }
    //printf("Target : %d %s %d \n",strlen(target),target,size);

    for(i=0;i<size;i++){

        for(k=0;k<strlen(nm[i].personName)+1;k++){
            temp_word[k] = tolower(nm[i].personName[k]);
            //printf("check : %d %s %d",strlen(nm[i].personName[k]),nm[i].personName[k],i);
        }

        if(strcmp(temp_word,target) == 0){
            printf("The target person name is found \n");
            printf("nameCardID: %d \n",nm[i].nameCardID);
            printf("personName: %s \n",nm[i].personName);
            printf("companyName: %s \n",nm[i].companyName);
            return 0;
        }

        if(i==(size-1)){
            return 2;
        }

    }

}


void Sort(NameCard *nm, int size) {

    int i, j;
    int temp;
    char temp_name[80], temp_comp[80];


    for (i=0; i<size; i++) {
        for (j=i+1; j<size; j++) {
            if (nm[i].nameCardID > nm[j].nameCardID) {
                temp=nm[i].nameCardID;
                nm[i].nameCardID=nm[j].nameCardID;
                nm[j].nameCardID=temp;

                memcpy(temp_name,nm[i].personName, strlen(nm[i].personName)+1);
                memcpy(nm[i].personName,nm[j].personName, strlen(nm[j].personName)+1);
                memcpy(nm[j].personName,temp_name, strlen(temp_name)+1);

                memcpy(temp_comp,nm[i].companyName, strlen(nm[i].companyName)+1);
                memcpy(nm[i].companyName,nm[j].companyName, strlen(nm[j].companyName)+1);
                memcpy(nm[j].companyName,temp_comp, strlen(temp_comp)+1);

            }
        }
    }
}
