package com.taxi.Db;

import org.hibernate.Transaction;
import  com.taxi.model.User;
import org.hibernate.Session;


//此类主要操作对数据库User库 信息增删改查
public class UserManager {
	
	private static UserManager admin=new UserManager();

	public static UserManager getAdmin() {
		return admin;
	}
	
	// login 验证
	public String loginUser(User tuser)
	{
		Session session = null;
		Transaction tx = null;
		User user=null;
		String str="false";
		try{
			session = HibernateFilter.getSession();
			tx = session.beginTransaction();
			 user=(User)session.get(User.class, tuser.getID());
			 if(user==null)
			 {
				 str="AccountError ";
			 }
			  if(user.getPassword().equals(tuser.getPassword()))
			  {
				  str="true";
			  }
			  System.out.println("user.name="+user.getType());
			   tx.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
			
			
		return str;
	}

	
	
	
	
	//_________________________________________________________________________新函数
	
	
	//根据id 查找User
	public User findUserbyid(String id)
	{
		Session session = null;
		Transaction tx = null;
	     User user=new User("Error","Error","Driver");
		try
		{
			session = HibernateFilter.getSession();
			session.beginTransaction();
			user=(User)session.get(User.class, id);
			if(user==null)
			{
				System.out.println("??????????????????????????????????是否抛出异常？");
				return null;
				
			}
		}
			catch(Exception e) {
				if(user==null)
				{
					System.out.println("！！！！！！！！！！！！！！！！！！！！null抛出异常？");
					return null;
				}
				//记录日志,log4j等......
				e.printStackTrace();
				session.getTransaction().rollback();
				return user;
			}
		
		   return user;
		
	}
	
	// 注册
	public boolean registerUser(User tuser)
	{
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateFilter.getSession();
			tx = session.beginTransaction();
			session.save(tuser);
		    tx.commit();
		}catch(Exception e) {
			 e.printStackTrace();
			 session.getTransaction().rollback();
			return false;
		}	
		return true;
	}

	
	//修改账号信息
	public void modify(User us)
	{
		 Session session = null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			session.update(us);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}	
	
	}
	
	
	
   
}
