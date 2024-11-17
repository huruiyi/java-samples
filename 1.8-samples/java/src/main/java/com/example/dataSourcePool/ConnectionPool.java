package com.example.dataSourcePool;

import com.example.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * @author levue 自定义数据池
 */
public class ConnectionPool {

  static LinkedList<Connection> pool = new LinkedList<Connection>();

  static {
    try {
      for (int i = 0; i < 3; i++) {
        Connection connection = JdbcUtils.getConnection();
        pool.add(connection);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return 获取连接池的容量
   */
  public static int GetCapacity() {
    return pool.size();
  }

  /**
   * @return 从数据池获取一个连接
   */
  public static Connection getConnection() {
    if (pool.isEmpty()) {
      try {
        for (int i = 0; i < 3; i++) {
          Connection connection = JdbcUtils.getConnection();
          pool.add(connection);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    Connection connection = pool.removeFirst();
    ConnectionWrap connectionWrap = new ConnectionWrap(connection, pool);
    return connectionWrap;
  }


}
