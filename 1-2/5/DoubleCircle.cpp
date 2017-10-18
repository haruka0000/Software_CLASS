#include "DoubleCircle.h"

DoubleCircle::DoubleCircle():ColorCircle(){
	ratio = 50;
  color2;
}

DoubleCircle::DoubleCircle(int cx, int cy, int r, std::string c, std::string c2, int ra):ColorCircle(cx, cy, r, c){
	ratio = ra;
  color2 = c2;
}

void DoubleCircle::draw(svg* svgObj){
	svgObj->drawCircle(x, y, rad, color); 
  svgObj->drawCircle(x, y, rad*ratio/100, color2);
}

void DoubleCircle::setRatio(int ra){
	ratio = ra;
}

void DoubleCircle::setColor2(std::string c2){
	color2 = c2;
}
