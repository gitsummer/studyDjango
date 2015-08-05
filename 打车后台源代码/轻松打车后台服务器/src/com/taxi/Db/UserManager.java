package com.taxi.Db;

import org.hibernate.Transaction;
import  com.taxi.model.User;
import org.hibernate.Session;


//������Ҫ���������ݿ�User�� ��Ϣ��ɾ�Ĳ�
public class UserManager {
	
	private static UserManager admin=new UserManager();

	public static UserManager getAdmin() {
		return admin;
	}
	
	// login ��֤
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

	
	
	
	
	//_________________________________________________________________________�º���
	
	
	//����id ����User
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
				System.out.println("??????????????????????????????????�Ƿ��׳��쳣��");
				return null;
				
			}
		}
			catch(Exception e) {
				if(user==null)
				{
					System.out.println("����������������������������������������null�׳��쳣��");
					return null;
				}
				//��¼��־,log4j��......
				e.printStackTrace();
				session.getTransaction().rollback();
				return user;
			}
		
		   return user;
		
	}
	
	// ע��
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

	
	//�޸��˺���Ϣ
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
