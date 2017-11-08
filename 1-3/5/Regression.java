/*
概要：
抽象回帰クラス。
FoodRegressionクラスの基底クラスであり、単回帰計算に用いるメンバー変数をもつ。
抽象メソッドとして、compMean(), computeR2(), doRegression()メソッドを備える。
また、係数aと切片bのゲッターも備える。
*/
public abstract class Regression{
  protected double a;           // 係数
  protected double b;           // 切片
  protected double R2;          // 寄与率
  protected double xmean;       // 説明変数の平均値
  protected double ymean;       // 目的変数の平均値
  protected int samples;        // データのサンプル数
  protected double[] variables; // 説明変数
  protected double[] labels;    // 目的変数
  protected double[] predicted; // 目的変数の予測値

  // 基底クラスのコンストラクタ
  public Regression(double[] variables, double[] labels){
    // 引数から各値を代入
    this.variables = variables;
    this.labels = labels;
    this.samples = variables.length;
    this.predicted = new double[samples];
  }

  public double getA(){
    return this.a;
  }
  
  public double getB(){
    return this.b;
  }

  public abstract double computeR2();

  public abstract void compMean();

  public abstract void doRegression();

}
