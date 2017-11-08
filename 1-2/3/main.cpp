#include "LineCircle.h"
#include <math.h>
#include <stdlib.h>

int main (int argc, char* argv[]) {
  svg *svgObj = new svg(); // SVGドキュメントを生成するオブジェクト
  int numCircles, width; // 円の個数と線の幅
	int bigrad = 100;	// 円の半径
	std::string color1, color2; // 円の描画色（配置／環状円）
 	LineCircle **l_circles = new LineCircle* [2];	// 環状円
  int area[2] = {640,400};	// 描画範囲
	int i, x, y;
	int xy[2];

  if (argc < 4) {
		std::cout << "引数が足りません";
    return 0;
  }
  numCircles = atoi(argv[1]);
  color1 = argv[2];
  color2 = argv[3];
  width = atoi(argv[4]);
   
	double deg = 360.0 / numCircles; // 角度の計算
	double rad = deg * M_PI / 180.0;	// 角度からラジアンに変更
	// 半径の計算
	// 2点の座標を計算後に2点間の直線距離を計算し、その半分が半径となる 
	int x1 = bigrad * cos(0);		// 1つ目の小円のx座標
	int y1 = bigrad * sin(0);		// 1つ目の小円のy座標
	int x2 = bigrad * cos(rad);	// 2つ目の小円のx座標
	int y2 = bigrad * sin(rad);	// 1つ目の小円のy座標
	int r = sqrt(pow(x1-x2, 2.0) + pow(y2-y1, 2.0))/2; // 配置円の半径の計算

	// Circle 配列に格納　配置円の個数分用意
	ColorCircle **c_circles = new ColorCircle* [numCircles];

	for (i = 0; i < numCircles; i++){
		rad = i * deg * M_PI / 180.0;	// 角度からラジアンに変更

		// x,y座標の計算（中心から）
		x = area[0]/2 + bigrad * cos(rad); 
		y = area[1]/2 + bigrad * sin(rad);

		// 格納	
		c_circles[i] = new ColorCircle(x, y, r, color1);
	}
	
	l_circles[0] = new LineCircle(area[0]/2, area[1]/2, bigrad+r, color2, width);
	l_circles[1] = new LineCircle(area[0]/2, area[1]/2, bigrad-r, color2, width);


	// svg 書き出し
	svgObj->open("Circle.html", 640, 400);	// ファイルオープン
	for (i = 0; i < numCircles; i++){
		//c_circles[i]->getPosition(xy);	// x,y（座標）の取得
		//r = c_circles[i]->getRadius();	// r（半径）の取得
		c_circles[i]->draw(svgObj);	// 求めた座標・半径で書き出し
	}
	//l_circles[0]->getPosition(xy);	// x,y（座標）の取得
	//r = c_circles[0]->getRadius();	// r（半径）の取得
	l_circles[0]->draw(svgObj);	// 求めた座標・半径で書き出し
	//l_circles[1]->getPosition(xy);	// x,y（座標）の取得
	//r = c_circles[1]->getRadius();	// r（半径）の取得
	l_circles[1]->draw(svgObj);	// 求めた座標・半径で書き出し

	svgObj->close();	// ファイルクローズ
    
  delete c_circles;
  delete l_circles;
  delete svgObj;

  return (1);
}
