# 1、Ioc

- Ioc(Inversion of Control)控制反转
- 对象的创建控制权由程序转移到**外部**,这种思想称为控制反转
- 在使用对象时,由主动new产生对象转换为由外部提供对象,此过程中对象创建控制权由程序转移到外部,此思想称为控制反转
- Spring技术提供了一个容器,称为**IoC容器**,用来充当IoC思想中的**外部**
- IoC容器负责对象的创建.初始化等一系列工作,被创建或被管理的对象在IoC容器中称为**Bean**

- **DI(Dependency Injection) 依赖注入**

- 在容器中建立bean与bean之间的依赖关系的整个过程,称为依赖注入

  ## 目标

- 目标都是为了充分解耦

### 入门案例(XML版本)

1. 导入Spring坐标

   ```xml
   <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-context</artifactId>
              <version>5.2.10.RELEASE</version>
   </dependency>
   ```

2.定义Spring管理的类(接口)

```java
public interface BookService
{
   public void save();
}
```

```java
public class BookServicelmpl implements BookService
{
    private BookDao bookDao=new BookDaoImpl();
    public void save()
    {
        System.out.println("book service save...");
        bookDao.save();
    }
}
```

3.创建Spring配置文件,配置对应类作为Spring管理的Bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="bookDao" class="lyh.dao.impl.BookDaoImpl"/>
    <bean id="bookService" class="lyh.service.impl.BookServicelmpl"/>
</beans>
```

4.在主函数中获取IoC容器

```java
public class App2
{
    public static void main(String[] args) {
        //获取IoC容器
        ApplicationContext atc=new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取Bean
/*        BookDao bookDao = (BookDao) atc.getBean("bookDao");
        bookDao.save();*/

        BookService bookService = (BookService) atc.getBean("bookService");
        bookService.save();
    }
}
```

### 静态工厂实例化

主要代码在factory工厂:

```java
public class OrderDaoFactory
{
    public static OrderDao getOrderDao()
    {
        System.out.println("factory setup.....");
        return new OrderDaoImpl();
    }
}
```

和xml文件中配置

```xml
<!--配置关系 利用静态工厂的方法来配置-->
    <bean id="orderDao" class="lyh.factoty.OrderDaoFactory" factory-method="getOrderDao"/>
```

主函数中:

```java
 //利用工厂创建对象
        OrderDao orderDao= OrderDaoFactory.getOrderDao();
        orderDao.save();
```

### 实例化工厂

- 其实和之前的IoC容器的获取差不多

xml中:

```xml
<!--方式三:使用实例化工厂实例化bean-->
<!--这个就是在实例化-->
    <bean id="userFactory" class="lyh.factory.UserDaoFactory"/>
<!--其实就是把上面bean实例化的东西再直接使用-->
    <bean id="userDao" factory-method="getUserDao" factory-bean="userFactory"/>
```

主函数:

```java
ApplicationContext atc=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao=(UserDao) atc.getBean("userDao");
        userDao.save();
```

UserDaoFactory中的代码:

```java
public class UserDaoFactory
{
    public UserDao getUserDao()
    {
        return new UserDaoImpl();
    }
}
```

### 生命周期

- 初始化容器

  1.创建对象(内存分配)

  2.执行构造方法

  3.执行属性注入(set操作)

  4.**执行bean初始化方法**

- 使用bean
  1. 执行业务操作

- 关闭/销毁容器
  1. **执行bean销毁方法**

- 容器关闭前触发bean的销毁

- 关闭容器方式:

  - 手工关闭容器

  ```java
  ConfigurableApplicationContext cac=new ClassPathXmlApplicationContext();
  cac.close();
  ```

  - 注册关闭钩子,在虚拟机关闭前先关闭容器

  ```java
  ConfigurableApplicationContext cac=new ClassPathXmlApplicationContext();
          cac.registerShutdownHook();
  ```

  

### DI 依赖注入方式

1. setter注入

在service的BookServicelmpl中

```java
public class BookServicelmpl implements BookService
{
    private BookDao bookDao;
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save()
    {
        System.out.println("book service save...");
        bookDao.save();
        userDao.save();
    }

    //提供对应的set方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
```

然后再dao中impl中

```java
public class BookDaoImpl implements BookDao
{
    private int num;
    private String databaseName;

    public void setNum(int num) {
        this.num = num;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void save()
    {
        System.out.println("book dao save..."+databaseName+","+num);
    }
}
```

主函数主要就是:

```java
ApplicationContext atc=new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService=(BookService) atc.getBean("bookService");
        bookService.save();
```

xml中配置:

```xml
   <!--这里配置的是bookDao中的基础数据类型-->
	<bean id="bookDao" class="lyh.dao.impl.BookDaoImpl">
        <property name="databaseName" value="mysqlLYH"></property>
        <property name="num" value="400"></property>
    </bean>
    <bean id="userDao" class="lyh.dao.impl.UserDaoImpl"/>
	
	<!--这里配置的是bookService中的-->
    <bean id="bookService" class="lyh.service.impl.BookServicelmpl">
        <!-- 配置server与dao关系-->
      <!--  property标签表示配置当前bean的属性
        name属性表示配置哪一个的具体属性
        ref表示bean id的属性-->
        <property name="bookDao" ref="bookDao"/>
        <property name="userDao" ref="userDao"/>
    </bean>
```

### 依赖自动装配

自动装配特征:

- 自动装配用于引用类型依赖注入,不能对简单类型进行操作
- 使用按类型装配时(byType)必须保障容器中相同类型的bean唯一,推荐使用
- 使用按名称装配时(byName)必须保障容器中具有指定名称的bean,因变量名与配置耦合,不推荐使用
- 自动装配优先级低于setter注入与构造器注入,同时出现时自动装配配置消失

### 集合注入

在BookDaoimpl中:

```java
public class BookDaoImpl implements BookDao
{
    private int[] arr;
    private List<String> list;
    private Set<String> set;
    private Map<String,String> map;
    private Properties properties;

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    public void save()
    {
        System.out.println("book dao save...");
        System.out.println("list:"+list);
        System.out.println("arr:"+toString());
        System.out.println("set:"+set);
        System.out.println("map:"+map);
        System.out.println("Properties:"+properties);
    }
}
```

xml中:

```xml
<bean id="bookDao" class="lyh.dao.impl.BookDaoImpl">
        <property name="arr">
            <array>
                <value>100</value>
                <value>105</value>
                <value>980</value>
            </array>
        </property>
        <property name="list">
            <list>
                <value>200</value>
                <value>LYH</value>
                <value>980</value>
            </list>
        </property>
        <property name="set">
            <set>
                <value>LYH888</value>
                <value>585</value>
                <value>980</value>
            </set>
        </property>
        <property name="map">
            <map>
                <entry key="fuck" value="you"></entry>
                <entry key="fucks" value="yourself"></entry>
                <entry key="fucking" value="her"></entry>
            </map>
        </property>
        <property name="properties">
            <props>
                <prop key="fuck">US</prop>
                <prop key="fucks">UK</prop>
                <prop key="fucking">Amrican</prop>
            </props>
        </property>
    </bean>
```

## 注解开发

- 使用@Component定义bean

```java
@Component("bookDao")
public class BookDaoImpl implements BookDao
{
    public void save()
    {
        System.out.println("book dao save...");
    }
}
```

- 核心配置文件中通过组件扫描加载bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--先定义出context-->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
">
    <context:component-scan base-package="lyh.dao"/>
</beans>
<!--然后用context中去扫描包中的注解-->
```

### 纯注解开发

- Java类代替Spring核心配置文件

```java
@Configuration
@ComponentScan("lyh")
public class SpringConfig
{

}
```

在BookDao中还是要有@Component("bookDao")

主函数中加载配置类文件用类去加载

```java
public class AppForAnnotation
{
    public static void main(String[] args)
    {
        //加载配置类
        ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao =(BookDao) ctx.getBean("bookDao");
        System.out.println(bookDao);
    }
}
```

- @Configuration注解用于设定当前类为配置类
- @ComponentScan注解用于设定扫描路径,此注解只能添加一次,多个数据用数组格式

```java
@ComponentScan("lyh.dao","lyh.factory")
```

### 依赖注入 自动装配

- 分为集中注入 一种是通过@Autowired对类进行装配

```java
//这个相当于是配置它的bean的属性
@Repository("bookDao")
public class BookServicelmpl implements BookService
{
    	//这个是自动装配bean
        @Autowired
        private BookDao bookDao;
        public void save()
        {
            System.out.println("book service save...");
            bookDao.save();
        }
}
```

- 在加载propertySource注解加载properties文件

```java
@Component
public class BookDaoImpl implements BookDao
{
    @Value("${name}")
    private String name;
    public void save()
    {
        System.out.println("book dao save..."+name);
    }
}
```

还需要在SpringConfig中加载资源

```java
@Configuration
@ComponentScan("lyh")
//加载在properties中的jdbc文件资源
@PropertySource("jdbc.properties")
public class SpringConfig
{

}
```

这样在jdbc中的值才可以被访问到

总结:

1. ​	自动装配
   - @Autowired 自动装配
   - @Qualifier  用于相同的多个类的配置
   - @Value   用于配置一般数据类型
2. 读取properties文件
   - @PropertySource("...") 读取文件

### 注解开发管理第三方Bean

- 使用@Bean配置第三方bean 同样也是在config中定义一个java类

```java
public class JdbcConfig
{
    //2.添加@Bean,表示当前方法的返回值是一个Bean
    @Bean
    public DataSource dataSource()
    {
        //1.定义一个方法获得要管理的对象
        DruidDataSource ds=new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }
}
```

然后再之前的那个SpringConfig中增加一个@Imprt

```java
@Configuration
@Import(JdbcConfig.class)
public class SpringConfig
{

}
```

- 在主函数中加载

```java
    public static void main(String[] args)
    {
        //加载配置类
        ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource);
    }
```

#### 注入第三方且还有其他函数的调用时

JdbcConfig中:

```java
public class JdbcConfig
{
    //2.添加@Bean,表示当前方法的返回值是一个Bean
    @Bean
    //这个BookDao主要采用的是一个自动装配 在容器中去找这个类
    public DataSource dataSource(BookDao bookDao)
    {
        System.out.println(bookDao);
        bookDao.save();
        //1.定义一个方法获得要管理的对象
        DruidDataSource ds=new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db1");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
    }
}
```

SpringConfig中:

```java
@Configuration
//是通过扫描这里的包
@ComponentScan({"lyh"})
//同时也要添加这个类
@Import(JdbcConfig.class)
//因为我在BookDao中有一个EL表达式需要Properties资源所以要加载这个资源
@PropertySource("jdbc.properties")
public class SpringConfig
{

}
```

主函数与上述加载相同



## 注解开发总结 XML配置比对注解配置

|      功能      |                           XML配置                            |                             注解                             |
| :------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|    定义bean    |                  bean标签 id属性 class属性                   | @Componet  @Controller   @Service  @Repository  @ComponentScan |
|  设置依赖注入  | setter注入(set方法):引用       构造器注入(构造方法):引用 自动装配 |                @Autowired  @Qualifier  @Value                |
| 配置第三方bean |          bean标签     静态工厂,实例工厂,FactoryBean          |                            @Bean                             |
|    作用范围    |                          scope属性                           |                            @Scope                            |
|    生命周期    |             标准接口 init-method  destroy-method             |                @PostConstructor  @PreDestroy                 |

## Spring整合Mybatis

1.现在Maven构建的XML中添加所需的依赖:

```xml
<dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.32</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.10.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>
        </dependency>
```

2.先准备JdbcConfig.java文件,在里面准备jdbc的连接信息,由于是使用的是@Value来获取,所以还要准备个properties资源包

jdbc:

```java
public class JdbcConfig
{
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    //2.添加@Bean,表示当前方法的返回值是一个Bean
    @Bean
    public DataSource dataSource()
    {
        //1.定义一个方法获得要管理的对象
        DruidDataSource ds=new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}

```

properties包:

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/db1?useSSl=false
jdbc.username=root
jdbc.password=123456
```

3.由于jdbc需要调用SqlSessionFactory工厂,所以再准备一个MybatisConfig.java包,用来书写SqlSessionFacoty,因为它们内部都是方法体,还需要标注@Bean来协调对象之间的关系

mybatis:

```java
public class MybatisConfig
{
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource)
    {
        SqlSessionFactoryBean ssfb=new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("lyh.domain");
        ssfb.setDataSource(dataSource);
        return ssfb;
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer()
    {
        MapperScannerConfigurer msc=new MapperScannerConfigurer();
        msc.setBasePackage("lyh.dao");
        return msc;
    }
}
```

4.然后再SpringConfig中添加需要的扫描,配置类注解

```java
@Configuration
@ComponentScan({"lyh"})
@PropertySource("jdbc.properties")
@Import({JdbcConfig.class,MybatisConfig.class})
public class SpringConfig
{

}
```

5.开始书写对应的Dao文件

AccountDao:

```java
public interface AccountDao
{

    @Insert("insert into db1.tb_user(username, password) values(#username,#password)")
    void save(Account account);

    @Insert("update db1.tb_user set username=#{name},password=#{password} where id=#{id}")
    void update(Account account);

    @Insert("delete from db1.tb_user where id=#{id} ")
    void delete(int id);

    @Select("select  *from db1.tb_user where id=#{id}")
    Account findById(int id);

    @Select("select  *from db1.tb_user")
    List<Account> findAll();
}
```

6.在domain文件中书写对应的类

Account类:

```java
private Integer id;
    private String username;
    private String password;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
```

7.在Service中书写对应的AccountServiceImpl.java

```java
@Service
public class AccountServiceImpl implements AccountService
{
    @Autowired
    private AccountDao accountDao;

    public void save(Account account)
    {
        accountDao.save(account);
    }
    public void update(Account account)
    {
        accountDao.update(account);
    }
    public void delete(int id)
    {
        accountDao.delete(id);
    }
    public Account findById(int id)
    {
        return accountDao.findById(id);
    }
    public List<Account>findAll()
    {
        return accountDao.findAll();
    }

}
```

8.由于主函数调用的对象时AccountService,所以还需要一个AccoutnService接口书写对应sql语句

```java
public interface AccountService
{
    @Insert("insert into db1.tb_user(username, password) values(#username,#password)")
    void save(Account account);

    @Insert("update db1.tb_user set username=#{name},password=#{password} where id=#{id}")
    void update(Account account);

    @Insert("delete from db1.tb_user where id=#{id} ")
    void delete(int id);

    @Select("select  *from db1.tb_user where id=#{id}")
    Account findById(int id);

    @Select("select  *from db1.tb_user ")
    List<Account> findAll();
}
```

8.最后在主函数中调用对应类实例化对象后,使用方法即可

```java
public class AppForAnnotation
{
    public static void main(String[] args)
    {
        //加载配置类
        ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = ctx.getBean(AccountService.class);
        Account byId = accountService.findById(2);
        System.out.println(byId);
        List<Account> all = accountService.findAll();
        System.out.println(all);
    }
}
```

## AOP简介

- AOP(Aspect Orente Programming)面向切面编程,一种编程范式,指导开发者如何组织程序结构

- OOP(Object Oriented Programming)面向对象编程
- 作用:在不惊动原始设计的基础上为其进行功能增强
- Spring理念:无入侵式/无侵入式

### AOP核心概念

- 连接点(JoinPoint):程序执行过程中的任意位置,粒度为执行方法,抛出异常,设置变量等
  - 在SpringAOP中,理解为方法的执行
- 切入点(Pointcut):匹配连接点的式子
  - 在SpringAOP中,一个切入点可以只描述一个具体方法,也可以匹配多个方法
    - 一个具体方法:lyh.dao包下的BookDao接口中的无形参返回值为save方法
    - 匹配多个方法:所有的save方法,所有的get开头的方法,所有以Dao结尾的接口中的任意方法,所有带有一个参数的方法
- 通知(Advice):在切入点处执行的操作,也就是共性功能
  - 在SpringAOP中,功能最终以方法的形式呈现
- 通知类:定义通知的类
- 切面(Aspect):描述通知与切入点的对应关系

### AOP入门案例

1. 导入AOP相关坐标

```xml
<!-- Spring AOP坐标-->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>
```

2.定义dao接口与实现类

接口:

```java
public interface BookDao
{
    void save();
    void update();
}
```

实现类:

```java
@Repository
public class BookDaoImpl implements BookDao
{
    public void save()
    {
        System.out.println(System.currentTimeMillis());
        System.out.println("book dao save...");
    }
    public void update()
    {
        System.out.println("book dao update...");
    }
}
```

3.定义通知类,制作通知

```java
@Component//告诉Spring来加载
@Aspect//用切入点方法
public class MyAdvice
{
    @Pointcut("execution(void lyh.dao.BookDao.update())")//绑定执行代码
    private void pt(){}//定义的这个切入点什么都不要写
    @Before("pt()")//绑定切入点
    public void method()
    {
        System.out.println(System.currentTimeMillis());
    }
}
```

4.在SpringConfig中添加注解

```java
@Configuration
@ComponentScan({"lyh"})
@EnableAspectJAutoProxy//告诉Spring有用注解开发的AOP
public class SpringConfig
{

}
```

### AOP的工作流程

1. Srping容器启动
2. 读取所有切面配置中的切入点(只读取使用的)
3. 初始化bean,判定bean对应的类中的方法是否匹配到任意切入点
   - 匹配失败,创建对象
   - 匹配成功,创建原始对象(目标对象)的代理对象
4. 获取bean执行方法
   - 获取bean,调用方法并执行,完成操作
   - 获取的bean是代理对象时,根据代理对象的运行模式运行原始方法与增强的内容

**核心概念**  (代理模式)

- 目标对象(Target):原始功能去掉共性功能对应的类产生的对象,这种对象时无法直接完成最终工作的
- 代理(Proxy):目标对象无法直接完成工作,需要对其进行功能回填,通过原始对象的代理对象实现

### 案例:测量业务层接口万次执行效率

​	1.和前面差不多 先在pom.xml中添加坐标

​	2.然后在SpringConfig中书写对应的注解

```java
@Configuration
@ComponentScan({"lyh"})
@Import({JdbcConfig.class, MybatisConfig.class})//添加数据库的config信息
@PropertySource("classpath:jdbc.properties")//获取资源文件
@EnableAspectJAutoProxy//告诉Spring有用注解开发的AOP
public class SpringConfig
{

}
```

​	3.用test文件添加测试类

```java
@RunWith(SpringJUnit4ClassRunner.class)//这是JUnit的依赖 要在pom.xml中添加后可用
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTestCase
{
    @Autowired
    private AccountService accountService;

    @Test//测试类
    public void testFindById()
    {
        Account ac= accountService.findById(2);
        System.out.println(ac);
    }
    @Test
    public void testFindAll()
    {
        List<Account> all=accountService.findAll();
        System.out.println(all);
    }
}
```

​	4.在aop文件中书写对应的aop功能

```java
@Component
@Aspect//告诉Spring这是一个切面
public class ProjectAdvice
{
    //匹配业务层的所有方法
    @Pointcut("execution(* lyh.service.*Service.*(..))")
    private void servicePt(){}
    @Around("ProjectAdvice.servicePt()")//采取的是环绕方式
    public void runSpeed(ProceedingJoinPoint pjp) throws Throwable
    {
        Signature signature = pjp.getSignature();
        String classname = signature.getDeclaringTypeName();//获取类名
        String methodName = signature.getName();//获取方法名 方便查看每个方法的执行时间 然后好对其进行操作
        long start=System.currentTimeMillis();
        for(int i=0;i<10000;i++)
            pjp.proceed();
        Object ret = pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("业务层接口万次"+classname+"."+methodName+"---->" +(end-start)+"ms");
    }
}
```

### 	案例:百度网盘密码数据兼容处理(在字符串的前端或者后面去除空格的问题)

1.在aop中书写功能切面函数

```java
@Component
@Aspect
public class DataAdvice
{
    @Pointcut("execution(boolean lyh.service.*Service.*(*,*))")
    private  void servicePt(){}
    
    @Around("DataAdvice.servicePt()")
    public Object trimStr(ProceedingJoinPoint pjp) throws Throwable {
        Object[]args=pjp.getArgs();
        for (int i = 0; i < args.length; i++)
        {
            //判断参数是不是字符串
            if(args[i].getClass().equals(String.class))//如果是参数的话 才开始去除空格
            {
                args[i]=args[i].toString().trim();
            }
        }
        Object ret = pjp.proceed(args);//把修改的参数传回去
        return ret;
    }
}
```

2.主函数进行传参操作

```java
public class AppForAnnotation
{
    public static void main(String[] args)
    {
        //加载配置类
        ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        ResourcesService resourcesService = ctx.getBean(ResourcesService.class);
        boolean flag = resourcesService.openURL("http://pan.baidu.com/heihei", "  root         ");
        System.out.println(flag);
    }
}
```

## AOP总结

- 通知类型
  - 环绕通知(重点)
    - 环绕通知依赖形参ProceedingJoinPoint才能实现对原始方法的调用
    - 环绕通知可以隔离原始方法的调用执行
    - 环绕通知返回值设置为Object类型
      - 环绕通知中可以对原始方法调用过程中出现的异常进行处理

## Spring事物简介

- 事物作用:在数据层保障一系列的数据库操作同成功同失败
- Spring事物作用:在数据层或**业务层**保障一系列的数据库操作同成功同失败

### 案例:银行账户转账

1. 添加Spring事物管理

   ```java
   public interface AccountService
   {
       @Transactional//Spring的事物管理
       public void transfer(String out,String in,Double money);
   }
   ```

2. 在JdbcConfig中设置事物管理器(Mybatis框架使用的是JDBC事物)

   ```java
   //设置事物管理器
       @Bean
       public PlatformTransactionManager transactionManager(DataSource dataSource)
       {
           DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
           transactionManager.setDataSource(dataSource);
           return transactionManager;
       }
   ```

3. 开启注解式事物驱动

   ```java
   @Configuration
   @ComponentScan({"lyh"})
   @Import({JdbcConfig.class, MybatisConfig.class})
   @PropertySource("jdbc.properties")
   @EnableTransactionManagement//主要是这个
   public class SpringConfig
   {
   	
   }
   ```

4. 其他的就是数据库的书写

   ```mysql
   public interface AccountDao
   {
   
      @Update("update db1.goods set money=money+#{money} where name=#{name}")
      void inMoney(@Param("name") String name,@Param("money") Double money);
      @Update("update db1.goods set money=money-#{money} where name=#{name}")
      void outMoney(@Param("name") String name,@Param("money") Double money);
   }
   ```

5. 还有Dao的引用,函数的具体调用,使用

   ```java
   @Service
   public class AccountServiceImpl implements AccountService
   {
   
       @Autowired
       private AccountDao accountDao;
       public void transfer(String out,String in,Double money)
       {
           accountDao.outMoney(out,money);
           int i=1/0;
           accountDao.inMoney(in,money);
       }
   }
   ```

6. 最后在测试案例中调用测试方法即可

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration(classes = SpringConfig.class)
   public class AccountServiceTestCase
   {
       @Autowired
       private AccountService accountService;
   
       @Test
       public void testFindById()
       {
           accountService.transfer("Tom","Jerry",100D);
       }
   }
   ```


### 案例:银行转账增加日志(无论事物是否成功都要进行日志记录+记录是否成功)

1. 先书写Service层的代码

   ```java
   public interface LogService
   {
       @Transactional(propagation = Propagation.REQUIRES_NEW)//这个后面的是开启一个新事物,不与转账事物绑定
       void log(String out,String in,double money,int flag);
   }
   ```

   ```java
   @Service
   public class AccountServiceImpl implements AccountService
   {
       @Autowired
       private AccountDao accountDao;
       @Autowired
       private LogService logService;
       public void transfer(String out,String in,Double money,int flag)
       {
          try
          {
              accountDao.outMoney(out,money);
              int i=1/0;//使事物失败,注释掉就可以使事物成功
              accountDao.inMoney(in,money);
              flag=1;//记录事物是否成功
          }
          finally
          {
              logService.log(out,in,money,flag);
          }
       }
   }
   ```

   ```java
   public interface AccountService
   {
       @Transactional//Spring的事物管理
       public void transfer(String out,String in,Double money,int flag);
   }
   ```

2. 编写Dao层

   ```java
   public interface AccountDao
   {
      @Update("update db1.goods set money=money+#{money} where name=#{name}")
      void inMoney(@Param("name") String name,@Param("money") Double money);
      @Update("update db1.goods set money=money-#{money} where name=#{name}")
      void outMoney(@Param("name") String name,@Param("money") Double money);
   }
   ```

   ```java
   public interface LogDao
   {
       @Insert("insert into db1.tb_log(info, createDate,success) values(#{info},now(),#{flag})")
       void log(@Param("info") String info,@Param("flag") int flag);
   }
   ```

3. 编写Config

   ```java
   public class JdbcConfig
   {
       @Value("${jdbc.driver}")
       private String driver;
       @Value("${jdbc.url}")
       private String url;
       @Value("${jdbc.username}")
       private String username;
       @Value("${jdbc.password}")
       private String password;
       //2.添加@Bean,表示当前方法的返回值是一个Bean
       @Bean
       public DataSource dataSource()
       {
           //1.定义一个方法获得要管理的对象
           DruidDataSource ds=new DruidDataSource();
           ds.setDriverClassName(driver);
           ds.setUrl(url);
           ds.setUsername(username);
           ds.setPassword(password);
           return ds;
       }
   
       //设置事物管理器
       @Bean
       public PlatformTransactionManager transactionManager(DataSource dataSource)
       {
           DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
           transactionManager.setDataSource(dataSource);
           return transactionManager;
       }
   
   }
   ```

   ```java
   public class MybatisConfig
   {
       @Bean
       public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource)
       {
           SqlSessionFactoryBean ssfb=new SqlSessionFactoryBean();
           ssfb.setTypeAliasesPackage("lyh.domain");
           ssfb.setDataSource(dataSource);
           return ssfb;
       }
       @Bean
       public MapperScannerConfigurer mapperScannerConfigurer()
       {
           MapperScannerConfigurer msc=new MapperScannerConfigurer();
           msc.setBasePackage("lyh.dao");
           return msc;
       }
   }
   ```

   ```java
   @Configuration
   @ComponentScan({"lyh"})
   @Import({JdbcConfig.class, MybatisConfig.class})
   @PropertySource("jdbc.properties")
   @EnableTransactionManagement
   public class SpringConfig
   {
   
   }
   ```

4. 最后测试调用

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration(classes = SpringConfig.class)
   public class AccountServiceTestCase
   {
       @Autowired
       private AccountService accountService;
   
       @Test
       public void testFindById()
       {
           accountService.transfer("Tom","Jerry",80D,0);
       }
   }
   ```

## SpringMVC简介

- SpringMVC技术与Servlet技术功能等同,均属于web层开发技术
- SpringMVC是一种表现层框架技术



### SpringMVC入门案例

1. 先导坐标

   ```xml
   <dependencies>
           <dependency>
               <groupId>jakarta.servlet</groupId>
               <artifactId>jakarta.servlet-api</artifactId>
               <version>5.0.0</version>
           </dependency>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-webmvc</artifactId>
               <version>6.0.9</version>
           </dependency>
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <version>4.0.1</version>
               <scope>provided</scope>
           </dependency>
       </dependencies>
   ```

2. 创建SpringMVC控制器类(也就是Servlet)

   ```java
   @Controller//定义Controller 使用这个定义Bean也就是MVC中的m
   public class UserController
   {
       @RequestMapping("/save")//设置当前操作访问路径
       @ResponseBody//设置当前操作的返回值 也就是设置当前返回的值做显示
       public String save()
       {
           System.out.println("User save is running");
           return "{'module':'springmvc'}";
       }
   }
   ```

3. 创建ServletContainersInitConfig

   ```java
   //定义一个servlet容器启动的配置类,在里面加载spring的配置
   public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer
   {
       //加载springMVC容器操作
       @Override
       protected WebApplicationContext createServletApplicationContext()
       {
           AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
           ctx.register(SpringMvcConfig.class);
           return ctx;
       }
       //设置哪些请求归属springMVC处理
       @Override
       protected String[] getServletMappings()
       {
           return new String[]{"/"};
       }
       //加载spring容器设置
       @Override
       protected WebApplicationContext createRootApplicationContext()
       {
           return null;
       }
   }
   ```

4. 创建SpringMvcConfig

   ```java
   @Configuration
   @ComponentScan("lyh.controller")
   public class SpringMvcConfig
   {
   }
   
   ```


## json传参

1. 添加坐标

   ```xml
   <dependency>
               <groupId>com.fasterxml.jackson.core</groupId>
               <artifactId>jackson-databind</artifactId>
               <version>2.9.10</version>
           </dependency>
   ```

2. 编写Controller的json传参函数

   ```java
   //集合参数:json格式
       @RequestMapping("/listParamForJson")
       @ResponseBody
       public String listParamForJson(@RequestBody List<String> likes)
       {
           System.out.println("list common(json)参数传递:"+likes);
           return "{'module':'Json'}";
       }
       @RequestMapping("/pojoParamForJson")
       @ResponseBody
       public String pojoParamForJson(@RequestBody User user)
       {
           System.out.println("list common(json)参数传递:"+user);
           return "{'module':'pojo for Json'}";
       }
       @RequestMapping("/listPojoParamForJson")
       @ResponseBody
       public String listPojoParamForJson(@RequestBody List<User> list)
       {
           System.out.println("list common(json)参数传递:"+list);
           return "{'module':'list pojo for Json'}";
       }
   ```

   1. 由于传参形式不同,JSON书写格式也不同

      1. 在List<String> likes中是

         ```json
         ["唱","跳","rap","足球"]
         ```

      2. 在User user中是

         ```json
         {
             "name":"梅西",
             "age":"18"
         }
         ```

      3. 在List<User>中是

         ```json
         [
             {"name":"卡卡","age":19},
             {"name":"内马尔","age":25}
         ]
         ```

   2. **特别注意,List数组中用JSON传参时要加@**RequestBody

3. 在SpringMvcConfig中增加一个注解(开启自动转换json数据支持)

   ```javs
   @EnableWebMvc
   ```

4. 顺便上一张Postman的JSON的传参截图

   ![image-20230525220752095](C:\Users\lyh\AppData\Roaming\Typora\typora-user-images\image-20230525220752095.png)

## REST

### REST简介

- REST(Representational State Transfer), 表现形式状态转换
- 优点:
  - 隐藏资源的访问行为,无法通过地址得知对资源是何种操作
  - 书写简化

### REST风格的代码书写

- 在请求访问时,只需要在Postman里面进行不同的请求就可以请求到不同的数据

```java
@RestController//也就是@ResponseBody+@Controller
@RequestMapping("/books")
public class BookController
{
    @PostMapping
    public String save(@RequestBody User user) {
        System.out.println("普通参数传递:" + user);
        System.out.println("BookSave is running");
        return "{'module':'book save'}";
    }

    //@RequestMapping(value = "/{id}",method = RequestMethod.DELETE) 这些都是下面简化的信息
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("book delete:" + id);
        return "{'module':'book delete'}";
    }

    //集合参数:json格式
    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public String update(@RequestBody User user) {
        System.out.println("book update:" + user);
        return "{'module':'book update'}";
    }

    //@RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id)
    {
        System.out.println("book getById:" + id);
        return "{'module':'book getById'}";
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getAll()
    {
        System.out.println("book getAll:");
        return "{'module':'book getAll'}";
    }
}
```

### RESTful页面数据交互

1. 制作SpringMVC控制器

   ```java
   @RestController
   @RequestMapping("/books")
   public class BookController
   {
       //保存
       @PostMapping
       public String save(@RequestBody Book book)
       {
           System.out.println("book save:"+book);
           return "{'module':'book save success'}";
       }
       //获取全部
       @GetMapping
       public List<Book> getAll()
       {
           System.out.println("book getAll is running");
           List<Book>bookList=new ArrayList<Book>();
           Book book1=new Book();
           book1.setType("计算机");
           book1.setName("算法");
           book1.setDescription("ACM教程");
   
           Book book2=new Book();
           book2.setType("书法");
           book2.setName("楷书的书写");
           book2.setDescription("就是正");
           bookList.add(book1);
           bookList.add(book2);
           return bookList;
       }
   }
   ```

2. 设置对静态资源的访问放行

   ```java
   @Configuration
   public class SpringMvcSupport extends WebMvcConfigurationSupport
   {
       @Override
       protected void addResourceHandlers(ResourceHandlerRegistry registry)
       {
           //当访问/pages下面时 不走mvc
           registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
           registry.addResourceHandler("/js/**").addResourceLocations("/js/");
           registry.addResourceHandler("/css/**").addResourceLocations("/css/");
           registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
       }
   }
   ```

3. 在SpringMvcConfig中添加扫描包

   ```java
   @Configuration
   @ComponentScan({"lyh.controller","lyh.config"})
   @EnableWebMvc
   public class SpringMvcConfig
   {
   }
   ```

4. 在前端页面通过异步提交访问后台控制器

   ```js
   //添加
                   saveBook () {
                       axios.post("/SpringMVC_war/books",this.formData).then((res)=>{
                       });
                   },
   
                   //主页列表查询
                   getAll() {
                       axios.get("/SpringMVC_war/books").then((res)=>{
                               this.dataList=res.data;
                       });
                   },
   ```

   
