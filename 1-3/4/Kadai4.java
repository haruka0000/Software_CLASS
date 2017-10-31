import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Random;  // 乱数

public class Kadai4{
  public static void main(String[] args){
    Color color = new Color(0, 0, 1);
    Coord2 v1 = new Coord2();
    v1.setCoord2(100, 100);
    Coord2 v2 = new Coord2();
    v2.setCoord2(200, 100);
    Coord2 v3 = new Coord2();
    v3.setCoord2(200, 200);


    try{
      PrintStream cout = new PrintStream(args[0], "UTF-8"); // プリント出力するファイル名
    
      cout.println("%!");
    

      Triangle tri1 = new Triangle(v1, v2, v3, color);
      tri1.psPrint(cout);
    
      cout.println("showpage");
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
