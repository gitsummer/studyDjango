package com.text;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taxi.Db.InfoManage;
import com.taxi.Db.OrderManager;
import com.taxi.act.PerJson;
import com.taxi.take.NeedManage;
import com.taxi.take.PassNeed;
import com.taxi.take.Point;

import junit.framework.TestCase;


public class te extends TestCase {
	private static   int  Nnum=1;
	
	public void  text()
	{
	System.out.println("kkkkkkkkk");	
		
	   Map<String,Point> Dd  =Collections.synchronizedMap(new HashMap<String, Point>());
	       int a=(int) (120*1E6);
	      int b=(int)(36*1E6);
		  Point p1=new Point(1,36);
		  Point p2=new Point(a,b);
		  Point p3=new Point();
		  Dd.put("1001", p1);
		  Dd.put("1002", p2);
		  Dd.put("1003", p3);
		  
		     Gson gson = new Gson();
	        String jsonObject= gson.toJson(NeedManage.getDd()); 
	        System.out.println(jsonObject);	
	         
	        Map<String, Point> retMap = gson.fromJson(jsonObject,  
	                new TypeToken<Map<String, Point>>() {  }.getType());  
	        
	        
	     
       // 遍历HashaMap  全部
	        for (Map.Entry<String,Point> entry:retMap.entrySet()) {

	             System.out.println("Key:"+entry.getKey());
                 Point p=entry.getValue();
	             System.out.println("Value:"+p.getX()+"           "+p.getY());

	           }
	    	  
	    	  
//	         Collection<Point> collection = retMap.values(); 
//			  Iterator<Point> iter = collection.iterator(); 
//			  while (iter.hasNext())
//			  {    
//				  Point p=iter.next();
//				  System.out.println(p.getX()+"fffff "+p.getY());
//				  
//				  
//			  }

	}
	
	
	
	
	public void textSite()
	{
		
	      
		  Map<String,Point> Dd  =Collections.synchronizedMap(new HashMap<String, Point>());
	       int a=(int) (120*1E6);
	       int b=(int)(36*1E6);
	       Point p2=new Point(a,b);
	       Dd.put("1002", p2);
	        Gson gson = new Gson();
	        String jsonObject= gson.toJson(Dd); 
	        System.out.println(jsonObject);	
	        JSONObject obj=JSONObject.fromObject(jsonObject);
	        Point p=(Point) obj.get("1002");
	        System.out.print("ddddddddd");
	        System.out.print(p.getX()+"ffff"+p.getY());
	        
	}

	//司机site入Map
	public void text3() throws CloneNotSupportedException
	{     
		 Map<String,PassNeed> Pd  =Collections.synchronizedMap(new HashMap<String, PassNeed>());
		 PassNeed P1=new PassNeed();
		      P1.setUid("1001");
		      P1.setScore(80);
		      
		      PassNeed P2=new PassNeed();
		      P2.setUid("1002");
		      P2.setScore(44);
		      PassNeed P3=new PassNeed();
		      P3.setUid("1003");
		      P3.setScore(33);
		      Pd.put("1001", P1);
		      Pd.put("1002", P2);
		      Pd.put("1003", P3);
		 int Dscore=InfoManage.getAdmin().findScore("110", 'D');
		System.out.println("ddddd"+Dscore);
		
		 Map<String,PassNeed> temp=new HashMap<String,PassNeed>();
		  Collection<PassNeed> collection = Pd.values(); 
		  Iterator<PassNeed> iter = collection.iterator(); 
		  while (iter.hasNext())
 		 {
  		 PassNeed p=iter.next();
  		 
  		  if(p.getScore()<Dscore)
  		  {   
  			  System.out.print("ffffffffffffff"+p.getScore());
  		     PassNeed clo=(PassNeed)p.clone();
  			  int Pscore=InfoManage.getAdmin().findScore(clo.getUid(), 'P');
  			  clo.setScore(Pscore);
  			  temp.put(p.getUid(), clo);
  		  }
 		}
		  
		  Gson gson = new Gson();
	        String jsonObject= gson.toJson(temp); 
	        System.out.println(jsonObject);	
		
	}
		
	
	
	public void textdate()
	{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");;  
        //是不是严格按照指定的格式解析日期   
        format1.setLenient(false);;  
        String DateStr = format1.format(new Date());  
        System.out.println(DateStr);
		
		
		
	}
	
	public static String createNo()
	{
		String No="";
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");;  
        //是不是严格按照指定的格式解析日期   
        format1.setLenient(false);;  
        String DateStr = format1.format(new Date());  
        
        String NumStr=String.valueOf(Nnum);
        int n = NumStr.length();
          if(n==1)
        	{No = "00" + NumStr; 
        	  System.out.println(No);
        	}
         
            else if(n==2)
        	{No = "0" + NumStr;}
        	else
        		No=NumStr;
      
          StringBuffer orderId = new StringBuffer(DateStr + No ); 
          Nnum++;
		System.out.println(orderId.toString());
		
		return orderId.toString();
		
	}
		
	//使用JSONObject
	public void perjsonObjec()
	{
		
		
		JSONObject  hh=new JSONObject();
		hh.put("Type", "sss");
		hh.put("orderId", "9999");
		hh.put("Did", "1001");
		String sss=hh.toString();
		System.out.println(sss);
		
		// JSONObject obj=JSONObject.fromObject(sss);
		JSONObject obj = JSONObject.fromString(sss);
		  String Type=obj.getString("Type");
		  String DId=obj.getString("Did");
		  String orderId=obj.getString("orderId");
		  System.out.println("type:"+Type+"    order:"+orderId+"    did:"+DId);
	}
	
	
	
	public  void  textlist()
	{
		
		
	      String id="15192602856";
	      String type="Pass";
		  List info=OrderManager.getOrderAdmin().findAllHistory(id, type);
		  Gson gson=new Gson();
		  String s = gson.toJson(info);  
		  System.out.println(s);	
		
		
	}
			
}
