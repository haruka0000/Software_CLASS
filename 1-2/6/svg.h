#pragma once
#include <fstream> // std::ofstream を使用するために必要なヘッダ
#include <string> // std::string を使用するために必要なヘッダ

class svg { // グラフィクスを描画するSVGドキュメントを生成／保存するクラス
private:
	static std::ofstream ofs; // ファイルを出力するストリーム
public:
	// ファイル名と横縦長を入力して、ファイル出力ストリームに対してSVGのルート要素を構成する
	static void open (std::string filename, int width, int height);
	static void close (); // SVGファイルを閉じる
  static void drawRect(int x, int y, int width, int height);
  static void drawText(int x, int y, std::string label);
};

