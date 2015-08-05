package com.taxi.Db;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFilter implements Filter {

private static ThreadLocal hibernateHolder = new ThreadLocal();
	
	private static SessionFactory factory = null; 
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			Session session = (Session)hibernateHolder.get();                                  // 浏览器response后就会到 此处   渲染完毕   关闭session
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
				hibernateHolder.remove();
			}
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}	
	}
	
	public static Session getSession() {
		Session session = (Session)hibernateHolder.get();
		if (session == null) {
			session = factory.openSession();
			hibernateHolder.set(session);
		}
		return session;
	}

}
