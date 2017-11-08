#include "BarGraph.h"

ofstream svg::ofs;

int main(){
  const char* fileName = "Aichi.txt";
  BarGraph bg;
  Population pop;
  pop.read(fileName);
  bg.read(&pop); 
  bg.draw();
  
  return (1);
}
