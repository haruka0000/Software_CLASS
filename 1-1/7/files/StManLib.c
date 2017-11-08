#include "StManLib.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Student *insert_value(char ID[],char name[], int score){
  struct Student *st;
  st = (struct Student*)malloc(sizeof(struct Student));  //新しいリストの領域を確保
  
  // 受け取った値に変更
  strcpy(st->ID, ID);
  strcpy(st->name, name);
  st->score = score;

  return st;
}


void add_list(struct List *root, struct Student *st){
  struct List *p;      // 新規ノード用
  struct List *next;   // 末尾
  struct List *prev;   // 直前        
 
  if(root->data == NULL){
    root->data = st;
  }
  else{
    p = (struct List*)malloc(sizeof(struct List));  //新しいリストの領域を確保

    p->data = st;    // 代入
    p->next = NULL;   // 末尾を示すNULL


    prev = root;
    // 先頭から末尾まで移動
    for(next=root->next; next!=NULL; next=next->next){
      prev=next; 
    }
  
    prev->next = p;
    p->prev = prev;
  }
}



struct List *del_list(struct List *root, char st_id[]){
  struct List *prev = root;   // 直前 
  struct List *delete;   // 削除するリスト
  struct Student *st = root->data;

  if(strcmp(st->ID, st_id)==0){
    delete = root;
  }else{
    for(; prev!=NULL;){   // 先頭のリストから削除するリストの直前のリストまで移動
      st = (prev->next)->data;
      if(strcmp(st->ID, st_id)==0){
        break;
      }
    
      prev = prev->next;
    }
  
    delete = prev->next;    // 削除するリストに移動
  }


  if(delete == root){
    root = root->next;
  }else{
    prev->next = delete->next;  // 削除対象の次のアドレスを前のnextに代入
  }

  free(delete);

  return root;
}



// 連結リストの全てを表示する
void disp_data(struct List *root){
  struct List *list = root;
  struct Student *st;

  while(list != NULL){
    st = list->data;
    printf("%s\t%s      \t%d\n", st->ID, st->name, st->score);
    list = list->next;
  }
}


// 一行の文字列を「，」で三分割
void split(char d[3][256], char s[]){
  int t = 0;
  int i = 0;
	int num = 0;

	while(1){
		if(s[t] == ','){
      d[num][i] = '\0';
      i = 0;
      num++;
      t++;
    }
    if(s[t] == '\n'){
      d[num][i] = '\0';
      break;
    }
    d[num][i] = s[t];
    i++;
    t++;
	}
}

void sort_list(struct List *root, int sign){
  struct List *list = root;
  struct List *end = NULL;
  struct Student *tmp;
  
  while(end != root->next){
    while(list->next != end){
      if(sign == 0){
        if((list->data)->score > ((list->next)->data)->score){
          tmp = list->data;
          list->data = (list->next)->data;
          (list->next)->data = tmp;
        }
      } else{
        if((list->data)->score < ((list->next)->data)->score){
          tmp = list->data;
          list->data = (list->next)->data;
          (list->next)->data = tmp;
        }
      }
      list=list->next;
    }
    end = list;
    list = root;
  }

  printf("------ sorted ------\n");
}
