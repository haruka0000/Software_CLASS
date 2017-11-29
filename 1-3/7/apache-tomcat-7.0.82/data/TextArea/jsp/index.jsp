<!--
	File: index.jsp
	住所から緯度・経度を取得し表示
-->
<%@ page contentType = "text/html; charset=UTF-8"%>
<html>
<head>
<title>住所から緯度・経度を取得し距離を計算</title>
</head>
<body bgcolor="lightYellow">
<center>
<h2>住所から緯度・経度を取得し距離を計算</h2>
<hr>
<br />
<h4>
住所を入力し、送信してください。
</h4>
<br/>

<form method="post" action="GoogleGeocoder.jsp">
第1の住所：<input type="text" name="address1" size="50" />
<br/>
第2の住所：<input type="text" name="address2" size="50" />
<br/>
<input type="submit" value="送信" />
</form>
</center>
</body>
</html>
