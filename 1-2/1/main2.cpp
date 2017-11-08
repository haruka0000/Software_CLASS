#include "Circle.h"

int main(){
	for(int i = 0; i < 10; i++){
		Circle *circ = new Circle(10*(i+1), 5*(i+2), 5*i+5);
		circ->draw();
		delete circ;
	}
}
