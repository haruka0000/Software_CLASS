import java.io.PrintStream; // 出力ストリーム

public class Rectangle extends Shape2D{
  private Coord2 v1, v2, v3;
  
  public Rectangle(Coord2 v1, Coord2 v2, Color c){  // コンストラクタ
    super.setColor(c);
    this.v1 = v1;
    this.v2 = v2;
  }

  public Coord2[] getV(){ // Coord2のゲッター
    Coord2[] tmp = {v1, v2};
    return tmp;
  }

  public double perimeter(){
    // 周囲長を計算し、合計する
    return 2 * Math.abs(
                        (Math.abs((v1.getX() - v2.getX()))
                        + Math.abs(v1.getY() - v2.getY()))
                        );
 
  }

  public double area(){
    // 四角形の面積を計算し、戻り値として返す
    return Math.abs(v2.getY() - v1.getX()) * Math.abs(v1.getY() - v2.getY()); 
  }

  public void psPrint(PrintStream cout){
    // 以下、三角形を描画する
    cout.println("newpath");
    
    Color c = super.getColor(); // Colorクラスのオブジェクトを作成
    
    // 四角形を描画
    cout.printf("%f %f moveto\n", v1.getX(), v1.getY());
    cout.printf("%f %f lineto\n", v2.getX(), v1.getY());
    cout.printf("%f %f lineto\n", v2.getX(), v2.getY());
    cout.printf("%f %f lineto\n", v1.getX(), v2.getY());
    cout.printf("%f %f lineto\n", v1.getX(), v1.getY());

    // 色の情報を書出し
    cout.printf("%f %f %f setrgbcolor\n", 
                c.getR(), 
                c.getG(),
                c.getB());

    cout.println("stroke");
    cout.println("closepath");  // 一つの図形の描画終了
  }
}
