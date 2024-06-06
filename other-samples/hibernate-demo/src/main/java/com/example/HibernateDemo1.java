package com.example;

import com.example.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateDemo1 {

  public static void main(String[] args) {
    // 1.加载Hibernate的核心配置文件
    Configuration configuration = new Configuration().configure();
    // 手动加载映射
    configuration.addResource("Customer.hbm.xml");
    // 2.创建一个SessionFactory对象：类似于JDBC中连接池
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    // 3.通过SessionFactory获取到Session对象：类似于JDBC中Connection
    Session session = sessionFactory.openSession();
    // 4.手动开启事务：
    Transaction transaction = session.beginTransaction();
    // 5.编写代码

    Customer customer = new Customer();
    customer.setCust_name("小曼儿");

    session.save(customer);

    // 6.事务提交
    transaction.commit();
    // 7.资源释放
    session.close();

    sessionFactory.close();

  }
}
