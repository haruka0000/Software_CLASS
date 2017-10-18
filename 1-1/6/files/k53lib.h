#ifndef _K53LIB_H_
#define _K53LIB_H_

struct List {
  int data;
  struct List *next;
};

// プロトタイプ宣言
void add_node(struct List *, int);    // リストにノードを一つ追加する関数
void disp_data(struct List *);        // リストを先頭から走査をしてデータを表示する関数
void bubblesort(struct List *, int);

#endif // _K53LIB_H_
