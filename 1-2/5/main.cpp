#include <math.h>
#include <stdlib.h>
#include <vector>
#include "DoubleCircle.h"
#include "LineCircle.h"
#include "RingCircles.h"

int main (int argc, char* argv[]) {
  int area[2] = {1280,400};
  int num_circle = atoi(argv[1]);
  int rad = 100;
  int sub_rad =  rad * sin(M_PI / num_circle);
  svg svgObj;
  int offsetx = (rad+sub_rad)*2;
  try{
    RingCircles<ColorCircle> cc(num_circle);
    RingCircles<LineCircle> lc(num_circle);
    RingCircles<DoubleCircle> dc(num_circle);

    cc.setRadius(rad);
    cc.setPosition(area[0]/2-offsetx, area[1]/2);

    lc.setRadius(rad);
    lc.setPosition(area[0]/2, area[1]/2);

    dc.setRadius(rad);
    dc.setPosition(area[0]/2+offsetx, area[1]/2);


    svgObj.open("RingCircles.html", area[0], area[1]);
    cc.draw(&svgObj);
    dc.draw(&svgObj);
    lc.draw(&svgObj);
    svgObj.close();
  }
  catch(RingCirclesException rce){
    rce.printStackTrace();
    return(0);
  }

  return (1);
}
