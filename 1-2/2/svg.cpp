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

void svg::drawCircle(int x, int y, int rad) {
	ofs << "<circle cx='" << x << "' cy='" << y << "' r='" << rad << "' fill='black'/>"; // 円の描画
	ofs << std::endl; // 改行
}
