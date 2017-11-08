/*
概要：
Regressionクラスの継承クラス。
Foodに関する単回帰の計算を行い、それらを基底クラスのメンバー変数に格納する。
基底クラスの抽象メソッドをオーバーライドしている。
*/

public class FoodRegression extends Regression{
  // 基底クラスのコンストラクタを呼び出す
  public FoodRegression(double[] variables, double[] labels){
    super(variables, labels);
  }


  // xmean, ymeanそれぞれを格納する
  public void compMean(){
    double v_sum = 0; // variablesの和
    double l_sum = 0; // labelsの和
    
    // 和を計算
    for(int i = 0; i < this.samples; i++){
      v_sum += this.variables[i];
      l_sum += this.labels[i];
    }

    // 平均を計算
    this.xmean = v_sum / this.samples;
    this.ymean = l_sum / this.samples;
  }

  // 単回帰の計算を行い、結果を基底クラスのメンバー変数に格納する
  public void doRegression(){
    double Sxx = 0; // xのサンプル平方和
    double Syy = 0; // yのサンプル平方和
    double Sxy = 0; // xとyのサンプル偏差積和
    
    // R2(寄与率)の計算用一時変数
    double Syyyy = 0;
    double Syy_p = 0;
    double pre_sum = 0;
    double pre_mean = 0;
    
    // 平方和、偏差積和を計算
    for(int i = 0; i < samples; i++){
      Sxx += Math.pow((variables[i] - xmean), 2);
      Syy += Math.pow((labels[i] - ymean), 2);
      Sxy += (variables[i] - xmean) * (labels[i] - ymean);
    }
    
    a = Sxy / Sxx;          // 回帰係数aを計算
    b = ymean - a * xmean;  // 回帰切片bを計算

    for(int i = 0; i < samples; i++){
      predicted[i] = a * variables[i] + b;  // 目的変数の予測値(サンプル数個)の計算
      pre_sum += predicted[i];              // 予測値の和(寄与率計算用)
    }
    
    pre_mean = pre_sum / samples;       // 予測値の平均

    for(int i = 0; i < samples; i++){
      Syyyy += (labels[i] - ymean) * (predicted[i] - pre_mean);   // 目的変数と予測値の偏差積和
      Syy_p += Math.pow((predicted[i] - pre_mean), 2);            // 予測値の平方和
    }

    
    R2 = Math.pow(Syyyy, 2) / ( Syy * Syy_p );        // 寄与率R2の計算
  }


  // R2を返す
  public double computeR2(){
    return this.R2;
  }
}
