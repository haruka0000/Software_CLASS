import java.io.*;
import java.util.*;
import java.text.Format;  //フォーマットのため
import java.text.DateFormat;  // 日付のフォーマット用

public class Matrix{
  public String[] myName = {  // プリントヘッダー
    "******************************",
    "作成者：",
    "日付：",
    "入力ファイル名：",
    "******************************"
  };
  
  public void myPrint(String[] files){ // mainの最初に呼ばれる
    Date now = new Date();  // Dateクラスのオブジェクト生成
    Format fmt= DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);  // 日付を日本語ロカールに従ってフォーマット
    for (int i = 0 ; i < myName.length ; i++){
      System.out.print(myName[i]);
      switch (i) {
        case 2: System.out.print(fmt.format(now)); break; // 日付
        case 3: System.out.print(files[0] + ", " + files[1]); break;  // データファイル名
      }
        System.out.print('\n');
    }
  } 


  private int row;  // 行数
  private int col;  // 列数

  private double[][] m; // 行列

  
  public int getRow(){
    return row;
  }

  public int getCol(){
    return col;
  }

  // ファイルを読み込み、行列に値を格納
  public static Matrix read(String filename){
    Matrix mat = new Matrix();  // Matrix型オブジェクトの初期化
    try {
      // BufferedReaderクラスのオブジェクト生成
      BufferedReader br = new BufferedReader(new FileReader(filename));

      String line;/* 行単位の読み込んだ文字列を保持 */
      line = br.readLine();   // 1行目の読み込み
      // 1行目を分割して配列row_colに代入
      String[] row_col = line.split(" ", 0);
      
      // row, colに値を代入
      mat.row = Integer.parseInt(row_col[0]);
      mat.col = Integer.parseInt(row_col[1]);

      // 3 <= M,N,K <= 10を満たしていなければ終了 
      if ((mat.row < 3) || (mat.row > 10)){
        System.out.println("【ERROR】行列が 3 <= M,N,K <= 10 の条件を満たしていません");
        System.exit(0);
      }

      // 行列mを初期化
      mat.m = new double[mat.row][mat.col];

      int i = 0;
      int j = 0;
      while ((line = br.readLine()) != null){ // 行単位で処理
        for(String str_d: line.split(" ", 0)){  // 1行をスペースで分割して各行（配列）に追加
          mat.m[i][j] = Double.parseDouble(str_d);  // String型からDouble型に変換
          j++;
        }
        j = 0;
        i++;
      }
    }
    catch(Exception e){
      e.printStackTrace();/* エラー処理 :トレース*/
    }

    return mat; // ファイルを読み込んだオブジェクトを返す
  } 

  // 行列の中身を全て表示する
  public void print(){
    for(int i = 0; i < getRow(); i++){
      System.out.print("| ");
      for(int j = 0; j < getCol(); j++){
        System.out.printf("%7f ", m[i][j]); // 小数点以下7桁で表示
      }
      System.out.println("|");
    }
  }
  
  // 行列の積を計算して返す
  public Matrix multiply(Matrix B){
    // Kが一致していない場合、計算できないと表示し終了する
    if(getCol() != B.getRow() && getRow() != B.getCol()){
      System.out.println("【ERROR】Kが一致しておらず計算できません");
      System.exit(0);
    }
    
    
    Matrix ANS = new Matrix();  // 計算結果を格納するオブジェクトを初期化
    ANS.row = getRow(); // Aの行を取得
    ANS.col = B.getCol(); // Bの列数を取得
    ANS.m = new double[getRow()][B.getCol()]; // 配列の初期化
    
    // 行列の積の計算
    for(int i = 0; i < getRow(); i++){
      for(int j = 0; j < B.getCol(); j++){
        for(int k = 0; k < getCol(); k++){
          ANS.m[i][j] = ANS.m[i][j] + m[i][k] * B.m[k][j];
        }
      }
    }

    return ANS; // 答えの行列を持つオブジェクトを返す
  }

  // メイン
  public static void main(String[] args){
    // 作成者、日時、入力ファイルを表示するmyPrint()用のオブジェクト
    Matrix mat = new Matrix();
    mat.myPrint(args); // 作成者等を表示

    Matrix A = new Matrix().read(args[0]);  // ファイルAを読み込み、オブジェクトの初期化
    Matrix B = new Matrix().read(args[1]);  // ファイルBを読み込み、オブジェクトの初期化
    Matrix C = A.multiply(B); // 行列の積を計算し、オブジェクトを格納

    // 結果の表示
    System.out.println("入力行列 A, サイズ：(" + A.getRow() + ", " + A.getCol() + ")");
    A.print();
    System.out.println("入力行列 B, サイズ：(" + B.getRow() + ", " + B.getCol() + ")");
    B.print();
    System.out.println("出力の積行列 C = A x B, サイズ：(" + C.getRow() + ", " + C.getCol() + ")");
    C.print();
  }
}
