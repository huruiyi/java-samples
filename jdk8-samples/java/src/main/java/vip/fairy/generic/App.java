package vip.fairy.generic;

import vip.fairy.generic.dao.BaseDao;
import vip.fairy.generic.service.impl.OrderDaoImpl;
import vip.fairy.generic.service.impl.ProductDaoImpl;

public class App {

  public static void main(String[] args) {
    BaseDao<?> orderDao = new OrderDaoImpl();
    orderDao.findById(1L);

    BaseDao<?> productDao = new ProductDaoImpl();
    productDao.findById(1L);
  }

}
