import java.io.PrintStream; // 出力ストリーム

public class Circle extends Shape2D{
  private Coord2 v1;  // 中心座標
  private double rad; // 半径

  public Circle(Coord2 v1, double rad,Color c){
    super.setColor(c);
    this.v1 = v1;
    this.rad = rad;
  }

  public double getR(){
    // 半径radを返す
    return this.rad;
  }

  public Coord2[] getV(){ // Coord2のゲッター
    Coord2[] tmp = {v1};
    return tmp;
  }

  public double perimeter(){
    // 円周を計算する
    return 2 * getR() * Math.PI;
  }

  public double area(){
    // 三角形の面積を計算し、戻り値として返す
    return getR() * getR() * Math.PI;
  }

  public void psPrint(PrintStream cout){
    // 以下、三角形を描画する
    cout.println("newpath");
    Color c = super.getColor(); // Colorクラスのオブジェクトを作成
    
    // 円の描画
    cout.printf("%f %f %f %d %d arc\n", v1.getX(), v1.getY(), getR(), 0, 360);

    // 色の情報を書出し
    cout.printf("%f %f %f setrgbcolor\n", 
                c.getR(), 
                c.getG(),
                c.getB());
    cout.println("stroke");
    cout.println("closepath");  // 一つの図形の描画終了
  }
}
