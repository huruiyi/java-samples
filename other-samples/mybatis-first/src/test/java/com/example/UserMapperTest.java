package com.example;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeClass
  public static void init() {
    try {
      InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      inputStream.close();
    } catch (IOException ignore) {
      ignore.printStackTrace();
    }
  }

  @Test
  public void selectByPrimaryKey() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    System.err.println("查询Id<100的用户！！！");
    for (int i = 1; i < 10; i++) {
      User user = mapper.selectByPrimaryKey(i);
      if (user != null) {
        System.out.println(user.toString());
      }
      System.out.println("********************************************************");
    }
  }

  @Test
  public void selectAllUsers() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    System.err.println("查询所有用户信息！！！");
    List<User> users = mapper.selectAllUsers();
    for (User user : users) {
      System.out.println(user);
    }
  }

}
