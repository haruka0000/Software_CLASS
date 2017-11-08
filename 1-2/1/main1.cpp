#include "Circle.h"

int main(){
	Circle circ;
	
	for(int i = 0; i < 10; i++){
		circ.x = i + 10;
		circ.y = 2 * i + 10;
		circ.rad = 10 * (i + 1);
		circ.draw();
	}
}
