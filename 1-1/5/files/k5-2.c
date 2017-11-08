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
void del_list(struct List *, int);

int main(void){
  // root（先頭）のデータを定義
  struct List *root = (struct List *) malloc (sizeof (struct List));
  root->data = 0;
  root->next = NULL;
  
  int i;
  for(i = 1; i <= 10; i++){   // 1〜10の整数データを持つリストを作成
    add_node(root, i);
  }

  disp_data(root);            // すべて表示 1


  int del_num = 1;            // 削除するリストの位置（n番目）

  printf("\n%d番目を削除\n", del_num);
  
  del_list(root, del_num);          // 削除


  disp_data(root);            // すべて表示 2

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



void del_list(struct List *root, int num){
  
  struct List *prev;   // 直前 
  struct List *delete;   // 削除するリスト

  int i;
  
  prev = root;    // 先頭アドレス

  for(i = 0; i < num-1; i++){   // 先頭のリストから削除するリストの直前のリストまで移動
    prev = prev->next;
  }
  
  delete = prev->next;    // 削除するリストに移動

  prev->next = delete->next;

  free(delete);

}
