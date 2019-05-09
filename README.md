---
title: 东软睿道实训杂记
data: 2018-05-07 10:00:00
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
2019.05-07 - 至今  
<img src="http://ws1.sinaimg.cn/large/006tNc79ly1g2sta9fzrjj303w027glg.jpg" width="200rpx"/>

# 目录
- Day 1 - Day 4: MyBatis
- Day 4 - Day X: Spring IOC
- Learning...

现仍处于学习阶段，随着学习程度的加深会对现有知识有新的理解以及找寻到更好的参考资料，随时会对笔记进行更新。😋  
为了获得更优阅读体验，您可移步至我的博客。  
[东软睿道实训杂记](https://ravenxu.top/2019/05/07/%E4%B8%9C%E8%BD%AF%E7%9D%BF%E9%81%93%E5%AE%9E%E8%AE%AD%E6%9D%82%E8%AE%B0/)

# Day 1 - Day 4: MyBatis
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

### 2.1. Load driver into memory 注册驱动
```
Class.forName("com.mysql.jdbc.Driver");
```
### 2.2. Get connection from database 创建connection
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
![](http://ws3.sinaimg.cn/large/006tNc79ly1g2sifw8z3tj30fz0aujrd.jpg)
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
```
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/scott
username=root
password=root
```
在mybatis-config.xml中导入
```
    <!--Load db.properties-->
    <properties resource="db.properties"></properties>
```
### 5.2. 使用类型别名
resultType省去路径前缀
```
    <typeAliases>
        <package name="com.neuedu.model.po"/>
    </typeAliases>
```
### 5.3. 一次引入所有mapper文件
```
    <mappers>
        <package name="./mapper"/>
    </mappers>
```
不过我在intellij里没一次导入成功，貌似找不到路径，只得挨个导入mapper
```
    <mappers>
        <mapper resource="mapper/DeptMapper.xml"></mapper>
        <mapper resource="mapper/EmpMapper.xml"></mapper>
        <mapper resource="mapper/ScoresMapper.xml"></mapper>
    </mappers>
```
## 6. 使用log.4j日志管理工具
[最详细的Log4J使用教程](https://blog.csdn.net/u013870094/article/details/79518028)
### 6.1. 配置文件
```
# Global logging configuration
log4j.rootLogger=DEBUG, stdout
# MyBatis logging configuration...
log4j.logger.org.mybatis.example.BlogMapper=TRACE
# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```
### 
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

## 8. Result Maps
hashmap或者po作为返回值需要字段名与属性名一致，但有时数据库字段的user_id和Java属性的驼峰式userId不一样，需要有一个映射(不用resultmap的话也可以在sql里用别名关键字`as`)
### 8.1. 一对一多表查询
### 8.2. 一对多多表查询
### 8.3. 多对多多表查询
### 8.4. LazyLoading
### 8.5. 利用`<sql>`替换重复sql代码
### 8.6. 字符串替换
因为Preparestatement后sql的结构不变
> \#和$的区别  
> 
> 理解「sql结构与变量」的概念    
> $可以改变sql的结构有sql注入的风险

## 9. 动态SQL
### 9.1. 试一下传统方法
需要大量判断或者多次查询
### 9.2. if标签
省去了判断根据属性的顺序传值的部分代码，但还是需要写不同if的and代码  
利用where + if标签完成
```
    <select id="getEmpByConditions" resultType="Emp">
      select * from emp
        <where>
            <if test="empno != 0">
                and empno = #{empno}
            </if>
            <if test="ename != null">
                and ename = #{ename}
            </if>
            <if test="job != null">
                and job = #{job}
            </if>
        </where>
    </select>
```
### 9.3. choose + when + otherwise (switch case default)
```
    <select id="getEmpByOneCondition" resultType="Emp">
        select * from emp
        <where>
            <choose>
                <when test="empno != 0">
                    and empno = #{empno}
                </when>
                <when test="ename != null">
                    and ename = #{ename}
                </when>
                <when test="job != null">
                    and job = #{job}
                </when>
            </choose>
        </where>
    </select>
```
### 9.4. Dynamic Update
```
    <update id="updateEmpByCondition">
        update emp
        <set>
            <if test="ename != null">
                ename = #{ename},
            </if>
            <if test="job != null">
                job = #{job},
            </if>
        </set>
        where empno = #{empno}
    </update>
```

### 9.5. foreach

### 9.6. 分页 limit
```
    <select id="getEmpByPage" resultType="Emp">
        select * from emp limit #{index}, #{count}
    </select>
```
## 10. MyBatis Cache
mybatis的性能是不如JDBC的，但是它通过cache等操作尽力提升了性能(虽然还是差jdbc很多)
**demo**
```
    public static void queryByPage() {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        List<Emp> emps = mapper.getEmpByPage(0, 10);
        for(Emp e : emps) {
            System.out.println(e.getEmpno() + "\t" + e.getEname() + "\t" + e.getJob());
        }

        // this time, get data from cache
        // by default, data will be cached and shared in one session
        // if you create two different sessions, data can not be shared
        List<Emp> emps2 = mapper.getEmpByPage(0, 10);
        for(Emp e : emps) {
            System.out.println(e.getEmpno() + "\t" + e.getEname() + "\t" + e.getJob());
        }
    }
```
如下图所示，同一个session里的同样的sql语句只被执行一次
![](http://ws1.sinaimg.cn/large/006tNc79ly1g2uw4nfuc0j31wk0bkwit.jpg)

## 11. Maven Project
优点
- 在一个位置管理全部依赖包
  - 只需写几行代码，导入方便
  - 自动将依赖的jar一次性下载完毕
  - 发送给别人方便，不需要附带依赖的jar包，接收方可以根据pom.xml自行导入依赖
- 有mvn命令，便于打包发布持续集成自动化构建

## 附1：本单元补充知识点
### 1. iBatis
### 2. Java Bean
### 3. sql.date and utils.date
[java.util.Date和java.sql.Date的区别及应用](https://www.cnblogs.com/IamThat/p/3264234.html)

### 4. SQL注入与Preparestatement
[预处理prepareStatement是怎么防止sql注入漏洞的？](https://www.cnblogs.com/yaochc/p/4957833.html)
## 附2：一点思考
1. MyBatis底层还是对JDBC的封装，查看运行日志可知最终运行的还是sql代码，只不过使用mybatis可以帮助我们更好地管理复杂的数据库访问代码，以更贴近Java本身的设计思想的方式还访问数据库。  
2. XML 接口文件 理解这些文件的作用需要领悟**映射**的概念。  
   一个mapper.mxl对应一个接口文件对应一个数据库的表
3. MyBatis
   MyBatis作为一个框架，还是对现有接口的封装，可以参考轻量级框架[sql2o](https://www.sql2o.org/)的设计思想自行设计一个框架
4. 存储过程的使用场景：阿里巴巴Java开发手册明确写明了禁止使用存储过程，究其原因更多是因为存储过程代码可读性极差、debug困难，对于阿里这样的大企业有其他措施弥补性能；对于东软这样的外包公司，如果是一个**需求明确**的任务还是可以写存储过程的，毕竟因为存储过程在数据库内一次性完成多个操作性能会更好。
# Day 4 - Day X: Spring IOC
## 参考资料
- [参考代码](https://github.com/Raven98/Neuedu/tree/master/SpringCore)  
- [Spring官方文档](https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/core.html)  
- [费老师著作](https://www.kancloud.cn/winter1981/spring/543484)
## 1. 了解Spring IOC
### 1.1. IOC: invertion of control
#### Demo for IOC
```
public class TestService {
    // IOC inversion of control, also called DI(dependency injection)
    private TestDAO testDAO;

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    public static void main(String[] args) {
        //get a instance of TestService from Spring
        //ask spring for instance
        //create a Spring container,
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //get instance from Spring container
        //by default, spring uses singleton mode
        TestService service = (TestService)context.getBean("testService");
        TestService service2 = (TestService)context.getBean("testService");
        TestService service3 = (TestService)context.getBean("testService");

        System.out.println(service);
        System.out.println(service2);
        System.out.println(service3);
    }
}
```

#### Ouput
```
testspringcore.TestService@6d4b1c02
testspringcore.TestService@6d4b1c02
testspringcore.TestService@6d4b1c02

Process finished with exit code 0
```
IOC的目的是要让Sring来创建我们所需要的对象，不需要通过new建立。我们在applicationContext.xml配置Java bean对象即可被spring所调用。Spring默认使用单例模式。（上面的hash值指向同一个对象）由Spring所创建的对象被放到Spring的容器中。

### 1.2.单例模式 Singleton
> 单例模式与静态类
> 观点一：（单例 ）
单例模式比静态方法有很多优势：
首先，单例可以继承类，实现接口，而静态类不能（可以集成类，但不能集成实例成员）；
其次，单例可以被延迟初始化，静态类一般在第一次加载是初始化；
再次，单例类可以被集成，他的方法可以被覆写；
最后，或许最重要的是，单例类可以被用于多态而无需强迫用户只假定唯一的实例。举个例子，你可能在开始时只写一个配置，但是以后你可能需要支持超过一个配 置集，或者可能需要允许用户从外部从外部文件中加载一个配置对象，或者编写自己的。你的代码不需要关注全局的状态，因此你的代码会更加灵活。
>
>观点二：（静态方法 ） 静态方法中产生的对象，会随着静态方法执行完毕而释放掉，而且执行类中的静态方法时，不会实例化静态方法所在的类。如果是用singleton,   产生的那一个唯一的实例，会一直在内存中，不会被GC清除的(原因是静态的属性变量不会被GC清除)，除非整个JVM退出了。这个问题我之前也想几天，并 且自己写代码来做了个实验。
>
>观点三：（Good！ ）
由于DAO的初始化，会比较占系统资源的，如果用静态方法来取，会不断地初始化和释放，所以我个人认为如果不存在比较复杂的事务管理，用 singleton会比较好。个人意见，欢迎各位高手指正。  
>参考链接:
>[静态类和单例模式区别](https://www.cnblogs.com/zhtao_tony/p/3956047.html)

### 1.3. 对Spring调用bean构造方法的探究(告诉Spring 如何构造对象)
#### 1.3.1 重写构造方法加入打印调用，发现的确有输出
#### 1.3.2 将构造器声明为private也能被创建
[反射破坏单例的私有构造函数保护](https://blog.csdn.net/tiwerbao/article/details/20838903)  
帮助理解Spring为何能做到这点
#### 1.3.3. 三种构造方法(告诉Spring怎样构建对象)
1. Constructor
```
    <bean id="testDAO" class="testspringcore.TestDAO">
    </bean>
```
最普通的方法，默认新建单例模式的对象，标签内增加`scope="prototype"`可以允许新建多个实例。
Spring会在最初的
`
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
`
步骤自动创建所有被注册的单例模式的Java bean，标签内增加`lazy-init="true"`则指定在需要时在创建。
2. 绑定getInstance()
```
    <bean id="testBean" class="testspringcore.TestBean" factory-method="getInstance" scope="prototype">
    </bean>
```
这种方法的好处是可以手动规定创建实例的方法，例如通过绑定如下getInstance方法可以规定该类最多被创建10次。
```
//only have ten instance
//This class has the function of producing instance

public class TestBean {

    private static int count = 0;

    public static TestBean getInstance()
    {
        System.out.println("factory method invoked");
        if(count<10)
        {
            count++;
            return new TestBean();
        }
        return null;
    }

}
```
3. 绑定Factory.class
```
    <!-- register factory bean -->
    <bean id="testBean2Factory" class="testspringcore.TestBean2Factory"></bean>

    <!-- register testbean2 -->
    <bean id="testBean2" factory-bean="testBean2Factory" factory-method="getInstance" scope="prototype"></bean>
```
与方法2类似，不过这时我们为要新建的bean类构造了一个对应的Factory类。