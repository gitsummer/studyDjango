package com.taxi.Db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taxi.model.DrivInfo;
import com.taxi.model.PassInfo;
import com.taxi.model.User;
import com.taxi.model.UserInfo;

//此类主要操作对数据库Passinfo,DrivInfo库 信息增删改查
public class InfoManage {

	private static InfoManage admin=new InfoManage();

	public static InfoManage getAdmin() {
		return admin;
	}
	
	
// 由type判断查那个库，查找sccore
public int  findScore(String ID ,char type)
{
	Session session = null;
	 Transaction tx = null;
	 int score=0;
	 try{
		 session = HibernateFilter.getSession();
			tx = session.beginTransaction();
			if(type=='D')
			{ 
				DrivInfo dri=(DrivInfo)session.get(DrivInfo.class, ID);
				score=dri.getScore();
			}
			else
				{
				PassInfo pass=(PassInfo)session.get(PassInfo.class, ID);
				 score=pass.getScore();
				}
	           
			 System.out.println("777777777777777777777777");
			   tx.commit();
	 
	 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	  
	 return score;
	

}
	

public void updateScore(int a,char type, String ID)
{
	 Session session = null;
	 Transaction tx = null;
	 try{
		   session = HibernateFilter.getSession();
			tx = session.beginTransaction();
			if(type=='D')
			{ 
				 DrivInfo dri=(DrivInfo)session.get(DrivInfo.class, ID);
				 dri.setScore(dri.getScore()+a);
				 session.save(dri);
			}
			else
				{
				PassInfo pass=(PassInfo)session.get(PassInfo.class, ID);
				pass.setScore(pass.getScore()+a);
				session.save(pass);
				}
	           
			 System.out.println("777777777777777777777777");
			   tx.commit();
	 
	 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	


}
	
public PassInfo findPass(String Pid)
{
	 Session session = null;
	 Transaction tx = null;
	 PassInfo pass=null;
	 try{
		 session = HibernateFilter.getSession();
		     	tx = session.beginTransaction();
			   pass=(PassInfo)session.get(PassInfo.class, Pid);
			   tx.commit();
	 
	 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	  
	 return pass;
}
public DrivInfo findDriv(String Did)
{
	 Session session = null;
	 Transaction tx = null;
	 DrivInfo pass=null;
	 try{
		 session = HibernateFilter.getSession();
		     	tx = session.beginTransaction();
			   pass=(DrivInfo)session.get(DrivInfo.class, Did);
			   tx.commit();
	 
	 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	  
	 return pass;
}


//更新用户信息
public void updatePinfo(PassInfo p)
{
	 Session session = null;
	 Transaction tx = null;
	 try{
		 session = HibernateFilter.getSession();
			tx = session.beginTransaction();
				session.save(p);
			    tx.commit();

		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		 }

}
public void updateDinfo(DrivInfo p)
{
	 Session session = null;
	 Transaction tx = null;
	 try{
		 session = HibernateFilter.getSession();
				session.save(p);
			    tx.commit();

		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		 }

}



//——————————————————————————————————————————

 //此类负责根据Id找到Info
public UserInfo findUserInfo(String id ,String type)
{
	Session session = null;
	UserInfo info=null;
	 try
	 {
			 session = HibernateFilter.getSession();
			 session.beginTransaction();
			 if(type.equals("Pass"))
			 info=(UserInfo) session.get(PassInfo.class, id);
			 else
		     info=(UserInfo) session.get(DrivInfo.class, id); 	 
	 }
	    catch(Exception e) {
			 e.printStackTrace();
			session.getTransaction().rollback();
	    }
	 return info;

}



public void  modifyInfo(UserInfo info,String type)
{
	Session session = null;
	   PassInfo p=null;
	   DrivInfo d=null;
	 
	try {
		session = HibernateFilter.getSession();
		session.beginTransaction();
		if(type.equals("Pass"))
		{  p=(PassInfo)info;
		    session.update(p);
		}
	      else
	      {
	    	 d=(DrivInfo)info;
	    	 session.update(d);  
	       }
		session.getTransaction().commit();
	}catch(Exception e) {
		//记录日志,log4j等......
		e.printStackTrace();
		session.getTransaction().rollback();
	}
}

public void changeScore(int a,String type, String id)
{
	  Session session = null;
	  Transaction tx = null;
	 try{
		     session = HibernateFilter.getSession();
			tx = session.beginTransaction();
			if(type.equals("Driv"))
			{     
				   System.out.println("8888888888888   进入了 修改 score");
				   DrivInfo dri=(DrivInfo)session.get(DrivInfo.class, id);
				   dri.setScore(dri.getScore()+a);
				  session.save(dri);
			}
			else
				{
				PassInfo pass=(PassInfo)session.get(PassInfo.class, id);
				pass.setScore(pass.getScore()+a);
				 session.save(pass);
				}
	           
			   tx.commit();
	 
	 
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

}


// 查询所有乘客/司机 Info
public List findallUserInfo( String type)
{
	Session session = null;
	 List li=null;
	try
	{
		session = HibernateFilter.getSession();
		session.beginTransaction();
		Query query = null;
		if(type.equals("PassInfo"))
		query = session.createQuery("from PassInfo a order by a.ID");
		else
			query = session.createQuery("from DrivInfo a order by a.ID");
		System.out.print("dbdbddbdbdbbdbdbd");
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


//删除PassInfo 可以多个
	public void deleteUserInfo(String[] IdList, String type)
	{
		Session session = null;
		try
		{
			session = HibernateFilter.getSession();
			session.beginTransaction();
			if(type.equals("Pass"))
			{
			  for (int i=0; i<IdList.length; i++) {
				PassInfo pass = (PassInfo)session.load(PassInfo.class, IdList[i]);
				session.delete(pass);
		     }
			}
			else
			{
				   for (int i=0; i<IdList.length; i++) {
					DrivInfo pass = (DrivInfo)session.load(DrivInfo.class, IdList[i]);
					session.delete(pass);
				   }
			}
				
			session.getTransaction().commit();	
	    }    catch(Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();

 }
}

	// 添加info
	public boolean  addUserInfo(UserInfo us, String type)
	{
	   Session session = null;
		PassInfo p=null;
		DrivInfo d=null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			if(type.equals("Pass"))
			{
				 System.out.println("6622222222222222222222");
				p=(PassInfo)us;
				session.save(p);
			}
			else
			{
				d=(DrivInfo)us;
				session.save(d);
			 }
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
          return true;
	}



	public List  findAllHistory(String Id, String type)
	{
    		
		Session session = null;
		 List li=null;
		try
		{
			session = HibernateFilter.getSession();
			session.beginTransaction();
			Query query = null;
			if(type.equals("Pass"))
			query = session.createQuery("from OrderHistory a where a.Pid=?").setParameter(0, Id);
			else
		   query = session.createQuery("from OrderHistory a where a.Did=?").setParameter(0, Id);
			
			
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



}
