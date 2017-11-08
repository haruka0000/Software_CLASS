public class Color{
  private double r,g,b; // Red, Green, Blue, 0.0 <= r,g,b <= 1.0

  public Color(double r, double g, double b){
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public double getR(){
    return this.r;
  }
  
  public double getG(){
    return this.g;
  }
  
  public double getB(){
    return this.b;
  }
}
