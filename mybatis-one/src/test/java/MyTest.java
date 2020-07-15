import com.xinyet.mapper.UserMapper;
import com.xinyet.pojo.User;
import com.xinyet.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(2);
        System.out.println(user);

        MybatisUtil.closeSqlSession(sqlSession);
    }

    @Test
    public void getAllUsers(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        MybatisUtil.closeSqlSession(sqlSession);
    }


    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("刘慧敏");
        user.setAge(24);
        userMapper.addUser(user);
        System.out.println(user);

        sqlSession.commit();//要添加事务
        MybatisUtil.closeSqlSession(sqlSession);
    }


    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("刘慧敏");
        user.setAge(25);
        user.setId(2);
        userMapper.updateUser(user);
        sqlSession.commit();

        MybatisUtil.closeSqlSession(sqlSession);
    }


    @Test
    public void deleteUserById(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUserById(2);

        sqlSession.commit();
        MybatisUtil.closeSqlSession(sqlSession);
    }
}
