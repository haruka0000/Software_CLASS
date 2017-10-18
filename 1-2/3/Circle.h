#pragma once
#include <iostream>
#include "svg.h"

class Circle { 
protected:
    int x, y;
    int rad;

public:
    Circle ();
    Circle (int cx, int cy, int r);
    void setPosition (int x, int y);
    void setRadius (int rad);
    int getRadius ();
		void getPosition (int xy[]);
		void draw (svg* svgObj); // 引数はポインタ渡し
		bool checkOverlap (Circle circ);
};

