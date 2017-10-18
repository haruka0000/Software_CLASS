#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// 構造体の型枠の宣言 
struct student {
	char ID[9];		    //学生番号
	char name[33];	// 氏名
	int score;      // 点数
};

int main(void){
	// 構造体の宣言と初期化
	struct student *student1;
  student1 = (struct student*) malloc(sizeof(struct student));

  strcpy(student1->ID, "B173335");
  strcpy(student1->name, "TAKAHASHI");
  student1->score = 65;
	
	printf("%s %s %d \n",student1->ID, student1->name, student1->score);
	
  free(student1);
	return 0;
}
