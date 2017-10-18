#ifndef _STMANLIB_H_
#define _STMANLIB_H_

struct Student { 
  char ID[9];     // 学生番号
  char name[33];  // 名前
  int score;      // 成績
};

struct List {
  struct Student *data;
  struct List *next;
  struct List *prev;
};

// プロトタイプ宣言
struct Student *insert_value(char[], char[], int);
void add_list(struct List *, struct Student *);
void disp_data(struct List *);
void split(char[3][256], char[]);
void sort_list(struct List *, int);
struct List *del_list(struct List *, char[]);

#endif // _STMANLIB_H_
