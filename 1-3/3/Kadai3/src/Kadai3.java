/*
作成者：
学籍番号：
提出日：
概要：
  りんご、いちご、グレープフルーツの3つのフルーツの個数を入力し、
  それらの総額及びビタミンC総含有量を出力するプログラムである。
  基底クラスをFruit、派生クラスをApple、Strawberry、GrapefruitとしてArrayListを用いて、
  実装する。
  それぞれの購入数は必ず1以上とする。
  異なる2種類以上のパラメータを実行する。
*/


import java.io.*;
import java.util.*;
import java.text.Format;  //フォーマットのため
import java.text.DateFormat;  // 日付のフォーマット用

public class Kadai3{
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
        case 3: 
          for(int j = 0; j < files.length; j++){
            System.out.print(files[j]);
            if(j >= (files.length - 1)){
              System.out.print(", ");
            } 
          }
          break;  // データファイル名
      }
      System.out.print('\n');
    }
  }


  public static void main(String[] args){
    // ヘッダー出力
    Kadai3 k3 = new Kadai3();
    k3.myPrint(args);
    
    // FruitのArrayListを宣言
    ArrayList <Fruit> fruits = new ArrayList<Fruit>();
    
    int amount = 0;
    
    // input.txtがコマンドライン引数で与えられた場合
    if(args.length != 0 && args[0].indexOf(".txt") != -1){
      // BufferedReaderクラスのオブジェクト生成

      try{
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
      
        amount = Integer.parseInt(br.readLine());  // 1行目の読み込み
        // 個数を引数として渡してオブジェクトを作成すると同時にArrayListに追加
        fruits.add(new Apple(amount));
        System.out.println("りんご " + amount + "[個]");
        
        amount = Integer.parseInt(br.readLine());  // 2行目の読み込み
        // 個数を引数として渡してオブジェクトを作成すると同時にArrayListに追加
        fruits.add(new Strawberry(amount));
        System.out.println("いちご " + amount + "[個]");  
        
        amount = Integer.parseInt(br.readLine());  // 3行目の読み込み
        // 個数を引数として渡してオブジェクトを作成すると同時にArrayListに追加
        fruits.add(new Grapefruit(amount));
        System.out.println("グレープフルーツ " + amount + "[個]");

      }catch(Exception e){
        e.printStackTrace();  // エラー処理 :トレース
      }

    }else{    // コマンドライン引数が無い場合
      Scanner input = new Scanner( System.in ); // System.in（端末から）
      
      System.out.println("各果物の個数を入力してください");
      do{
        System.out.print("りんご[個] >> ");
        amount = input.nextInt(); // 第一引数を整数として読込む (nextInt関数)

        // 1個以上買うならばAppleオブジェクトを作成し、代入する
        if(amount != 0){
          // 個数を引数として渡してオブジェクトを作成すると同時にArrayListに追加
          fruits.add(new Apple(amount));
          break;
        }
      }while(true);

      do{
        System.out.print("いちご[個] >> ");
        amount = input.nextInt(); // 第一引数を整数として読込む (nextInt関数)
        // 1個以上買うならばStrawberryオブジェクトを作成し、代入する
        if(amount != 0){
          // 個数を引数として渡してオブジェクトを作成すると同時にArrayListに追加
          fruits.add(new Strawberry(amount));
          break;
        }
      }while(true);

      do{
        System.out.print("グレープフルーツ[個] >> ");
        amount = input.nextInt(); // 第一引数を整数として読込む (nextInt関数)
        // 1個以上買うならばGrapefruitオブジェクトを作成し、代入する
        if(amount != 0){
          // 個数を引数として渡してオブジェクトを作成すると同時にArrayListに追加
          fruits.add(new Grapefruit(amount));
          break;
        }
      }while(true);
    }

    double total_vitaminC = 0.0;  // ビタミンC総含有量
    int total_price = 0;    // 総額
    
    System.out.println("-----------------------------------------");

    for(int i = 0; i < fruits.size(); i++){
      Fruit f = fruits.get(i);
      // ビタミンC総含有量の計算
      total_vitaminC += f.getVitaminC() * f.getHowMany();
      // 総額の計算
      total_price += f.getPrice() * f.getHowMany();
    }


    System.out.printf("ビタミンC総含有量 = %.3fmg\n", total_vitaminC);
    System.out.println("総額 = " + total_price + "円");
  }
}
