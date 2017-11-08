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


public class Strawberry extends Fruit{
  final static int weight = 13;  // 1個あたりの重量
  final static double vitaminC = 62.0 * (weight/100.0);  // 1個あたりのVC量
  final static int price = 40; // 1個あたりの価格

  // 基底クラスのコンストラクタの呼び出し(個数を受け取る)
  public Strawberry(int amount){
    super(Strawberry.vitaminC, Strawberry.price, amount);
  }
}
