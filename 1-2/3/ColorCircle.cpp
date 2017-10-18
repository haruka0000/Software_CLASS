#include "ColorCircle.h"

ColorCircle::ColorCircle () : Circle () { // 構築子
	color = "black";
}

// 属性を指定する構築子// 属性を指定する構築子
ColorCircle::ColorCircle (int cx, int cy, int r, std::string c) : Circle (cx, cy, r) {
	color = c;
}


// Circle クラスの draw () を上書き（オーバーライド）する
void ColorCircle::draw (svg* svgObj) { 
	svgObj->drawCircle(x, y, rad, color);
} 

// 色の設定
void ColorCircle::setColor (std::string c) {
	color = c;
}
