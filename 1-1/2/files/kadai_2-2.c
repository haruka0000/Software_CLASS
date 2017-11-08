#include <stdio.h>

int main(int argc, char *argv[]){
  int i = 1;
  printf("input > \n");

  char input;
  int count = 0;
  while(input != '\n'){
    input = getchar();
    if (input == ','){
      count++;
    }
  }
  printf("There are %d ’,’s. in the input string.\n", count);
  return 0;
}
