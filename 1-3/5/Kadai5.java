import java.io.*; // 入出力
import java.util.ArrayList; // ArrayListを使う

public class Kadai5{
  public static void main(String[] args){
    
    // Food型のArrayListの作成
    ArrayList<Food> foods = new ArrayList<Food>();
    
    String line;  // 行単位の読み込んだ文字列を保持
    String[] d; // line分割して格納する
    String name; // 食べ物の名前
    double carbon = 0;  // 炭水化物含有量
    double fat = 0; // 脂質含有量
    double calorie = 0; // カロリー量
    double[] variables; // 説明変数
    double[] labels;  // 目的変数

    FoodRegression FR;  // FoodRegressionオブジェクト
    
    try{
      File f = new File(args[0]); // 入力ファイル  
      
      // BufferedReaderクラスのオブジェクト生成
      BufferedReader br = new BufferedReader(new FileReader(f));
      
      line = br.readLine();   // 1行目の読み込み(1行目の破棄)
      
      while((line = br.readLine()) != null){
        // lineを分割して配列dに代入
        d = line.split(",", 0);
        name    = d[0];
        carbon  = Double.parseDouble(d[2]);
        fat     = Double.parseDouble(d[4]);
        calorie = Double.parseDouble(d[3]);

        // Food型ArrayListにFood型オブジェクトを作成と同時に追加
        foods.add(new Food(name, carbon, fat, calorie));
      }

      variables = new double[foods.size()]; // サンプルの数で初期化
      labels    = new double[foods.size()]; // サンプルの数で初期化

      for(int i = 0; i < foods.size(); i++){
        Food food = foods.get(i); // 一時的に格納
        variables[i]  = food.getCarbon();   // 炭水化物含有量を説明変数に格納
        labels[i]     = food.getCalorie();  // カロリー量を目的変数に格納
      }
      
      FR = new FoodRegression(variables, labels); // FoodRegressionオブジェクトの初期化
      
      FR.compMean();
      FR.doRegression();
      System.out.println("a (回帰係数)\t= " + FR.getA());
      System.out.println("b (回帰切片)\t= " + FR.getB());
      System.out.println("R2 (寄与率)\t= "  + FR.computeR2());
      
      System.out.println("「落花生」のカロリー予測\t= " + ( FR.getA() * 19.6 + FR.getB() ) );
      System.out.println("「絹豆腐」のカロリー予測\t= " + ( FR.getA() * 2 + FR.getB() ) );
      System.out.println("「しいたけ」のカロリー予測\t= " + ( FR.getA() * 4.9 + FR.getB() ) );
    

      
    }catch (NumberFormatException e){
      System.err.println("サンプル数を適切な数にしてください。");
      System.exit(1);
    }catch (FileNotFoundException e){
      System.err.println("ファイル" + args[1] + "は、見つかりません。");
      System.exit(1);
    }catch (UnsupportedEncodingException e){
      System.err.println("UTF-8符号はサポートされていません。");
      System.exit(1);
    }catch (ArrayIndexOutOfBoundsException e){
      System.err.println("コマンドライン引数の数が適していない可能性が有ります。");
      System.exit(1);
    }catch (Exception e){
      System.err.println("想定外のエラーです。");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
