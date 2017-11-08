#include <stdio.h>

class Circle { 
private:
    int x, y;
    int rad;

public:
    Circle (); // 関数の内容は記述しない
    Circle (int cx, int cy, int r = 10);
    void setPosition (int x, int y);
    void setRadius (int rad);
    int getRadius ();
		void getPosition (int xy[]);
    void draw ();
		bool checkOverlap (Circle circ);
};

