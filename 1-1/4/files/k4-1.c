#include <stdio.h>
#include <stdlib.h>

int main(void){
	
	FILE *fp;		// ファイルポインタ
	char s[256];
	
	// オープン
	if ((fp = fopen("sample.txt", "r")) == NULL) {
		printf("ERROR!!\n");
		exit(EXIT_FAILURE);
	}
	
	// ファイル読み込み
	while (fgets(s, 256, fp) != NULL) {
		// １行単位で読み出し
		printf("%s", s);
	}
	fclose(fp);
	
	return 0;
}
