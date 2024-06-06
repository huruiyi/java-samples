package com.example.dataSourcePool;

import java.sql.Connection;
import java.sql.SQLException;

public class DBPoolDemo {

  public static void main(String[] args) throws SQLException {
    Connection connection = ConnectionPool.getConnection();
    connection.close();
  }

}
