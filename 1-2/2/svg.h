#include <fstream> // std::ofstream を使用するために必要なヘッダ
#include <string> // std::string を使用するために必要なヘッダ

class svg { // グラフィクスを描画するSVGドキュメントを生成／保存するクラス
private:
	std::ofstream ofs; // ファイルを出力するストリーム
public:
	// ファイル名と横縦長を入力して、ファイル出力ストリームに対してSVGのルート要素を構成する
	void open (std::string filename, int width, int height);
	void close (); // SVGファイルを閉じる
  void drawCircle(int x, int y, int rad); // 円を描画する
};

