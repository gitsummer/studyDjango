<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ë¾»úÐÅÏ¢</title>
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
<td>¹ØÓÚÎÒÃÇ</a></td>
    </tr>
    <tr style="background-color:#FFF">
    <td>°ïÖú</a></td>
    </tr>
    </table>
   </span>
</div>
<div >
 <span class="pic6">
<table width="897"  border="1px" style="text-align:center";>
<tr>
<td width="29" class="rd6"><input type="checkbox" name="ifAll"   id="ifAll" onClick="checkAll()" ></td>
<td width="92" height="62">ÕËºÅ</td>
<td width="84">ÐÕÃû</td>
<td width="74">ÄêÁä</td>
<td width="74">ÐÔ±ð</td>
<td width="85">·ÖÊý</td>
<td width="122">Ö¤¼þºÅ</td>
<td width="88">³µÅÆºÅ</td>
<td width="89">³µÐÍ</td>
<td width="96">ÓÊÏä</td>
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
   <td width="41%" height="39"><input name="btnAdd" type="button" id="btnAdd"  value="Ìí ¼Ó" onClick="addDri()"></td>
    <td width="41%"> <input name="btnDelete" type="button" id="btnDelete" value="É¾ ³ý" onClick="deleteDri()"></td>
   <td width="18%"> <input name="btnModify"  type="button" id="btnModify" value="ÐÞ¸Ä"  onClick="modifyDri()"></td>
      
    </tr>
  </table>
<span class="pic2"><p2> Çàµº¿Æ¼¼´óÑ§ÌìÂíÐÐ×Ù¶Ó</p2></span>
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
			alert("ÇëÑ¡ÔñÐèÒªÐÞ¸ÄµÄÓÃ»§£¡");
			return;
		}
		if (count > 1) {
			alert("Ò»´ÎÖ»ÄÜÐÞ¸ÄÒ»¸öÓÃ»§£¡");
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
			alert("ÇëÑ¡ÔñÐèÒªÉ¾³ýµÄÓÃ»§£¡");
			return;
		}
		if (window.confirm("È·ÈÏÉ¾³ýÂð£¿")) {
		      
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