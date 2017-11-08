#include <iostream>
#include "Population.h"

int main() {
    Population popA, popZ;
    popA.read("./Aichi.txt");
    popZ.read("./Zenkoku.txt");
    popA % popZ;
    int maxN = ! popA;
    std::string maxLabel = popA ^ maxN; 
    std::cout << maxLabel << std::endl;
}
