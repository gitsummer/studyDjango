package com.taxi.admin.db;

import org.hibernate.Session;

import com.taxi.Db.HibernateFilter;
import com.taxi.model.PassInfo;
import com.taxi.model.User;



//此类可以删除 08/07
public class WebUser {
	
	private static WebUser adminUser=new WebUser();

	public static WebUser getAdminUser() {
		return adminUser;
	}
	
	public void addUser(User us)
	{
		Session session = null;
		try {
			//session = HibernateUtils.getSession();
			session = HibernateFilter.getSession();
			session.beginTransaction();
			session.save(us);
			session.getTransaction().commit();
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			session.getTransaction().rollback();
		}		
	}
	
	public User findUserbyId(String Id)
	{
		   Session session = null;
		   User us = null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			us = (User)session.load(User.class, Id);
			session.getTransaction().commit();
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return us;	
		
		
	}

	
	public void modify(User us)
	{
		Session session = null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			session.update(us);
			session.getTransaction().commit();
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			session.getTransaction().rollback();
		}	
		
		
		
	}
}
