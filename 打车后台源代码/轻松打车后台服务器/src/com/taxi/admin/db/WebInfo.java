package com.taxi.admin.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taxi.Db.HibernateFilter;

import com.taxi.model.DrivInfo;
import com.taxi.model.PassInfo;


// 此类可以删除  08/07
public class WebInfo {
	
	private static WebInfo admin=new WebInfo();

	public static WebInfo getAdmin() {
		return admin;
	}
	
	
	// 查询所有乘客/司机 Info
	public List findallPassInfo( String Type)
	{
		Session session = null;
		List li=null;
		try
		{
			session = HibernateFilter.getSession();
			session.beginTransaction();
			Query query = null;
			if(Type.equals("PassInfo"))
			query = session.createQuery("from PassInfo a order by a.ID");
			else
				query = session.createQuery("from DrivInfo a order by a.ID");
			System.out.print("dbdbddbdbdbbdbdbd");
			 li =query.list();
			 if(li==null)
				 System.out.print("dbdbddbdbdbbdbdbd77777777777777777777777777777777777777777777");
		}
		catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			session.getTransaction().rollback();
//	     }finally {
//	     	HibernateUtils.closeSession(session);
		}	
	return li;
	}

	
	// 删除PassInfo 可以多个
	public void deletePassInfo(String[] IdList, String Type)
	{
		Session session = null;
		try
		{
			session = HibernateFilter.getSession();
			session.beginTransaction();
			if(Type.equals("PassInfo"))
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
		//记录日志,log4j等......
		e.printStackTrace();
		session.getTransaction().rollback();
//	    }finally {
//		 HibernateUtils.closeSession(session);
    }
}

	public void addPassInfo(PassInfo p)
	{
		Session session = null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}
	
	public PassInfo findPassbyId(String id)
	{
		   Session session = null;
		   PassInfo pass = null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			pass = (PassInfo)session.load(PassInfo.class, id);
			session.getTransaction().commit();
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return pass;
	}
	
	
	public void  modify(PassInfo p)
	{
		Session session = null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			session.update(p);
			session.getTransaction().commit();
		}catch(Exception e) {
			//记录日志,log4j等......
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
