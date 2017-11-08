import java.util.Date;        // 日付
import java.text.Format;      // フォーマットのため
import java.text.DateFormat;  // 日付のフォーマット用
import java.lang.ArrayIndexOutOfBoundsException;  // index系エラー
import java.io.FileNotFoundException;             // ファイルが見つからないエラー
import java.io.*;             // 入出力
import java.util.ArrayList;   // ArrayListを使う

public class Kadai5{
  public static void main(String[] args){
    Date now = new Date();  // Dateクラスのオブジェクト生成
    
    // 日付を日本語ロカールに従ってフォーマット
    Format fmt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);

    // Food型のArrayListの作成
    ArrayList<Food> foods = new ArrayList<Food>();
    
    String line;        // 行単位の読み込んだ文字列を保持
    String[] d;         // line分割して格納する
    String name;        // 食べ物の名前

    double carbon = 0;    // 炭水化物含有量
    double fat = 0;       // 脂質含有量
    double calorie = 0;   // カロリー量
    
    double[] variables;   // 説明変数
    double[] labels;      // 目的変数
    
    String x;               // 説明変数の選択用
    String selected = "";   // 選ばれた説明変数
    
    FoodRegression FR;    // FoodRegressionオブジェクト
    
    try{
      x = args[1]; // 説明変数決定  ( "C" or "F" )

      File f = new File(args[0]); // 入力ファイル  
      
      // BufferedReaderクラスのオブジェクト生成
      BufferedReader br = new BufferedReader(new FileReader(f));
      
      line = br.readLine();   // 1行目の読み込み(1行目の破棄)
      
      while((line = br.readLine()) != null){
        
        // lineを分割して配列dに代入
        d = line.split(",", 0);
        
        name    = d[0];

        // double型に変換して格納
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
        
        // コマンドライン第二引数に応じて変更
        switch(x){
          case "C":
            variables[i]  = food.getCarbon();   // 炭水化物含有量を説明変数に格納
            selected = "炭水化物";
            break;
          
          case "F":
            variables[i]  = food.getFat();   // 脂質含有量を説明変数に格納
            selected = "脂質";
            break;
        }

        labels[i]     = food.getCalorie();  // カロリー量を目的変数に格納
      }
      
      FR = new FoodRegression(variables, labels); // FoodRegressionオブジェクトの初期化
      
      FR.compMean();      // xmeanとymeanを計算
      FR.doRegression();  // 単回帰を計算

      // 作成者情報と日付の出力
      System.out.println("**************************************");
      System.out.println("課題5：名前，学籍番号");
      System.out.println("日付：" + fmt.format(now));
      System.out.println("内容：カロリーを" + selected + "で単回帰した場合");
      System.out.println("**************************************");

      // 回帰分析に関する情報の表示
      System.out.println("a (回帰係数)\t= " + FR.getA());
      System.out.println("b (回帰切片)\t= " + FR.getB());
      System.out.println("R2 (寄与率)\t= "  + FR.computeR2());
      
      // コマンドライン第二引数に応じて変更
      switch(x){
        case "C":
          // 炭水化物含有量からカロリーの予測(計算)
          System.out.println("「落花生」のカロリー予測\t= " + ( FR.getA() * 19.6 + FR.getB() ) );
          System.out.println("「絹豆腐」のカロリー予測\t= " + ( FR.getA() * 2 + FR.getB() ) );
          System.out.println("「しいたけ」のカロリー予測\t= " + ( FR.getA() * 4.9 + FR.getB() ) );
          break;

        case "F":
          // 脂質からカロリーの予測(計算)
          System.out.println("「落花生」のカロリー予測\t= " + ( FR.getA() * 49.4 + FR.getB() ) );
          System.out.println("「絹豆腐」のカロリー予測\t= " + ( FR.getA() * 3 + FR.getB() ) );
          System.out.println("「しいたけ」のカロリー予測\t= " + ( FR.getA() * 0.4 + FR.getB() ) );
          break;
      }

    }catch (FileNotFoundException e){
      System.err.println("ファイル" + args[0] + "は、見つかりません。");
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
