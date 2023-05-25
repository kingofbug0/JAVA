

# 1、DOS的使用

盘符切换：  d:

dir： 查看当前空间下的文件名称及其内容

cd 目录： 跳转到目录下  若要多次进去目录 可以 cd 目录\子目录

cd..：返回到上一个目录
cd\:  直接返回至盘符
cls：清屏
exit：quit

netstat -ano|findstr"8080":查找端口号8080的占用情况

tasklist|findstr"10112":查找到8080的占用情况后 后面跟随的PID 查询这个PID可以查看是哪个程序在占用

# 2、内存分配问题

栈内存：存储局部变量
堆内存：存储new出来的内容（实体，对象）
数组在初始化时会为存储空间添加默认值
布尔：false
字符：空字符
引用数据类型：NULL
每一个new出来的东西都有一个地址值使用完毕，会在垃圾回收器空闲时被回收

# 3、若想要自己输入值时

```java
Scanner in=new Scanner (System.in);  //这里的in是自定义的
int x=in.nextInt();     //用一个x去接受它
```

评委打分：

```java
public class HelloWorld
{
    public static void main(String[] args) //主函数
    {
        int arr[]=new int[6];
        for(int i=0;i<6;i++)
        {
            System.out.println("this is number " + (i+1) +" judges:");
            Scanner in=new Scanner(System.in);
            arr[i]=in.nextInt();
        }

    sortGrade(arr);
    System.out.println("So the last grade is:"+Max(arr));
    System.out.print("the avengers is :");
    Ave(arr);
}
public static void sortGrade(int arr[])    //对成绩进行排序
{

    for(int i=0;i<arr.length-1;i++)
    {
        for(int j=0;j<arr.length-i-1;j++)
        {
         if(arr[j]>arr[j+1])
         {
              int temp=arr[j];
              arr[j]=arr[j+1];
              arr[j+1]=temp;
          }
        }
    }
    System.out.print("the biggest grade is:");
    System.out.println(arr[arr.length-1]);
    System.out.print("the minimum grade is:");
    System.out.println(arr[0]);
}
public static int  Max(int arr[])     //计算最大值
{
    int sum=0;
    for(int i=1;i<arr.length-1;i++)
    {
        sum+=arr[i];
    }
    return sum;
}
public static void Ave(int arr[])    //计算平均值
{
    double Ave=Max(arr)/4;
    System.out.println(Ave);
}

}
```

# 4、双指针的一个位置交换问题

```java
public class HelloWorld
{
    public static void main(String[] args)
    {
        int arr[]={7,6,88,99,77,66};
        System.out.print("before the exchange:");
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]+",");
        }
        System.out.println("");
        swap(arr);
    }
   public static void swap(int arr[])
   {
       for(int star=0,end=arr.length-1;star<end;star++,end--)//双指针
       {
           int temp=arr[star];
           arr[star]=arr[end];
           arr[end]=temp;
       }

   System.out.print("after the exchange:");
   for(int i=0;i<arr.length;i++)
   {
       System.out.print(arr[i]+",");
   }

   }
}
```

## 成员变量和局部变量的区别

|                | 成员变量       | 局部变量                             |
| -------------- | -------------- | ------------------------------------ |
| 类中的位置不同 | 类中方法外     | 方法内或者方法声明上                 |
| 内存中位置不用 | 堆内存         | 栈内存                               |
| 生命周期不同   | 随对象而消失   | 随方法的调用完毕而消失               |
| 初始化值不同   | 有默认初始化值 | 没有默认的初始化值，必须先定义，赋值 |

# 5、字符串的内容比较

equals()
public boolean equals(Object anObject):将此字符串与指定对象进行比较，参数是之间传递一个字符串（并不是地址的比较！！）

```java
public class HelloWorld
{
    public static void main(String[] args)
    {
        String username = "admin";
        String Password = "a123456";
        for (int i = 0; i < 3; i++)
        {
            System.out.print("请输入您的账号:");
            Scanner in = new Scanner(System.in);
            String zh = in.next();
            System.out.print("请输入您的密码:");
            Scanner in2 = new Scanner(System.in);
            String mm = in2.next();
            User us=new User();
            us.setuser(zh );
            us.setpassword(mm);
            if (username.equals(us.getUser()) && Password.equals(us.getPassword()))    //这个username.equals（里面就是和username对比的东西）对的返回true 否则false
            {
                System.out.println("登录成功");
                break;
            }
            else
            {
                System.out.println("登录失败，请重试,您还剩余" + (2 - i) + "次机会");
            }
        }
    }
}
```

# 6、String类中的遍历字符串问题

```java
public class HelloWorld
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String s = sc.nextLine();
        //遍历字符串
        for(int i=0;i<s.length();i++)
        {
            System.out.println(s.charAt(i));
        }
    }
}

StringBuilder:
public class HelloWorld
{
    public static void main(String[] args)
    {
        StringBuilder s=new StringBuilder("fifif");
        s.append("hello");//增加数据 在后面增加
        StringBuilder s2=new StringBuilder();
        System.out.println("s2="+s2);
        System.out.println("s1="+s);
    }
}
```

# 7、ArrayList的一些增删改查

```java
public class HelloWorld
{
    public static void main(String[] args)
    {
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("hello");//添加元素
        arr.add("world");
        arr.add(1,"my");//插入元素 
        System.out.println(arr.get(1));//直接索引元素 索引了前面添加的my
        System.out.println(arr.set(1,"bad"));//改变元素 先是索引位置 然后是要改变的元素
        System.out.println(arr.remove(0));//删除元素，可以从位置上删除，也可以直接删除你想要删除的元素
        System.out.println("arr element:"+arr);
    }
}
```

## Arraylist的存储与类的数组的定义

```
public class ArrayListStu
{
    public static void main(String[]args)
    {
        ArrayList<student> arr=new ArrayList<student>();
        student []stu=new student[3];
       for(int i=0;i<stu.length;i++)  //！！！这个很重要 要先创建一个新的 初始化 否则是空指针无法运行！！！！
       {
           stu[i]=new student();
       }
        for(int i=0;i<3;i++)
        {
            System.out.println("请输入第"+(i+1)+"学生姓名:");
            stu[i].setName();
            System.out.println("请输入第"+(i+1)+"学生年龄:");
            stu[i].setAge();
            arr.add(stu[i]);
        }
        for(int i=0;i<3;i++)
        {
            student s=arr.get(i);
            System.out.println(s.getName()+""+s.getAge());
        }
    }
}
```

# 8、继承的格式

public class 子类名 extends 父类名{}
而java 中类只支持单继承不支持多继承（但是应该可以用接口来实现多继承）
java类支持多层继承 grandpa->father->son

### 动物的多态

```java
package Java_Stu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args)
    {
        animal a=new cat();//用新的猫的类去接受这个创建的animal
        a.setName("百猫");
        a.setAge(8);//设置姓名与年龄
            System.out.println(a.getName()+","+a.getAge());
        System.out.println(a);
        a.eat();//调用的是cat中的eat函数

        a=new cat("百猫",8);//创建了新地址 相当于是一个新的cat
        System.out.println(a.getName()+","+a.getAge());
        System.out.println(a);//通过这两个sout a可以看出是创建了两个不同的猫的
        a.eat();//
}
}
animal:
package Java_Stu;

public class animal
{
    private String name;
    private  int age;

animal()
{
}
animal(String name,int age)
{
    this.name=name;
    this.age=age;
}
public String getName()
{
    return name;
}

public void setName(String name)
{
    this.name = name;
}

public int getAge()
{
    return age;
}

public void setAge(int age)
{
    this.age = age;
}
public void eat()
{
    System.out.println("动物吃东西");
}

}
cat：
package Java_Stu;
public class cat extends animal
{
    cat()
    {
    }
   cat(String name,int age)
   {
       super(name, age);
   }
   public  void eat()
   {
       System.out.println("猫儿正在吃老鼠");
   }
}
package Java_Stu;
public class dog extends animal
{
   dog()
   {

   }
   dog(String name,int age)
   {
       super(name, age);
   }
   public void eat()
   {
       System.out.println("狗狗正在吃饭");
   }
}
```

# 9、抽象类

抽象类和抽象方法必须使用abstract关键字修饰
public abstract class 类名{}
public abstract void eat();
抽象类中不一定有抽象方法，有抽象方法一定要是抽象类
抽象类不能实例化
若想实例化的话 需要参照多态的方式去通过子类继承对象实例化
如 animal a=new cat(); 这叫抽象类多态
抽象类的子类
要么重写抽象类中的所有抽象方法
要么直接是抽象类

在JAVA中 用final来作常量修饰符
final 就是 c和c++中的static

# 10、接口

接口用关键字interface修饰 interface是接口、界面的意思          public interface 接口名{}
类实现接口用implements表示 implements是执行、使生效的意思
public class 类名 implements 接口名{}
接口不能实例化
接口也要用多态方式进行实例化 叫做接口多态
多态的形式：具体类多态，抽象类多态，接口多态
多态的前提：有继承或者实现关系；有方法重写；有父（类/接口）引用指向（子/实现）类对象
主函数:

```java
package Java_Stu;

public class main 
{
    public static void main(String[] args)
    {
     Interface j=new Cat();
     j.jumping();
    }

}
//接口:
package Java_Stu;

public interface Interface
{
    public abstract void jumping();

}
//cat类使用接口:
package Java_Stu;

public class Cat implements Interface
{
    @Override
    public void jumping() {
        System.out.println("猫在跳高");
    }
}
```

接口中的成员变量都是被final修饰的
接口是没有的构造方法的
接口中只能有抽象的成员方法

接口中的继承:
//main:

```java
package Java_Stu;

public class main 
{
    public static void main(String[] args) 
    {
        Cat c = new Cat();
        c.setName("大黑");
        c.setAge(6);
        System.out.println(c.getName() + "," + c.getAge());
        c.eat();
        c.jumping();
    }
}

//Interface:
package Java_Stu;

public interface Interface
{
    public abstract void jumping();

}

//Animal：
package Java_Stu;

public abstract class Animal 
{
    private int age;
    private String name;

public Animal()
{
}

public Animal(int age, String name) 
{
    this.age = age;
    this.name = name;
}

public int getAge()
{
    return age;
}

public void setAge(int age)
{
    this.age = age;
}

public String getName() 
{
    return name;
}

public void setName(String name)
{
    this.name = name;
}

public abstract void eat();
{
}

//Cat：
package Java_Stu;
public class Cat extends Animal implements Interface
{
    public Cat()
    {

}

public Cat(int age, String name)
{
    super(age, name);
}

@Override
public void eat()
{
    System.out.println("猫在吃鱼");
}

@Override

public void jumping()
{
    System.out.println("猫在跳高");
}

}
```

一个类中可以实现多个接口，用,号隔开

# 11、抽象类和接口的区别

|          | 抽象类                                     | 接口           |
| :------- | ------------------------------------------ | -------------- |
| 成员区别 | 变量，常量；构造方法；抽象方法；非抽象方法 | 常量；抽象方法 |



| 关系区别   |                                |
| ---------- | ------------------------------ |
| 类和类     | 继承，单继承                   |
| 类与接口   | 实现，可以单实现，也可以多实现 |
| 接口和接口 | 继承，单继承，多继承           |

| 设计理念的区别                            |
| ----------------------------------------- |
| 1、方法的形参是类名，需要的是该类的对象   |
| 2、方法的返回值是类名，需要的是该类的对象 |

# 12、抽象类名作为返回值

```java
package Java_Stu;

public class main
{
    public static void main(String[] args)
    {
        catOperator co=new catOperator();//创建操作类对象
        cat c=new cat();//
        co.useCat(c);//类名作为形参 需要是的是一个类
        cat c2=co.getcat();
        c2.eat();
    }
}

cat类:
package Java_Stu;

public class cat
{
    public void eat()
    {
        System.out.println("猫吃鱼");
    }
}

catOpearator类
package Java_Stu;

public class catOperator
{
    void useCat(cat c)//这里形参是一个类名 相当于是 cat c=new cat();
    {
        c.eat();
    }

public cat getcat()//这里的cat是一个返回值类型
{
    cat c=new cat();
    return c;
}

}
```

# 13、String与int类型的转化

```java
package Java_Stu;

public class main
{
    public static void main(String[] args)
    {
        int num = 10;
      String s=String.valueOf(num);
        System.out.println(s);

}

字符串中数据排列
有一个字符串“91 27 46 38 50”从小到大排序 
package Java_Stu;
import java.util.Arrays;
public class String_sort
{
    public static void main(String[] args)
    {
        String s="91 27 46 38 50";
        String[] s_arr=s.split(" ");//把字符串数字数据存储到一个int类型的数组中去 用split中的"空格"空格方法进行分割
        //定义一个int数组
          int []arr=new int[s_arr.length];//长度为s_arr的数组长度
          for(int i=0;i<arr.length;i++)
          {
              arr[i]=Integer.parseInt(s_arr[i]);//Integer.parseInt把一个字符串数据转换成一个int数据
          }
      Arrays.sort(arr);//排序

  StringBuilder sb=new StringBuilder();//排序后的int数组中的元素进行一个拼接得到一个字符串，StringBuilder就是用来拼接的
  for(int i=0;i<arr.length;i++)
  {
      if(i==arr.length-1)
      {
          sb.append(arr[i]);
      }
      else
      {
          sb.append(arr[i]).append(" ");
      }
  }
  String res=sb.toString();
    System.out.println(res);
}

}
```

# 14、日期工具类

```java
DateUtils:

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.SimpleTimeZone;

public class DateUtils
{
    private DateUtils(){}

    public static String dateToString(Date date,String format)
    {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String s=sdf.format(date);//把日期转化成指定格式的字符串
        return s;
    }

   public static Date stringToDate(String s,String format) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Date d = sdf.parse(s);
        return d;
    }

}
DateDemo:
package Java_Stu;
import java.text.ParseException;
import  java.util.Date;


public class DateDemo
{
    public static void main(String[] args) throws ParseException {
        Date d=new Date();//创建日期对象

    String s1=DateUtils.dateToString(d,"yyyy年MM月dd日 HH:mm:ss");
    System.out.println(s1);

    String s2=DateUtils.dateToString(d,"yyyy年MM月dd日");
    System.out.println(s2);

    String s3=DateUtils.dateToString(d,"HH:mm:ss");     System.out.println(s3);

    String s="2048-08-09 18:03:59";
    Date d2 = DateUtils.stringToDate(s, "yyyy-MM-dd HH:mm:ss");//alt+enter抛出一个异常就可以改掉bug
    System.out.println(d2);
}
```

}

# 15、Collection集合

```java
package Java_Stu;
import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        Collection<String> c=new ArrayList<String>();
        //添加元素
        c.add("Hello");
        c.add("java");
        c.add("world");
        System.out.println(c);
        //清除元素
        c.clear();
        //查找元素
        System.out.println(c.contains("world"));//如果找到了返回true 没找到返回false
        //判断集合是否为空
        System.out.println(c.isEmpty());//如果为空返回true 不为空返回false
        //判断集合的大小
        System.out.println(c.size());

    System.out.println(c);
}

}
```

# 16、迭代器Iterator

```java
package Java_Stu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        Collection<String> c=new ArrayList<String>();
        //添加元素
        c.add("Hello");
        c.add("java");
        c.add("world");
        System.out.println(c);
        //迭代器的创建 String要和上面的保持一致
        Iterator<String> it=c.iterator();
        System.out.println(it.next());//这个next每次获得一个元素
        it.hasNext();//这个是判断是否有元素
        while(it.hasNext())//用循环直接遍历所有元素 若有就直接输出
        {
            System.out.println(it.next());
        }

}

}
```

## 用集合Collection来存储Student类

```java
main:
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        Collection<Student> c=new ArrayList<Student>();//创建以学生类为对象的集合
        Student s=new Student("战兽山",18);
        Student s2=new Student("望山",20);
        Student s3=new Student("梅西",35);

    c.add(s);//添加对象到集合中去
    c.add(s2);
    c.add(s3);

    Iterator<Student> it=c.iterator();
    while(it.hasNext())
    {
        Student s_show=it.next();
        System.out.println(s_show.getName()+","+s_show.getAge());
    }

}

}

Student:
package Java_Stu;

public class Student
{
        private String name;
        private int age;
        public Student()
        {

    }
    public Student(String name, int age)
	 {
        this.name = name;
        this.age = age;
    }

    public String getName()
    {
			return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}
```

# 17、List集合

是一个有序集合(也称为序列)存储和取出的元素位置相同,可以精确的控制列表中每个元素的插入位置，也可以通过证书索引访问元素,且搜索列表中的元素
与Set集合不同，列表通常允许重复的元素
但Set集合是不允许有重复的元素的，所有leetcode有一题数组的题目就是直接用set集合来得到数组中是否有重复的元素

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        List<String> list=new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("java");
        list.add("world");

    Iterator<String>it=list.iterator();
    while(it.hasNext())
    {
        String s=it.next();
        System.out.println(s);
    }
}

}
```

### list的学生类

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        List<Student> list=new ArrayList<Student>();
        Student s1=new Student("李华",18);
        Student s2=new Student("丽琴霞",20);
        Student s3=new Student("琪琪",22);
        list.add(s1);
        list.add(s2);
        list.add(s3);

    for(int i=0;i<list.size();i++)//这个是用for循环遍历得到内容
    {
        Student s = list.get(i);
        System.out.println(s.getName()+","+s.getAge());
    }
    Iterator<Student>it=list.iterator();
    while(it.hasNext())//这个是用迭代器来获取元素内容
    {
        Student s=it.next();
        System.out.println(s.getName()+","+s.getAge());
    }

}

}
Student:
package Java_Stu;

public class Student
{
        private String name;
        private int age;
        public Student()
        {

    }
    public Student(String name, int age)
    {
        this.name = name;
       this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}
```

# 18、并发修改异常

```java
while(it.hasnext())
       {
           String s= it.next();
           if(s.equals("World"))
           {
               list.add("javaee");//在这里抛出的异常
           }
       }
```

是由于迭代器在遍历的过程中，在list.add添加了新的元素至集合中，修改其长度，造成了迭代器获取元素中的判断预期修改值与实际的修改至不一样

```java
protected transient int modCount = 0//实际修改值
int expectedModCount =modCount
//expectedModCount是判断预期修改值
 public boolean add(E e) //就是由于调用了这个add后 导致modCount++了 而expectedModCount没有++导致两个值不一样 抛出异常
{
        modCount++;
        add(e, elementData, size);
        return true;
}
```

用for循环遍历不调用迭代器即可

```java
Listlterrator:列表迭代器
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
/*ConcurrentModificationException*/  //并发修改异常
import java.util.*;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        List<String>list=new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("java");

    //获取列表迭代器
    ListIterator<String> lit=list.listIterator();
    while(lit.hasNext())//列表迭代器的while
    {
        String s= lit.next();
        if(s.equals("world"))//和普通的迭代器不同，这里的这个列表迭代器可以进行add操作
        {
            lit.add("javaee");//进行add操作后 实际修改值与判断预期修改值的值保持一样 不会抛出错误
        }
    }
    System.out.println(list);
}

}
```

# 19、增强for循环

##### for(元素数据类型 变量名:数组或者Collection集合) 其就是一个迭代器

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        int[]arr={1,5,6,7};
        for(int i:arr)
        {
            System.out.println(i);
        }

​    String []str_arr={"Hello","World","Java"};
​    for(String s:str_arr)
​    {
​        System.out.println(s);
​    }

​    List<String>list=new ArrayList<String>();
​    list.add("Hello");
​    list.add("java");
​    for(String s:list)
​    {
​        System.out.println(s);
​    }
​    //增强for的内部原理是一个迭代器
​    //用这个方法可以判断出来
​    for(String s:list)
​    {
​        if(s.equals("Hello"))
​        {
​            list.add("javaee");
​        }
​    }
}

}
public static void main(String[] args)
    {
        List<Student>list=new ArrayList<Student>();
        Student s1=new Student("李华",11);
        Student s2=new Student("梅西",35);
        Student s3=new Student("哈维",48);

​    //将对象添加到list中去
​    list.add(s1);
​    list.add(s2);
​    list.add(s3);
​    //用迭代器的方法进行遍历
​    Iterator<Student>it=list.iterator();
​    //迭代器特有的遍历
​    while(it.hasNext())
​    {
​        Student s=it.next();
​        System.out.println(s.getName()+","+s.getAge());
​    }
​    //普通for的遍历
   for(int i=0;i<list.size();i++)
   {
​       Student s=list.get(i);
​       System.out.println(s.getName()+","+s.getAge());
   }

   //用增强for的遍历
   for(Student s:list)
   {
       System.out.println(s.getName()+","+s.getAge());
   }

}
```

# 20、栈与队列

栈是先进后出，队列(从后端进去，从前端出来)是先进先出
ArrayList和LinkedList:
ArrayList是查询快，但增删慢（集合）
LinkedList是查询慢，增删慢 （链表）

```java
package Java_Stu;
import java.util.*;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("Hello");
        arr.add("World");
        arr.add("Java");
        //普通for的遍历
        for(int i=0;i<arr.size();i++)
        {
           String s = arr.get(i);
           System.out.println(s);
        }
        System.out.println("-----------");
        //增强for
        for(String s:arr)
        {
            System.out.println(s);
        }
        System.out.println("------------");
        //迭代器
        Iterator<String>it=arr.iterator();
        while(it.hasNext())
        {
            String s=it.next();
            System.out.println(s);
        }

​    System.out.println("-----------");
​    LinkedList<String> linkedList=new LinkedList<String>();
​    linkedList.add("Hello");
​    linkedList.add("World");
​    linkedList.add("Java");
​    //普通for
​    for(int i=0;i<linkedList.size();i++)
​    {
​        String s=linkedList.get(i);
​        System.out.println(s);
​    }
​    System.out.println("-----------");
​    //增强for
​    for(String s:linkedList)
​    {
​        System.out.println(s);
​    }
​    System.out.println("-----------");
​    //迭代器
​    Iterator<String> link_it=linkedList.iterator();
​    while(link_it.hasNext())
​    {
​        String s=link_it.next();
​        System.out.println(s);
​    }
}

}
```

# 21、LinkedList的特有功能

| addFirst(E e) | 在该列表开头插入指定的元素//这里的E e应该是个模板类E |
| ------------- | ---------------------------------------------------- |
| addLast(E e)  | 将指定的元素追加到此列表的末尾                       |
| getFirst()    | 返回此列表的第一个元素                               |
| getLast()     | 返回此列表的最后一个元素                             |
| removeFirst() | 从此列表中删除并返回第一个元素                       |
| removeLast()  | 从此列表中删除并返回最后一个元素                     |

# 22、Hash

- 哈希值:是JDK根据对象的地址或者字符串或者数字算出来的int类型的数值
- HashSet的存储方法:

1. 调用对象的hashCode()方法获取对象的哈希值
2. 根据对象的哈希值计算对象的存储位置
3. 该位置是否有元素存在

- 若有 就遍历该位置的所有元素，和新存入的元素比较哈希值是否相同；有相同的就调用equals()方法比较对象内容是否相等，相等返回true 说明元素重复，不存储
- 如果该位置没有元素存在 就将元素存储到该位置，
- 有元素存在就遍历该位置的所有元素和新存入的元素比较哈希值，不同就存储，相同进入到equals()方法比较，不同返回false然后存储元素到该位置

### HashSet集合存储学生对象并遍历

- 需要重写hashCode()和equals()方法，这样才可以使两个相同的对象只存储一次

```java
package Java_Stu;

import java.util.*;

public class HashCodeDemo

{

    public static void main(String[] args) 
    {

        Student s1 = new Student("伊涅斯塔", 38);
        Student s2 = new Student("梅西", 35);
        Student s3 = new Student("伊布", 40);
        Student s4=new Student("梅西",35);//在Student中重写后就不会显示这个人物信息了,直接用自动生成就行了

    HashSet<Student> hs = new HashSet<Student>();
    hs.add(s1);
    hs.add(s2);
    hs.add(s3);
    hs.add(s4);

    for (Student s : hs)
    {
        System.out.println(s.getName()+","+s.getAge());
    }
}

}
```

### TreeSet

元素是有序的，没有带索引的方法，不能用普通for循环遍历， 是Set集合 所以不包含重复元素的集合

```java
package Java_Stu;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) {
       TreeSet<Integer>ts=new TreeSet<Integer>();
       ts.add(20);
       ts.add(10);
       ts.add(5);
       ts.add(19);
        for(Integer i:ts)
        {
            System.out.println(i);
        }

    System.out.println("------------------");
   TreeSet<String>ts1=new TreeSet<String>();
   ts1.add("v");
   ts1.add("b");
   ts1.add("a");
   ts1.add("f");//他会自动排序
   for(String s:ts1)
   {
       System.out.println(s);
   }

}

}
```

#### Compare比较器的使用  是对于TreeSet的

```java
package Java_Stu;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) {
        TreeSet<Student>ts=new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //先判断年龄
                int num=o1.getAge()-o2.getAge();//如果年龄大于0就升，小于0就降
                //判断当年龄相同但姓名不同时
                //当年龄相等时，进行比较姓名 如果o1的名字与o2的名字compareTo方法后 如果相同 返回num=0 如果不同就进行排序
                int num2=num==0?o1.getName().compareTo(o2.getName()):num;
                return num2;
            }
        });

    Student s1 = new Student("伊涅斯塔", 38);
    Student s2 = new Student("梅西", 35);
    Student s3 = new Student("伊布", 40);
    Student s4=new Student("罗纳尔多",38);
    Student s5=new Student("罗纳尔多",40);

    ts.add(s1);
    ts.add(s2);
    ts.add(s3);
    ts.add(s4);
    ts.add(s5);

    for (Student s : ts)
    {
        System.out.println(s.getName()+","+s.getAge());
    }
}

} 
```

##### 存储1-20的随机数 且不重复

```java
package Java_Stu;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args)
    {
       Set<Integer>s=new HashSet<Integer>();

   Random r=new Random();
   while(s.size()<10)
   {
       int i = r.nextInt(20) + 1;
       s.add(i);
   }
   for(Integer i:s)
   {
       System.out.println(i);
   }
}

}
```

# 23、泛型方法的定义与使用

格式:public void 函数名(T t)  {}
调用时直接可以使用 不用去定义类型了

```java
//Generic:
package Java_Stu2;
public class Generic <T>
{
    public void show(T t)
    {
        System.out.println(t);
    }
}
GenericDemo:
package Java_Stu2;

public class GenericDemo
{
    public static void main(String[] args)
    {
        Generic g1=new Generic();
        g1.show(15);
        g1.show(15.7);
        g1.show("领取时");
        g1.show(true);
    }
}
```

# 24、可变参数

```java
package Java_Stu2;

public class ArgsDemo
{
    public static void main(String[] args)
    {
        System.out.println(sum(10,20,30,50,70));
        System.out.println(sum(50,80,70,55,70));

}
public static int sum(int ...a)
{
    int sum=0;
    for(int i:a)
    {
        sum+=i;
    }
    return sum;
}

}
```

# 25、Map集合的基本功能

Map<String,String> map=new HashMap<String,String>();
//前面一个String存的是键 后面是印射的值

| V put(K key,V value)                | 添加元素                             |
| ----------------------------------- | ------------------------------------ |
| V remove(Object key)                | 根据键删除键值对元素                 |
| void clear()                        | 移除所有的键值对元素                 |
| boolean containsKey(Object key)     | 判断集合是否包含指定的键             |
| boolean containsValue(Object value) | 判断集合是否包含指定的值             |
| boolean isEmpty()                   | 判断集合是否为空                     |
| int size()                          | 集合的长度，也就是集合中键值对的个数 |
| V get(object key)                   | 根据键获取值                         |
| Set<K> keySet()                     | 获取所有键的集合                     |
| Collection<V> values()              | 获取所有值得集合                     |

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
       Map<String,String> map=new HashMap<String,String>();
       //添加值
        map.put("球王","梅西");
        map.put("追风王子","劳尔");
        map.put("小白","伊涅斯塔");
        map.put("球仙","梅西");
        //void clear()  移除所有的键值对元素
        System.out.println(map.remove("球王"));
        //boolean containsKey(Object key)   判断集合是否包含指定的键
        System.out.println(map.containsKey("球王"));
        System.out.println(map.containsValue("梅西"));

    System.out.println(map);
}

}

```

# 26、Map集合的遍历

- 1、用keyset遍历

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
       Map<String,String> map=new HashMap<String,String>();
       //添加值
        map.put("球王","梅西");
        map.put("追风王子","劳尔");
        map.put("小白","伊涅斯塔");
        map.put("球仙","梅西");

​    //获取所有键的集合，用keySet()方法实现
​    Set<String> keyset=map.keySet();
​    for(String s:keyset)
​    {
​        String value=map.get(s);
​        System.out.println(s+","+value);
​    }
}

}
```

- 2、用Map.Entry的方式去遍历

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
       Map<String,String> map=new HashMap<String,String>();
       //添加值
        map.put("球王","梅西");
        map.put("追风王子","劳尔");
        map.put("小白","伊涅斯塔");
        map.put("球仙","梅西");
        //用Map.Entry的方式去遍历
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for(Map.Entry<String, String> me :entries)
        {
            String key = me.getKey();
            String value = me.getValue();
            System.out.println(key+","+value);
        }
    }
}
```

### HashMap集合存储学生对象并遍历

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        HashMap<String,Student> hashmap=new HashMap<String,Student>();
        Student s1=new Student("梅西",35);
        Student s2=new Student("伊涅斯塔",38);
        Student s3=new Student("卡卡",41);

​    hashmap.put("001",s1);
​    hashmap.put("002",s2);
​    hashmap.put("003",s3);

​    //遍历集合 键找值
​    Set<String> keySet = hashmap.keySet();
​    for(String key:keySet)
​    {
​        Student s = hashmap.get(key);
​        System.out.println(key+","+s.getName()+","+s.getAge());
​    }
​    System.out.println("--------------");

​    //键值对 对象找键和值
​    Set<Map.Entry<String, Student>> entries = hashmap.entrySet();
​    for(Map.Entry<String, Student> me:entries)
​    {
​        String key=me.getKey();
​        Student s=me.getValue();
​        System.out.println(key+","+s.getName()+","+s.getAge());
​        System.out.println(key+","+s);
​    }
}

}
```

### 集合嵌套之HashMap嵌套ArrayList

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        //创建HashMap里面的键是String类型 值是ArrayList类型
        HashMap<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
        ArrayList<String> arr1=new ArrayList<String>();
        arr1.add("伊涅斯塔");
        arr1.add("梅西");
        hm.put("巴萨",arr1);

​    //而ArrayList是String的
​    ArrayList<String> arr2=new ArrayList<String>();
​    arr2.add("鲁尼");//在arr中添加元素
​    arr2.add("马奎尔");
​    hm.put("曼联",arr2);//在HashMap中添加键与值

​    ArrayList<String> arr3=new ArrayList<String>();
​    arr3.add("哈兰德");
​    arr3.add("德布劳内");
​    hm.put("曼城",arr3);

​    Set<String> keySet1 = hm.keySet();//用HashMap的keySet方法组成Set集合
​    for(String key:keySet1)//增强for遍历 HashMap的元素
​    {
​        System.out.println(key);//输出一下键
​        ArrayList<String> value = hm.get(key);//用HashMap的get来得到映射的值
​        for(String s:value)//增强for遍历 映射的值
​        {
​            System.out.println("\t"+s);//得到映射的值，也就是值
​        }
​    }
}

}
```

### 统计字符串中每个字符串出现的次数

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一串字符:");
        String s = sc.nextLine();

​    TreeMap<Character,Integer> tm=new TreeMap<Character,Integer>();//char用来显示字符，int用来计数
​    //遍历字符串
​    for(int i=0;i<s.length();i++)
​    {
​        char key=s.charAt(i);//转化为char
​        Integer value = tm.get(key);//把键拿到TreeMap中去找值
​        if(value==null)//如果在这个map中没有这个值，就添加进去，并且初始化Integer为1
​        {
​            tm.put(key,1);
​        }
​        else
​        {
​            value++;
​            tm.put(key,value);//计算重复的字符，并且统计
​        }
​    }
​    //用StringBuilder进行拼接
​    StringBuilder sb=new StringBuilder();
​    Set<Character> keySet = tm.keySet();//准备开始遍历键
​    System.out.println(keySet);
​    for(Character key:keySet)
​    {
​        Integer value = tm.get(key);
​        sb.append(key).append("(").append(value).append(")");
​    }
​    String s1 = sb.toString();
​    System.out.println(s1);
}

}
```

# 27、Collections

- 是针对集合操作的工具类

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        ArrayList<Student> arr=new ArrayList<Student>();
        Student s1=new Student("张三",18);
        Student s2=new Student("网二",20);
        Student s3=new Student("梅西",35);
        Student s4=new Student("嘎嘎",18);

​    arr.add(s1);
​    arr.add(s2);
​    arr.add(s3);
​    arr.add(s4);

​    Collections.sort(arr, new Comparator<Student>()//排序 由于不能直接传递Arraylist的arr 需要采用 new一个比较的方法
​    {
​        @Override
​        public int compare(Student s1, Student s2)
​        {
​            int num=s1.getAge()-s2.getAge();//还是和之前一样 先看年龄
​            int num2=num==0?s1.getName().compareTo(s2.getName()):num;//年龄一样 比较字符串大小
​            return num2;
​        }
​    });

​    for(Student s:arr)
​    {
​        System.out.println(s.getName()+","+s.getAge());
​    }
}

}
```

### 模拟斗地主

```java
package Java_Stu;
import jdk.swing.interop.SwingInterOpUtils;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        HashMap<Integer,String> hm=new HashMap<Integer,String>();//用哈希表来存储，第一个是存数字的索引，第二个是存牌
        ArrayList<Integer> arr=new ArrayList<Integer>();//添加一个ArrayList来存数字索引
        String[] color={"黑桃","红桃","梅花","方块"};//给牌上色
        String[] numbers={"3","4","5","6","7","8","9","10","J","Q","K","A","2"};//给牌数字

​    int index=0;//添加索引
​    for(String number:numbers)//在HashMap中存储编号和牌
​    {
​        for(String s:color)
​        {
​            hm.put(index,s+number);//添加索引与牌到HashMap中
​            arr.add(index);//索引加到ArrayList数组中去
​            index++;//索引加1
​        }
​    }
​    hm.put(index,"小王");
​    arr.add(index);
​    index++;
​    hm.put(index,"大王");
​    arr.add(index);
​    //洗牌
​    Collections.shuffle(arr);

​    //发牌  发给三个人 且还有三张是底牌  用TreeSet存储是为了排序
​    TreeSet<Integer> lyh_poker=new TreeSet<Integer>();
​    TreeSet<Integer> bm_poker=new TreeSet<Integer>();
​    TreeSet<Integer> lc_poker=new TreeSet<Integer>();
​    TreeSet<Integer> last_poker=new TreeSet<Integer>();

​    for(int i=0;i<arr.size();i++)//开始添加牌
​    {
​        int x=arr.get(i);//这里添加的是牌的索引
​        if(i>=arr.size()-3)
​        {
​            last_poker.add(x);
​        }
​        else if(i%3==0)
​        {
​            lyh_poker.add(x);

​        }
​        else if(i%3==1)
​        {
​            lc_poker.add(x);

​        }
​        else if(i%3==2)
​        {
​            bm_poker.add(x);

​        }
​    }

​    look_poker("神龟",lyh_poker,hm);
​    look_poker("百猫",bm_poker,hm);
​    look_poker("怪兽",lc_poker,hm);
​    look_poker("底牌",last_poker,hm);
}

public static void look_poker(String name,TreeSet<Integer> ts,HashMap<Integer,String> hm)//看牌函数 需要名字，TreeSet，HashMap
{
    System.out.print(name+"的牌是:");
    for(Integer key:ts)//键找值的方法
    {
        String poker = hm.get(key);
        System.out.print(poker+"");//输出值
    }
    System.out.println();
}

}
```

# 28、IO类的操作

```java
File：
package Java_Stu;
import java.io.File;
import java.io.IOException;
public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        File f1=new File("D:\\JavaSE\\src\\java.txt");
        f1.createNewFile();//创建文件 如果没有这个文件就返回true并创建 如果有就返回false
        File f2=new File("D:\\JavaSE\\src\\Java");
        f2.mkdir();//创建一个文件夹目录 若要多级创建目录 就用mkdirs
    }
}
```

#### File类判断和获取功能

| public boolean isDirectory()    | 测试此抽象路径名表示的File是否为目录                     |
| ------------------------------- | -------------------------------------------------------- |
| public boolean ifFile()         | 测试此抽象路径名表示的File是否为文件                     |
| public boolean exists()         | 测试此抽象路径名表示的File是否存在                       |
| public String getAbsolutePath() | 返回此抽象路径名的绝对路径名字符串                       |
| public String getPath()         | 将此抽象路径名转换为路径名字符串                         |
| public String getName()         | 返回由此抽象路径名表示的文件或目录的名称                 |
| public String[] list()          | 返回此抽象路径名表示的目录中的文件和目录的名称字符串数组 |
| public File[] listFiles()       | 返回此抽象路径名表示的目录中的文件和目录的File对象数组   |

### 递归求阶层

```java
package Java_Stu;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        int result=jc(5);
        System.out.println("5的阶乘是:"+result);
    }
    public static int jc(int n)
    {
        if(n==1)//定义出口
        {
            return 1;
        }
        else
        {
            return n*jc(n-1);
        }
    }
}
```

### 递归查找文件

```java
package Java_Stu;
import java.io.File;
import java.io.IOException;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        File f1=new File("D:\\JavaSE");//需要查找的文件目录
        get_file(f1);//调用递归
    }

//书写递归函数
public static void get_file(File f)
{

​    File[] fileArray = f.listFiles();//存储listFiles
​    if(fileArray!=null)//如果这个数组不是空的就开始遍历
​    {
​        for(File file:fileArray)//用增强for开始遍历
​        {
​            if(file.isDirectory())//如果是目录 就接着递归返回  这里我感觉应该是个逐级递增的过程 就是一层目录一层目录的打开 如果不是文件就打开
​            {
​                get_file(file);
​            }
​            else
​            {
​                System.out.println(file.getAbsolutePath());//如果是文件 就输出这个文件的绝对路径
​            }
​        }
​    }
}

}
```

### 字节流输入文件 

##### 在文末接着添加元素的问题

```java
package Java_Stu;
import java.io.FileOutputStream;
import java.io.IOException;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        FileOutputStream fos=new FileOutputStream("D:\\JavaSE\\fos.txt",true);//后面增加true的话就是接着文尾接着写
        for(int i=0;i<10;i++)
        {
            fos.write("芜湖芜湖".getBytes());
            fos.write("\n".getBytes());
        }

​    fos.close();
}

}
```

### 字节流读数据

```java
package Java_Stu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream fis=new FileInputStream("D:\\JavaSE\\fos.txt");
        //第一次读取数据
        int read = fis.read();//只能读取一个数据
        while(fis.read()!=-1)//因为后面没数据时 值为-1 所有以-1为界限
        {
            System.out.print((char)read);//开始转char类型输出语句
        }

    fis.close();

}

}
```



### 各种复制操作的时间快慢

```java
package Java_Stu;
import java.io.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        long starTime=System.currentTimeMillis();
        method4();
        long endTime=System.currentTimeMillis();
        System.out.println("共耗时:"+(endTime-starTime)+"毫秒");

}

//基本字节流 一次读取一个字节   共耗时:276574毫秒
public static void method1() throws IOException
{
    FileInputStream fis=new FileInputStream("D:\\JavaSE\\src\\6.avi");
    FileOutputStream fos=new FileOutputStream("D:\\JavaSE\\6.avi");
    int read = fis.read();
    while(fis.read()!=-1)
    {
        fos.write(read);
    }
    fos.close();
    fis.close();
}

//基本字节流 一次读取一个字节数组 共耗时:389毫秒
public static void method2() throws  IOException
{
    FileInputStream fis=new FileInputStream("D:\\JavaSE\\src\\6.avi");
    FileOutputStream fos=new FileOutputStream("D:\\JavaSE\\6.avi");

​    byte[] bys = new byte[1024];
​    int len;
​    while((len=fis.read(bys))!=-1)
​    {
​        fos.write(bys,0,len);
​    }
​    fos.close();
​    fis.close();
}

//字节缓冲流 一次读取一个字节  共耗时:1834毫秒
public static void method3() throws IOException
{
    BufferedInputStream bis=new BufferedInputStream(new FileInputStream("D:\\JavaSE\\src\\6.avi"));
    BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("D:\\JavaSE\\6.avi"));

​    int read = bis.read();
​    while(bis.read()!=-1)
​    {
​        bos.write(read);
​    }
​    bos.close();
​    bis.close();

}

//字节缓冲流 一次读取一个字符数组 共耗时:101毫秒
public static void method4() throws IOException
{
    BufferedInputStream bis=new BufferedInputStream(new FileInputStream("D:\\JavaSE\\src\\6.avi"));
    BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("D:\\JavaSE\\6.avi"));

​    byte[] bys=new byte[1024];
​    int len;
​    while((len=bis.read(bys))!=-1)
​    {
​        bos.write(bys,0,len);
​    }
​    bos.close();
​    bis.close();

}

}
```

# 29、字符缓冲流

| BufferedWriter | 将文本写入字符输出流，缓冲字符，提供单个字符，数组和字符串的高效写入 |
| -------------- | ------------------------------------------------------------ |
| BufferedReader | 从字符输入流读取文本，缓冲字符，提供字符，数组和行的高效读取 |

```java
package Java_Stu;
import java.io.*;
public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        //存数据
       BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\JavaSE\\66.txt"));
       bw.write("hello\n");
       bw.write("world");
       bw.close();

   BufferedReader br=new BufferedReader(new FileReader("D:\\JavaSE\\66.txt"));
   //读数据
    char []chs=new char[1024];
    int len;
    while((len=br.read(chs))!=-1)
    {
        System.out.println(new String(chs,0,len));
    }
    br.close();
}

}
```

### 字符缓冲流的复制

```java
package Java_Stu;
import java.io.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        //创建字符缓冲流
        //这个是复制后文件的位置
       BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\JavaSE\\JavaCopy.java"));
        //这个写的是要复制的文件
       BufferedReader br=new BufferedReader(new FileReader("D:\\JavaSE\\JavaTest.java"));
       //以一次读取一个字符数组
        char []chs=new char[1024];
        int len;
        while((len=br.read(chs))!=-1)
        {
           bw.write(chs,0,len);
        }
        bw.close();
        br.close();
    }
}
```

改进版 字符缓冲流复制JAVA文件:
这种方法好在不用写数组，没有测试速度是否会快一些

```java
package Java_Stu;
import java.io.*;
public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        //创建字符缓冲流
        //这个是复制后文件的位置
       BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\JavaSE\\JavaCopy.java"));
        //这个写的是要复制的文件
       BufferedReader br=new BufferedReader(new FileReader("D:\\JavaSE\\JavaTest.java"));
        //一次读一行数据
        String line;
        while((line=br.readLine())!=null)
        {
            bw.write(line);//写数据
            bw.newLine();//写换行符
            bw.flush();//做一个刷新
        }
        bw.close();	
        br.close();
    }
}
```

### 集合到文件中

```java
package Java_Stu;
import java.io.*;
import java.util.ArrayList;
public class HashCodeDemo {
    public static void main(String[] args) throws IOException
    {
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("hello");
        arr.add("world");
        arr.add("java");
        BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\JavaSE\\arr.txt"));
        //遍历数组
        for(String s:arr)
        {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
}
```

### 文件到集合中去读取

```java
package Java_Stu;
import java.io.*;
import java.util.ArrayList;
public class HashCodeDemo {
    public static void main(String[] args) throws IOException
    {
        ArrayList<String> arr=new ArrayList<String>();
        BufferedReader br=new BufferedReader(new FileReader("D:\\JavaSE\\arr.txt"));
        String line;
        while((line=br.readLine())!=null)//读取数据
        {
            //把读取到的数据添加到集合中去
            arr.add(line);
        }
        br.close();
        //遍历集合
        for(String s:arr)
        {
            System.out.println(s);
        }
    }
}
```

### 随机点名器

```java
package Java_Stu;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class HashCodeDemo {
    public static void main(String[] args) throws IOException
    {
        //随机点名器
        ArrayList<String> arr=new ArrayList<String>();
        BufferedReader br=new BufferedReader(new FileReader("D:\\JavaSE\\arr.txt"));
        String line;
        while((line=br.readLine())!=null)//读取数据
        {
            //把读取到的数据添加到集合中去
            arr.add(line);
        }
        br.close();
        Random rand=new Random();//创建随机数
        int r=rand.nextInt(0,arr.size());//随机数范围是[0,数组长度]
        System.out.println("第"+(r+1)+"位学生是:"+arr.get(r));
    }
}
```

### 集合到文件中 并排序(TreeSet和类的使用写入)

```java
package Java_Stu;
import java.io.*;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        TreeSet<Student_Grade> ts=new TreeSet<Student_Grade>(new Comparator<Student_Grade>() {
            @Override
            public int compare(Student_Grade s1, Student_Grade s2)
            {
                //先判断总体成绩
                int total=(s2.getChinese()+s2.getEnglish()+s2.getMath())-(s1.getMath()+s1.getEnglish()+s1.getEnglish());
                //判断语文成绩
                int math=total==0?s2.getChinese()-s1.getChinese():total;
                return math;
            }
        });

​    //存入文件 键盘录入 由高到低排序 用TreeSet
​    for(int i=0;i<2;i++)
​    {
​        Student_Grade S=new Student_Grade();
​        System.out.println("请输入第"+(i+1)+"个学生的信息:");
​        System.out.println("名字:");
​        Scanner sc=new Scanner(System.in);
​        String name = sc.nextLine();
​        System.out.println("语文成绩:");
​        Scanner chinese_int=new Scanner(System.in);
​        int chinese = chinese_int.nextInt();
​        System.out.println("数学成绩:");
​        Scanner math_int=new Scanner(System.in);
​        int math = math_int.nextInt();
​        System.out.println("英语成绩:");
​        Scanner english_int=new Scanner(System.in);
​        int english = english_int.nextInt();
​        S.setName(name);
​        S.setChinese(chinese);
​        S.setMath(math);
​        S.setEnglish(english);
​        ts.add(S);
​    }
​    //写入数据
​    BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\JavaSE\\arr2.txt"));
​    //遍历集合并且写入
​    for(Student_Grade S:ts)
​    {
​        //在Student_Grade中有一个toString的重写操作
​        String s = S.toString();
​        bw.write(s);
​        bw.newLine();
​        bw.flush();
​    }
​    bw.close();
}

}
```

### 复制单级文件夹

```java
package Java_Stu;
import java.io.*;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        //创建目录file对象
        File srcFile=new File("D:\\66");
        //获取目录file的对象名称
        String file_name= srcFile.getName();
        //创建目的地的file对象
        File destFolder=new File("D:\\JavaSE",file_name);
        //判断目的地目录是否存在
        if(!destFolder.exists())//如果不存在
        {
            destFolder.mkdir();//创建一个新的
        }
        //获取file目录下的文件
        File[] files = srcFile.listFiles();
        System.out.println(files.length);
        //遍历files所有文件
        for(File f:files)
        {
            String name = f.getName();
            //创建目的地文件file对象，路径名是目的地目录
            File destFile=new File(destFolder,name);
            //复制文件
            copyFile(f,destFile);
        }
    }

private static void copyFile(File file, File destFile) throws IOException
{
    BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
    BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(destFile));
    //读写操作
    byte []bys=new byte[1024];
    int len;
    while((len=bis.read())!=-1)
    {
        bos.write(bys,0,len);
    }
    bis.close();
    bos.close();
}

}
```

### 复制多级文件夹

```java
package Java_Stu;
import java.io.*;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        //创建目录file对象
        File srcFile=new File("D:\\66");
        //获取目录file的对象名称
        String file_name= srcFile.getName();
        //创建目的地的file对象
        File destFolder=new File("C:\\");
        copyFolder(srcFile,destFolder);
    }
    //复制文件夹
    public static void copyFolder(File file,File destFile) throws IOException
    {
        //判断是否为目录
        if(file.isDirectory())
        {
            //在目的地下创建和file名称一样的目录
            String name = file.getName();
            File newFile=new File(destFile,name);//这里的name是destFolder的D:\\JavaSE
            if(!newFile.exists())//判断一下是否有这个文件
            {
                newFile.mkdir();//没有就创建
            }
            //获取数据源file下所有文件或者目录的file数组
            File[] files = file.listFiles();
            for(File f:files)
            {
                //把该file作为数据源file对象，递归调用
                copyFolder(f,newFile);
            }
        }
        else
        {
          //说明是文件 就直接复制
          File newFile=new File(destFile,file.getName());
          copyFile(file,newFile);
        }
    }
    //复制文件
     private static void copyFile(File file, File destFile) throws IOException
    {
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(destFile));
        //读写操作
        byte []bys=new byte[1024];
        int len;
        while((len=bis.read())!=-1)
        {
            bos.write(bys,0,len);
        }
        bis.close();
        bos.close();
    }
}
```

# 30、标准输入流：InputSteam

- 就是Scanner的底层原理 用字符输入流去读取 在内部有一个InputStreamReader而在里面还有一个System.in来写入

```java
package Java_Stu;
import java.io.*;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        //用字符输入流读取 输入的信息
        BufferedReader bis=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符串:");
        String line = bis.readLine();
        System.out.println("您输入的字符串是:"+line);

​    System.out.println("请输入一个整数:");
​    int i = Integer.parseInt(bis.readLine());
​    System.out.println("您输入的整数是:"+i);
}

}
```

- 对象(这个玩意不出意外 应该是类)序列化：就是将对象保存到磁盘中，或者在网络中传输对象

| 对象序列化流   | ObjectOutputStream |
| -------------- | ------------------ |
| 对象反序列化流 | ObjectInputStream  |




- 如果一个对象想要被序列化，该对象所属的类必须实现Serializable接口，只用实现就可，不用重写任何方法

```java
//就是这个public class Student implements Serializable

package Java_Stu;
import java.io.*;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化流:
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("D:\\JavaSE\\oos.txt"));
        Student s1=new Student("梅西",18);
        Student s2=new Student("哈维",20);
        //写入s1与s2到序列化流中
        oos.writeObject(s1);
        oos.writeObject(s2);

​    //反序列化流
​    ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\JavaSE\\oos.txt"));
​    Object obj = ois.readObject();
​    Object obj2=ois.readObject();
​    System.out.println(obj);
​    System.out.println(obj2);

​    ois.close();
​    oos.close();
}

}
```

### Properties作为Map集合的使用

- Properties：是一个Map体系的集合类，Properties可以保存到流中或从流中加载

```java
package Java_Stu;
import java.io.*;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
        Properties pp=new Properties();
        Student s1=new Student("梅西",18);
        Student s2=new Student("哈维",20);
        pp.put(s1,s2);//因为这个Properties的内部传参是属于object类型 所有可以直接传对象
       Set<Object> keySet=pp.keySet();//和那个HashMap差不多的获取方式 keyset
        for(Object key:keySet)//遍历
        {
            Object value = pp.get(key);//键
            System.out.println(key);
            System.out.println(value);//值
        }
   }
}
```

### Properties作为集合的特有方法

| Object setProperty(String key,String value) |  设置集合的键和值，都是String类型，底层调用Hashtable方法put  |
| ------------------------------------------- | :----------------------------------------------------------: |
| String getProperty(String key)              |               使用此属性列表中指定的键搜索属性               |
| Set<String> stringPropertyNames()           | 从该属性列表中返回一个不可修改的键集，其中键及其对应的值是字符串 |

```java
package Java_Stu;
import java.io.*;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args)
    {
        Properties pp=new Properties();
        pp.setProperty("龟龟","李易恒");
        pp.setProperty("猫猫","宣百");
        pp.setProperty("怪兽","晨儿");
        Set<String> keySet=pp.stringPropertyNames();
        for(String key:keySet)
        {
            String value=pp.getProperty(key);
            System.out.println(key+","+value);
        }
    }
}
```

### Properties的IO流操作方法

| void load(InputStream inStream)              |                   从输入字节流读取属性列表                   |
| -------------------------------------------- | :----------------------------------------------------------: |
| void load(Reader reader)                     |                   从输入字符流读取属性列表                   |
| void store(OutputStream out,String comments) | 将此属性列表(键和元素对)写入此Properties表中，以合适与使用load(InputStream)方法的格式写入输出字节流 |
| void store(Writer writer,String comments)    | 将此属性列表(键和元素对)写入此Properties表中，以合适使用load(Reader)方法的格式写入输出字符流 |

```java
package Java_Stu;
import java.io.*;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException
    {
        //把集合保存进文件
        Properties pp=new Properties();
        pp.setProperty("龟龟","李易恒");
        pp.setProperty("猫猫","宣百");
        pp.setProperty("怪兽","晨儿");
        FileWriter fw=new FileWriter("D:\\JavaSE\\oos.txt");
        pp.store(fw,null);//里面存的是Write和String类型的数据
        fw.close();
        //把文件数据加载到集合
        Properties pp_load=new Properties();//创建一个新的集合
        FileReader fr=new FileReader("D:\\JavaSE\\oos.txt");
        pp_load.load(fr);//获取一下键和值
        System.out.println(pp_load);//输出
        fr.close();
    }
}
```

### 猜数游戏 只能玩三次 

```java
package Java_Stu;
import java.io.*;
import java.util.*;

public class HashCodeDemo
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
       Properties pp=new Properties();//创建一个Properties对象
       FileReader fr=new FileReader("D:\\JavaSE\\src\\Java_Stu\\game.txt");//读取game.txt 这里面的内容需要提前写好 写键与值的对应 及  次数=0
       pp.load(fr);//加载game.txt的内容
       fr.close();
       String ss = pp.getProperty("次数");//搜索里面的"次数"关键字 用ss去接受
       int i = Integer.parseInt(ss);//转换成int类型 方便做运算
        if(i>=3)//只能玩三次
        {
            System.out.println("游戏试玩结束,请充值");//三次一到 不让玩了
        }
        else//没到三次
        {
            game();
            i++;//玩一次就做一次i++
            pp.setProperty("次数",String.valueOf(i));//设置值为i i变成3  意思键对的值也是3了
            FileWriter fw=new FileWriter("D:\\JavaSE\\src\\Java_Stu\\game.txt");
            pp.store(fw,null);//写入Properties表中
            fw.close();
        }
    }
    public static void game()
    {
      Random r=new Random();
      int rand=r.nextInt(0,100);
      System.out.println(rand);
      System.out.print("请输入你的猜测结果:");
      Scanner sc=new Scanner(System.in);
      int g_ans = sc.nextInt();
      while(rand!=g_ans)
      {
          if(g_ans>rand)
          {
              System.out.print("您输入的结果偏大,请重新输入:");
              g_ans=sc.nextInt();
          }
          else if (g_ans<rand)
          {
              System.out.print("您输入的结果偏小，请重新输入:");
              g_ans=sc.nextInt();
          }
          else
          {
              System.out.println("恭喜您，猜中了！");
          }
      }
        if(rand==g_ans)
        {
            System.out.println("恭喜您，猜中了！");
        }
    }
}
```

# 31、多线程

- 方法1:继承Thread类
- 定义一个类继承Thread类
- 在类中重写run()方法
- 创建类的对象
- 启动线程

- 为什么要重写run ()方法:

​			因为run()是用来封装被线程执行的代码

- run()方法和start()方法的区别

​			run():封装线程执行的代码，直接调用，相当于普通方法的调用

​			start():启动线程；然后由JVM调用此线程的方法



### 设置和获取线程名称

- ​	Thread类中设置和获取线程名称的方法

| void setName(String name) | 将此线程的名称更改为等于参数name |
| ------------------------- | -------------------------------- |
| String getName()          | 返回此线程的名称                 |

- 通过构造方法也可以设置线程名称

- 如何获取main()方法所在的线程名称

| public static Thread currentThread() | 返回对当前正在执行的线程对象的引用 |
| ------------------------------------ | ---------------------------------- |

### 线程调度

| 分时调度模型   | 所有线程轮流使用CPU的使用权，平均分配每个线程占用CPU的时间片 |
| -------------- | ------------------------------------------------------------ |
| 抢占式调度模型 | 优先让优先级高的线程使用CPU，如果线程的使用级别相同，会随机调用线程，优先级高的线程获取的CPU时间片会多一点 |

- Java使用的是抢占式调度模型
- Thread类中设置和获取线程优先级的方法

| public final int getPriority()                 | 返回此线程的优先级 |
| ---------------------------------------------- | ------------------ |
| public final void setPriority(int newPriority) | 更改此线程的优先级 |

- 线程默认的优先级是5，最低优先级是1，最高优先级是10

### 线程控制

| static void sleep(long millis) | 使当前正在执行的线程暂停指定的毫秒数                         |
| ------------------------------ | ------------------------------------------------------------ |
| void join()                    | 等待这个线程死亡                                             |
| void setDaemon(boolean on)     | 将此线程标记为守护线程，当运行的线程都是守护线程时，JVM将退出 |

### 多线程的实现方式

方法2:实现Runnable接口

- 定义一个类实现 Runnable接口

- 在类中重写run()方法

- 创建类的对象

- 创建对象

- 启动线程

多线程的实现方案有两种

- 继承Thread类

- 实现Runnable接口

相比于继承Thread类，实现Runnable接口的好处

- 避免了Java单继承的局限性

- 适合多个相同程序的代码去处理同一个资源的情况，把线程和程序的代码、数据有效分离，较好的体现了面向对象的设计思想

```java
package Java_Stu;
public class game implements Runnable
{
    public  void run()
    {
        for(int i=0;i<100;i++)
        {
            System.out.println(Thread.currentThread().getName()+","+i);
        }
    }
}
```

```java
package Java_Stu;
import java.io.*;
import java.util.*;
public class HashCodeDemo
{
    public static void main(String[] args)
    {
       game g1=new game();
       game g2=new game();

       Thread t1=new Thread(g1,"G63");
       Thread t2=new Thread(g2,"B-2轰炸机");

       t1.start();
       t2.start();

    }
}
```

### 卖票

同步代码块: 锁多条语句操作共享数据，可以使用同步代码块实现

- 格式

`synchronized(Object obj)` 这个如果只需要一把锁的话 在类中定义一个 `Object obj=new object()`

{

​		多条语句操作共享数据的代码

}

`synchronized(Object obj)`:就相当于给代码加锁了,任意对象可以看出一把锁

同步的好处和弊端

- 好处:解决了多线程的数据安全问题

- 弊端:当线程多时，每个线程都回去判断同步上的锁，浪费资源

```java
package Java_Stu;

import static java.lang.System.exit;

public class sellTickets implements Runnable
{
    private int Tickets=0;
    private Object obj=new Object();
    @Override
    public void run()
    {
        while(true)
        {
            synchronized (obj)//给同步代码块上锁 上一把锁 使每次只能进入一个线程
            {
                if(Tickets<100)
                {
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                    Tickets++;//每卖一张票就做一次++
                    System.out.println(Thread.currentThread().getName()+"正在售出第:"+Tickets+"张票");
                    if(Tickets==100)//当卖票数为100后 退出程序
                    {
                        System.out.println("票已卖完");
                        exit(0);//直接退出程序了
                    }
                }
            }
        }
    }
}
```

```java
package Java_Stu;

public class SellTicketThread
{
    public static void main(String[] args)
    {
        sellTickets s1=new sellTickets();

        Thread t1=new Thread(s1,"卖票口1:");
        Thread t2=new Thread(s1,"卖票口2:");
        Thread t3=new Thread(s1,"卖票口3:");

        t1.start();
        t2.start();
        t3.start();
    }
}
```

# 32、位或运算

```java
package Java_Stu;

public class LeetCode
{
    public static void print(int num)
    {
        for(int i=31;i>=0;i--)
        {
            System.out.print((num&(1<<i))==0? "0":"1"); //用左移运算符来算
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        print(5);
    }
}
```

# 33、Arrays.sort的比较问题

对于二维数组的比较是这样的

```java
 Arrays.sort(boxTypes,(a,b)->(a[0]-b[0]));   //这里的函数运用的是compare比较器 (a,b)->(a[0]-b[0])是对第一个元素进行排序 (a,b)->(a[1]-b[1])是对第二个元素进行排序
```

# 34、MyBatis

- MyBatis是一款持久层框架,用于简化JDBC的开发
- 几乎免除了所有的JDBC代码以及设置参数和获取结果集的工作

**持久层**

- 负责将数据到保存到数据库的那一层代码
- JavaEE三层架构:表现层、业务层、持久层

**快速入门**

- 找寻user表中所有数据

1. 创建user表，添加数据(使用了Navicat创建，创建了一个名字为mybatis的数据库里面有一个叫tb_user的表)
2. 创建模块,导入坐标(就是各种依赖 版本号等等)

​	3.编写MyBatis核心配置文件-->替换连接信息 解决硬编码问题(命名为mybatis-config.xml 位于resources文件中)

```xml
<!--数据库连接信息 -->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///mybatis?useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
```

​	4.编写SQL映射文件-->统一管理sql语句,解决硬编码问题

```xml
<select id="selectAll" resultType="pojo.User">
        select * from tb_user;
    </select>
```

这里主要是改变一下resultType的名称为我们创建在pojo文件夹中的User类

​	5.编码

​				  1.定义POJO类

​				  2.加载核心配置文件，获取SqlSessionFactory对象

​				  3.获取SqlSession对象,执行SQL语句

​				  4.释放资源

```java
        //加载mybatis的核心配置文件 获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取Sqlsession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql
        List<Object> users = sqlSession.selectList("test.selectAll");
        System.out.println(users);
        //释放资源
        sqlSession.close();

```

### 使用Mapper代理完成入门案例

1. 定义与SQL映射文件同名的Mapper接口,并且将Mapper接口和SQL映射文件放置在同一目录下

![image-20230310004941074](C:\Users\lyh\AppData\Roaming\Typora\typora-user-images\image-20230310004941074.png)

这里需要注意的是在resources下面的mapper是要与上面的java存在接口的文件名相同,然后将UserMapper.xml文件拖进去,用Maven构建测试接口与xml文件是不是在同一个文件夹中

2.设置SQL映射文件的namespace属性为Mapper接口全限定名

```xml
<!--namespace改为接口文件下面的接口 -->
<mapper namespace="mapper.UserMapper">
    <select id="selectAll" resultType="pojo.User">
        select * from tb_user;
    </select>
</mapper>
```

3.在Mapper接口中定义方法,方法名就是SQL映射文件中sql语句的id,并保持参数类型和返回值类型一致

```java
public interface UserMapper
{
    List<User> selectAll();
}
//因为获取的是一组数据 所以是List 后面的方法是前面select的id方法
```

4.编码

1. 通过SqlSession的getMapper方法获取 Mapper接口的代理对象
2. 调用对应方法完成sql的执行

```java
UserMapper mapper = sqlSession.getMapper(UserMapper.class);//这里用的就是接口里面的方法
        List<User> users = mapper.selectAll();//实例化方法
```

### 用mybatis来进行查询

- 比如这里我查询了tb_brand表中的id为1的数据 需要先在Brand接口中添加一个方法为

  ```java
  Brand selectByID(int id);
  ```

- 然后在BrandMapper.xml中进行配置sql查询方法

```xml
<select id="selectByID" resultType="pojo.Brand">
        select *
        from tb_brand where id=#{id};
    </select>
```

- 最后在test文件夹中进行执行方法的测试

```java
//3.获取Mapper接口的代理对象
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
//4.执行方法
        Brand brand = brandMapper.selectByID(id);
        System.out.println(brand);
```

- 查询结果

$$
Brand{id=1, brandName='三只松鼠', companyName='三只松鼠股份有限公司', ordered=5, description='不好吃还贵', status=0}
$$

- 在使用过程中有一个参数占位符
- #{}:执行SQL时,会将#{}占位符替换成?,自动设置参数值
- ${}:拼SQL,会存在SQL注入问题 比如我在id=${id};当我传入id=1时,会显示1
- 参数传递用#{}
- 如果要对表名,列名进行动态设置,只能用${}进行sql拼接

2.SQL语句中特殊字符处理:

- 转义字符
- <![CDATA[内容]]>

```xml
from tb_brand where id <![CDATA[>
    ]]>#{id};
```

(但是还是没能用上<符号..)

### 条件查询

- 散装查询:如果方法中有多个参数,需要使用@Param("SQL参数占位符名称")

```java
List<Brand> selectByCondition(@Param("status")int status, @Param("companyName")String companyName, @Param("brandName")String brandName);
```



- 对象参数:对象的属性名称要和参数占位符名称一致

如果我们要运用到模糊查询的话 要用到**like#**这样的字段

```xml
<select id="selectByCondition" resultType="pojo.Brand">
    select *from tb_brand
    where status=#{status}
    and companyName like#{companyName}<!--模糊查询 用like#{SQL名}-->
        and brandName like#{brandName}
    </select>
```

### 多条件动态查询

if:用于判断参数是否有值,使用test属性进行条件判断

如果只查其中一个值时 可能会出现问题 出现where and...的报错,

需要使用<where>标签替换where关键字

```xml
<where>
        <if test="status!=null">
            and status=#{status}
        </if>
        <if test="companyName!=null and companyName!=''">
            and companyName like#{companyName}<!--模糊查询 用like#{SQL名}-->
        </if>
        <if test="brandName!=null and brandName!=''">
            and brandName like#{brandName}
        </if>
    </where>
```

### 单条件动态查询

- **choose(when,otherwise)**:选择,相当于switch语句

但是如果用where标签后,就可以不用写otherwise

```xml
 <select id="selectByConditionSingle" resultType="pojo.Brand">
        select * from tb_brand
       <where>
           <choose>
               <when test="status!=null">
                   status=#{status}
               </when>
               <when test="companyName!=null and companyName!=''">
                   companyName like#{companyName}
               </when>
               <when test="brandName!=null and brandName!=''">
                   brandName like#{brandName}
               </when>
           </choose>
       </where>
    </select>
```

### 添加功能

- 在BrandMapper.xml中添加 insert

```xml
<insert id="add">
        insert into tb_brand(brandName,companyName,ordered,description,status)
        values(#{brandName},#{companyName},#{ordered},#{description},#{status});
    </insert>
```

- 然后再BrandMapper中添加方法

```java
 void add(Brand brand);
```

- 最后在测试中执行方法

其中在执行添加时,如果不进行提交会导致事物回滚,无法记录进入sql中去,所有有两种方法来进行提交事物

- openSession():默认开启事物,进行增删改操作后需要使用sqlSession.commit();手动提交事物

```java
//4.执行方法
        brandMapper.add(brand);
        //4.1提交事物
        sqlSession.commit();
```

- openSession(true):可以设置为自动提交事物(关闭事物)

```java
 //2.获取sqlSession对象
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
```

### 全部修改功能

```xml
<!--修改功能-->
    <update id="update">
        update tb_brand
        set
        brandName=#{brandName},
        companyName=#{companyName},
        ordered=#{ordered},
        description=#{description},
        status=#{status}
        where id=#{id};
    </update>
```

这里是通过id来进行修改的,通过查询到的id来进行修改,在BrandMapper中的接口是

```java
int update(Brand brand);
```

### 动态修改字段

```xml
<update id="update">
        update tb_brand
        <set>
        <if test="brandName!=null and brandName!=''">
            brandName=#{brandName}
        </if>
        <if test="status!=null">
            status=#{status}
        </if>
        <if test="companyName!=null and companyName!=''">
            companyName=#{companyName}
        </if>
        <if test="ordered!=null and ordered!=''">
            ordered=#{ordered}
        </if>
        <if test="description!=null and description!=''">
            description=#{description}
        </if>
        where id=#{id};
        </set>
    </update>
```

通过if去判断来进行修改,如果只修改一个值,用set语句,这样可以防止只传入一个参数时,其他参数被置为null的情况

### 删除功能

- 单个删除(通过id进行删除)

```xml
<delete id="deleteById">
        delete from tb_brand where id=#{id}
    </delete>
```

- 批量删除(通过ids[]数组进行删除)

```java
void deleteByIds(@Param("ids") int ids[]);
```

- 默认:array=数组 array ,或者在BrandMapper中用@Param改变Map集合默认key的名称
- item是元素
- separator是分隔符
- 数组通过foreach来遍历 这里的collection="ids"是通过前面的Param来改变的名称

```xml
<delete id="deleteByIds">
        delete from tb_brand where id
        in(
            <foreach collection="ids" item="id" separator=",">
                #{id}
            </foreach>
        );
    </delete>
```

# 35、TomCat

- 也被称为Web容器,Servlet容器. Servlet需要依赖于Tomcat运行

# 36、Servlet

- Servlet是Java提供的一门动态web资源开发技术
- Servlet本质上是一个接口,是由JavaEE(Java企业版)的规范之一

1. 创建web项目,导入Servlet依赖坐标

```xml
<dependencies>
    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>4.0.4</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>
```

​	2.创建:定义一个类,实现Servlet接口,并重写接口中所有方法(用alt+enter就可以完成全部重写)

```java
 public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException
    {
        System.out.println("servlet hello world");
    }
```

​	3.配置:在类上使用@WebServlet注解,配置该Servlet的访问路径

```java
@WebServlet("/demo1")
public class Demo1 implements Servlet
```

​	4.访问:启动Tomcat,访问到该Servlet

- Servlet运行在Servlet容器(web服务器)中,其生命周期由容器来管理,分为4个阶段

1. **加载和实例化**:默认情况下,当Servlet第一次被访问时,由容器创建Servlet对象
2. **初始化**:在Servlet实例化之后,容器将调用Servlet的**init()**方法初始化这个对象,完成一些如加载配置文件、创建链接等初始化的工作;该方法只**调用一次**
3. **请求处理**:每次请求Servlet时,Servlet容器都会调用Servlet的**service()**方法对请求进行处理
4. **服务终止**:当需要释放内存或者容器关闭时,容器就会调用Servlet实例的destroy()方法完成资源的释放.在**destroy()**方法调用之后,容器会释放这个Servlet实例,该实例随后会被Java的垃圾收集器所回收

HttpServlet使用步骤

1. 继承HttpServlet
2. 重写doGet和doPost方法

HttpServlet原理

​	获取请求方式,并根据不同的请求方式,调用不动的方法

- 在HttpServlet中会根据不同的请求方式来调用不同的方法,是根据Get和Post的请求消息不大一样,需要分别处理
- 通过不同的请求逻辑,用if_else语句进行判断调用

### Servlet的urlPattern配置

1. 精确匹配:

- 配置路径:

  ```java
  @WebServlet("/demo2")
  ```

- 访问路径:用localhost:8080后面跟随demo2就可以访问了

​	2.目录匹配

- 配置路径:

  ```java
  @WebServlet("/demo2/*")
  ```

- 访问路径:与精确匹配差不多,但是会先访问到精确匹配

​	3.扩展名匹配

- 配置路径

```java
@WebServlet("*.do")
```

- 访问路径:在后面跟.do

​	4.任意匹配

- 配置路径

  ```java
  @WebServlet("/")
  @WebServlet("*")
  ```

- 访问路径:无论访问什么路径,都会访问到这个设置的任意匹配路径来

### Request&&Respons

- Request:获取请求数据
- Responss:设置响应数据

一些请求方式:

```java
 //获取请求方式:GET
        String method = req.getMethod();
        System.out.println(method);
        //获取虚拟目录(项目访问路径):/request-demo
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        //获取URL(统一资源定位符)
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL);
        //获取URI(统一源标识符)
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        //获取请求参数(GET方法)
        String queryString = req.getQueryString();
        System.out.println(queryString);
```

#### 登录请求

先写好html文件:

```html
<form action="demo3" method="post">//这个demo3就是我WebServlet的设置名称 用post请求方式 这里只能用post去请求,用get请求不到
    UserName:<input type="text" name="username"/><br />
    PassWord:<input type="password" name="password"/><br />
    <input type="submit" value="Submit">
  </form>
```

然后再demo3中写:

```java
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
       //获取post的请求体

        //1.获取字符输入流
        BufferedReader reader = req.getReader();
        //2.读取数据
        String line=reader.readLine();
        System.out.println(line);
    }
```

### 使用Request通用方法来获取请求参数

- Map<String String[] getParameterMap()>:获取所有参数Map集合
- String[]getParameterValues(String name):根据名称获取参数值(数组)
- String getParameter(String name):根据名称获取参数值(单个值)

```java
 //Get请求
        System.out.println("Get!");
        //获取所有参数的Map集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        for(String key: parameterMap.keySet())
        {
            System.out.print(key+":");
            //获取值
            String[]values= parameterMap.get(key);
            for(String value:values)
                System.out.println(value+":");
            System.out.println();
        }
        System.out.println("----------------------");
        //获取对应的参数值
        String[] hobbies = req.getParameterValues("hobby");
        for(String s:hobbies)
        {
            System.out.println(s);
        }
        //根据key获取单个参数值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);

protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
       //获取post的请求体
        this.doGet(req, resp);
    }
```

```html
 <form action="demo3" method="post">
    UserName:<input type="text" name="username"/><br/>
    PassWord:<input type="password" name="password"/><br/>
      <input type="checkbox" name="hobby" value="1">Swim
      <input type="checkbox" name="hobby" value="2">Soccer<br>
    <input type="submit" value="Submit">
  </form>
```

在post中调用get后就可以请求到get中去了

### 请求转发

- 请求转发(forword):一种在服务器内部的资源跳转方式

- 实现方法:

- ```java
  req.getRequestDispatcher("资源B路径").forward(req,resp);
  ```

- 请求转发资源间共享数据:使用Request对象
- void setAttribute(String name,Object o):存储数据到request域中;
- Object getAttribute(String name):根据key,获取值;
- void removeAttribute(String name):根据key,删除该键值对

#### 它的特点

- 浏览器地址栏路径不发生变化
- 只能转发到当前服务器的内部资源
- 一次请求,可以在转发的资源间使用request共享数据

Demo3中:

```java
 		System.out.println("demo3");
        //存储数据
        req.setAttribute("Fuck","You");
        //请求转发
        req.getRequestDispatcher("/demo5").forward(req,resp);
```

Demo5中:

```java
		System.out.println("哈哈哈哈");
        Object fuck = req.getAttribute("Fuck");
        System.out.println(fuck);
        //输出的就是You 和 上面的哈哈哈哈
```

### 案例:登录与注册

1.首先进行登录页面的编写

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html";charset="UTF-8";>
    <title>Login</title>
</head>
<body>
  <form action="demo3" method="post">
    UserName:<input type="text" name="username"/><br/>
    PassWord:<input type="password" name="password"/><br/>
    <input type="submit" value="Submit">
      <input type="reset" value="reset">
      <a href="register.html">No Username?Register Now!</a>
  </form>
</body>
</html>
```

2.编写好后,准备mybatis的数据

在resources中添加mybatis-config.xml文件 里面放置数据库连接信息

提前创建好数据库db1并在其库中建立表单tb_user

3.在resources中建立一个Mapper文件夹用于存放UserMapper.xml

UserMapper.xml中存放名称空间

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--
    namespace:名称空间
-->
<mapper namespace="mapper.UserMapper">

</mapper>
```

4.在java下的文件中添加一个用于装UserMapper接口的文件

```java
public interface UserMapper
{
    @Select("select *from tb_user where username= #{username} and password= #{password}")
    User select(@Param("username")String username, @Param("password")String password);

    //根据用户名查询是否存在
    @Select("select *from tb_user where username= #{username}")
    User selectByUsername(String username);

    //添加用户
    @Insert("insert into tb_user values(null,#{username},#{password})")
    void add(User user);
}
```

5.建立一个pojo文件夹,用于装User对象

对象中主要储存id username password

```java
public class User
{
    private Integer id;
    private String username;
    private String password;

    public User() 
    {
    }
}
```

6.现在可以进行登录页面的后端书写了

```java
@WebServlet("/demo3")
public class ServletDemo3 extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //1.接收用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.调用Mybatis完成查询
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
        //2.2获取SqlSession对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //2.3获取Mapper
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        //2.4调用方法
        User user=userMapper.select(username,password);
        //2.5释放资源
        sqlSession.close();

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html;charset=utf-8");
        //3.判断User是否为null
        if(user!=null)
        {
            //登录成功
           resp.sendRedirect("welcome.html");
        }
        else
        {
            //登录失败
            writer.write("登录失败!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
       //获取post的请求体
        this.doGet(req, resp);
    }
}
```

7.然后是注册页面后端书写

```java
@WebServlet(value = "/demo4")
public class ServletDemo4 extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //1.接受用户数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //封装用户对象
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        //2.调用Mybatis完成查询
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
        //2.2获取SqlSession对象
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //2.3获取Mapper
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        //2.4调用方法
        User user1 = userMapper.selectByUsername(username);

        //3.判断用户对象是否为null
        if(user1==null)
        {
            //说明用户名不存在 可用 添加用户
            userMapper.add(user);
            //提交事物
            sqlSession.commit();
            sqlSession.close();
            resp.sendRedirect("login.html");
        }
        else
        {
            //不能添加
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("不要取公交车用户名哦!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

# 37、Cookie

### cookie基本使用

**发送Cookie**

- 1.创建Cookie对象,设置数据

```java
Cookie cookie=new Cookie("username","zs");
//第一个是key 第二个是value
```

- 2.发送Cookie到客户端:使用response对象

```java
resp.addCookie(cookie);
```

**获取Cookie**

- 1.获取客户端携带的所有Cookie,使用request对象

```java
Cookie[]cookies=req.getCookies();
```

- 2.遍历数组,获取每一个Cookie对象:for
- 3.使用Cookie对象方法获取数据

```java
cookie.getName();
cookie.getValue();
```

总体代码:

```java
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
       //获取Cookie数组
        Cookie[] cookies = req.getCookies();
        for(var cookie:cookies)
        {
                String name=cookie.getName();
                String value=cookie.getValue();
                System.out.println(name+":"+value);
                break;
        }

    }
```

### cookie原理

- Cookie的实现是基于HTTP协议的

- 响应头:set-cookie
- 请求头:cookie

**Cookie存活时间**

- 默认情况下,Cookie存储在浏览器内存中,当浏览器关闭,内存释放,则Cookie被销毁
- setMaxAge(int seconds):设置Cookie存活时间

1. 正数:将Cookie写入浏览器所在电脑的硬盘,持久化存储
2. 负数:默认值,Cookie在当前浏览器内存中,当浏览器关闭,则Cookie被销毁
3. 0:删除对应Cookie

**Cookie存储中文**

# 38、Session

### Session基本

- 服务端回话跟踪技术:将数据保存到服务端
- JavaEE提供的HttpSession接口,来实现一次回话的多次请求
- 使用

1.获取Session对象

```java
HttpSession session=request.getSession();
```

2.Session对象功能

| 函数名                                  | 功能                  |
| --------------------------------------- | --------------------- |
| Object getAttribute(String name)        | 根据key获取值         |
| void setAttribut(String name ,Object o) | 存储数据到session域中 |
| void removeAttribute(String name)       | 根据key,删除该键值对  |

- Session钝化、活化:

  **钝化**:在服务器正常关闭后,Tomcat会自动将Session数据写入硬盘文件中

  **活化**:再次启动服务器后,从文件中加载数据到Session中

- Session销毁:

  **默认:**无操作情况下,30分钟自动销毁

  还可以调用Session对象的invalidate()方法

## Cookie和Session小结

- Cookie和Session都是来完成一次回话内多次请求间**数据共享**的

- 区别:

​	

|            | Cookie                                                       | Session                           |
| ---------- | ------------------------------------------------------------ | --------------------------------- |
| 存储位置   | 客户端                                                       | 服务端                            |
| 安全性     | 不安全(在网络端进行传输时,被拦截,或者存在本地浏览器的Cookie可能会被他人盗取) | 安全(存储在服务器端,不容易被攻破) |
| 数据大小   | 最大3KB                                                      | 无大小限制                        |
| 存储时间   | 可以长期存储                                                 | 默认30min                         |
| 服务器性能 | 不占用服务器资源                                             | 占用服务器资源                    |

## 案例:登录注册升级版

可以实现账号密码的记住cookie功能,以及用jsp实现提示功能

- 登录

一般需要三个层面

- Dao层

也就是控制数据库那一层,主要使用mybatis来控制数据库中数据

```java
String resource = "mybatis-config.xml";
    InputStream inputStream;
    {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    SqlSessionFactory sqlSessionFactory =new 				SqlSessionFactoryBuilder().build(inputStream);
    //登录方法
    public User login(String username,String password)
    {
        //获取sqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.select(username, password);
        //释放资源
        sqlSession.close();
        return user;
    }
```

- Service层

调用userMapper中的select方法

```java
@Select("select *from tb_user where username= #{username} and password= #{password}")
    User select(@Param("username")String username, @Param("password")String password);

    //根据用户名查询是否存在
    @Select("select *from tb_user where username= #{username}")
    User selectByUsername(String username);

    //添加用户
    @Insert("insert into tb_user values(null,#{username},#{password})")
    void add(User user);
```

- Web层

1.接受用户名和密码

2.调用service查询User

3.判断User是否为null(就是判断数据库中是否有该用户存在)

```java
 private UserService service=new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       //获取用户填写的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        //调用Service注册
        boolean flag=service.register(user);
        //判断是否成功
        if(flag)
        {
            //跳转登录页面
            req.setAttribute("register_msg","注册成功,请登录!");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
        else
        {
            //注册失败 跳转注册失败
            req.setAttribute("register_msg","用户名已存在!");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doGet(req, resp);
    }
```

- 开始写Cookie

主要是在Web层来书写

- Web层

1.接受用户名和密码

2.调用service查询User

3.判断User是否为null

3.1判断是否勾选记住用户

3.2勾选:写cookie

jsp页面:

```html
 <div>${login_msg}${register_msg}</div>
    UserName:<input type="text" value="${cookie.username.value}" name="username"/><br/>
    PassWord:<input type="password" value="${cookie.password.value}"  name="password"/><br/>
    Remember:<input type="checkbox" value="1" name="remember">
```

Web LoginServlet页面:

```java
private UserService service=new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        String remember = req.getParameter("remember");
        //调用对应Service来查询
        User user=service.login(username,password);
        //判断
        if(user!=null)
        {
            //登录成功
            if("1".equals(remember))
            {
                //创建cookie对象
                Cookie c_username=new Cookie("username",username);
                Cookie c_password=new Cookie("password",password);
                //设置存活时间
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                resp.addCookie(c_username);
                resp.addCookie(c_password);
            }
            //将登录成功后的user对象,存储在session中
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            //重定向
            resp.sendRedirect("welcome.jsp");
        }
        else
        {
            //登录失败
            req.setAttribute("login_msg","用户名或密码错误");
            //跳转到login.jsp
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
```

- 注册

- Dao层

需要两个方法

一个是void add(user)

另一个是User select(username)//这个主要是判断用户名是否存在

```java
@Select("select *from tb_user where username= #{username}")
    User selectByUsername(String username);

    //添加用户
    @Insert("insert into tb_user values(null,#{username},#{password})")
    void add(User user);
```

- Service层

在register中 判断用户名是否存在,如果不存在就添加新用户

```java
 public boolean register(User user)
    {
        //获取sqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //判断用户名是否存在
        User user1 = mapper.selectByUsername(user.getUsername());
        if(user1==null)
        {
            //用户名不存在
            mapper.add(user);
            sqlSession.commit();
            return true;
        }
        sqlSession.close();
        return user1==null;
    }
```

- Web层

1.接受用户信息

2.调用service添加

3.注册成功,则跳转登录页面

4.注册失败,给出提示信息

jsp:

```html
<%@ page isELIgnored="false" %>
<form action="registerservlet" method="post">
  <h1>Welcome to Register!</h1>
  <div>${register_msg}</div>
  RegisterUserName:<input type="text" name="username"/><br/>
  RegisterPassWord:<input type="password" name="password"/><br/>
  <input type="submit" value="Submit">
  <input type="reset" value="reset">
  <a href="login.jsp">I have a account,Login IN!</a>
</form>
```

java:

```java
private UserService service=new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       //获取用户填写的用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        //调用Service注册
        boolean flag=service.register(user);
        //判断是否成功
        if(flag)
        {
            //跳转登录页面
            req.setAttribute("register_msg","注册成功,请登录!");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
        else
        {
            //注册失败 跳转注册失败
            req.setAttribute("register_msg","用户名已存在!");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
    }
```

# 39、Filter

- 概念:Filter表示过滤器,是JavaWeb三大组件(Servlet  Filter  Listener)之一
- 过滤器可以把对资源的请求**拦截**下来,从而实现一些特殊功能
- 过滤器一般完成一些**通用**的操作,比如:权限控制,统一编码处理,敏感字符处理等

### 快速入门

1.定义类,实现Filter接口,并重写其方法,注意这里的Filter是jakarta.servlet

2.配置Filter拦截资源的路径,在类上定义@WebFilter注解

```java
@WebFilter("/*")
//也可以直接拦截固定的项目
@WebFilter("/welcome.jsp")
```

3.放行

```java
 @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo...");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
```

### 注册拦截案例

```java
@WebFilter("/*")
public class FilterDemo implements Filter
{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        //判断访问资源路径是否和登录注册相关
        String []urls={"/login.jsp","/loginservlet","/register.jsp","registerservlet"};
        //获取访问资源路径
        String url = req.getRequestURL().toString();
        //循环判断
        for(String s:urls)
        {
            if(url.contains(s))
            {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        //1.判断session中是否有user
        HttpSession session= req.getSession();
        Object user = session.getAttribute("user");
        //放行
        if(user!=null)
            filterChain.doFilter(servletRequest, servletResponse);
        else//没有账号登录
        {
            req.setAttribute("login_msg","您尚未登录!");//给出提示信息,拦截登录
            req.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        }

    }
}
```

   其实还有一个就是在WebFilter里面直接添加/welcome.jsp拦截,这样就不会一进浏览器就报尚未登录的提示了

# 40、Listener

- 概念:Listener表示监听器,也是JavaWeb的三大组件之一
- 监听器可以监听就是在application,session,request三个对象,销毁或者往其中添加修改删除属性时**自动**执行代码的功能组件
- Listener分类:JavaWeb中提供了8个监听器

| 监听器分类         | 监听器名称                       | 作用                                         |
| ------------------ | -------------------------------- | -------------------------------------------- |
| ServletContext监听 | ServletContextListener           | 用于对ServletContext对象进行监听(创建,销毁)  |
|                    | ServletContextAttributeListrener | 对ServletContext对象中属性的监听(增删改属性) |
| Session监听        | HttpSessionListener              | 对Session对象的整体状态的监听(创建,销毁)     |
|                    | HttpSessionAttributeListener     | 对Session对象中的属性监听(增删改属性)        |
|                    | HttpSessionBindingListener       | 监听对于Session的绑定和解除                  |
|                    | HttpSessionActivationListener    | 对Session数据的钝化和活化的监听              |
| Request监听        | ServletRequestListener           | 对Request对象进行监听(创建,销毁)             |
|                    | ServletRequestAttributeListener  | 对Request对象中的属性监听(增删改属性)        |

```java
@WebListener
public class ContextLoaderListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //加载资源
        System.out.println("ContextLoaderListener被执行了!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
```

# 41、AJAX

- 概念:AJAX(Asynchronous JavaScript And XML):**异步**的JavaScript和XML

- AJAX作用:

​			1.与服务器进行数据交换:通过AJAX可以给服务器发送请求,并获取服务器响应的数据

​				可以使用AJAX和服务器进行通信,就可以使用HTML+AJAX来替换JSP页面

​			2.异步交互:可以在不重新加载整个页面的情况下,与服务器交换数据并更新部分网页的技术,如:搜索联想,用户名是否可用校验,等等......

![image-20230511222237049](C:\Users\lyh\AppData\Roaming\Typora\typora-user-images\image-20230511222237049.png)

同步进行的话需要等待服务器的响应,而异步进行不需要等待服务器的响应

```java
@WebServlet("/selectuserservlet")
public class SelectUserServlet extends HttpServlet
{
    private final UserService service=new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取用户填写的用户名和密码
        String username = req.getParameter("username");
        User user=new User();
        user.setUsername(username);
        //调用Service注册
        boolean flag=service.username_judge(user.getUsername());
        resp.getWriter().write(""+flag);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

UserService:

```java
 public boolean username_judge(String username)
    {
        //获取sqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //判断用户名是否存在
        String username1 = mapper.selectByUsername1(username);
        if(username1==null)
        {
            //用户名不存在
            return false;
        }
        sqlSession.close();
        return true;
    }
```

html:

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Register</title>
</head>
<body>
<form action="selectuserservlet" method="post">
  RegisterUserName:<input type="text" name="username" id="username"><br>
  <span id="username_err" class="err_msg" style="display: none">用户名已存在</span>
  RegisterPassWord:<input type="password" name="password"><br>
  <input type="submit" value="Submit">
  <input type="reset" value="reset">
  <a href="login.html">I have a account,Login IN!</a>
</form>
<script>
  document.getElementById("username").onblur=function ()
  {
    //发送AJAX请求
    var username =this.value;
    //创建核心对象
    var xhttp;
    if(window.XMLHttpRequest)
    {
      xhttp=new XMLHttpRequest();
    }
    else
    {
      xhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    //发送请求
    xhttp.open("GET","http://localhost:8080/TomCat_war/selectuserservlet?username="+username);
    xhttp.send();

    //获取响应
    xhttp.onreadystatechange=function ()
    {
      if(this.readyState==4&&this.status==200)
      {
        /*alert(this.responseText);*/
        //判断
        if(this.responseText=="true")
        {
          //用户名存在 显示提示信息
          document.getElementById("username_err").style.display='';
        }
        else
        {
          //不存在,清除提示信息
          document.getElementById("username_err").style.display='none';
        }
      }
    };
  }
</script>

</body>
</html>
```

