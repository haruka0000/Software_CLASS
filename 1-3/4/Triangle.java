import java.io.PrintStream; // 出力ストリーム

public class Triangle extends Shape2D{
  private Coord2 v1, v2, v3;
  
  public Triangle(Coord2 v1, Coord2 v2, Coord2 v3, Color c){
    super.setColor(c);
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
  }

  public Coord2[] getV(){
    Coord2[] tmp = {v1, v2, v3};
    return tmp;
  }

  public double perimeter(){
    // 各点間のユークリッド距離を計算し、合計する
    return Coord2.distance(v1, v2) + Coord2.distance(v1, v3) + Coord2.distance(v2, v3);
  }

  public double area(){
    return (
            (v1.getX() - v3.getX()) * (v2.getY() - v3.getY()) 
            - (v2.getX() - v3.getX()) * (v1.getY() - v3.getY())
           ) / 2;
  }

  public void psPrint(PrintStream cout){
    cout.println("newpath");
    Color c = super.getColor();
    cout.printf("%f %f moveto\n", v1.getX(), v1.getY());
    cout.printf("%f %f lineto\n", v2.getX(), v2.getY());
    cout.printf("%f %f lineto\n", v3.getX(), v3.getY());
    cout.printf("%f %f lineto\n", v1.getX(), v1.getY());
    cout.printf("%f %f %f setrgbcolor\n", 
                c.getR(), 
                c.getG(),
                c.getB());
    cout.println("stroke");
    cout.println("closepath");
  }
}
