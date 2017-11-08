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


public class Fruit{
    private double vitaminC;
    private int price;
    private int howMany;

    // コンストラクタ
    public Fruit(double vitaminC, int price, int howMany){
      this.vitaminC = vitaminC; // VCの代入
      this.price = price; // priceの代入
      this.howMany = howMany; // 個数の代入
    }
    

    // それぞれの変数を返す
    public double getVitaminC(){
      return vitaminC;
    }

    public int getPrice(){
      return price;
    }

    public int getHowMany(){
      return howMany;
    }
}
