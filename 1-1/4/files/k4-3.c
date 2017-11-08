#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 構造体の型枠の宣言 
struct student {
  char ID[9];       //学生番号
  char name[33];  // 氏名
  int score;      // 点数
};


// 構造体のポインタと各値を引数として受け取る
void insert_value(struct student *st, char ID[], char name[], int score){

  // 受け取った値に変更
  strcpy(st->ID, ID);
  strcpy(st->name, name);
  st->score = score;
 
}


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

void op_file(struct student *st, char fname[], int count){
  FILE *fp;		// ファイルポインタ 
  int i;
  char row[256];

  fp = fopen( fname, "w" );
  if( fp == NULL ){
    printf( "%s ERROR!!¥n", fname);
  }
  

  for(i = 0; i < count; i++){
    sprintf(row, "%s,%s,%d\n", (st+i)->ID, (st+i)->name, (st+i)->score);
    
    fputs(row, fp);
  }

  fclose( fp );
}

int main(void){
	
	FILE *fp;		// ファイルポインタ
	char s[256];    // 一行分を保持
  int count = 0;  // 読み込んだ行数
  int i;
  char fname[12] = "sample.csv";  // 書き出しファイル不明
  
  char d[3][256];

	// 構造体の宣言と初期化
  struct student student_l[100];
	struct student *student_p;
	
	student_p = student_l;


	// オープン
	if ((fp = fopen("students.csv", "r")) == NULL) {
		printf("ERROR!!\n");
		exit(EXIT_FAILURE);
	}

	// ファイル読み込み
	while (fgets(s, 256, fp) != NULL) {
		// １行単位で読み出し
		
    split(d, s);  // 3つに分割する
    
    /* 分割して保存しているデータdを構造体に保存する関数に渡す
    その際にd[2]はint型に変換する*/
    insert_value(student_p+count, d[0], d[1], atoi(d[2]));   
    count++;
	}

	fclose(fp);
	
  for(i = 0; i < count; i++){
    printf("%s %s %d\n", (student_p+i)->ID, (student_p+i)->name, (student_p+i)->score);
  }

  op_file(student_p, fname, count);

	return 0;
}
