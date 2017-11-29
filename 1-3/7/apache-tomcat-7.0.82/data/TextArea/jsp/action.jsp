<!--
	File: action.jsp
	Author: Masaki Aono
-->
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean class="textarea.Action" id="action" scope="session"/>
<html>
<head>
<title>テキスト領域テスト</title>
</head>
<body>
<%
	try { request.setCharacterEncoding("UTF-8"); }
	catch (Exception e){	e.printStackTrace(); }

	String body = request.getParameter("body");
	action.setBody(body);
	String result = action.getBody();
%>
<center>
<h3>入力されたテキストは、下記のとおりです。</h3>
</center>
<hr>
<br><br>
<%=result%>
<hr>
<div align="right">
<a href="index.jsp">　戻　る  </a>
</div>
<br />
</body>
</html>
