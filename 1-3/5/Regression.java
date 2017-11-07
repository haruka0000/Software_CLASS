public abstract class Regression{
  protected double a; // 係数
  protected double b; // 係数
  protected double R2;  // 寄与率
  protected double xmean; // 説明変数の平均値
  protected double ymean; // 目的変数の平均値
  protected int samples;  // データのサンプル数
  protected double[] variables; // 説明変数
  protected double[] labels;  // 目的変数
  protected double predicted;  // 目的変数の予測値

  public Regression(double[] variables, double[] labels){
    this.variables = variables;
    this.labels = labels;
    this.samples = variables.length();
  }

  public double getA(){
    return this.a;
  }
  
  public double getB(){
    return this.b;
  }

  public abstract double computeR2();

  public abstract double compMean();

  public abstract double doRegression();

}
