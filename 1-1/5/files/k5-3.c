#include <stdio.h>
#include <stdlib.h>

struct List {
  int data;
  struct List *next;
};


// プロトタイプ宣言
void add_node(struct List *, int);    // リストにノードを一つ追加する関数
void disp_data(struct List *);        // リストを先頭から走査をしてデータを表示する関数
void bubblesort(struct List *, int);


int main(void){
  // root（先頭）のデータを定義
  struct List *root = (struct List *) malloc (sizeof (struct List));
  root->data = 0;
  root->next = NULL;
  
  int n = 10;       // 追加するデータ数

  int i;
  for(i = 0; i <= n; i++){   // 1〜10の整数データを持つリストを作成
    add_node(root, rand());
  }

  disp_data(root);            // すべて表示 1

  printf("\n------ sorted ------\n");

	bubblesort(root, n+1);       // バブルソート　追加するデータ数＋ルート　を引数

  disp_data(root);            // すべて表示 2

  free(root);    // 解放
  
  return 0;
}


void disp_data(struct List *root){
  
  struct List *node = root;
    
  while(node != NULL){
    printf("%d\n", node->data);
    node = node->next;      //  ノードが持つ次のノードのアドレスを代入
  }

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



void bubblesort(struct List *root, int len){
	struct List *prev = root;   // 直前 
  struct List *list = prev->next;

	int i,j;
	int tmp;             // 交換用変数

	for(i=0; i<len; i++){
		for(j=len; j>i; j--){

			if(list->data < prev->data){
				
				// dataの入れ替え
  			tmp = list->data;
  			list->data = prev->data;
  			prev->data = tmp;
      } 
    	prev = prev->next;
			list = prev->next;

    }
    prev = root;
		list = prev->next;

  }
}
