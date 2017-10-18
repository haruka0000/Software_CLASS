#include <stdio.h>
#include <string.h>

int split(char buffer[], char separator, char items[][5], int max_range, int max_num){
  int i = 0;
  int j = 0;
  int num = 0;

  while(1){
    if(buffer[i] == separator || buffer[i] == '\0'){
      if((i-j) > max_range){
        strncpy(items[num], buffer+j, max_range);
        items[num][max_range] = '\0';
      }
      else{
        strncpy(items[num], buffer+j, i-j);
        items[num][i-j] = '\0';
      }
      j = i+1;
      num++;
      if(buffer[i] == '\0'){
        break;
      }
      if(num == max_num){
        strncpy(items[num], buffer+j, max_range);
        items[num][max_range] = '\0';
        break;
      }
    }
    i++;
  }
  return num;
}

int main(int argc, char *argv[]){
  char word = ',';    // 分割記号
  char input[256];    // 入力値が入る
  char items[5][5];   // 配列の初期化 
  int max_range = 3;  // 最大長 ※加点用
  int max_num = 4;    // 最大分割数 ※加点用

  printf("input > ");
  scanf("%s",&input);

  int num = split(input, word, items, max_range, max_num);

  for(int x; x < num; x++){
    printf("Item[%d] = %s\n", x, items[x]);
  }
  return 0;
}

