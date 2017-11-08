#include "svg.h" // ヘッダファイルの読み込み

// ドキュメントの生成（ファイルオープン）とSVGルート要素の生成
void svg::open(std::string filename, int width, int height) {
	ofs.open(filename);
	ofs << "<svg width='" << width << "' height='" << height << "' >"; // ルート要素の開始
	ofs << std::endl;  // 改行
}

// SVGドキュメントを閉じる（自動的にファイル保存される）
void svg::close () {
	ofs << "</svg>"; // ルート要素の終了
	ofs.close(); // ファイルを閉じる
}

void svg::drawCircle(int x, int y, int rad, std::string color, int width) {
  if (width > 0) // 線幅が指定されていれば輪郭線を描画する
    ofs <<  "<circle cx='" << x << "' cy='" << y << "' r='" << rad
    << "' stroke='" << color << "' stroke-width='" << width << "' fill='none' />";
  else // 線幅が指定されていないか、値が0以下であれば、円の内部を塗りつぶす
    ofs <<  "<circle cx='" << x << "' cy='" << y << "' r='" << rad
    << "' fill='" << color << "' />";
  
	ofs << std::endl; // 改行
}
