<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>乘客信息修改</title>
<style>
  p1{ font-family:"黑体"; font-size:55px; font-style:inherit;color:#33F;}
  p2{ font-family:"黑体"; font-size:24px; color:#FFF}
   .pic {
	position:absolute;
	right:300px;
	top: 21px;
	width: 664px;
	height: 59px;
}
   .pic1 {
	position:absolute;
	left:50px;
	top: 3px;
}
 .pic2 {
	position:absolute;
	left:640px;
	top: 613px;
}
.pic3{
	position:absolute;
	width: 204px;
	left: 19px;
	top: 112px;
	height: 99px;
}
.pic4{
	position:absolute;
	left: 20px;
	top: 215px;
}
.pic5{
	position:absolute;
	left: 19px;
	top: 320px;
}
.pic6{
	position:absolute;
	left: 340px;
	top: 139px;
	width: 462px;
	height: 288px;
}
.pic7{
	position:absolute;
	left: 400px;
	top: 460px;
	width: 219px;
	height: 74px;
}
</style>
</head>
<body>


<form  name="itemForm" target="_self" id="itemForm">
<div style=" height:80px; border-bottom:groove #00F">
<span class="pic"> 
<p1>轻 松 打 车 管 理 系 统</p1></span>

<span class="pic1"><img src="图标.png">
</span>
</div> 
<div style=" width:195px; height:560px; border-right:groove #00F">
  <span class="pic3">  
  <table width="163" height="98" style=" border:solid 2px; border-color:#000; text-align:center" >
    <tr style="background-color:#F0C"><td width="153">*订单信息</td>
    </tr>
    <tr style="background-color:#FFF">
<td><a href="first.do?command=listOrder">历史订单</a></td>
    </tr>
    <tr style="background-color:#FFF">
    <td>b
    </td>
    </tr>
    </table>
   </span>
<span class="pic4">
     <table width="163" height="98" style=" border:solid 2px; border-color:#000; text-align:center" >
    <tr style="background-color:#FF3"><td width="153">*用户管理</td>
    </tr>
    <tr style="background-color:#FFF">
<td><a href="first.do?command=findAllUser&type=PassInfo">乘客信息</a></td>
    </tr>
    <tr style="background-color:#FFF">
        <td><a href="first.do?command=findAllUser&type=DrivInfo">司机信息
    </td>
    </tr>
    </table>
   </span>
<span class="pic5">
     <table width="163" height="98" style=" border:solid 2px; border-color:#000; text-align:center" >
    <tr style="background-color:#6F3"><td width="153">*系统服务</td>
    </tr>
    <tr style="background-color:#FFF">
<td>关于我们</td>
    </tr>
    <tr style="background-color:#FFF">
    <td>帮助</td>
    </tr>
    </table>
   </span>
</div>
<div>
<span class="pic6">
<table width="404" height="305" bgcolor="#33FF33" border="1" bordercolor="#000000" cellpadding="0"style="text-align:center;" >
<tr><td width="80">类 型：</td><td width="312">乘 客</td></tr>
<tr><td>账 号：</td><td><input name="userid" type="text"  id="userid" size="25" value="${ user.ID }"></td></tr>
<tr><td>密 码：</td><td><input name="userpsw" type="text"  id="userpsw" size="25" value="${ user.password }"></td></tr>
<tr><td>姓 名：</td><td><input name="username" type="text"  id="username" size="25" value="${ passinfo.name }"></td></tr>
<tr><td>年 龄：</td><td><input name="userage" type="text"  id="userage" size="25" value="${ passinfo.age }"></td></tr>
<tr><td>性 别：</td><td><input name="usersex" type="text"  id="usersex" size="25" value="${ passinfo.sex }"></td></tr>
<tr><td>地 址：</td><td><input name="useraddress" type="text"  id="useraddress" size="25" value="${ passinfo.address }"></td></tr>
<tr><td>邮 箱：</td><td><input name="useremail" type="text"  id="useremail" size="25" value="${ passinfo.email }"></td></tr>
</table>
</span>
<span class="pic7">
<table width="227" height="58">
<tr>
<td width="111"><input name="Add" type="submit" id="Add" value="修 改" onclick="modifyUser()" /></td>
<td width="104"><a href="Untitled-2.html"><input name="btnBack"  type="submit" id="btnBack"  value="返 回" onclick="goBack()" /></a></td>
</tr></table></span>
<span class="pic2"><p2> 青岛科技大学天马行踪队</p2></span>
</div>

</form>
</body>
</html>

<script type="text/javascript">
function modifyUser() {
		
		with (document.getElementById("itemForm")) {
			method = "post";
			action = "first.do?command=modify&userty=Passager";
			submit();
		}
	}
	
	function goBack() {
		window.self.location = "first.do?command=findAllUser&type=PassInfo";
	}

</script>


