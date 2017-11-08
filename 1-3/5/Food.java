/*
概要：
食べ物クラス。
food.csvから読み込んだ各食べ物の情報を格納するクラス。
メンバー変数はprivateで、それぞれのゲッターを備える。
*/
public class Food{
  private String name;  // 食べ物の名前
  private double carbon;  // 炭水化物含有量
  private double fat; // 脂質含有量
  private double calorie; // カロリー量

  // コンストラクタ
  public Food(){
  }
  
  // 引数からメンバー変数に代入する
  public Food(String name, double carbon, double fat, double calorie){
    this.name = name;
    this.carbon = carbon;
    this.fat = fat;
    this.calorie = calorie;
  }

  // 以下、各メンバー変数のゲッター
  public String getName(){
    return this.name;
  }
  
  public double getCarbon(){
    return this.carbon;
  }

  public double getFat(){
    return this.fat;
  }

  public double getCalorie(){
    return this.calorie;
  }

}
