#pragma once
#include "ColorCircle.h"

class LineCircle : public ColorCircle {
	protected:
		int width;	// 幅

	public:
		LineCircle();
		LineCircle(int cx, int cy, int r, std::string c, int w);
		
		void draw(svg* svgObj);
		void setWidth(int w);
};
