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

void svg::drawRect(int x, int y, int width, int height) {
  ofs <<  "<rect x='" << x << "' y='" << y << "' width='" << width << "' height='" << height
  << "' fill='black' />" << std::endl;
}

void svg::drawText(int x, int y, std::string label) {
  ofs << "<text x='" << x << "' y='" << y << "'>" << std::endl; // 開始タグ
  ofs << label; // 描画テキスト
  ofs << "</text>"; // 終了タグ
}    

