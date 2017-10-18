#include "BarGraph.h"

void BarGraph::draw (){
  svg::open("graph.html", 2000, 1000);
  for(int i = 0; i < labels.size(); i++){
    svg::drawText(x, y+i*20, labels[i]);
    svg::drawRect(x+100, y+i*20-height, values[i], height);
  }
  svg::close();
}

void BarGraph::read(Population* pop) { 
  labels = pop->getLabels();
  values = pop->getValues();
}
