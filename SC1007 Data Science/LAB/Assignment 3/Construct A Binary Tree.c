#include <stdio.h>
#include <stdlib.h>

#define MAX_N 120

typedef struct _btnode{
    char id;
    struct _btnode *left;
    struct _btnode *right;
} BTNode;

void buildTree(BTNode** node, char* preO, char* postO);
void inOrder(BTNode *cur);
void preOrder(BTNode *cur);
void postOrder(BTNode *cur);

int main()
{
    char preO[MAX_N];
    char postO[MAX_N];
    scanf("%s",preO);
    scanf("%s",postO);

    BTNode* root=NULL;
    buildTree(&root,preO,postO);
    if(root==NULL) printf("error\n");
    preOrder(root);
    printf("\n");
    postOrder(root);
    printf("\n");

    inOrder(root);
    printf("\n");

    return 0;
}
void inOrder(BTNode *cur){
    if (cur == NULL)
        return;

    inOrder(cur->left);
    printf("%c",cur->id);
    inOrder(cur->right);
}
void preOrder(BTNode *cur){
    if (cur == NULL)
        return;

    printf("%c",cur->id);
    preOrder(cur->left);
    preOrder(cur->right);
}
void postOrder(BTNode *cur){
    if (cur == NULL)
        return;

    postOrder(cur->left);
    postOrder(cur->right);
    printf("%c",cur->id);
}

/*
124567389ABC
56472BCA9831
*/
BTNode * populate(char * preO,char * postO, int *pre, int *post) {

    BTNode *tree = NULL;
    tree = (BTNode*) malloc(sizeof(BTNode));
    tree->id = preO[(*pre)++];
    tree->left = tree->right = NULL;

    if (tree->id != postO[*post]){
        tree->left = populate(preO,postO,pre,post);
    }

    if (tree->id != postO[*post]){
        tree->right = populate(preO,postO,pre,post);
    }

    //printf("%d\n",*post);
    (*post)++;

    return tree;

}

void buildTree(BTNode** node, char* preO, char* postO)
{


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
//*/

// Write your code here
//BTNode *temp=*node;
int i=0,j=0, pre=0,post=0;

*node = populate(preO,postO,&pre,&post);


}
