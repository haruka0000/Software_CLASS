#include <stdio.h>

int main(){
    int sum = 0;
    int i = 0;
    for(i=1; sum < 1000; i++){
        sum = sum + i;
    }
    
    printf("%d\n", i-1);    // for文を抜ける時点でiは1加算されているため1引く
    return 0;
}
