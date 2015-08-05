<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>³Ë¿ÍÐÅÏ¢</title>
<style>
  p1{ font-family:"ºÚÌå"; font-size:55px; font-style:inherit;color:#33F;}
  p2{ font-family:"ºÚÌå"; font-size:24px; color:#FFF}
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
	top: 600px;
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
	left: 293px;
	top: 120px;
	width: 701px;
}
.pic7{
	position:absolute;
	left: 293px;
	top: 580px;
	width: 200px;
}

</style>
</head>
<body background-image="1.jpg">
<form name="itemForm" id="itemForm">
<div style=" height:80px; border-bottom:groove #00F">
<span class="pic"> 
<p1>Çá ËÉ ´ò ³µ ¹Ü Àí Ïµ Í³</p1></span>

<span class="pic1"><img src="Í¼±ê.png" />
</span>
</div> 
<div style=" width:195px; height:560px; border-right:groove #00F">
  <span class="pic3">  
  <table width="163" height="98" style=" border:solid 2px; border-color:#000; text-align:center" >
    <tr style="background-color:#F0C"><td width="153">*¶©µ¥ÐÅÏ¢</td>
    </tr>
    <tr style="background-color:#FFF">
   <td><a href="first.do?command=listOrder">ÀúÊ·¶©µ¥</a></td>
    </tr>
    <tr style="background-color:#FFF">
    <td>b
    </td>
    </tr>
    </table>
   </span>
<span class="pic4">
     <table width="163" height="98" style=" border:solid 2px; border-color:#000; text-align:center" >
    <tr style="background-color:#FF3"><td width="153">*ÓÃ»§¹ÜÀí</td>
    </tr>
    <tr style="background-color:#FFF">
    <td><a href="first.do?command=findAllUser&type=PassInfo">³Ë¿ÍÐÅÏ¢</a></td>
    </tr>
    <tr style="background-color:#FFF">
    <td><a href="first.do?command=findAllUser&type=DrivInfo">Ë¾»úÐÅÏ¢</a> </td>
    </tr>
    </table>
   </span>
<span class="pic5">
     <table width="163" height="98" style=" border:solid 2px; border-color:#000; text-align:center" >
    <tr style="background-color:#6F3"><td width="153">*ÏµÍ³·þÎñ</td>
    </tr>
    <tr style="background-color:#FFF">
<td>¹ØÓÚÎÒÃÇ</td>
    </tr>
    <tr style="background-color:#FFF">
    <td>°ïÖú</td>
    </tr>
    </table>
   </span>
</div>
<div >
<span class="pic6">
<table width="783"  height="450" border="1px" style="text-align:center";>
<tr>
<td width="31" class="rd6"><input type="checkbox" name="ifAll"  id="ifAll"onClick="checkAll()" ></td>
<td width="100" height="45">ÕËºÅ</td>
<td width="100">ÐÕÃû</td>
<td width="100">ÄêÁä</td>
<td width="100">ÐÔ±ð</td>
<td width="100">·ÖÊý</td>
<td width="200">µØÖ·</td>
<td width="200">ÓÊÏä</td>
</tr>

 <c:forEach items="${userlist}" var="item">
<tr>
 <td width="31" class="rd8" ><input type="checkbox" name="selectFlag" id="selectFlag" class="checkbox1" value="${ item.ID }"></td>
<td height="40">${ item.ID }</td>
<td>${ item.name }</td>
<td>${ item.age }</td>
<td>${ item.sex }</td>
<td>${ item.score }</td>
<td>${ item.address }</td>
<td>${ item.email }</td>
</tr>
</c:forEach>
</table></span>
  <span class="pic7">
  <table width="50%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" >
    <tr> 
       <td  width="50"> <input name="btnAdd" type="button" id="btnAdd"  value="    add    " onClick="addPass()" size="50"></td>
      <td>  <input name="btnDelete" type="button" id="btnDelete" value="  delete  " onClick="deletePass()"size="50"></td>
        <td><input name="btnModify"  type="button" id="btnModify" value="  modify  "  onClick="modifyPass()"size="50">
      </td>
    </tr>
  </table>
  </span>
<span class="pic2"><p2> Çàµº¿Æ¼¼´óÑ§ÌìÂíÐÐ×Ù¶Ó</p2></span>
</div>
  </form>
</body>
</html>


<script src="../script/windows.js"></script>
<script type="text/javascript">

function addPass() {
		window.self.location = "adduser.do";
	}
	
function modifyPass() {
		var count = 0;
		var j = 0;
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			if (document.getElementsByName("selectFlag")[i].checked) {
				j = i;
				count++;
			}
		}
		if (count == 0) {
			alert("ÇëÑ¡ÔñÐèÒªÐÞ¸ÄµÄÓÃ»§£¡");
			return;
		}
		if (count > 1) {
			alert("Ò»´ÎÖ»ÄÜÐÞ¸ÄÒ»¸öÓÃ»§£¡");
			return;
		}
		if (count == 1) {
			window.self.location = "first.do?command=loadinfo&type=Pass&id=" + 
			                        document.getElementsByName("selectFlag")[j].value ;
			                   
		}
	}
	function deletePass() {
		var flag = false;
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			if (document.getElementsByName("selectFlag")[i].checked) {
				flag = true;
			}		
		}
		if (!flag) {
			alert("ÇëÑ¡ÔñÐèÒªÉ¾³ýµÄÓÃ»§£¡");
			return;
		}
		if (window.confirm("È·ÈÏÉ¾³ýÂð£¿")) {
		      
		          with (document.getElementById("itemForm")) {
				method = "post";
				action = "first.do?command=deleteInfo&type=PassInfo";
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