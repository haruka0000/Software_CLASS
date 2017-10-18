#include "Circle.h"

int main () {
    int p[2], r;
    for (int i = 0; i < 10; i++) {
        Circle *circ = new Circle (i+10, 2*i+10, 15*(i+1)); // 値を代入しながらの生成
				
				circ->getPosition(p);
        r = circ->getRadius();
        printf("Position = %d, %d, Radius = %d\n", p[0], p[1], r);
        circ->draw ();
    }
    return (1);
}
