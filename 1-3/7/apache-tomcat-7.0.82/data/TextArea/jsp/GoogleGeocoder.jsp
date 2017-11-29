<!--
	File: Geocoder.jsp
	Google Geocoder利用部分：入力から住所がUTF-8でくると仮定
-->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URL"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.io.*"%>
<%@ page import="org.xml.sax.*"%>
<%@ page import="org.xml.sax.helpers.*"%>
<%@ page import="javax.xml.parsers.*"%>

<html>
<head>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false&key=AIzaSyBRLvMe1RfG45_tnZ9QXgxN7O5LZr1frbY"></script>
<title>Google Geocoderを使ってみる</title>
</head>
<body bgcolor="lightYellow" onload="initialize();">
<%

    try { request.setCharacterEncoding("UTF-8"); }
    catch (UnsupportedEncodingException e){    e.printStackTrace(); }

    /* 以下の３つの文字列変数は、動的Webページで使用する */
    String[] address = new String[2];
    address[0] = request.getParameter("address1");    // 住所1
    address[1] = request.getParameter("address2");    // 住所2

    String[][] result = {{null, null},{null, null}};  // 緯度と経度の取得結果格納用配列

    for(int k = 0; k < 2; k++){
      try{
            String t = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address=";
            String utf8 = URLEncoder.encode(address[k], "UTF-8");
            String s = t+utf8;
            URL url = new URL(s);
            InputStream in = url.openStream();
            
            /* XMLの処理 */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new org.xml.sax.ErrorHandler() {/* エラー処理 */
                // 致命的なエラーは無視する
                public void fatalError(SAXParseException exception)    throws SAXException { }
                // パーズでのエラーは報告できるように準備しておく
                public void error(SAXParseException e) throws SAXParseException {
                    String s = "Error at " +e.getLineNumber() + " line.";
                    String se = e.getMessage();
                }
                // 警告情報を取得する
                public void warning(SAXParseException err) throws SAXParseException{
                    String se = err.getMessage();
                }
            });

            Document document = builder.parse(in);        // XML文書のパージング
            NodeList nodes = null;                        // 全体のDOM木のルート
            nodes = document.getElementsByTagName("lat"); // 緯度
            result[k][0] = nodes.item(0).getFirstChild().getNodeValue();
            nodes = document.getElementsByTagName("lng"); // 経度
            result[k][1] = nodes.item(0).getFirstChild().getNodeValue();
           } catch (DOMException e){    // DOM木のエラー
            out.println("Error DOM: " + e.getMessage());
           } catch (SAXParseException e) {    // XMLのパージングのエラー
            out.println(e.getMessage()    +" at line "+e.getLineNumber()
                +", column "+e.getColumnNumber());
        } catch (SAXException e ) {
            out.println("Error Parsing: " + e.getMessage());
        } catch (ParserConfigurationException e ) {
            out.println("Error DocumentBuilder: " + e.getMessage());
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /*=== 以下計算処理 ====*/

    //２座標の測地線距離を求める関数
    double x_lad = Double.parseDouble(result[0][0]);  // 座標1の緯度
    double x_lng = Double.parseDouble(result[0][1]);  // 座標1の経度
    double y_lad = Double.parseDouble(result[1][0]);  // 座標2の緯度
    double y_lng = Double.parseDouble(result[1][1]);  // 座標2の経度

    double R = 6371;    // 地球の半径の近似
    
    double delta_lad = Math.toRadians(Math.abs(x_lad - y_lad));   // 2点の緯度の差のラジアン
    double delta_lng = Math.toRadians(Math.abs(x_lng - y_lng));   // 2点の経度の差のラジアン
    double delta_1 = Math.toRadians(x_lad);                       // 座標1の緯度のラジアン
    double delta_2 = Math.toRadians(y_lad);                       // 座標2の緯度のラジアン
    
    double a = Math.pow(Math.sin(delta_lad/2), 2)
              + Math.cos(delta_1) * Math.cos(delta_2) * Math.pow(Math.sin(delta_lng/2), 2);
    
    double sita = 2 * Math.atan2(Math.sqrt(Math.abs(a)), Math.sqrt(Math.abs(1-a)));

    double distance = R * sita;   // 2地点間の距離


%>
<html>
<head>
<title>Google Geocoderで2地点間の距離を求める</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<center>
<h3>Google Geocoderを使ってみる</h3>
<hr>
<br>
<br>
第1の住所： <%=address[0]%>
<table border="1">
  <tr>
    <td>緯度</td>
    <td>経度</td>
  </tr>
  <tr>
    <td><%=result[0][0]%></td>
    <td><%=result[0][1]%></td>
  </tr>
</table>
<br>
第2の住所： <%=address[1]%>
<table border="1">
  <tr>
    <td>緯度</td>
    <td>経度</td>
  </tr>
  <tr>
    <td><%=result[1][0]%></td>
    <td><%=result[1][1]%></td>
  </tr>
</table>
<br><br>

距離：<%=distance%> km
<div id="map_canvas" style="width:50%; height:50%;">
<script type="text/javascript">
 function initialize() {
  var latlng1 = new google.maps.LatLng("<%=result[0][0]%>", "<%=result[0][1]%>"); // 1箇所目 緯度・経度
  var latlng2 = new google.maps.LatLng("<%=result[1][0]%>", "<%=result[1][1]%>"); // 2箇所目 緯度・経度
  var myOptions = {
   zoom: 5, //拡大倍率
   center: latlng1,
   mapTypeId: google.maps.MapTypeId.ROADMAP //地図の種類
  };
  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions); //地図を表示
  //地図上にマーカーを配置する
  var marker2 = new google.maps.Marker({
    position : latlng1, //緯度・経度
    map : map          //表示する地図
  });
  var marker2 = new google.maps.Marker({
    position : latlng2, //緯度・経度
    map : map          //表示する地図
  });

 }
</script>
</div>
</center>
<hr>
<div align="right">
<a href="index.jsp">戻る    </a>
</div>
<br>
</body>
</html>
