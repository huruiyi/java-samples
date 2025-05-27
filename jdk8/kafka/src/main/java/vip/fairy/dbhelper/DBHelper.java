package vip.fairy.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DBHelper {

  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://127.0.0.1:3306/kafka_demo";
  private static final String USER = "root";
  private static final String PASS = "root";
  private static Connection conn = null;
  private static PreparedStatement preparedStatement = null;

  //加载驱动，创建连接
  static {
    try {
      Class.forName(DRIVER);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("message : ClassNotFoundException");
    }
  }

  /**
   * 执行插入数据的操作，只需提供sql语句和需要的占位符参数即可
   *
   * @param sql    需要执行的sql语句
   * @param values 为占位符提供的参数
   */
  public static int insert(String sql, Object... values) {
    int result = 0;
    try {
      conn = DriverManager.getConnection(URL, USER, PASS);
      preparedStatement = conn.prepareStatement(sql);
      setParameter(values);
      result = preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }


  /**
   * @param values 要被预编译的sql语句所有占位符的值，为可变参数
   */
  public static void setParameter(Object... values) throws SQLException {
    if (values == null || values.length <= 0) {
      return;
    }
    for (int i = 0; i < values.length; i++) {
      preparedStatement.setObject((i + 1), values[i]);
    }
  }

  public static void main1(String[] args) {
    String sql = "insert into t_admin values(?,?,?)";
    Object[] values = new Object[3];
    values[0] = UUID.randomUUID().toString().replaceAll("-", "");
    values[1] = "nibudon---o";
    values[2] = UUID.randomUUID().toString().replaceAll("-", "");
    insert(sql, values);
  }

  public static void main(String[] args) {
    String sql = "insert into t_info(id,topic, message,insert_date) values(?,?,?,now());";
    Object[] values = {UUID.randomUUID().toString().replaceAll("-", ""), "zrh", "12345"};
    insert(sql, values);
  }

}
