package com.xinyet.test;

import com.xinyet.mapper.UserMapper;
import com.xinyet.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyTest {

    @Test
    public void getUserById() {
        //加载xml配置文件
        String resource = "mybatis-config.xml";
        //获取输入流
        InputStream inputStream = null;
        SqlSession session = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            //获取SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //获取SqlSession
            session = sqlSessionFactory.openSession();

            //不推荐使用
            User user = session.selectOne("com.xinyet.mapper.UserMapper.getUserById", 3);
            System.out.println(user);

            //推荐使用
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user1 = mapper.getUserById(4);
            System.out.println(user1);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
