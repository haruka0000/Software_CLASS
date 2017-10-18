import java.io.*;

public class Matrix{
  private int row;
  private int col;

  private double[][] m;
 
  public int getRow(){
    return row;
  }

  public int getCol(){
    return col;
  }

  public static Matrix read(String filename){
    Matrix mat = new Matrix();
    try {
      /* BufferedReaderクラスのオブジェクト生成 */
      BufferedReader br = new BufferedReader(new FileReader(filename));

      String line;/* 行単位の読み込んだ文字列を保持 */
      line = br.readLine();   // 1行目の読み込み
      // 1行目を分割して配列row_colに代入
      String[] row_col = line.split(" ", 0);
      
      // row, colに値を代入
      mat.row = Integer.parseInt(row_col[0]);
      mat.col = Integer.parseInt(row_col[1]);

      // 行列mを初期化
      mat.m = new double[mat.row][mat.col];

      int i = 0;
      int j = 0;
      while ((line = br.readLine()) != null){ // 行単位で処理
        //System.out.println(line);
        for(String str_d: line.split(" ", 0)){  // 分割して各行に追加
          mat.m[i][j] = Double.parseDouble(str_d);
          j++;
        }
        j = 0;
        i++;
      }
    }
    catch(Exception e){
      e.printStackTrace();/* エラー処理 :トレース*/
    }
    return mat;
  } 

  public void print(){
    for(int i = 0; i < row; i++){
      System.out.print("| ");
      for(int j = 0; j < col; j++){
        System.out.print(m[i][j] + "\t");
      }
      System.out.println("| ");
    }
  }
  
  public Matrix multiply(Matrix B){
    if(col != B.row && row != B.col){
      System.out.println("計算できません");
    }
    System.out.println(row);
    System.out.println(B.col);
    
    Matrix ANS = new Matrix();
    ANS.row = row;
    ANS.col = B.col;
    ANS.m = new double[row][B.col];
    
    for(int i = 0; i < row; i++){
      for(int j = 0; j < B.col; j++){
        for(int k = 0; k < col; k++){
          ANS.m[i][j] = ANS.m[i][j] + m[i][k] * B.m[k][j];
        }
      }
    }
    System.out.println(ANS.m[0][0]);

    return ANS;
  }
  public static void main(String[] args){
    Matrix A = new Matrix().read(args[0]);
    Matrix B = new Matrix().read(args[1]);
    Matrix C = A.multiply(B);
    System.out.println("入力行列, サイズ(" + A.getRow() + ", " + A.getCol() + ")");
    A.print();
    System.out.println("入力行列, サイズ(" + B.getRow() + ", " + B.getCol() + ")");
    B.print();
    System.out.println("入力行列, サイズ(" + C.getRow() + ", " + C.getCol() + ")");
    C.print();
  }
}
/* 
    getRow(), getCol()に直す
    名前の出力部分を作成する
*/
