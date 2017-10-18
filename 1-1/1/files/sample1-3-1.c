#include <stdio.h>

int main(){
    int sum = 0;
    int i = 0;
    while(sum <= 1000){
        i++;
        sum = sum + i;
    }
    
    printf("%d\n", i);
    return 0;
}
