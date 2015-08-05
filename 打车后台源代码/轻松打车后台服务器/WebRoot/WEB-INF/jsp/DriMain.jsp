<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>司机信息</title>
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
	left:641px;
	top: 621px;
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
	left: 276px;
	top: 150px;
	width: 899px;
}
.pic7{
	position:absolute;
	left: 423px;
	top: 316px;
	width: 481px;
}
</style>
</head>




<body >
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
<td>关于我们</a></td>
    </tr>
    <tr style="background-color:#FFF">
    <td>帮助</a></td>
    </tr>
    </table>
   </span>
</div>
<div >
 <span class="pic6">
<table width="897"  border="1px" style="text-align:center";>
<tr>
<td width="29" class="rd6"><input type="checkbox" name="ifAll"   id="ifAll" onClick="checkAll()" ></td>
<td width="92" height="62">账号</td>
<td width="84">姓名</td>
<td width="74">年龄</td>
<td width="74">性别</td>
<td width="85">分数</td>
<td width="122">证件号</td>
<td width="88">车牌号</td>
<td width="89">车型</td>
<td width="96">邮箱</td>
</tr>
 <c:forEach items="${userlist}" var="item">
<tr>
 <td width="29" class="rd8" ><input type="checkbox" name="selectFlag" id="selectFlag" value="${ item.ID }"></td>
<td height="72">${ item.ID }</td>
<td>${item.name}</td>
<td>${item.age}</td>
<td>${item.sex}</td>
<td>${item.score}</td>
<td>${item.license}</td>
<td>${item.plate}</td>
<td>${item.model}</td>
<td>${item.email}</td>
</tr>
</c:forEach>
</table></span>

  <table width="78%" height="45"  >
    <tr> 
   <td width="41%" height="39"><input name="btnAdd" type="button" id="btnAdd"  value="添 加" onClick="addDri()"></td>
    <td width="41%"> <input name="btnDelete" type="button" id="btnDelete" value="删 除" onClick="deleteDri()"></td>
   <td width="18%"> <input name="btnModify"  type="button" id="btnModify" value="修改"  onClick="modifyDri()"></td>
      
    </tr>
  </table>
<span class="pic2"><p2> 青岛科技大学天马行踪队</p2></span>
</div>
 </form>
</body>

</html>

<script src="../script/windows.js"></script>
<script type="text/javascript">

function addDri() {
		window.self.location = "adddri.do";
	}
	
function modifyDri() {
		var count = 0;
		var j = 0;
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			if (document.getElementsByName("selectFlag")[i].checked) {
				j = i;
				count++;
			}
		}
		if (count == 0) {
			alert("请选择需要修改的用户！");
			return;
		}
		if (count > 1) {
			alert("一次只能修改一个用户！");
			return;
		}
		if (count == 1) {
			window.self.location = "first.do?command=loadinfo&type=Dri&id=" + 
			                        document.getElementsByName("selectFlag")[j].value ;
			                   
		}
	}
	function deleteDri() {
		var flag = false;
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			if (document.getElementsByName("selectFlag")[i].checked) {
				flag = true;
			}		
		}
		if (!flag) {
			alert("请选择需要删除的用户！");
			return;
		}
		if (window.confirm("确认删除吗？")) {
		      
		          with (document.getElementById("itemForm")) {
				method = "post";
				action = "first.do?command=deleteInfo&type=DrivInfo";
				submit();
			}
			}
		
	}
	
	
function checkAll() {
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
		    
			document.getElementsByName("selectFlag")[i].checked = document.getElementById("ifAll").checked;
		}
	}


</script>