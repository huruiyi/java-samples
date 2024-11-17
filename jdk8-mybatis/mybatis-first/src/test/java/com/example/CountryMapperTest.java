package com.example;

import com.example.mapper.CountryMapper;
import com.example.model.Country;
import java.util.ArrayList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeClass
  public static void init() {
    try {
      Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      reader.close();
    } catch (IOException ignore) {
      ignore.printStackTrace();
    }
  }

  @Test
  public void testSelectAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      List<Country> countryList = sqlSession.selectList("selectAll");
      printCountryList(countryList);
    } finally {
      sqlSession.close();
    }
  }


  @Test
  public void mapperSelectAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
      List<Country> countryList = mapper.selectAll();
      printCountryList(countryList);
    } finally {
      sqlSession.close();
    }
  }

  /**
   * 二级缓存是SqlSessionFactory级别的，整个应用程序只有一个 以namespace划分缓存区域
   */
  @Test
  public void cacheTest() {
    try {
      //SqlSession1,使用SqlSession查询第一次查询数据
      SqlSession sqlSession1 = sqlSessionFactory.openSession();
      CountryMapper countryMapper1 = sqlSession1.getMapper(CountryMapper.class);
      //处理,假设从客户端传递过来要删除的多个参数为一个集合
      List<Integer> list1 = new ArrayList<>();
      list1.add(11);
      list1.add(22);
      System.out.println("SqlSession1查询id=1,2的数据为" + countryMapper1.findCountry(list1));
      sqlSession1.commit();
      sqlSession1.close();//SqlSession关闭时会将数据写入二级缓存
      System.out.println("----------------------------------");

      //SqlSession2,使用SqlSession查询第二次查询数据
      SqlSession sqlSession2 = sqlSessionFactory.openSession();
      CountryMapper countryMapper2 = sqlSession2.getMapper(CountryMapper.class);
      //处理,假设从客户端传递过来要删除的多个参数为一个集合
      List<Integer> list2 = new ArrayList<>();
      list2.add(11);
      list2.add(22);
      System.out.println("SqlSession2查询id=1,2的数据为" + countryMapper2.findCountry(list2));
      sqlSession2.commit();
      sqlSession2.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private void printCountryList(List<Country> countryList) {
    for (Country country : countryList) {
      System.out.printf("%-4d    %s    %s\n", country.getId(), country.getCountryCode(), country.getCountryName());
    }
  }
}
