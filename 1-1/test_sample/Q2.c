#include<stdio.h>
#include<string.h>

void main(void) {
	char str[6] = "abcde", c;
	int len, i, j;
	len = strlen(str);
	for (i = 0; i < 3; i++) {
	  //for (c = str[len], j = len; j > 0; j--) // 問2-3での修正対象行
		for(c = str[len-1], j = len-1; j > 0; j--) 
      str[j] = str[j-1];
		str[0] = c;
	}
	printf("%s\n",str);
}
