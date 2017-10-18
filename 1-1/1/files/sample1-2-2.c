#include <stdio.h>

int main(){
    char cmd;
    
    while(1){
        printf("input > ");
        cmd = getchar();
        
        if (cmd == 'o'){
            printf("Open file\n");
        }
        else if (cmd == 's'){
            printf("Save file\n");
        }
        else if (cmd == 'p'){
            printf("Print data\n");
        }
        else if (cmd == 'a'){
            printf("Add data\n");
        }
        else if (cmd == 'q'){
            printf("Quit program\n");
        }
        else{
            printf("Invalid command\n");
        }
        
        // バッファクリア
        int c;
        while ((c = getchar()) != '\n'){}
    }
    return 0;
}