/*

概要：
10〜30個のボールがウィンドウ内を移動するアニメーションを描画するプログラムである。
キーボード入力に応じて停止(sキー)、再開(rキー)、終了(Escキー)の動作をする。
このクラスはThread処理、描画処理、mainを含むクラスである。
RunnableとKeyListenerの2つのインタフェースを実装している。
実行時にコマンドライン引数で与えられた数のボールをBallクラスの情報より、描画する。
また、キーボード入力に応じたThreadの稼働と停止に関する処理を行う。
*/
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.KeyListener;/* キーボードからのイベントのリスナー */
import java.awt.event.KeyEvent;/* キーボードからのイベント */

public class Kadai6 extends JFrame implements Runnable, KeyListener {
	final static int sleepTime = 50;	// 休止時間（ミリ秒）
  
  // オフスクリーン画像用のデータ
	Thread thread         = null; // スレッド用データ
	Thread saveThread     = null; // セーブスレッド用データ
	Image offScreenImage  = null; // 前景オフスクリーン用データ
	Image backGroundImage = null; // 背景オフスクリーン用データ
	Graphics backG        = null; //背景グラフィクス
	Graphics offG         = null; // 前景グラフィクス
	Graphics saveOffG     = null; // 前景グラフィクスのセーブ（再開で使用）
	Graphics2D offG2      = null; // 前景グラフィクスGraphics2D
	private int w;                // 横幅
	private int h;                // 縦幅

  int numBalls;                 // ボールの数
  Ball[] balls      = null;     // ボールオブジェクトを格納する配列
  Ball[] saveBalls  = null;     // セーブ時のボールオブジェクトを格納する配列
  
  java.awt.Color backColor;     // 背景色

  // コンストラクタ
	public Kadai6(String name, int numBalls){
    super(name);  // ラベル名
    // ボールの数が10〜30個でなければ終了する
    if(numBalls >= 10 && numBalls <= 30){
      this.numBalls = numBalls; // ボール数を代入
    }else{
      System.out.println("ボールの数を10〜30個で指定してください");
      System.exit(1);
    }
  }


  // 終了する
	public void quit(){
    System.exit(0);
  }
	
  // 以下の３つの関数はキーボードのイベントを取得するために宣言が必要
	public void keyPressed(KeyEvent e){   // キーが押された時のイベント
	  if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
      quit();   // VK_ESCAPEはキーボードのEscapeボタン
    }
	  else if (e.getKeyCode()==KeyEvent.VK_S) { // ストップ
		  stop();
	  }
	  else if (e.getKeyCode()==KeyEvent.VK_R) { // Rキー：リズーム（再開）
		  resume();
	  }
	}

	public void keyReleased(KeyEvent e){
  }   // キーがリリースされたとき
	
  
  public void keyTyped(KeyEvent e){
  }   // キーがタイプされたとき

	// 全体の初期設定
  public void init(){
    java.awt.Color backColor = new java.awt.Color(1.0f,1.0f,1.0f);  // 背景色
    
    balls     = new Ball[numBalls];   // ボール数で初期化
    saveBalls = new Ball[numBalls];   // ボール数で初期化

    // 指定数のボールを生成
    for (int i = 0 ; i < numBalls ; i++){
      balls[i] = new Ball(); 
    }

    saveBalls = balls;  // ボールを保存

	  Dimension d = getSize();  // ウィンドウサイズ獲得
	  w = d.width;              // 横幅セット
	  h = d.height;             // 縦幅セット

	  // オフスクリーン（前面）画像用のデータ領域
	  offScreenImage = createImage(w,h);                // 前面オフスクリーン領域生成
	  offG  = saveOffG = offScreenImage.getGraphics();   // 前面オフスクリーン描画用オブジェクト獲得
	  offG2 = (Graphics2D) offG;

	  // オフスクリーン（背景）画像用のデータ領域
	  backGroundImage = createImage(w,h);     // 後面オフスクリーン領域生成
	  backG = backGroundImage.getGraphics();  // 後面オフスクリーン描画用オブジェクト獲得
	  backG.setColor(backColor);              // 背景は白色
	  backG.fillRect(0,0,w,h);                // 背景を白で塗りつぶし
	  addKeyListener(this);                   // キーボードのリスナーを設定

	}


	public void paint(Graphics g) {
		// キャンバスが準備できていない場合は何もしない
		if (offG == null || backGroundImage == null) return;

		// まず背景オフスクリーン画像を描画
		offG.drawImage(backGroundImage,0,0,this);

    // 全てのボールを描画
		for(int i = 0; i < numBalls; i++){
      Ball ball = balls[i];   // ballsから1つずつ呼び出し、一時的に格納
      
      float R,G,B; // 各ボールのもつRGBを格納
      R = ball.r;
      G = ball.g;
      B = ball.b;
      
      java.awt.Color centerColor = new java.awt.Color(R,G,B);   // ボールの色
	    // ボールの色2
      java.awt.Color color2 = new java.awt.Color(
                                                  Math.max((int)(1.25*R),255),
		                                              Math.max((int)(1.25*G),255),
		                                              Math.max((int)(1.25*B),255)
                                                );
		  
		  
      float radius = ball.rad; // 半径
      
      // offG用にint型にキャスト
      int r = (int)radius;
      int x = (int)ball.x;
      int y = (int)ball.y;
      
      Point2D center = new Point2D.Double(x, y);  // ボールの中央位置

		  float[] dist    = {0.15f, 0.8f};          // color2, centerColor を補間
		  Color[] colors  = {color2, centerColor};  //補間のための2色
		  RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);
		  offG2.setPaint(rgp);  // ボールの色塗り設定 (Graphics2Dクラス(offG2)が必要）
		  offG.fillOval(x-r, y-r, r*2, r*2);    // ボールの描画（前景）
		  g.drawImage(offScreenImage,0,0,this);
    }
    
	}


	public void animate() {   // ボールの移動
    // ストップされていなければ、座標を更新
    if (thread != null){
      java.awt.Rectangle bounds = getBounds();  // 移動可能範囲の取得
      for(int i = 0; i < numBalls; i++){
        balls[i].move(bounds);  // 各Ballのmove()で座標を更新
      }		  
      // paint()メソッドを呼び出す(repaint()で間接的に)
		  repaint();
    }
	}

	/**
	 * This method is from the Runnable interface.  It is the body of the 
	 * thread that performs the animation.  The thread itself is created 
	 * and started in the start() method.
	 **/
	public void run() {
		while (true){
			animate();//描画位置更新
			try {
				Thread.sleep(sleepTime);  // 待つ
			}
			catch (InterruptedException e){}  //割り込みなし
	  }
  }

	/** Start animating when the browser starts the applet */
	public void start() {
		if (thread == null){
			thread = saveThread = new Thread(this); //スレッド生成
			thread.start();   //スレッド開始
		}
	}

	/** Stop animating when the browser stops the applet */
	public void stop() {
		if (thread != null){
      saveBalls = balls;  // 現在のボールの情報をセーブ(主に座標)
			offG      = null;   // thread.stop()は非推奨なのでpaintを空回りさせ動きをストップさせる
			thread    = null;   
		}
	}
	public void resume(){   // スレッド再開
    if (offG == null && thread == null){
      balls   = saveBalls;  // セーブしていたボールのデータ(主に座標)をballsに上書き
		  offG    = saveOffG;   // セーブしていたオフスクリーングラフィックスを戻す
		  thread  = saveThread; // セーブしていたスレッドを戻す
    } 
	}

  public static void main(String[] args) {
    int num = Integer.parseInt(args[0]);
	  Kadai6 bcf = new Kadai6("名前：2017/11/28", num);
	  bcf.setSize(600, 600);  // ウィンドウサイズを600x600で作成
	  bcf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	  bcf.setVisible(true);
	  bcf.init();
	  bcf.start();
  }
}
