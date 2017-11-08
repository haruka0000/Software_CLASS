#include <stdio.h>
#include <stdlib.h>
#include "k53lib.h"

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
