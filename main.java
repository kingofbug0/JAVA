package Java_Address;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args)
    {
        ArrayList<student> arr=new ArrayList<student>();//����ѧ���������ڴ洢ѧ������
        while(true)
        {
            System.out.println("------------------------");
            System.out.println("-------ѧ���������-------");
            System.out.println("-------1�����ѧ��-------");
            System.out.println("-------2��ɾ��ѧ��-------");
            System.out.println("-------3���޸�ѧ��-------");
            System.out.println("-------4���鿴ѧ��-------");
            System.out.println("-------5��������Ϣ-------");
            System.out.println("------6��ɾ��������Ϣ-----");
            System.out.println("-------7���˳�ϵͳ-------");
            System.out.println("-----------------------");
            System.out.print("���������ѡ��:");
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
                    System.out.println("��ӭ�´�ʹ��!");
                      System.exit(0);
                    break;
                default:
                    System.out.println("����������������������!8");
            }
        }

    }
   static public void add(ArrayList<student> arr)//дѧ����ӵĹ���  �������������һ������Ĳ��� student��� arr��ArrayList��ʵ����
    {
        System.out.print("������ѧ��������");
        Scanner in=new Scanner(System.in);
        String name=in.nextLine();

        System.out.print("������ѧ��ѧ��:");
        Scanner in_2=new Scanner(System.in);
        int num=in_2.nextInt();

        System.out.print("������ѧ������:");
        Scanner in_3=new Scanner(System.in);
        int age=in_3.nextInt();

        System.out.print("������ѧ����ͥסַ:");
        Scanner in_4=new Scanner(System.in);
        String address=in_4.nextLine();

        student s=new student();//ʵ�������� ��
        s.setAddress(address);
        s.setAge(age);
        s.setName(name);
        s.setNum(num);

        arr.add(s);//��ǰ����д��ѧ����ӵ�arr������ȥ ��arr����ȥ������

        System.out.println("�ɹ���ӣ�");
    }

    static public void display(ArrayList<student> arr)
    {
        if(arr.isEmpty())
        {
            System.out.println("��ǰû��ѧ����Ϣ����¼����ٲ�ѯ��");
        }
        else
        {
            System.out.println("����\tѧ��\t����\t\t��ͥ��ַ ");
            for (int i = 0; i < arr.size(); i++)
            {
                student s = arr.get(i);//����ÿһ�������еĶ���
                System.out.println(s.getName() +"\t"+ s.getNum()+"\t" + s.getAge()+"\t" + s.getAddress()+"\t");
            }
        }
    }
    static public void delete(ArrayList<student> arr)
    {

        if(arr.isEmpty())
        {
            System.out.println("��ǰû��ѧ����Ϣ��");
        }
        else
        {
            display(arr);//����һ��ѧ������ �������
            System.out.println("��������Ҫ����ѧ����ѧ��:");
            Scanner in=new Scanner(System.in);
            int true_num=in.nextInt();
            for(int i=0;i<arr.size();i++)
            {
                student s=arr.get(i);//�õ�ÿһ��ѧ������Ϣ
                if(s.getNum()==true_num)//�ж�ѧ��ѧ���Ƿ��������ѧ����ͬ
                {
                    arr.remove(i);//�����ͬ ���Ƴ���
                    System.out.println("ɾ���ɹ�!");
                    break;
                }
                else
                {
                    System.out.println("δ��ѯ����ѧ�� ����������룡");
                    break;
                }
            }
        }
    }

    static public void modify(ArrayList<student> arr)
    {
        if(arr.isEmpty())
        {
            System.out.println("��ǰû��ѧ����Ϣ��");
        }
        else
        {
            display(arr);//����һ��ѧ������ �������
            System.out.println("��������Ҫ�޸�ѧ����ѧ��:");
            Scanner in=new Scanner(System.in);
            int true_num=in.nextInt();
            for(int i=0;i<arr.size();i++)
            {
                student s=arr.get(i);//�õ�ÿһ��ѧ������Ϣ
                if(s.getNum()==true_num)//�ж�ѧ��ѧ���Ƿ��������ѧ����ͬ
                {
                    System.out.println("������ѧ����������");
                    Scanner in_1=new Scanner(System.in);
                    String name=in_1.nextLine();

                    System.out.println("������ѧ����ѧ��:");
                    Scanner in_2=new Scanner(System.in);
                    int num=in_2.nextInt();

                    System.out.println("������ѧ��������:");
                    Scanner in_3=new Scanner(System.in);
                    int age=in_3.nextInt();

                    System.out.println("������ѧ���¼�ͥסַ:");
                    Scanner in_4=new Scanner(System.in);
                    String address=in_4.nextLine();

                    student s_new=new student();//���ǵ�ʵ����һ���¶���
                    s_new.setName(name);
                    s_new.setAge(age);
                    s_new.setAddress(address);
                    s_new.setNum(num);

                    arr.set(i,s_new);//iΪindex s_newΪ����ӵ�ѧ������ ��setȥ�ı���Ҫ�ı�Ķ���

                    System.out.println("�޸ĳɹ���");
                }
                else
                {
                    System.out.println("δ��ѯ����ѧ�� ����������룡");
                    break;
                }
            }

        }
    }
}
