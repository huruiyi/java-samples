package vip.fairy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtils {

  static final String DRIVERCLASS;
  static final String URL;
  static final String USER;
  static final String PASSWORD;

  static {
    ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    DRIVERCLASS = bundle.getString("driverClass");
    URL = bundle.getString("url");
    USER = bundle.getString("user");
    PASSWORD = bundle.getString("password");
  }

  static {
    try {
      Class.forName(DRIVERCLASS);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
    closeResultSet(resultSet);
    closePreparedStatement(preparedStatement);
    closeConnection(connection);
  }

  private static void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      connection = null;
    }
  }

  private static void closePreparedStatement(PreparedStatement preparedStatement) {
    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      preparedStatement = null;
    }
  }

  private static void closeResultSet(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      resultSet = null;
    }
  }
}
