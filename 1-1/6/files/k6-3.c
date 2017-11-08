#include <stdio.h>
#include <stdlib.h>
#include "k53lib.h"

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


// コマンド　gcc -o k6-3 k6-3.c k53lib.c
