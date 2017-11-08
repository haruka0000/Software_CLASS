import java.util.*;
import java.io.*;
import java.text.Format;    // フォーマット
import java.text.DateFormat;  // 日付フォーマット

class Kadai1{
  public String[] myName = {
    "**************************************************",
    "作成者：",
    "日付：",
    "入力ファイル名：",
    "**************************************************",
  };

  public void myPrint(String name){
    Date now = new Date();
    Format fmt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
    for (int i = 0 ; i < myName.length ; i++){
      System.out.print(myName[i]);
      switch (i) {
        case 2: System.out.print(fmt.format(now)); break; // 日付
        case 3: System.out.print(name); break;  // データファイル名
      }
      System.out.print('\n');
    }
  }

  public static void main(String[] args){
    String fileName = args[0];  // 第一引数
    //TreeMapデータを作成
    TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
    int str_len = 0;  // 行の長さ
    int c;    // 1文字単位の読み込んだ文字列を保持

    String token = "";
    if (args.length != 1){
      System.out.println("java(u) Kadai1[ファイル名]");
      System.exit(0);
    }
    Kadai1 fr = new Kadai1(); // 1文字単位の読み込み
    try {
      fr.myPrint(args[0]);  // ヘッダーをプリント 
      File f = new File(args[0]);

      // BufferedReaderクラスのオブジェクト生成
      BufferedReader br = new BufferedReader(new FileReader(f)); 
      
      boolean alph_flag;  // アルファベット探索用フラッグ
      int strcnt = 0;   // 文字の結合index
      while (true){   // 文字単位で処理
        c = br.read(); // １文字読み出し
        
        if(('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || (c == '\'') || (c == '-')){ // 英字か判定
          // 文頭(strcnt == 0)の時は記号をtokenに結合しない
          if((strcnt != 0) || (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z'))){
            token = token + (char)c;    // 文字を結合する
            token = token.toLowerCase();  // 単語を小文字に変換
            strcnt++;
          }
        }
        else{
          alph_flag = false;  // アルファベットを含むか判定するフラッグ
          for(int i = 'a'; i <= 'z'; i++){  // アルファベットを総当りする
            if(token.indexOf(String.valueOf((char)i)) != -1){   // アルファベットを含むならばフラッグをtrueにする
              alph_flag = true;
            }
          }

          // アルファベットを含まない（記号のみ　例：'--'など）場合はtreeMapに登録しない

          if((token != "") && alph_flag){
            // 単語(token)が既にtreeMapに含まれるか判定
            if(!(tm.containsKey(token))){ // 含まれてなければ登録
              tm.put(token, 1);
            }else{  // 含まれていれば更新
              int c_count = tm.get(token);  // 現在のカウント数を取得(current count)
              tm.put(token, c_count + 1); // TreeMapの値の更新
            }
          }
          token = "";
          strcnt = 0;
        }
        if(c == -1){  // ファイルの最後まで読み終わったら終了
          break;
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();  // エラー処理 :トレース
    }

    int count = 0;  // 総単語数のカウント用変数

    // 拡張for文でtreeMapの持つ全てのデータを出力する
    for (Map.Entry<String, Integer> map : tm.entrySet()) {
      System.out.println(map.getKey() + "\t[" + map.getValue() + "]");
      count++;
    } 
    System.out.println("総単語数は" + count + "です");
  }
}
