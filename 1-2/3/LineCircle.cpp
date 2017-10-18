#include "LineCircle.h"

LineCircle::LineCircle():ColorCircle(){
	width = 0;
}

LineCircle::LineCircle(int cx, int cy, int r, std::string c, int w):ColorCircle(cx, cy, r, c){
	width = w;
}

void LineCircle::draw(svg* svgObj){
	svgObj->drawCircle(x, y, rad, color, width); 
}

void LineCircle::setWidth(int w){
	width = w;
}
