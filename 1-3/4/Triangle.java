import java.io.PrintStream; // 出力ストリーム

public class Triangle extends Shape2D{
  private Coord2 v1, v2, v3;
  
  public Triangle(Coord2 v1, Coord2 v2, Coord2 v3, Color c){  // コンストラクタ
    super.setColor(c);
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
  }

  public Coord2[] getV(){ // Coord2のゲッター
    Coord2[] tmp = {v1, v2, v3};
    return tmp;
  }

  public double perimeter(){
    // 各点間のユークリッド距離を計算し、合計する
    return Coord2.distance(v1, v2) + Coord2.distance(v1, v3) + Coord2.distance(v2, v3);
  }

  public double area(){
    // 三角形の面積を計算し、戻り値として返す
    return Math.abs(
            Math.abs(v1.getX() - v3.getX()) * Math.abs(v2.getY() - v3.getY()) 
            - Math.abs(v2.getX() - v3.getX()) * Math.abs(v1.getY() - v3.getY())
           ) / 2;
  }

  public void psPrint(PrintStream cout){
    // 以下、三角形を描画する
    cout.println("newpath");
    Color c = super.getColor(); // Colorクラスのオブジェクトを作成
    cout.printf("%f %f moveto\n", v1.getX(), v1.getY());  // 書き始めの位置に移動
    cout.printf("%f %f lineto\n", v2.getX(), v2.getY());  // 始点からv2の座標まで、直線を描画
    cout.printf("%f %f lineto\n", v3.getX(), v3.getY());  // v2の座標からv3の座標まで、直線を描画
    cout.printf("%f %f lineto\n", v1.getX(), v1.getY());  // v3の座標から始点まで、直線を描画
    
    // 色の情報を書出し
    cout.printf("%f %f %f setrgbcolor\n", 
                c.getR(), 
                c.getG(),
                c.getB());
    cout.println("stroke");
    cout.println("closepath");  // 一つの図形の描画終了
  }
}
