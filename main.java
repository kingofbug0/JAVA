package Java_Address;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args)
    {
        ArrayList<student> arr=new ArrayList<student>();//创建学生集合用于存储学生数据
        while(true)
        {
            System.out.println("------------------------");
            System.out.println("-------学生管理面板-------");
            System.out.println("-------1、添加学生-------");
            System.out.println("-------2、删除学生-------");
            System.out.println("-------3、修改学生-------");
            System.out.println("-------4、查看学生-------");
            System.out.println("-------5、保存信息-------");
            System.out.println("------6、删除所有信息-----");
            System.out.println("-------7、退出系统-------");
            System.out.println("-----------------------");
            System.out.print("请输入你的选择:");
            int chose;
            Scanner in=new Scanner(System.in);
            chose=in.nextInt();
            switch (chose)
            {
                case 1:
                     add(arr);
                     break;
                case 2:
                    delete(arr);
                    break;
                case 3:
                    modify(arr);
                    break;
                case 4:
                    display(arr);
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    System.out.println("欢迎下次使用!");
                      System.exit(0);
                    break;
                default:
                    System.out.println("您的输入有误，请重新输入!8");
            }
        }

    }
   static public void add(ArrayList<student> arr)//写学生添加的功能  里面这个传的是一个数组的参数 student类的 arr是ArrayList的实例化
    {
        System.out.print("请输入学生姓名：");
        Scanner in=new Scanner(System.in);
        String name=in.nextLine();

        System.out.print("请输入学生学号:");
        Scanner in_2=new Scanner(System.in);
        int num=in_2.nextInt();

        System.out.print("请输入学生年龄:");
        Scanner in_3=new Scanner(System.in);
        int age=in_3.nextInt();

        System.out.print("请输入学生家庭住址:");
        Scanner in_4=new Scanner(System.in);
        String address=in_4.nextLine();

        student s=new student();//实例化对象 用
        s.setAddress(address);
        s.setAge(age);
        s.setName(name);
        s.setNum(num);

        arr.add(s);//将前面书写的学生添加到arr集合中去 用arr集合去接收它

        System.out.println("成功添加！");
    }

    static public void display(ArrayList<student> arr)
    {
        if(arr.isEmpty())
        {
            System.out.println("当前没有学生信息，请录入后再查询！");
        }
        else
        {
            System.out.println("姓名\t学号\t年龄\t\t家庭地址 ");
            for (int i = 0; i < arr.size(); i++)
            {
                student s = arr.get(i);//遍历每一个数组中的对象
                System.out.println(s.getName() +"\t"+ s.getNum()+"\t" + s.getAge()+"\t" + s.getAddress()+"\t");
            }
        }
    }
    static public void delete(ArrayList<student> arr)
    {

        if(arr.isEmpty())
        {
            System.out.println("当前没有学生信息！");
        }
        else
        {
            display(arr);//调用一下学生界面 方便查找
            System.out.println("请输入你要查找学生的学号:");
            Scanner in=new Scanner(System.in);
            int true_num=in.nextInt();
            for(int i=0;i<arr.size();i++)
            {
                student s=arr.get(i);//得到每一个学生的信息
                if(s.getNum()==true_num)//判断学生学号是否与输入的学号相同
                {
                    arr.remove(i);//如果相同 就移除它
                    System.out.println("删除成功!");
                    break;
                }
                else
                {
                    System.out.println("未查询到该学生 请查正后输入！");
                    break;
                }
            }
        }
    }

    static public void modify(ArrayList<student> arr)
    {
        if(arr.isEmpty())
        {
            System.out.println("当前没有学生信息！");
        }
        else
        {
            display(arr);//调用一下学生界面 方便查找
            System.out.println("请输入你要修改学生的学号:");
            Scanner in=new Scanner(System.in);
            int true_num=in.nextInt();
            for(int i=0;i<arr.size();i++)
            {
                student s=arr.get(i);//得到每一个学生的信息
                if(s.getNum()==true_num)//判断学生学号是否与输入的学号相同
                {
                    System.out.println("请输入学生新姓名：");
                    Scanner in_1=new Scanner(System.in);
                    String name=in_1.nextLine();

                    System.out.println("请输入学生新学号:");
                    Scanner in_2=new Scanner(System.in);
                    int num=in_2.nextInt();

                    System.out.println("请输入学生新年龄:");
                    Scanner in_3=new Scanner(System.in);
                    int age=in_3.nextInt();

                    System.out.println("请输入学生新家庭住址:");
                    Scanner in_4=new Scanner(System.in);
                    String address=in_4.nextLine();

                    student s_new=new student();//还是得实例化一个新对象
                    s_new.setName(name);
                    s_new.setAge(age);
                    s_new.setAddress(address);
                    s_new.setNum(num);

                    arr.set(i,s_new);//i为index s_new为新添加的学生对象 用set去改变想要改变的对象

                    System.out.println("修改成功！");
                }
                else
                {
                    System.out.println("未查询到该学生 请查正后输入！");
                    break;
                }
            }

        }
    }
}
