package vip.fairy.dataSourcePool;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCPDemo {

  private static BasicDataSource basicDataSource;

  // commons-dbcp ，commons-logging ，commons-pool
  public static void main(String[] args) throws Exception {
    Demo2();
  }

  public static void Demo1() throws SQLException {
    basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    basicDataSource.setUrl("jdbc:mysql://localhost:3306/java");
    basicDataSource.setUsername("root");
    basicDataSource.setPassword("root");

    Connection connection = basicDataSource.getConnection();

    String sql = "insert into category values (?,?);";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "c04");
    prepareStatement.setString(2, "饮料");

    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据插入成功");
    } else {
      System.out.println("数据插入失败");
    }
  }

  public static void Demo2() throws Exception {
    // Properties prop = new Properties();
    // prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
    // prop.setProperty("url", "jdbc:mysql://localhost:3306/java");
    // prop.setProperty("username", "root");
    // prop.setProperty("password", "root");
    // BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(prop);

    Properties prop = new Properties();
    prop.load(new FileInputStream("src/dbcp.properties"));
    BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(prop);

    Connection connection = dataSource.getConnection();
    String sql = "insert into category values (?,?);";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    prepareStatement.setString(1, "c07");
    prepareStatement.setString(2, "药品1");

    int result = prepareStatement.executeUpdate();
    if (result > 0) {
      System.out.println("数据插入成功");
    } else {
      System.out.println("数据插入失败");
    }
  }

}
