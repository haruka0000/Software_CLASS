#include "Population.h"
#include "svg.h"

class BarGraph {
  private:
    int x = 50;
    int y = 50;
    int width = 0;
    int height = 10;
    vector<string> labels;
    vector<int> values;

  public:
    void draw();
    void read(Population*);
};
