<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>历史订单</title>

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
	left:637px;
	top: 617px;
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
	left: 259px;
	top: 145px;
	width: 1083px;
	height: 177px;
}

</style>
</head>
<body>
<form name="itemForm" id="itemForm">
<div style=" height:80px; border-bottom:groove #00F">
<span class="pic"> 
<p1>轻 松 打 车 管 理 系 统</p1></span>

<span class="pic1"><img src="图标.png" />
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
    <td><a href="first.do?command=findAllUser&type=DrivInfo">司机信息</a> </td>
    </tr>
    </table>
   </span>
<span class="pic5">
     <table width="163" height="98" style=" border:solid 2px; border-color:#000; text-align:center" >
    <tr style="background-color:#6F3"><td width="153">*系统服务</td>
    </tr>
    <tr style="background-color:#FFF">
<td><a href="Untitled-8.html">关于我们</a></td>
    </tr>
    <tr style="background-color:#FFF">
    <td><a href="Untitled-11.html">帮助</a></td>
    </tr>
    </table>
   </span>
</div>
<div >
<span class="pic6">
<table width="1042" height="92"  border="1px" style="text-align:center";>

<tr>
<td width="31" class="rd6"><input type="checkbox" name="ifAll"  id="ifAll"onClick="checkAll()" ></td>
<td width="90" height="41"> 订单号 </td>
<td width="90">订单类型</td>
<td width="90">乘客Id</td>
<td width="90">司机Id</td>
<td width="90">订单日期</td>
<td width="90">起点</td>
<td width="91">终点</td>
<td width="90">订单状态</td>
<td width="90">小费</td>
</tr>
 <c:forEach items="${order}" var="item">
<tr>
<td width="31" class="rd8" ><input type="checkbox" name="selectFlag" id="selectFlag" class="checkbox1" ></td>
<td>${ item.oid }</td>
<td>${ item.type }</td>
<td>${ item.pid }</td>
<td>${ item.did }</td>
<td>${ item.odate }</td>
<td>${ item.start }</td>
<td>${ item.finish }</td>
<td >${ item.status }</td>
<td >${ item.tip }</td>
</c:forEach>
</tr>

</table></span>

<span class="pic2"><p2> 青岛科技大学天马行踪队</p2></span>
</div>
  </form>
</body>
</html>