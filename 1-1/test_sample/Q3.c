#include <stdio.h>
#include <stdlib.h>

void main(void) {
  char matrix[][6] = {"ABCDE", "abc"};
  printf("%ld\n", sizeof(matrix[1]));
  printf("%s\n", matrix[0]);
  printf("%s\n", matrix[1]);
}
