package vip.fairy.dataSourcePool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class C3P0Demo {

  public static void main(String[] args) throws PropertyVetoException, SQLException {
    Demo3();
  }

  static void Demo1() throws PropertyVetoException, SQLException {
    ComboPooledDataSource datasource = new ComboPooledDataSource();
    datasource.setDriverClass("com.mysql.jdbc.Driver");
    datasource.setJdbcUrl("jdbc:mysql://localhost:3306/java");
    datasource.setUser("root");
    datasource.setPassword("root");

    Connection connection = datasource.getConnection();

    String sql = "insert into category values (?,?);";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "c09");
    prepareStatement.setString(2, "饮料y");

    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据插入成功");
    } else {
      System.out.println("数据插入失败");
    }
  }

  static void Demo2() throws SQLException {
    //自动读取配置文件(c3p0.properties)中的配置信息
    ComboPooledDataSource datasource = new ComboPooledDataSource();
    Connection connection = datasource.getConnection();

    String sql = "insert into category values (?,?);";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "c10");
    prepareStatement.setString(2, "饮料xy");

    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据插入成功");
    } else {
      System.out.println("数据插入失败");
    }
  }

  static void Demo3() throws SQLException {
    //读取配置文件 c3p0-config.xml 中的配置信息
    ComboPooledDataSource datasource = new ComboPooledDataSource("javadb");
    Connection connection = datasource.getConnection();

    String sql = "insert into category values (?,?);";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "c12");
    prepareStatement.setString(2, "饮料a");

    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据插入成功");
    } else {
      System.out.println("数据插入失败");
    }
  }
}
