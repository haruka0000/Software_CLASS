#include "Circle.h"

int main () {
    int p[2], r;
    bool judge;
		Circle *circ1 = new Circle (11, 11, 1); // 値を代入しながらの生成
		Circle circ2 = Circle (11, 12, 1); // 値を代入しながらの生成
				
		judge = circ1->checkOverlap(circ2);

		if(judge){
			printf("2つの円は交差しない\n");
		}else{ 
			printf("2つの円は交差する\n");
    }
		return (1);
}
