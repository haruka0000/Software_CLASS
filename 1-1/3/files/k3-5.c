#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// 構造体の型枠の宣言 
struct student {
	char ID[9];		    //学生番号
	char name[33];	// 氏名
	int score;      // 点数
};

// 構造体のポインタと各値を引数として受け取る
void change_value(struct student *st, char ID[], char name[], int score){
  // 受け取った値に変更
  strcpy(st->ID, ID);
  strcpy(st->name, name);
  st->score = score;
  
}


int main(void){
  // 構造体の宣言と初期化
	struct student *student1;
  student1 = (struct student*) malloc(sizeof(struct student));

  // 変更される前の値
  strcpy(student1->ID, "B000000");
  strcpy(student1->name, "NO NAME");
  student1->score = 0;

  // 構造体の値を変更する関数を呼び出し
  change_value(student1, "B173335", "TAKAHASHI", 65);

  printf("%s %s %d \n",student1->ID, student1->name, student1->score);

	return 0;
}
