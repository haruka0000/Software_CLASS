#include <stdio.h>
#include <stdlib.h>

int main (int argc, char *argv[]) {

	int a = atoi(argv[1]);
	int b;

	(a >= 0 ? (b = a) : (b = -a));

	printf("abs(%d) = %d\n", a, b);
	return 0;
}
