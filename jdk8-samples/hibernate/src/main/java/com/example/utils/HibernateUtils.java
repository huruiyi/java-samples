package com.example.utils;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate的工具类
 */
public class HibernateUtils {

//	public static final Configuration cfg;
//	public static final SessionFactory sf;
//
//	static {
//		cfg = new Configuration().configure();
//		sf = cfg.buildSessionFactory();
//	}
//
//	public static Session openSession() {
//		return sf.openSession();
//	}

  public static Session openSession() {
    return new Configuration().configure().buildSessionFactory().openSession();
  }

  public static Session openSession(String cfgxml) {
    return new Configuration().configure(cfgxml).buildSessionFactory().openSession();
  }

  public static Session getCurrentSession() {
    //Session不需要手动关闭, 线程,事务结束后自动关闭
    return new Configuration().configure().buildSessionFactory().getCurrentSession();
  }

  public static Session getCurrentSession(String cfgxml) {
    //Session不需要手动关闭, 线程,事务结束后自动关闭
    return new Configuration().configure(cfgxml).buildSessionFactory().getCurrentSession();
  }
}
