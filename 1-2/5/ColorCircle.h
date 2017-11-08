#pragma once
#include "Circle.h"
#include "svg.h"

class ColorCircle : public Circle {
	protected:
		std::string color = "black"; // 描画色
	
	public:
		ColorCircle (); // 構築子
		ColorCircle (int cx, int cy, int r, std::string c); // 違う形式の構築子

		virtual void draw (svg*); // Circle クラスの draw () を上書き（オーバーライド）する
		void setColor (std::string c); // 色の設定
};
