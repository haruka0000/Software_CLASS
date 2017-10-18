#include <stdio.h>

int main (int argc,char *argv[]) {
    int MAX_BUFFER = 10; char buffer[MAX_BUFFER];
    while (1) {
        printf ("Input > ");
        fgets (buffer, MAX_BUFFER, stdin);
        printf ("string␣=␣%s\n", buffer);
        if (buffer[0] == 'q') break;
    }
    return 0;
}
