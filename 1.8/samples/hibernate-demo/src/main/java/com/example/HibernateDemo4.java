package com.example;

import com.example.entities.Customer;
import com.example.entities.Customer6;
import com.example.utils.HibernateUtils;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.jupiter.api.Test;

public class HibernateDemo4 {

  /**
   * Hibernate 持久化类的三种状态
   */
  @Test
  public void demo1() {
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();

    // 瞬时态对象,没有唯一标识OID,没有被Session管理
    Customer customer = new Customer();
    customer.setCust_name("小明");
    customer.setCust_mobile("13645621236");

    // 持久化对象,有唯一标识OID,被Session管理
    Serializable id = session.save(customer);
    session.get(Customer.class, id);
    transaction.commit();
    session.close();

    // 托管状态,有唯一标识OID,没有被Session管理
    System.out.println("客户名称:" + customer.getCust_name());
  }

  /**
   * 一级缓存测试
   */
  @Test
  public void demo2() {
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();

    // 当查询相同数据时, 只会向数据库发送一次请求SQL,第二次从一级缓存中获取
    Customer customer1 = session.get(Customer.class, 1l);
    System.out.println(customer1);

    Customer customer2 = session.get(Customer.class, 1l);
    System.out.println(customer2);

    // customer2从一级缓存中取的
    System.out.println(customer1 == customer2);// true

    transaction.commit();
    session.close();
  }

  /**
   * 一级缓存清空测试
   */
  @Test
  public void demo3() {
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();

    // 当查询相同数据时, 只会向数据库发送一次请求SQL,第二次从一级缓存中获取
    Customer customer1 = session.get(Customer.class, 1l);
    System.out.println(customer1);

    // 清空所有缓存
    session.clear();
    // 清空指定缓存
    // session.evict(customer1);
    // 一级缓存被清空, 会再次发送请求SQL
    Customer customer2 = session.get(Customer.class, 1l);
    System.out.println(customer2);

    // customer2 从数据库再次请求的
    System.out.println(customer1 == customer2); // false

    transaction.commit();
    session.close();
  }

  /**
   * 需要开启:getCurrentSession
   * <property name="hibernate.current_session_context_class">thread</property>
   * 事务测试
   */
  @Test
  public void demo4() {
    Session session = HibernateUtils.getCurrentSession("hibernate6.cfg.xml");
    Transaction transaction = session.beginTransaction();

    Customer6 customer = null;

    for (int i = 0; i < 50; i++) {
      customer = new Customer6();
      customer.setP_cust_name("小花_" + i);
      customer.setCust_mobile("13645621236");
      session.save(customer);
    }
    transaction.commit();
    // 事务提交后session 就已经关闭了
    // session.close();
  }

  /**
   * HQL :Hibernate Query Language, 查询所有数据
   */
  @Test
  public void demo5() {
    Session session = HibernateUtils.getCurrentSession("hibernate6.cfg.xml");
    Transaction transaction = session.beginTransaction();

    String hql = "from Customer6";
    Query query = session.createQuery(hql);

    @SuppressWarnings("unchecked")
    List<Customer6> list = query.list();
    for (Customer6 customer : list) {
      System.out.println(customer);
    }
    transaction.commit();
  }

  /**
   * HQL :Hibernate Query Language, 查询指定数据
   */
  @Test
  public void demo6() {
    Session session = HibernateUtils.getCurrentSession("hibernate6.cfg.xml");
    Transaction transaction = session.beginTransaction();

    String hql = "from Customer6 where cust_name like ?";
    Query query = session.createQuery(hql);
    query.setParameter(0, "小%");
    @SuppressWarnings("unchecked")
    List<Customer6> list = query.list();
    for (Customer6 customer : list) {
      System.out.println(customer);
    }
    transaction.commit();
  }

  /**
   * HQL :Hibernate Query Language ,查询指定数据 分页查询
   */
  @Test
  public void demo7() {
    Session session = HibernateUtils.getCurrentSession("hibernate6.cfg.xml");
    Transaction transaction = session.beginTransaction();

    String hql = "from Customer6 t where t.p_cust_name = '小花_1'";
    Query query = session.createQuery(hql);

    // 从第六条记录开始
    // query.setFirstResult(6);
    // 查询5条记录
    // query.setMaxResults(5);

    @SuppressWarnings("unchecked")
    List<Customer6> list = query.list();
    for (Customer6 customer : list) {
      System.out.println(customer);
    }
    transaction.commit();
  }

  /**
   * Criteria Query
   */
  @Test
  public void demo8() {
    Session session = HibernateUtils.getCurrentSession("hibernate6.cfg.xml");
    Transaction transaction = session.beginTransaction();

    Criteria criteria = session.createCriteria(Customer6.class);

    // 等价于 criteria.add(Restrictions.like("cust_name", "%2%"));
    criteria.add(Restrictions.like("cust_name", "2", MatchMode.ANYWHERE));
    // 从第六条记录开始
    criteria.setFirstResult(6);
    // 查询5条记录
    criteria.setMaxResults(5);
    @SuppressWarnings("unchecked")
    List<Customer6> list = criteria.list();
    for (Customer6 customer : list) {
      System.out.println(customer);
    }
    transaction.commit();
  }

  /**
   * SQLQuery
   */
  @Test
  public void demo9() {
    Session session = HibernateUtils.getCurrentSession("hibernate6.cfg.xml");
    Transaction transaction = session.beginTransaction();

    SQLQuery query = session.createSQLQuery("select * from cst_customer").addEntity(Customer6.class);

    @SuppressWarnings("unchecked")
    List<Customer6> list = query.list();
    for (Customer6 customer : list) {
      System.out.println(customer);
    }
    transaction.commit();
  }
}
