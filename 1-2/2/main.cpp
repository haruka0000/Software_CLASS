#include <string>
#include <math.h>
#include "Circle.h"
#include <iostream>

int main(){
	// svgクラスの宣言
	svg *svg1 = new svg();

	std::string str("FIGURE.svg");	// 出力ファイル名
	int center[2] = {500,500};	// 図形の中心
	int amount = 3;			// 円の数
	std::cout << "3以上の整数を入力してください。 円の数 = ";
  std::cin  >> amount;
	int bigrad = 100;			// 大円の半径
	int i, x, y;
	double deg = 360.0 / amount; // 角度の計算
	double rad = deg * M_PI / 180.0;	// 角度からラジアンに変更
	int xy[2];


	// 半径の計算
	// 2点の座標を計算後に2点間の直線距離を計算し、その半分が半径となる 
	int x1 = bigrad * cos(0);		// 1つ目の小円のx座標
	int y1 = bigrad * sin(0);		// 1つ目の小円のy座標
	int x2 = bigrad * cos(rad);	// 2つ目の小円のx座標
	int y2 = bigrad * sin(rad);	// 1つ目の小円のy座標
	int r = sqrt(pow(x1-x2, 2.0) + pow(y2-y1, 2.0))/2; // 小円の半径の計算

	// Circle 配列に格納　小円の個数分用意
	Circle **circ = new Circle* [amount];
	for (i = 0; i < amount; i++){
		rad = i * deg * M_PI / 180.0;	// 角度からラジアンに変更

		// x,y座標の計算（中心から）
		x = center[0] + bigrad * cos(rad); 
		y = center[1] + bigrad * sin(rad);

		// 格納	
		circ[i] = new Circle(x, y, r);
	}

	
	// svg 書き出し
	svg1->open(str, 1000, 1000);	// ファイルオープン
	for (i = 0; i < amount; i++){
		circ[i]->getPosition(xy);	// x,y（座標）の取得
		r = circ[i]->getRadius();	// r（半径）の取得
		circ[i]->draw(svg1);	// 求めた座標・半径で書き出し
	}
	svg1->close();	// ファイルクローズ

	return 0;
}
