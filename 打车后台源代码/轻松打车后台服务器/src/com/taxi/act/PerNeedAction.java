package com.taxi.act;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.mapping.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taxi.Db.InfoManage;
import com.taxi.Db.OrderManager;
import com.taxi.model.OrderHistory;
import com.taxi.take.NeedManage;
import com.taxi.take.PassNeed;
import com.taxi.take.Point;

//此类接收 乘客查看附件司机,司机发送坐标，乘客发送订单，司机接单 请求；主要对系统Map操作
public class PerNeedAction extends DispatchAction {
	//乘客订单入Map
	public ActionForward  passNeed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    String json=PerJson.getPer().readJSON(request);
	          Gson gson = new Gson();  
	         PassNeed us=gson.fromJson(json, PassNeed.class);
		     String orderId=NeedManage.createNo();    //订单号
		     System.out.println(us);
             System.out.println("99999999999999999提交订单：：："+orderId);
	       
	        //如果是即时
	       if(request.getParameter("type").equals("Order"))   
	       {

		        NeedManage.getPd().put(orderId, us);
 
	       }
         else
         {
              NeedManage.getOd().put(orderId, us); 
		       
         }
	    
	       
	       
	       JSONObject jsonObj = new net.sf.json.JSONObject();
		    jsonObj.put("success",orderId);                     //返回订单号
		    PerJson.getPer().jsonToString(jsonObj.toString(), response);
		   
		  
		
		
		return  null;
	}

	//乘客查看附近车辆
	public ActionForward  passDri(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		 JSONObject jsonObject = JSONObject.fromObject(NeedManage.getDd());
//		 Collection<Point> collection = NeedManage.getDd().values(); 
//		  Iterator<Point> iter = collection.iterator(); 
//		  while (iter.hasNext())
//		  {    
//			  Point p=iter.next();
//			  System.out.println(p.getX()+"fffff "+p.getY());
//			  
//			  
//		  }
		
		     Gson gson = new Gson();
	        String jsonObject= gson.toJson(NeedManage.getDd()); 
		   
		  
		    PerJson.getPer().jsonToString(jsonObject, response);
		 
		return null;
	}

	
	//司机site入Map，预约或者即时 返回
	//requseet Did Site   reponse, Map
	public ActionForward  driSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		 //司机位置入Map
		
		
		      String json=PerJson.getPer().readJSON(request);
		      System.out.println("88888888888888888888888888 进入请求                              "+json);
		     JSONObject jsonObj = JSONObject.fromString(json);
		     String Id = jsonObj.getString("Id");
		     String Type=jsonObj.getString("Type");
		    // int x = Integer.parseInt(jsonObj.getString("x"));
		    // int y = Integer.parseInt(jsonObj.getString("y"));
		       int x=jsonObj.getInt("x");
		       int y=jsonObj.getInt("y");
		     Point p=new Point(x,y);
		    //Site 入Map
		   if( !(NeedManage.getDd().containsKey(Id)))
		   {
	           NeedManage.getDd().put(Id,p);
	           System.out.println("88888888        是否已经有了此车");
		   }
		    Gson gson = new Gson();
		    String jsonObject="";
		    
		    
		    if(Type.equals("Now"))
		    {
	             jsonObject= gson.toJson(NeedManage.DriNeed(Id)); 
	             System.out.println("88888888888888888888888888验证与订单返回      "+jsonObject.toString());      
		    }
		    else
		    {
		           jsonObject= gson.toJson(NeedManage.DriOneed(Id)); 
		           System.out.println("999999999999999999999999888验证与订单返回      "+jsonObject.toString());      
		     }
		           PerJson.getPer().jsonToString(jsonObject.toString(), response);		       
		  
	         
	         
		return  null;
	}
	
	
	//司机接单 remove Map        Request Did Pid  Reponse success:true|false
	public ActionForward  driRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		  String json=PerJson.getPer().readJSON(request);
		  JSONObject obj=JSONObject.fromObject(json);
		  
		  String Type=obj.getString("Type");
		  String DId=obj.getString("Did");
		  String orderId=obj.getString("orderId");
		  
		  JSONObject jsonObj = new net.sf.json.JSONObject();
		  if(Type.equals("Now"))
		  {
			  if(!NeedManage.getPd().containsKey(orderId))
			   {
				    jsonObj.put("success","false");
				    System.out.println("9999999999999999999            "+jsonObj.toString());
			   }
			  else 
			  {
				    jsonObj.put("success","true");
				    NeedManage.PutOrder(DId, orderId, Type);		
				    System.out.println("98888888888888888888888            "+jsonObj.toString());
			  }		  
		  }
		  else                  //预约
		  {
			  if(!NeedManage.getOd().containsKey(orderId))
			   {     
				  
				    jsonObj.put("success","false");
				    System.out.println("9999999999999999999            "+jsonObj.toString());
				    
			   }
			  else 
			  {
				     jsonObj.put("success","true");
				     NeedManage.PutOrder(DId, orderId, Type);   
				     System.out.println("8888888888888888888899            "+jsonObj.toString());
			  }    
		  }
		  PerJson.getPer().jsonToString(jsonObj.toString(), response);
		  
		  
			  
		return  null;
	}
	
	// 乘客查看 订单状态Map， 接受订单号 
	public ActionForward  lookOrderMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		     
		      String json=PerJson.getPer().readJSON(request);
		      JSONObject jsonObj = JSONObject.fromString(json);
		      String  orderId=jsonObj.getString("orderId");
		      
		    //  NeedManage.getPd().get(orderId).setNum(NeedManage.getPd().get(orderId).getNum()+1);
		     // int num1=NeedManage.getPd().get(orderId).getNum();
		    //  System.out.println("8888888888888888888888888888 geshu                        "+num1);
		   //   if(num1>15)
		    //  NeedManage.getPd().remove(orderId);
		      
		      
		    JSONObject  jsonout = new net.sf.json.JSONObject();
		    if(NeedManage.getPd().containsKey(orderId))     //如果还未被接受
		    {
		       System.out.println("66666666666666666666666666");
		        int num=NeedManage.getPd().get(orderId).getNum();
		        jsonObj.put("Num",num);                     //返回派送个数
		        jsonObj.put("Status", "false");

		    }
		    else
		    {
		     //查找数据库  找到司机Id	
		    	 
		         // OrderHistory order=OrderManager.getOrderAdmin().findbyOrderid(orderId)	;
		         //String did=order.getDid();
		          jsonObj.put("Status", "true");
		    	  //jsonObj.put("Did",did); 
		          jsonObj.put("Did","1001"); 
		    }
		    
		    PerJson.getPer().jsonToString(jsonObj.toString(), response);
	
	
		
		
		return null;
	
	}
	
	//乘客查看订单 库中状态
	public ActionForward  myOrderStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  String json=PerJson.getPer().readJSON(request);
	      JSONObject jsonObj = JSONObject.fromString(json);
	       String  orderId=jsonObj.getString("orderId");
	    
		  String status=OrderManager.getOrderAdmin().findbyOrderid(orderId).getStatus();
		   JSONObject  jsonout = new net.sf.json.JSONObject();
		   jsonout.put("Status", status);
		  PerJson.getPer().jsonToString(jsonObj.toString(), response);

		 return null;
	}
	
	// 乘客 司机评价订单；   Post  type D  or P    ID  int score 
	public ActionForward  orderAccess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		   String json=PerJson.getPer().readJSON(request);
	       JSONObject jsonObj = JSONObject.fromString(json);
	       String  ID=jsonObj.getString("Id");
	       String Type=jsonObj.getString("Type");
	        int    score=jsonObj.getInt("Score");
	        System.out.println("8888888888888订单评价"+ID+"      "+Type+"      "+score);
	        InfoManage.getAdmin().changeScore(score, Type, ID);
	        JSONObject  jsonout = new net.sf.json.JSONObject();
	        jsonout.put("Status", "success");
	      // 取得Did更新 司机score
	      
	      
		 		
		
		return null;
	}
    
	// 司机操作订单状态      Post  orderId  Type(success false)
	public ActionForward  driOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   String json=PerJson.getPer().readJSON(request);
	       JSONObject jsonObj = JSONObject.fromString(json);
	       String  orderId=jsonObj.getString("orderId");
	       String  status=jsonObj.getString("status");
	            if(status.equals("success"))
	       {
	    	   //更新 数据库状态；
	    	   OrderManager.getOrderAdmin().updateStatus(orderId, "success");
	       }
	       else
	       {
	    	   //订单失败  暂时还未 扣分
	          OrderManager.getOrderAdmin().updateStatus(orderId, "fail");
	          OrderHistory order=OrderManager.getOrderAdmin().findbyOrderid(orderId)	;
	          InfoManage.getAdmin().changeScore(-1, "Driv", order.getDid());
	         InfoManage.getAdmin().changeScore(-3, "PassInfo", order.getPid());
	         
	       }
		
		
		
		return null;
	}
	

	
	}
