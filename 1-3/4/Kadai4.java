import java.util.ArrayList;
import java.io.PrintStream;
import java.io.FileNotFoundException; // ファイルが見つからないエラー
import java.io.UnsupportedEncodingException;  // PrintStreamの第2引数の符号が見つからないエラー
import java.util.Random;  // 乱数

public class Kadai4{
  final static int XRANGE = 600;/* x方向のキャンバスサイズ */
  final static int YRANGE = 800;/* y方向のキャンバスサイズ */
  final static int RADIUS = 200;/* 半径の最大値 */
      
  public static void main(String[] args){

    int x1 = 10;  // 描画数の下限
    int x2 = 50;  // 描画数の上限
    
    Coord2 v1, v2, v3;  // 3点の座標を格納するオブジェクト
    double p_x, p_y, rad; // x,y座標と半径

    try{
      
      int n = Integer.parseInt(args[1]);  // 描画数
      
      // Randomクラスのインスタンス化
      Random rnd = new Random();
      
      // 書出し予定の図形情報を格納するArrayListの作成
      ArrayList<Shape2D> shape_list = new ArrayList<Shape2D>();

      // 入力された描画数の判定
      if (!(x1 <= n && n <= x2)){
        System.err.println("図形の総数は" + x1 + "から" + x2 + "の間の数にしてください");
        System.exit(1);
      }

      // 入力された描画数分の図形オブジェクトを作成し、ArrayListに格納
      for(int j = 0; j < n; j++){

        // r,g,b値を0~1でランダム生成
        double r = Math.random();
        double g = Math.random();
        double b = Math.random();

        Color color = new Color(r, g, b); // Colorオブジェクトの生成

        // x,y 座標のランダム生成 (0 <= x <= 600, 0 <= y <= 800)
        p_x = rnd.nextInt(XRANGE) + Math.random();
        p_y = rnd.nextInt(YRANGE) + Math.random();

        // 描画する図形をランダムに決定
        //int shape_num = rnd.nextInt(3); // 0:Circle, 1:Triangle, 2:Rectangle
        int shape_num = 1;

        // 始点の生成
        v1 = new Coord2();
        v1.setCoord2(p_x, p_y);
        
        // 描画する図形に合わせて処理を切り替え
        switch(shape_num){
          
          case 0: // Circle
            /*
            rad = rnd.nextInt(RADIUS) + Math.random(); // 半径の生成
            
            // Circleオブジェクトを生成と同時にshape_listに格納
            shape_list.add(new Circle(v1, v2, v3, color));
            */
            break;
            
          case 1: // Triangle
            // x,y 座標のランダム生成 (0 <= x <= 600, 0 <= y <= 800)
            p_x = rnd.nextInt(XRANGE) + Math.random();
            p_y = rnd.nextInt(YRANGE) + Math.random();
            
            // 2点目の生成
            v2 = new Coord2();
            v2.setCoord2(p_x, p_y);
            
            // x,y 座標のランダム生成 (0 <= x <= 600, 0 <= y <= 800)
            p_x = rnd.nextInt(XRANGE) + Math.random();
            p_y = rnd.nextInt(YRANGE) + Math.random();

            // 3点目の生成
            v3 = new Coord2();
            v3.setCoord2(p_x, p_y);
            
            // Triangleオブジェクトを生成と同時にshape_listに格納
            shape_list.add(new Triangle(v1, v2, v3, color));
            
            break;

          case 2: // Rectangle
            /*
            // x,y 座標のランダム生成 (0 <= x <= 600, 0 <= y <= 800)
            p_x = rnd.nextInt(XRANGE) + Math.random();
            p_y = rnd.nextInt(YRANGE) + Math.random();
            
            // 2点目の生成
            v2 = new Coord2();
            v2.setCoord2(p_x, p_y);
            
            // Rectangleオブジェクトを生成と同時にshape_listに格納
            shape_list.add(new Rectangle(v1, v2, v3, color));
            */
            break;

        }
      }


      // ****** 以下書き出し ******
      
      // PrintStreamオブジェクトの生成
      PrintStream cout = new PrintStream(args[0], "UTF-8"); // プリント出力するファイル名
    
      cout.println("%!");

      // shape_listに格納した図形をpostScriptファイルに書出し
      for(int i = 0; i < n; i++){
        shape_list.get(i).psPrint(cout);
      }
      
      cout.println("showpage"); // 図形の表示(末尾)
      cout.close(); // ファイルを閉じる
		}

		catch (NumberFormatException e){
			System.err.println("引数は[" + x1 + "-" + x2 + "]の間の整数にしてください");
			System.exit(1);
		}
		catch (FileNotFoundException e){
			System.err.println("ファイル" + args[1] + "は、見つかりません");
			System.exit(1);
		}
		catch (UnsupportedEncodingException e){
			System.err.println("UTF-8符号はサポートされていません");
			System.exit(1);
		}
		catch (Exception e){
			System.err.println("想定外のエラーです");
			e.printStackTrace();
			System.exit(1);
		} 
  }
}
