public class FoodRegression extends Regression{
  public FoodRegression(double[] variables, double[] labels){
    super(variables, labels);
  }

  public void compMean(){
    double v_sum = 0; // variablesの和
    double l_sum = 0; // labelsの和
    double[] mean = new double[2];
    
    for(int i = 0; i < this.samples; i++){
      v_sum += this.variables[i];
      l_sum += this.labels[i];
    }
    mean[0] = v_sum / this.samples;
    mean[1] = l_sum / this.samples;
    
    this.xmean = mean[0];
    this.ymean = mean[1];
  }


  public void doRegression(){
    double Sxx = 0; // xのサンプル平方和
    double Syy = 0; // yのサンプル平方和
    double Sxy = 0; // xとyのサンプル偏差積和
    
    // R2(寄与率)の計算用一時変数
    double Syyyy = 0;
    double Syy_p = 0;
    double pre_sum = 0;
    double pre_mean = 0;

    for(int i = 0; i < samples; i++){
      Sxx += Math.pow((variables[i] - xmean), 2);
      Syy += Math.pow((labels[i] - ymean), 2);
      Sxy += (variables[i] - xmean) * (labels[i] - ymean);
    }
    
    a = Sxy / Sxx;
    b = ymean - a * xmean;

    for(int i = 0; i < samples; i++){
      predicted[i] = a * variables[i] + b;  // 目的変数の予測値(サンプル数個)の計算
      pre_sum += predicted[i];  // 予測値の和(寄与率計算用)
    }
    
    pre_mean = pre_sum / samples; // 予測値の平均

    for(int i = 0; i < samples; i++){
      Syyyy += (labels[i] - ymean) * (predicted[i] - pre_mean); // 目的変数と予測値の偏差積和
      Syy_p += Math.pow((predicted[i] - pre_mean), 2);   // 予測値の平方和
    }

    R2 = Math.pow(Syyyy, 2) / ( Syy * Syy_p );
  }

  public double computeR2(){
    return this.R2;
  }
}
