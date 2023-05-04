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

//124567389ABC
//56472BCA9831

int getLength(char * string) {
    int size = 0;
    while (*string != '\0') {
        size++;
        *string++;
    }
    return size;
}

BTNode * newNode(char c) {
    BTNode * node = malloc(sizeof(BTNode));
    node->left = NULL;
    node->right = NULL;
    node->id = c;
    return node;
}

BTNode * dfs(char * preO,char * postO, int *pRI, int *pOI) {

    BTNode * root = newNode(preO[(*pRI)++]);
    if (root->id != postO[*pOI])
        root->left = dfs(preO,postO,pRI,pOI);
    if (root->id != postO[*pOI])
        root->right = dfs(preO,postO,pRI,pOI);
    (*pOI)++;
    return root;

}

void buildTree(BTNode** node, char* preO, char* postO)
{
    int preId = 0;
    int postId = 0;
    *node = dfs(preO,postO,&preId,&postId);
}
