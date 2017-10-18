#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "StManLib.h"

// プロトタイプ宣言
void del_newline(char *);   // 文字列末尾の\n削除
void f_output(struct List *, char[]);   // ファイル出力

int main(){
  int MAX_BUFFER = 256;       // 最大バッファサイズ
  char buffer[MAX_BUFFER];
  char input_d[MAX_BUFFER];   // オプション入力用変数
  FILE *fp;                 // ファイルポインタ
  char s[256];              // 一行分を保持
  int count = 0;            // 読み込んだ行数
  char input_file[256] = "students2017.csv";    // 読み込みファイル名
  char d[3][256];           // ID，名前，成績のデータを一時的に保持
  int i;
  int sign = 0;             // sort時の昇順降順決定用引数

  struct List *root = (struct List *) malloc (sizeof (struct List));    // rootのメモリを宣言と確保
  // 中身を一時的に空にしておく
  root->data = NULL;
  root->next = NULL;
  root->prev = NULL;
  

  // ファイルオープン
  if ((fp = fopen(input_file, "r")) == NULL) {
    printf("ERROR!!\n");
    exit(EXIT_FAILURE);
  }

  // ファイルから１行単位で読み出し    
  while (fgets(s, 256, fp) != NULL) {
    split(d, s);  // 3つに分割してdに格納
    add_list(root, insert_value(d[0], d[1], atoi(d[2])));
  }
  
  fclose(fp); // ファイルを閉じる
  
  while(1){
    // コマンドの入力
    printf (">> ");
    fgets (buffer, MAX_BUFFER, stdin);
    del_newline(buffer);  // 入力末尾の改行記号を削除
   
    // 表示
    if(!strcmp("disp", buffer)){
      disp_data(root);
    }

    // ソート
    if(!strcmp("sort", buffer)){
      while(1){
        printf ("[0:昇順 or 1:降順]>> ");
        fgets (input_d, MAX_BUFFER, stdin);   // オプションを選択
        del_newline(input_d);
        if(input_d[0] == '1' || input_d[0] == '0'){  // 0 or 1が入力されるまで繰り返す
          break;
        }
      }
      sign = atoi(input_d);   // 入力をint型に変換して格納
      sort_list(root, sign);  // sort用メソッドにrootと昇順or降順の情報を渡す

      disp_data(root);  // 全て表示
    }

    // 生徒の情報を追加
    if(!strcmp("add", buffer)){
      input_d[0] = '\0';
      // IDの入力
      while(input_d[0] == '\0'){  // 入力が空の間繰り返す
        printf ("[ID]>> ");
        fgets (input_d, MAX_BUFFER, stdin);
      }
      del_newline(input_d);
      strcpy(d[0], input_d);

      // nameの入力
      input_d[0] = '\0';
      while(input_d[0] == '\0'){
        printf ("[name]>> ");
        fgets (input_d, MAX_BUFFER, stdin);
      }
      del_newline(input_d);
      strcpy(d[1], input_d);

      // scoreの入力
      input_d[0] = '\0';
      while(input_d[0] == '\0'){
        printf ("[score]>> ");
        fgets (input_d, MAX_BUFFER, stdin);
      }
      del_newline(input_d);
      strcpy(d[2], input_d);

      // 入力された３つをaddするメソッドに渡す
      add_list(root, insert_value(d[0], d[1], atoi(d[2])));

      disp_data(root);
    }

    // 削除
    if(!strcmp("delete", buffer)){
      input_d[0] = '\0';
      // 削除する生徒のIDを入力
      while(input_d[0] == '\0'){
        printf ("[ID]>> ");
        fgets (input_d, MAX_BUFFER, stdin);
      }

      del_newline(input_d); 
      
      root = del_list(root, input_d); // 削除の実行

      disp_data(root);
    }

    // ファイル書き出し
		if(!strcmp("output", buffer)){
      f_output(root, "students_new.csv");
    }

    // 終了
    if(!strcmp("exit", buffer)){
      break;
    }
    

  } 
  return 0;
}


void del_newline(char *str){
  int i = 0;
  // 改行記号を終端記号に置き換える
  while(1){
    if(str[i]=='\n' || str[i]=='\0'){
      str[i] = '\0';
      break;
    }
    i++;
  }
}

void f_output(struct List *root, char fname[]){
  struct List *list = root;
  struct Student *st;
	FILE *fp;		// ファイルポインタ 
  int i;
  char row[256];

  fp = fopen( fname, "w" );
  if( fp == NULL ){
    printf( "%s ERROR!!¥n", fname);
  }
  
  while(list != NULL){
    st = list->data;
    sprintf(row, "%s,%s,%d\n", st->ID, st->name, st->score);
    fputs(row, fp);

		list = list->next;
  }

  fclose( fp );
}
