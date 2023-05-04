#include <stdio.h>
#include <string.h>
#define MAX 100

typedef struct {
   char name[20];
   int telno;
} PhoneBk;

struct bk {
    char DDT[40];
};


void printPB(PhoneBk *pb, int size);
int readin(PhoneBk *pb);
void search(PhoneBk *pb, int size, char *target);

int main() {

   PhoneBk s[MAX];
   struct bk book;
   char t[20], *p;
   int size=0, choice;
   char dummychar;

   printf("Select one of the following options: \n");
   printf("1: readin()\n");
   printf("2: search()\n");
   printf("3: printPB()\n");
   printf("4: sort()\n");
   printf("5: exit()\n");
   do
    {
      printf("Enter your choice: \n");
      scanf("%d", &choice);
      switch (choice)
      {
         case 1:
            scanf("%c", &dummychar);
            size= readin(s);
            break;
         case 2:
            scanf("%c", &dummychar);
            printf("Enter search name: \n");
            fgets(t, 20, stdin);
            if (p=strchr(t,'\n')) *p= '\0';
            search(s,size,t);
            break;
         case 3:
            printPB(s, size);
            break;
         case 4:
             sort(s,size);
             break;
      }
    } while (choice< 5);

return 0;
}


void printPB(PhoneBk *pb, int size)   {

    int i=0,j=0;

    if(size==0){
        printf("Empty Phonebook \n");
    }
    else{

        for(i=0;i<size;i++){
            printf("Name: %s \n",pb[i].name);
            printf("Telno: %d \n",pb[i].telno);
        }

    }


}
int readin(PhoneBk *pb) {

    int size=0;
    char dummy[80],*p;

    while(1){
        //printf("Size: %d\n",size);
        printf("Enter name: \n");
        fgets(pb->name,80,stdin);
        if(p=strchr(pb->name,'\n')) *p = '\0';
        if(strcmp((pb->name),"#") == 0){
            //printf("D\n");
            break;
        }

        printf("Enter tel: \n");
        scanf("%d",&(pb->telno));
        pb++;
        size++;
        fgets(dummy,80,stdin);

    }
    return size;


}
void search(PhoneBk *pb, int size, char *target) {

    int i=0,j=0,k=0;
    char temp_name[80],*p;

    for(k=0;k<strlen(target);k++){
        target[k] = tolower(target[k]);
    }

    if(size==0){
        printf("Empty Phonebook \n");
    }
    else{
        for(i=0;i<size;i++){
        k=0;
        memset(temp_name, 0, 80);
        //printf("1   Target: %s list: %s \n",target,temp_name);

        for(k=0;k<strlen(pb[i].name);k++){
            temp_name[k] = tolower(pb[i].name[k]);
        }
        if(p=strchr(temp_name,'\n')) *p = '\0';

        //printf("Target: %s list: %s \n",target,temp_name);

        if(strcmp(target,temp_name)==0){
            printf("Name = %s, Tel = %d \n",pb[i].name,pb[i].telno);
            break;
        }

        }

        if(i==size){
            printf("Name not found!\n");
        }


    }

}

void sort(PhoneBk *pb,int size){

    int i=0,j=1,k=0,temp_tel;
    char temp_name[80];

    for(i=0;i<size;i++){
        for(j=i+1;j<size;j++){
            //printf("Check %s and %s  is  %d\n",pb[i].name,pb[j].name,strcmp(pb[i].name,pb[j].name));
            if(strcmp(pb[i].name,pb[j].name) > 0 ){


                printf("Tel\n");
                temp_tel = pb[i].telno;
                pb[i].telno = pb[j].telno;
                pb[j].telno = temp_tel;
                printf("Name\n");
                memcpy(temp_name, pb[i].name ,strlen(pb[i].name)+1);
                memcpy(pb[i].name, pb[j].name, strlen(pb[j].name)+1);
                memcpy(pb[j].name, temp_name, strlen(temp_name)+1);

            }
        }

    }


    printf("Sorting done!\n");


}


