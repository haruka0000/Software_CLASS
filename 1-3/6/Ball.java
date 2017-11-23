/*

概要：
ボールに関する情報をもつクラスである。
初期化される際に、ボールのステータス(半径rad, 座標x,y ベクトルdx,dy RGB値r,g,b)
をランダムで生成する。
ボールの座標の更新を行うmoveメソッドをもつ。
ボールのスピードは40で固定する。
*/
import java.util.Random;  // 乱数

public class Ball{
  // 速度
  final static double speedX = 40;
  final static double speedY = 40;
  
  Random  rnd;    // Randomオブジェクト
  float   rad;    // 半径
  float   r;      // Red
  float   g;      // Green
  float   b;      // Blue
  double  x;      // x 座標
  double  y;      // y 座標
  double  dx;     // ベクトル x
  double  dy;     // ベクトル y
  double  lastx;  // 直前の x 座標
  double  lasty;  // 直前の y 座標

  // コンストラクタ
  public Ball(){
    // Randomクラスのインスタンス化
    rnd = new Random();
  
    // 0〜80.0 の乱数で半径を指定
    rad   = (float)(rnd.nextInt(80) + Math.random());
    r     = (float)Math.random();             // Redの値を乱数(0~1.0)で指定
    g     = (float)Math.random();             // Greenの値を乱数(0~1.0)で指定
    b     = (float)Math.random();             // Blueの値を乱数(0~1.0)で指定
    x     = rnd.nextInt(400) + Math.random(); // x 座標の初期値を乱数(0~400.0)で指定
    y     = rnd.nextInt(400) + Math.random(); // y 座標の初期値を乱数(0~400.0)で指定
    lastx = x;                                // 直前の x 座標(始めは x 座標の初期値と同じ)
    lasty = y;                                // 直前の y 座標(始めは y 座標の初期値と同じ)
    dx    = 2 * Math.random() - 1;            // ベクトル x の値を乱数(-1.0〜1.0)で指定
    dy    = 2 * Math.random() - 1;            // ベクトル y の値を乱数(-1.0〜1.0)で指定
  }
  

  // ボールの動き
  public void move(java.awt.Rectangle bounds){
    // ウィンドウの端に当たれば、符号を反転する
    if (x - rad + speedX * dx < 0 || x + rad + speedX * dx > bounds.width){
      dx = -dx;
    } 
    if (y - rad + speedY * dy < 0 || y + rad + speedY * dy > bounds.height){
      dy = -dy;
    }
    
    // 座標の更新
    x = lastx + speedX * dx;
    y = lasty + speedY * dy;
    
    // 座標の保存
    lastx = x;
    lasty = y;
  }
}

