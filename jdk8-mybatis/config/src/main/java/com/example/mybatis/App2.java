package com.example.mybatis;

import com.example.mybatis.entity.User;
import com.example.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * 使用原生接口
 */
public class App2 {
    public static void main( String[] args ) {
        // 加载 MyBatis 配置文件
        InputStream is = App2.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        // 获取 SqlSession, 代表和数据库的一次会话, 用完需要关闭
        // SqlSession 和Connection, 都是非线程安全的, 每次使用都应该去获取新的对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取实现接口的代理对象
        // UserMapper 并没有实现类, 但是mybatis会为这个接口生成一个代理对象(将接口和xml绑定)
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 1. 查询
        List<User> userList = userMapper.getUserList();
        userList.stream().forEach(obj -> System.out.println(obj.toString()));
        sqlSession.close();
    }
}