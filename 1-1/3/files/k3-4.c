#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// 構造体の型枠の宣言 
struct student {
	char ID[9];		    //学生番号
	char name[33];	// 氏名
	int score;      // 点数
};

struct student *rt_adr(char ID[], char name[], int score){
  // 構造体の宣言と初期化
	struct student *student1;
  student1 = (struct student*) malloc(sizeof(struct student));

  strcpy(student1->ID, ID);
  strcpy(student1->name, name);
  student1->score = score;
  
  return student1;
}



int main(void){
  struct student *student_rt = rt_adr("B173335", "TAKAHASHI", 65);
  
  printf("%s %s %d \n",student_rt->ID, student_rt->name, student_rt->score);

	return 0;
}
