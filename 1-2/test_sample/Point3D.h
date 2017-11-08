#include "Point.h"
#pragma once

using namespace std;

class Point3D : public Point{
  private:
    int z;
  public:
    Point3D() : Point(){ z = 0;} 
    ~Point3D(){
      cout << x << ", " << y << ", " << z << "消滅" << endl;
    }
    void set_x(int val){x = val;}
    void set_y(int val){y = val;}
    void set_z(int val){z = val;}

    int val_x(){return x;}
    int val_y(){return y;}
    int val_z(){return z;}

    void disp(){
      cout << x << ", " << y << ", " << z << endl;
    }
};
