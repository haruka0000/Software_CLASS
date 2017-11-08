#include "Circle.h"
#include<math.h>

Circle::Circle () {
	x = y = 0; rad = 10;
} 

Circle::Circle (int cx, int cy, int r) {
  x = cx;
	y = cy;
	rad = r; 
}

void Circle::setPosition (int x, int y) { 
  this->x = x; this->y = y; // this->x はメンバ変数の x であり，引数 x とは異なる変数！
}

void Circle::setRadius (int rad) { 
  this->rad = rad; // this->rad と rad は異なる変数！
}

int Circle::getRadius () {
	return rad;
}


void Circle::getPosition (int xy[]) {
	xy[0] = x;
	xy[1] = y;
}

bool Circle::checkOverlap (Circle circ) {	
	double xx = pow(this->x - circ.x, 2.0);			// xの2乗
	double yy = pow(this->y - circ.y, 2.0);			// yの2乗
	double rr = pow(this->rad - circ.rad, 2.0);	// radの2乗	
	// printf("%f,%f,%f\n", xx, yy, rr);

	if (xx + yy - rr > 0){
		return false;
	}else{
		return true;
	}
}

void Circle::draw (svg* svgObj) { // 円の描画関数（svg オブジェクトのポインタを受け取る）
    svgObj->drawCircle(x, y, rad); // 中心 (x,y)、半径 r の円の輪郭描画（SVG形式）
}
