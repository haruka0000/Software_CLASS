#include<stdio.h>

int main(int argc, char* argv[]){
  char month[2][5]={"April","May"};
	int n;
  
	for(n = 0; n < 2; n++){
	  printf("month[%d] = %s\n", n, month[n]);
  }
  
	return 0;
}
