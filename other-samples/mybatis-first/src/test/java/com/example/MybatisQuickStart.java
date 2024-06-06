package com.example;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisQuickStart {

  private SqlSessionFactory sqlSessionFactory;

  @Before
  public void init() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    // 1.读取mybatis配置文件创SqlSessionFactory
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    inputStream.close();
  }

  @Test
  // 快速入门
  public void quickStart() throws IOException {
    // 2.获取sqlSession
    SqlSession sqlSession = sqlSessionFactory.openSession();

    System.out.println(sqlSession.getConnection().toString());

    // 3.获取对应mapper
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    // 4.执行查询语句并返回结果
    User user = mapper.selectByPrimaryKey(1);
    System.out.println(user.toString());
  }
}
