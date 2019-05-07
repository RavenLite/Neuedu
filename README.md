---
title: 东软睿道实训杂记
data: 2018-05-07 15:58:00
category: "Java"
tags: 
    - 东软睿道
    - 实习
    - Java
    - 互联网架构
    - MyBatis
---

# Neuedu
Neuedu (东软睿道)  
实训内容：Java互联网架构基础知识  
2019.05-07  
<img src="https://ws1.sinaimg.cn/large/006tNc79ly1g2sta9fzrjj303w027glg.jpg" width="200rpx"/>

# Day 1 - Day X: MyBatis
[参考代码](https://github.com/Raven98/Neuedu/tree/master/TestMyBatis)  
[MyBatis 英文文档](http://www.mybatis.org/mybatis-3/)
[MyBatis 中文文档](http://www.mybatis.org/mybatis-3/zh/getting-started.html)  
本文内容很大程度地参考了官方文档，mybatis有中文版本的文档并且极其简洁，推荐阅读
## 1. 了解MyBatis
- 优秀的持久层框架
- 支持定制化 SQL、存储过程以及高级映射
- 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集
- 使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录

<!--more-->

## 2. 传统的JDBC
MyBatis是对JDBC的封装，使用JDBC我们需要以下步骤：

### 2.1. Load driver into memory
```
Class.forName("com.mysql.jdbc.Driver");
```
### 2.2. Get connection from database
```
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "root");
```
### 2.3. Create preparedStatement to write a Sql
```
PreparedStatement stat = conn.prepareStatement("select * from dept where deptno = ?");
```
### 2.4. Replace all ? with actual value
```
stat.setInt(1, 10);
```
### 2.5 execute sql
```
ResultSet rs = stat.executeQuery();
```
### 2.6. get the result
```
if(rs.next())
{
	int deptno = rs.getInt("deptno");
	String dname = rs.getString("dname");
	String loc = rs.getString("loc");
	
	System.out.println(deptno+"\t"+dname+"\t"+loc);
}
```

## 3. 试一试MyBatis
### 3.1. 从 XML 中构建 SqlSessionFactory
每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先定制的 Configuration 的实例构建出 SqlSessionFactory 的实例。
```
String resource = "mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
```
也可以不从XML而是直接在Java中构建SqlSessionFactory，不做详细讨论。
### 3.2. 从 SqlSessionFactory 中获取 SqlSession
既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。例如：
```
SqlSession session = sqlSessionFactory.openSession();
```
```
Dept d = session.selectOne("com/neuedu/model/DeptMapper.java.selectDept", 20);
System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());
```
诚然，这种方式能够正常工作，并且对于使用旧版本 MyBatis 的用户来说也比较熟悉。不过现在有了一种更简洁的方式 ——使用正确描述每个语句的参数和返回值的接口（比如 DeptMapper.class），你现在不仅可以执行更清晰和类型安全的代码，而且还不用担心易错的字符串字面值以及强制类型转换。  
例如：
```
DeptMapper mapper = session.getMapper(DeptMapper.class);
Dept d = mapper.selectDept(20);
System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());
```
>新旧方式对比(个人理解)
![](https://ws3.sinaimg.cn/large/006tNc79ly1g2sifw8z3tj30fz0aujrd.jpg)
旧方式业务代码直接根据(namespace+id)调用XML里面的某一个方法  
新方式将XML和接口双向绑定，业务代码不需要知道XML文件内容，直接调用接口。  
一个显而易见的变化就是旧方式的namespace随便写，而新方式必须为xml文件所在路径  
第二种方法有很多优势，首先它不依赖于字符串字面值，会更安全一点； 其次，如果你的 IDE 有代码补全功能，那么代码补全可以帮你快速选择**已映射的 SQL 语句**。  
这样做的好处在学习初期还不能完全理解，随着学习的加深能逐渐领悟  
### 3.3. 获取查询结果
```
	//to iterate this list
	Iterator<Dept> it = d.iterator();
	while(it.hasNext())
	{
		Dept item = it.next();
		//output its content
		System.out.println(item.getDeptno()+"\t"+item.getDname()+"\t"+item.getLoc());
	}
```
## 4. 对比JDBC和MyBatis
直观的代码改变是MyBatis需要一个主XML配置文件，需要根据业务需求写一些mapper XML文件，需要为每个mapper XML文件编写对应的接口文件，需要为数据库内实体编写对应的Java类。  
多了很多文件，但一定有很多好处：  
[参考资料](https://www.cnblogs.com/love-Stefanie/p/6838269.html)
### 4.1. 优化获取和释放  
我们一般在访问数据库时都是通过数据库连接池来操作数据库，数据库连接池有好几种，比如C3P0、DBCP，也可能采用容器本身的JNDI数据库连接池。我们可以通过DataSource进行隔离解耦，我们统一从DataSource里面获取数据库连接，DataSource具体由DBCP实现还是由容器的JNDI实现都可以，所以我们将DataSource的具体实现通过让用户配置来应对变化。
### 4.2. SQL统一管理，对数据库进行存取操作  
我们使用JDBC对数据库进行操作时，SQL查询语句分布在各个Java类中，这样可读性差，不利于维护，当我们修改Java类中的SQL语句时要重新进行编译。  
Mybatis可以把SQL语句放在配置文件中统一进行管理，以后修改配置文件，也不需要重新就行编译部署。
### 4.3. 生成动态SQL语句  
我们在查询中可能需要根据一些属性进行组合查询，比如我们进行商品查询，我们可以根据商品名称进行查询，也可以根据发货地进行查询，或者两者组合查询。如果使用JDBC进行查询，这样就需要写多条SQL语句。  
Mybatis可以在配置文件中通过使用`<if test=””></if>`标签进行SQL语句的拼接，生成动态SQL语句。比如下面这个例子：
```
<select id="getCountByInfo" parameterType="User" resultType="int">
        select count(*) from user
        <where>
            <if test="nickname!=null">
                and nickname = #{nickname} 
            </if>
            <if test="email!=null">
                and email = #{email} 
            </if>
        </where>
</select>
```
就是通过昵称或email或者二者的组合查找用户数。  
### 4.4. 能够对结果集进行映射（手动->自动）  
我们在使用JDBC进行查询时，返回一个结果集ResultSet，我们要从结果集中取出结果封装为需要的类型。  
在Mybatis中我们可以设置将结果**直接**映射为自己需要的类型，比如：JavaBean对象、一个Map、一个List等等。
## 5. 对mybatis-config.xml(主配置文件)的配置简化
### 5.1. 使用properties管理变量
数据库driver url username password等
### 5.2. 使用类型别名
### 5.3. 一次引入所有mapper文件
## 6. 使用log.4j日志管理工具

## 7. 尝试更多sql
1. like语句
2. 更多变量
3. 插入
4. 插入递增字段
5. 插入一个对象instance
6. 指定返回字段
7. 利用hashmap传参
8. update
9. delete
10. 多表查询
    - 返回值不能是po了
    - 在涉及到的表的mapper选一个。一般选主要的一个

## 8. 进阶学习
### 8.1. 利用<sql>替换重复sql代码
### 8.2. 字符串替换
因为Preparestatement后sql的结构不变
> \#和$的区别  
> 
> 理解「sql结构与变量」的概念    
> $可以改变sql的结构有sql注入的风险

### 8.3. resultMap
解决po属性名和表的字段名不一致的问题  
或者在sql语句中使用别名

## 附1：本单元补充知识点
### 1. iBatis
### 2. Java Bean
### 3. sql.date and utils.date
### 4. SQL注入与Preparestatement
## 附2：一点思考
1. MyBatis底层还是对JDBC的封装，查看运行日志可知最终运行的还是sql代码，只不过使用mybatis可以帮助我们更好地管理复杂的数据库访问代码，以更贴近Java本身的设计思想的方式还访问数据库。  
2. XML 接口文件 理解这些文件的作用需要领悟**映射**的概念。  
   一个mapper.mxl对应一个接口文件对应一个数据库的表
3. MyBatis
   MyBatis作为一个框架，还是对现有接口的封装，可以参考轻量级框架[sql2o](https://www.sql2o.org/)的设计思想自行设计一个框架

