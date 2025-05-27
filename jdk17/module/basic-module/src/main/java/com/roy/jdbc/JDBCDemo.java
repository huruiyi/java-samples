package com.roy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

public class JDBCDemo {

  @Test
  public void test() {
//    public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/coursedb?serverTimezone=UTC";
    String username = "root";
    String password = "root";
    Statement stmt = null;
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, username, password);
      //定义SQL
      String sql = "select cid from course";
      //获取执行SQL对象
      stmt = conn.createStatement();
      ResultSet resultSet = stmt.executeQuery(sql);
      while (resultSet.next()) {
        System.out.println(resultSet.getLong(1));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != stmt) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
      if (null != conn) {
        try {
          conn.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

    }


  }
}
