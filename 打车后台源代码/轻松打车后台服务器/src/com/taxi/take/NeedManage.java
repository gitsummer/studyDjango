package com.taxi.take;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.taxi.Db.InfoManage;
import com.taxi.Db.OrderManager;
import com.taxi.model.OrderHistory;



//此类主要包含 存放需求信息的HashMap
//此类还包括遍历Map查找合适的订单 返回Map
public class NeedManage {
	  
	//存放需求订单
	private static     Map<String,PassNeed> Pd  =Collections.synchronizedMap(new HashMap<String, PassNeed>());
    private static   Map<String,PassNeed> Od =Collections.synchronizedMap(new HashMap<String, PassNeed>());
    private static   Map<String,Point> Dd  =Collections.synchronizedMap(new HashMap<String, Point>());
    private static   int  Nnum=1;
	static
	{
		//Pd= (HashMap<String, PassNeed>) Collections.synchronizedMap(new HashMap<String,PassNeed>());
		//Pd= new HashMap<String,PassNeed>();
		//System.out.println("11111111111111111111111111111111111111");
	//	Map<String, PassNeed> Pd = Collections.synchronizedMap(new HashMap<String, PassNeed>());
	//	System.out.println("555555555555555555555555555555555555555");
		//Od= (HashMap<String, PassNeed>) Collections.synchronizedMap(new HashMap<String,PassNeed>());
	//	Dd= (HashMap<String, String>) Collections.synchronizedMap(new HashMap<String,String>());
		PassNeed text=new PassNeed("15192602856","qingdao","beijing","2013-8-2",22,55,1);
		Pd.put("20130803021", text);
		PassNeed text2=new PassNeed("100003","qingdao","beijing","2013-8-2",22,80,1);
		Pd.put("20130803022", text2);
		PassNeed text3=new PassNeed("15192602857","qingdao","beijing","2013-8-2",22,58,1);
		Pd.put("20130803023", text3);
		PassNeed text4=new PassNeed("15192602888","qAAA","beijing","2013-8-2",22,58,1);
		Pd.put("20130803024", text4);
		PassNeed text5=new PassNeed("15192702857","qCCCo","beijing","2013-8-2",22,58,11);
		Pd.put("20130803025", text5);
		PassNeed text6=new PassNeed("15192702967","qDDDo","beijing","2013-8-2",22,58,11);
		Pd.put("20130803026", text6);
		
		
		PassNeed text1=new PassNeed("15192602856","qingdao","beijing","2013-8-2",22,55,99);
		Od.put("20130803011", text1);
		PassNeed text12=new PassNeed("100003","qingdao","beijing","2013-8-2",22,80,88);
		Od.put("20130803012", text12);
		PassNeed text13=new PassNeed("15192602857","qingdao","beijing","2013-8-2",22,58,11);
		Od.put("20130803013", text13);
		PassNeed text14=new PassNeed("15192602888","qAAA","beijing","2013-8-2",22,58,11);
		Od.put("20130803014", text14);
		PassNeed text15=new PassNeed("15192702857","qCCCo","beijing","2013-8-2",22,58,11);
		Od.put("20130803015", text15);
		PassNeed text16=new PassNeed("15192702967","qDDDo","beijing","2013-8-2",22,58,11);
		Od.put("20130803016", text16);
		
		
		
		
		
		
		
//      int a=(int) (120.500*1E6);
//      int b=(int)(36*1E6);
//	  Point p1=new Point((int) (120.501*1E6),(int)(36.01*1E6));
//	  Point p2=new Point(a,b);
//	  Point p3=new Point((int) (120.511*1E6),(int)(36.01*1E6));
//	  Dd.put("1001", p1);
//	  Dd.put("1002", p2);
//	  Dd.put("1003", p3);
//	  
	  
	  

      
      
	}
    

   //生成订单号   
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
        	{No = "00" + NumStr;  }
         
            else if(n==2)
        	{No = "0" + NumStr;}
        	else
        		No=NumStr;
      
          StringBuffer orderId = new StringBuffer(DateStr + No ); 
          Nnum++;
		
		
		return orderId.toString();
	}
	public static Map<String, PassNeed> getPd() {
		return Pd;
	}


	public static Map<String, PassNeed> getOd() {
		return Od;
	}


	public static Map<String, Point> getDd() {
		return Dd;
	}

   
	 
	//传入Id 数据库找到司机Score，便利Map   
	//克隆抛出异常
	public static Map<String,PassNeed> DriNeed(String id) throws CloneNotSupportedException
	{
		
		    int Dscore=InfoManage.getAdmin().findUserInfo(id, "Driv").getScore();
		       //int Dscore=66;
		       
		    Map<String,PassNeed> temp=new HashMap<String,PassNeed>();
		       
		     for (Entry<String, PassNeed> entry:Pd.entrySet()) {

                   PassNeed p=(PassNeed) entry.getValue().clone();
                   if(p.getScore()<Dscore)
                   {
                	   System.out.println("找到了符合的司机。。。。。。");
                    ((PassNeed) entry.getValue()).setNum(p.getNum()+1);                   //派送车辆+1；
                    int Pscore=InfoManage.getAdmin().findUserInfo(p.getUid(), "Pass").getScore();
                    System.out.println("？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？"+Pscore);
                      p.setScore(Pscore);
               
                   temp.put(entry.getKey(), p);
                   }
	             

	           }
//		  Collection<PassNeed> collection =null;
//		  if(Type.equals("Now"))
//		  {
//		        collection = Pd.values(); 
//		  
//		  }
//		  else
//		  {
//	            collection = Od.values(); 
//			  
//		  }
//			  
//		  Iterator<PassNeed> iter = collection.iterator(); 
//		  
//     	  while (iter.hasNext())
//    		 {
//     		 PassNeed p=iter.next();
//     		  if(p.getScore()<Dscore)
//     		  {
//     			 PassNeed clo=(PassNeed)p.clone();
//     			  int Pscore=InfoManage.getAdmin().findScore(clo.getUid(), 'P');
//     			  clo.setScore(Pscore);
//     			  temp.put(p.getUid(), clo);
//     		  }
//    		}
     
     	  return temp;
	
	}

	
	public static Map<String,PassNeed> DriOneed(String id) throws CloneNotSupportedException
	{
		
		    int Dscore=InfoManage.getAdmin().findUserInfo(id, "Driv").getScore();
		   
		    Map<String,PassNeed> temp=new HashMap<String,PassNeed>();
		       
		     for (Entry<String, PassNeed> entry:Od.entrySet()) {

                   PassNeed p=(PassNeed) entry.getValue().clone();
                   if(p.getScore()<Dscore)
                   {
                	   System.out.println("找到了符合的司机。。。。。。");
                  ((PassNeed) entry.getValue()).setNum(p.getNum()+1);                   //派送车辆+1；
                    int Pscore=InfoManage.getAdmin().findUserInfo(p.getUid(), "Pass").getScore();
                    p.setScore(Pscore);
                   
                   temp.put(entry.getKey(), p);
                   }
	             

	           }

     
     	  return temp;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 将一个即时订单存入数据库
	public static void PutOrder(String Did,String orderId,String Type) throws CloneNotSupportedException
	{
		  
		  PassNeed clo=null;
		  if(Type.equals("Now"))
		  {
			   clo=(PassNeed)Pd.get(orderId).clone();
			   Pd.remove(orderId);
		  }
		  else
		  {
			  clo=(PassNeed)Od.get(orderId).clone();
			  Od.remove(orderId);  
		   }
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		  Date test=null;
		   try {
		   test = formatter.parse(clo.getOdate());
            } catch (ParseException e) {
			e.printStackTrace();
		   }
		   
		   
		  OrderHistory order=new OrderHistory(orderId,Type,clo.getUid(),Did,test,clo.getStart(),clo.getFinish(),"on",clo.getTip());
		  OrderManager.getOrderAdmin().putOrder(order);

	}


}
