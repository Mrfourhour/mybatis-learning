```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--
    1.mybatis可以使用properties来引入外部的配置文件；
        resource ： 引入类路径下的资源
        url : 引入磁盘路径下/网络路径下的资源
        通过 <property name="driver" value="${jdbc.driver}"/> 这种方式引用
    -->
    <properties resource="jdbc.properties"> </properties>

    <!--
    1.mybatis可以使用settings来设置很多重要的属性；
        比如 mapUnderscoreToCamelCase   默认为false; 若改为true则表示开启驼峰命名映射方式       lastName  ->   last_name
        <settings>
            <setting name="mapUnderscoreToCamelCase" value="true"/>
        </settings>
    -->
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
    
    <!--
    typeAliases : 别名不区分大小写,,一般在mapper文件中习惯使用全类名，方便直接点击进入
    1.typeAlias 为指定的Java类型取别名，  type为指定类型的全类名，，默认是类名小写
        alias 可以自定义别名
    2.package 为指定包名下的类批量取别名，，别名有可能冲突，，可以用注解@Alias("")单独取别名
    -->
    <typeAliases>
        <typeAlias type="com.sun.demo.jvmti.hprof.Tracker" alias="tracker"></typeAlias>
        <package name="" />
    </typeAliases>

    <!--类型处理器：mybatis3.4 之前需要手动引入日期时间类型的类型处理器，，，，3.5之后就已经引入了-->
    <typeHandlers></typeHandlers>


    <!-- environments : 可以配置多个环境
            environment ：配置一个具体的环境
                transactionManager 和 dataSource 必须有；
        通过default标签切换环境
        transactionManager ： 事务控制，，一般用spring来做事务控制
        dataSource ： 数据源
    -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!--databaseIdProvider： 支持多数据库厂商
        MySQL  Oracle
        <property name="MySQL" value="mysql"/> 取别名
        在mapper映射文件中，，用DataBaseId引用
    -->
    <databaseIdProvider type="DB_VENDOR">
        <property name="MySQL" value="mysql"/>
    </databaseIdProvider>

    <!--
    将写好的mapper文件注册到全局配置文件
        1.resource引用类路径下的资源
        2.url="" 引用磁盘路径下的资源
        3.class="" 引用注册接口
            1.有sql映射文件，映射文件必须与接口同名，并且放在同一目录下
            2.没有sql映射文件，所有的sql都是利用注解写在接口上
        4.package 包名
            批量注入  同一个目录下，，文件名相同
    -->
    <mappers>
        <mapper  url="" resource="mapper\UserMapper.xml"/>
    </mappers>
</configuration>
```