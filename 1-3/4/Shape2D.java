import java.io.PrintStream; // 出力ストリーム

public abstract class Shape2D{
  private Color color;  // 色オブジェクトを持つ

  public void setColor(Color c){  //  Color変数に値をセット
    this.color = c;
  }

  public Color getColor(){  // 戻り値としてColor変数を返す
    return color;
  }

  abstract double area();

  abstract double perimeter();

  abstract void psPrint(PrintStream cout);
}
