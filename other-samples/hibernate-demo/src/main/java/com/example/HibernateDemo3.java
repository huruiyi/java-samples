package com.example;

import com.example.entities.Customer2;
import com.example.entities.Customer3;
import com.example.entities.Customer4;
import com.example.entities.Customer5;
import com.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

public class HibernateDemo3 {

  @Test
  // increment create ,主键不自增 ,多线程报错, ,主键Id为整形
  // <property name="hibernate.hbm2ddl.auto">create</property>
  public void demo1() {
    Session session = HibernateUtils.openSession("hibernate2.cfg.xml");
    Transaction transaction = session.beginTransaction();

    Customer2 customer = new Customer2();
    customer.setCust_name("小明");
    customer.setCust_mobile("13645621236");
    session.save(customer);

    transaction.commit();
    session.close();
  }

  @Test
  // increment create,主键不自增 ,多线程报错, ,主键Id为整形
  // <property name="hibernate.hbm2ddl.auto">create</property>
  public void demo2() {
    Session session = HibernateUtils.openSession("hibernate2.cfg.xml");
    Transaction transaction = session.beginTransaction();

    Customer2 customer = new Customer2();
    customer.setCust_name("小花");
    customer.setCust_mobile("13645621236");
    session.save(customer);

    transaction.commit();
    session.close();
  }

  @Test
  // identity 数据库底层自动增长 ,主键Id为整形
  public void demo3() {
    Session session = HibernateUtils.openSession("hibernate3.cfg.xml");
    Transaction transaction = session.beginTransaction();

    Customer3 customer = new Customer3();
    customer.setCust_name("小花");
    customer.setCust_mobile("13645621236");
    session.save(customer);

    transaction.commit();
    session.close();
  }

  @Test
  // uuid 主键Id为字符串类型
  public void demo4() {
    Session session = HibernateUtils.openSession("hibernate4.cfg.xml");
    Transaction transaction = session.beginTransaction();

    Customer4 customer = new Customer4();
    customer.setCust_name("小花");
    customer.setCust_mobile("13645621236");
    session.save(customer);

    transaction.commit();
    session.close();
  }

  @Test
  // assigned 主键Id需要自己设置 ,主键Id为整形
  public void demo5() {
    Session session = HibernateUtils.openSession("hibernate5.cfg.xml");
    Transaction transaction = session.beginTransaction();

    Customer5 customer = new Customer5();
    customer.setCust_id(1000L);
    customer.setCust_name("小花");
    customer.setCust_mobile("13645621236");
    session.save(customer);

    transaction.commit();
    session.close();
  }
}
