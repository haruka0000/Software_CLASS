#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define MAX(a,b) ((a)>(b)?(b)=(a):0)

struct ExamScore {
	char name[100];
	int score[10]; // 各科目の点数
};

int maximum (struct ExamScore *es) {
	int n, val, max = 0;
	for (n = 0; n < 10; n++) {
		val = es->score[n];
		//if (val > max) max = val; // 問1-3, 1-4での変更部分
    //(val>max ? max=val : 0);
    MAX(val,max);
  }
  return max;
}

void main(void) {
	int n;
	int i;
  struct ExamScore *scoreData;
	scoreData = (struct ExamScore *)malloc(sizeof(struct ExamScore)* 100);
  
  for(n=0; n<100; n++){
    strcpy((scoreData+n)->name,"AAAA BBB");
    for (i=0; i<10; i++){
      (scoreData+n)->score[i] = i*10;
    }
  }
	
  for (n = 0; n < 100; n++){ // 学生ごとに各科目の最高点を出力する
	  printf("%d\n", maximum(scoreData+n));
  }
}
