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
        User user1 = userMapper.getUserByUserName("fairy");
        System.out.println(user1.toString());

        List<User> userList = userMapper.getUserList();
        userList.stream().forEach(obj -> System.out.println(obj.toString()));
        /*-------------------------------------------------------------------*/
        // 2. 新增
        User addUser = new User();
        addUser.setUsername("lzc");
        addUser.setUserEmail("lzc@qq.com");
        addUser.setUserCity("深圳");
        addUser.setAge(18);
        userMapper.addUser(addUser);
        sqlSession.commit(); // 提交事务
        /*-------------------------------------------------------------------*/
        // 3. 删除
        userMapper.deleteUser(user1.getId());
        sqlSession.commit(); // 提交事务
        /*-------------------------------------------------------------------*/
        // 4. 修改
        User updateUser = new User();
        updateUser.setId(4);
        updateUser.setUsername("lizhencheng");
        updateUser.setUserEmail("lizhencheng@qq.com");
        updateUser.setUserCity("桂林");
        updateUser.setAge(16);
        userMapper.updateUser(updateUser);
        sqlSession.commit(); // 提交事务
        /*-------------------------------------------------------------------*/
        sqlSession.close();
    }
}
