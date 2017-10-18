#include <stdio.h>
#include <string.h>

int countWord(char word, char input[]){ 
  int count = 0;
  int i = 0;
  while(input[i] != '\0'){
    if(word == input[i]){
      count++;
    }
    i++;
  }
  
  return count;
}

int main(int argc, char *argv[]){
  char word = ',';
  char input[256];

  printf("input > \n");
  scanf("%s",&input);

  printf("There are %d ’%c’s. in the input string.\n", countWord(word, input), word);

  return 0;
}

