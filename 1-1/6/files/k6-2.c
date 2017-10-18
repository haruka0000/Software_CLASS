#include <stdio.h>
#include <stdlib.h>
#define ABS(a) ((a) >= 0 ? (a) : -(a))

int main (int argc, char *argv[]) {

	int a = atoi(argv[1]);
	int b = ABS(a);

	printf("abs(%d) = %d\n", a, b);
	return 0;
}
