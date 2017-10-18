#include <iostream>
#include "Point3D.h"

using namespace std;

int main(){
  int size = 50;
  
  Point **data = new Point* [size];
  
  for(int i = 0; i < size; i++){
    data[i] = new Point3D();
    //data[i]->set_x(i);
    //data[i]->set_y(i*2);

    data[i]->disp();
    delete(data[i]);
  } 

  delete(data);
  return(1);
}
