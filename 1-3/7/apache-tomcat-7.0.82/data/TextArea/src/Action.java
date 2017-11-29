//
//	File: Action.java
/* テキスト保持用JavaBean */

package textarea;/* パッケージ名 */

public class Action { 

	String body;/* テキストエリア文字列本体 */
	String analyzed;/* 適当な文字列解析後の文字列(サンプルでは未使用） */

	public void setBody(String body){/* body文字列にセット */
		this.body = new String(body);
	}
	public String getBody(){/* body文字列をゲット */
		return body;
	}
	public void setAnalyzed(String analyzed){/* 解析文字列をセット */
		this.analyzed = new String(analyzed);
	}
	public String getAnalyzed(){/* 解析文字列をゲット */
		return analyzed;
	}
} 
