#include <stdio.h>
#include <stdlib.h>

void main(void) {
	char input[] = "abcde", output[6];
	char *inPtr = input;
	char *outPtr = output;
	for (; *inPtr != '\0';)
		*outPtr++ = *inPtr++;
	*outPtr = *inPtr;
	printf("%s\n",output);
}
