<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
body{ background-image:url(images/a.jpg);}
p {
	position: inherit; 
	font-size:38px;
	left: 401px;
	top: 85px;
}

p1{  font-size:30px;} p2{ font-size:20px;}
</style>
	
  </head>
  
  <body>
       <form action="web/login.do?command=webLog" method="post">

<div style=" width: auto; height:auto;margin-left:550px;
	margin-top: 200px;">
<p>出租车打车管理系统</p>
<table width="330">
  <tr>
  <td width="112">
  <p1>账  号：</p1>
  </td>
  <td width="206"><input type="text" name="Uid"><br>
  </td>
<tr>  
  <td height="44">
  <p1> 密  码：</p1>
  </td>
  <td><input type="password" name="Psw">
  
 </td>
 </tr>
 <tr>
 <td>
  <p2>验证码：</p2>
  </td>
  <td>
  <input name="fname" type="text" value="" size="8" maxlength="4" /></td> 
 <tr>
 <td height="46">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="登陆" /></td>
 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <input name="提交" type="submit" value="取消" />
 </td>
 </tr>
</table>
 </div>

	</form>
  </body>
</html>
