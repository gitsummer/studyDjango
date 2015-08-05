package com.taxi.Db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taxi.model.OrderHistory;
import com.taxi.model.User;

//操作订单 数据表
public class OrderManager {

	
	private static OrderManager orderAdmin=new OrderManager();


	
	public static OrderManager getOrderAdmin() {
		return orderAdmin;
	}

public String findDid(String orderId)
{
	    Session session = null;
		Transaction tx = null;
		String Did="";
		try{
			session = HibernateFilter.getSession();
			 tx = session.beginTransaction();
			 OrderHistory order=(OrderHistory)session.get(OrderHistory.class, orderId);
			Did= order.getDid();
		     tx.commit();
		}catch(Exception e) {
			 e.printStackTrace();
			session.getTransaction().rollback();
	 	}
		return Did;
	 

}
//向数据库 存入一条订单记录
 public  void putOrder(OrderHistory order)
 {
	    Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateFilter.getSession();
			tx = session.beginTransaction();
			session.save(order);
		    tx.commit();
		}catch(Exception e) {
			 e.printStackTrace();
			session.getTransaction().rollback();
	 	}
	 
	 
	 
 }

 
 public  String  findStatus(String orderId)
 {
	     Session session = null;
		Transaction tx = null;
	     String st ="";
		try{
			session = HibernateFilter.getSession();
			  tx = session.beginTransaction();
			  OrderHistory ord=(OrderHistory)session.get(OrderHistory.class, orderId);
			  st=ord.getStatus();
			   tx.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
     return st;
	 
 }
 
 
 
 

 
 //______________________________________________________________________
 
 //根据订单号 找到订单
 public OrderHistory findbyOrderid(String orderId)
 {
	 
	      Session session = null;
		  Transaction tx = null;
		  OrderHistory order=null;
		try{
			session = HibernateFilter.getSession();
			 tx = session.beginTransaction();
			order=(OrderHistory)session.get(OrderHistory.class, orderId);
			
		      tx.commit();
		}catch(Exception e) {
			 e.printStackTrace();
			session.getTransaction().rollback();
	 	}
		return order;

 }
 
 
 // 更新Order
 public void updateStatus(String orderId ,String s)
 {
	     Session session = null;
		Transaction tx = null;
		try{
			session = HibernateFilter.getSession();
			 tx = session.beginTransaction();
			 OrderHistory ord=(OrderHistory)session.get(OrderHistory.class, orderId);
			 ord.setStatus(s);
			 session.save(ord);
		      tx.commit();
		}catch(Exception e) {
			 e.printStackTrace();
			session.getTransaction().rollback();
		}

 }
 
 //根据id查找订单
 public List findAllHistory(String id, String type)
 {
	 
	 Session session = null;
	 List li=null;
	try
	{
		session = HibernateFilter.getSession();
		session.beginTransaction();
		Query query = null;
		if(type.equals("Passager"))
		query = session.createQuery("from OrderHistory a where a.Pid=?").setParameter(0, id);
		else
		 {
			query = session.createQuery("from OrderHistory a where a.Did=?").setParameter(0, id);
			System.out.print("dbdbddbdbdbbdbdbd");
	   	}
	
		 li =query.list();
		 if(li==null)
			 System.out.print("777777777777777777777777777777list null");
	}
	   catch(Exception e) {
	                  	e.printStackTrace();
		               session.getTransaction().rollback();
                  	}	
                 return li;	 
	  
 }
 
 public List findHistory()
 {
	 Session session = null;
	 List li=null;
	 try
		{
			session = HibernateFilter.getSession();
			session.beginTransaction();
			Query query = null;
			query = session.createQuery("from OrderHistory a order by a.Oid");
			 li =query.list();
		}
	 catch(Exception e) {
       	e.printStackTrace();
        session.getTransaction().rollback();
   	}	
	 
	 return li;	 
 }
 
}
