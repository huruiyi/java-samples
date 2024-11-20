package vip.fairy.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import vip.fairy.utils.JdbcUtils;

public class MySqlTest {


  @Test
  public void Insert() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
    String sql = "insert into category (`cid`, `cname`) values (?,?)";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "c004");
    prepareStatement.setString(2, "食品");
    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据插入成功");
    } else {
      System.out.println("数据插入失败");
    }
    prepareStatement.close();
    connection.close();
  }

  @Test
  public void Delete() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
    String sql = "delete from category where cid= ?";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "c004");
    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据删除成功");
    } else {
      System.out.println("数据删除失败");
    }
    prepareStatement.close();
    connection.close();
  }


  @Test
  public void Update() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
    String sql = "update category set cname = ? where cid = ?";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "手机X");
    prepareStatement.setString(2, "c03");
    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据更新成功");
    } else {
      System.out.println("数据更新失败");
    }
    prepareStatement.close();
    connection.close();
  }


  @Test
  public void Query() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
    String sql = "select * from category";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    ResultSet resultSet = prepareStatement.executeQuery(sql);
    while (resultSet.next()) {
      System.out.println(resultSet.getString("cid") + " " + resultSet.getString("cname"));
    }
    resultSet.close();
    prepareStatement.close();
    connection.close();
  }

  @Test
  public void UserJdbcUtils() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = JdbcUtils.getConnection();
      String sql = "select * from category";
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery(sql);
      while (resultSet.next()) {
        System.out.println(resultSet.getString("cid") + " " + resultSet.getString("cname"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JdbcUtils.closeResource(connection, preparedStatement, resultSet);
    }
  }
}
