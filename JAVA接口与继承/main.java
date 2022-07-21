package Java_Stu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class main
{
    public static void main(String[] args)
    {
         basketball_coach bc=new basketball_coach();
         bc.setAge(52);
         bc.setName("ÄÂÄáÄá°Â");
         System.out.println(bc.getName()+","+bc.getAge());
         bc.eat();
         bc.teach();

         basketball_players bp=new basketball_players();
         bp.setAge(18);
         bp.setName("°¢³¬");
         System.out.println(bp.getName()+","+bp.getAge());
         bp.eat();
         bp.stu();

         pingpong_coach pc=new pingpong_coach();
         pc.setAge(59);
         pc.setName("¹ÏµÏ°ÂÀ­");
         System.out.println(pc.getName()+","+pc.getAge());
         pc.eat();
         pc.teach();
         pc.stu_english();

         pingpong_players pp=new pingpong_players();
         pp.setName("Ã·Î÷");
         pp.setAge(27);
        System.out.println(pp.getName()+","+pp.getAge());
         pp.eat();
         pp.stu();
         pp.stu_english();
    }
}