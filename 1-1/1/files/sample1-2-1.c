#include <stdio.h>

int main(){
    char cmd;
    while(1){
        printf("input > ");
        cmd = getchar();
        switch(cmd){
                default:
                    printf("Invalid command\n");
                    break;
                case 'o':
                    printf("Open file\n");
                    break;
                case 's':
                    printf("Save file\n");
                    break;
                case 'p':
                    printf("Print data\n");
                    break;
                case 'a':
                    printf("Add data\n");
                    break;
                case 'q':
                    printf("Quite program\n");
                    break;
        }
        int c;
        while ((c = getchar()) != '\n'){}
    }
    return 0;
}