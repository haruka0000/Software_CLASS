#include "RingCirclesException.h"
#include <math.h>

template <class T> 
class RingCircles{
  private:
    std::vector<T *>circles;  // 環状に配置される円の要素
    int x, y; // この円の相対的座標
    int r;

  public:
    RingCircles(int n) throw(RingCirclesException){
      if(n < 4){
        throw RingCirclesException("Error in RingCircles ... the number of circles is too small!");
      }else if(n > 100){
        throw RingCirclesException("Error in RingCircles ... the number of circles is too large!!");
      }else{
        for(int i = 0; i < n; i++){
          circles.push_back(new T());
        }
      }
    } //構築子：構築する円の個数が引数
    
    void setPosition(int x, int y){
      double deg = 360.0 / circles.size(); // 角度の計算
      double rad = deg * M_PI / 180.0;
      int s_x,s_y;
      this->x = x;
      this->y = y;

      for(int i = 0; i < circles.size(); i++){
        rad = i * deg * M_PI / 180.0;	// 角度からラジアンに変更
        s_x = x + this->r * cos(rad);
        s_y = y + this->r * sin(rad);

        circles[i]->setPosition(s_x, s_y);
      }
    }
    
    void setRadius(int r){
      this->r = r;
      int xy1[2];
      int xy2[2];
      int s_r =  r * sin(M_PI / circles.size());

      for(int i = 0; i < circles.size(); i++){
        circles[i]->setRadius(s_r);
      }
    }

    void draw(svg *svgObj){
      for(int i = 0; i < circles.size(); i++){
        circles[i]->draw(svgObj);
      }
    }
};
