#include "DoubleCircle.h"
#include <math.h>
#include <stdlib.h>

int main (int argc, char* argv[]) {
  svg svgObj;
  int num_circle;
  int ratio;
	int bigrad = 100;	// 円の半径
	std::string color1, color2, color3; // 円の描画色（配置／環状円）
  int area[2] = {640,400};	// 描画範囲
	int i, x, y;
	int xy[2];

  if (argc < 5) {
		std::cout << "引数が足りません";
    return 0;
  }
  num_circle = atoi(argv[1]);
  color1 = argv[2];
  color2 = argv[3];
  color3 = argv[4];
  ratio = atoi(argv[5]);
   
	double deg = 360.0 / num_circle; // 角度の計算
	double rad = deg * M_PI / 180.0;	// 角度からラジアンに変更
	// 半径の計算
	// 2点の座標を計算後に2点間の直線距離を計算し、その半分が半径となる 
	int x1 = bigrad * cos(0);		// 1つ目の小円のx座標
	int y1 = bigrad * sin(0);		// 1つ目の小円のy座標
	int x2 = bigrad * cos(rad);	// 2つ目の小円のx座標
	int y2 = bigrad * sin(rad);	// 1つ目の小円のy座標
	int r = sqrt(pow(x1-x2, 2.0) + pow(y2-y1, 2.0))/2; // 配置円の半径の計算

	// Circle 配列に格納　配置円の個数分用意
	Circle **circleGroup = new Circle* [num_circle];

	for (i = 0; i < num_circle; i++){
		rad = i * deg * M_PI / 180.0;	// 角度からラジアンに変更

		// x,y座標の計算（中心から）
		x = area[0]/2 + bigrad * cos(rad); 
		y = area[1]/2 + bigrad * sin(rad);

    if(i % 2 == 0){   // 偶数の時
		// 格納	
		  circleGroup[i] = new ColorCircle(x, y, r, color1);    // 円の格納
	  } else{
      circleGroup[i] = new DoubleCircle(x, y, r, color2, color3, ratio);    // 二重円の格納
    }
  }
	
	// svg 書き出し
	svgObj.open("Circle.html", 640, 400);	// ファイルオープン
	for (i = 0; i < num_circle; i++){
      circleGroup[i]->draw(&svgObj);	// 格納済みの円の書き出し
	}

	svgObj.close();	// ファイルクローズ
    
  delete circleGroup;   // メモリ解放

  return (1);
}
