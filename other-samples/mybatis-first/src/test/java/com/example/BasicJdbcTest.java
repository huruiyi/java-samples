package com.example;

import com.example.model.User;
import org.junit.Test;

import java.sql.*;

public class BasicJdbcTest {

  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false&useJDBCCompliantTimezoneShift=true&&serverTimezone=UTC";

  static final String USER = "root";
  static final String PASS = "root";

  @Test
  public void QueryStatementDemo() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement();
    String userName = "lison";
    String sql = "SELECT * FROM t_user where user_name='" + userName + "'";
    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
      User user = new User();
      user.setId(rs.getInt("id"));
      user.setUserName(rs.getString("user_name"));
      user.setSex(rs.getByte("sex"));
      user.setMobile(rs.getString("mobile"));
      user.setEmail(rs.getString("email"));
      user.setNote(rs.getString("note"));

      System.out.println(user.toString());
    }

    rs.close();
    stmt.close();
    conn.close();
  }

  @Test
  public void QueryPreparedStatementDemo() {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      String sql = "SELECT * FROM t_user where user_name= ? ";
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, "lison");

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("user_name"));
        user.setSex(rs.getByte("sex"));
        user.setMobile(rs.getString("mobile"));
        user.setEmail(rs.getString("email"));
        user.setNote(rs.getString("note"));
        System.out.println(user.toString());
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      se.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se2) {
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }

  }

  @Test
  public void updateDemo() {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // 启动手动提交
      conn.setAutoCommit(false);

      String sql = "update t_user  set mobile= ? where user_name= ? ";
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, "123456");
      stmt.setString(2, "lison");
      System.out.println(stmt.toString());// 打印sql
      int ret = stmt.executeUpdate();
      System.out.println("此次修改影响数据库的行数为：" + ret);

      conn.commit();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      try {
        conn.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      se.printStackTrace();
    } catch (Exception e) {
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se2) {
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
  }
}
