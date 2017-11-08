#pragma once
#include "ColorCircle.h"

class DoubleCircle : public ColorCircle {
	protected:
		int ratio;	// 幅
    std::string color2;

	public:
		DoubleCircle();
		DoubleCircle(int cx, int cy, int r, std::string c, std::string c2, int ra);
		
		virtual void draw(svg*);
		void setRatio(int ra);
    void setColor2(std::string c2);
};
