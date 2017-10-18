#include <stdio.h>
#include <stdlib.h>
//#include <string.h>

struct List {
  int data;
  struct List *next;
};


// プロトタイプ宣言
void add_node(struct List *, int);    // リストにノードを一つ追加する関数
void disp_data(struct List *);        // リストを先頭から走査をしてデータを表示する関数


int main(void){
  // root（先頭）のデータを定義
  struct List *root = (struct List *) malloc (sizeof (struct List));
  root->data = 0;
  root->next = NULL;
  
  int i;
  for(i = 1; i <= 10; i++){   // 1〜10の整数データを持つリストを作成
    add_node(root, i);
  }

  disp_data(root);            // すべて表示
  
  free(root);    // 解放
  
  return 0;
}


void add_node(struct List *root, int tmp){
  
  struct List *p;      // 新規ノード用
  struct List *next;   // 末尾
  struct List *prev;   // 直前
  
  p = (struct List*)malloc(sizeof(struct List));  //新しいリストの領域を確保
  
  p->data = tmp;    // 代入
  
  p->next = NULL;   // 末尾を示すNULL

  prev = root;     // 先頭

  // 先頭から末尾まで移動
  for(next=root->next; next!=NULL; next=next->next){
    prev=next; 
  }
  
  prev->next = p;   // 直前ノードのnextに新規（末尾）ノードのアドレスの代入

}


void disp_data(struct List *root){
  
  struct List *node = root;
  
  while(node != NULL){
    printf("%d\n", node->data);
    node = node->next;      //  ノードが持つ次のノードのアドレスを代入
  }

}

