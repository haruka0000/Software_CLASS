# マクロ定義部
CC = g++
 
# 必要なオブジェクトファイルを列挙する（必要な.c ファイルの拡張子を.oに変える Ｃ言語の場合は、gcc を利用する）
OBJS = main.o svg.o Population.o BarGraph.o

# 生成規則部（実行ファイル名を circle としている）$@ → "circle", $(CC) → "g++ と置き換わる。
exe: $(OBJS)
	$(CC) -o $@ $(OBJS)

.cpp.o: # 使用する拡張子が異なる（.c, .c++等）場合には，修正する
	$(CC) -std=c++11 -c $<

all:
	${MAKE} exe

clean:
	rm $(OBJS)
