package vip.fairy.demoE;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextE.xml")
public class JdbcDemo4 {

  @Resource(name = "jdbcTemplate_c3p0")
  private JdbcTemplate jdbcTemplate;

  @Test
  public void demo0() throws PropertyVetoException {

    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springjdbc");
    dataSource.setUser("root");
    dataSource.setPassword("root");
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
  }

  @Test
  public void demo() throws PropertyVetoException {

    int result = 0;
    try {
      result = jdbcTemplate.update("insert into account values(null,?,?)", "小宝", 10000d);

    } catch (Exception e) {
      System.err.println(e);
    }
    if (result > 0) {
      System.out.println("添加成功!");
    } else {
      System.out.println("添加失败!");
    }
  }
}
