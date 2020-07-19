# mybatis-01-helloworld

## 一、学习环境
  - maven
  - mybatis 3.5.0
  - jdk 8
  - idea 2020.1.2
  - mysql 8.0
  - 学习基于官方文档

## 二、helloworld 项目记录

### 1.引入mybatis jar包
```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.0</version>
    </dependency>

    <!--用于测试 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.20</version>
    </dependency>

</dependencies>
```
### 2.从xml文档中构建出 SqlSessionFactory
```xml
<?xml version="1.0" encoding="UTF-8" ?> 
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="org/mybatis/example/BlogMapper.xml"/>
    </mappers>
</configuration>
```
```java
public class MyTest {

    @Test
    public void getUserById() {
        //加载xml配置文件
        String resource = "org/mybatis/example/mybatis-config.xml";
        //获取输入流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取SqlSession
        SqlSession session = sqlSessionFactory.openSession();
    }
}
```

### 3.创建实体类和表
```java
public class User {

    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```
    数据库记录       
        id name age   
        3 张三 18   
        4 李四 20   
### 4.编写mapper映射文件，并注册到主配置文件
```xml
<?xml version="1.0" encoding="UTF-8" ?> <!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xinyet.mapper.UserMapper">
    <select id="getUserById" resultType="com.xinyet.pojo.User">
    select * from user where id = #{id}  
    </select>
</mapper>
```
### 5.连接数据库，并获取记录
```java
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
```
