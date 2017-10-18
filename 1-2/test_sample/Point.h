#pragma once

class Point{
  protected:
    int x;
    int y;
  public:
    Point(){x = y = 0;}
    ~Point(){
      std::cout << x << "の基底が消滅" << std::endl;
    }
    virtual void disp(){
      std::cout << x << ", " << y << std::endl;
    }
};
