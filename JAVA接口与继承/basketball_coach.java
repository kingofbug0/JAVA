package Java_Stu;

public class basketball_coach extends coach
{

   public basketball_coach()
    {

    }
   public basketball_coach(String name,int age)
    {
        super(name, age);
    }


    @Override
    public void teach()
    {
        System.out.println("篮球教练在教学");
    }

    @Override
    public void eat()
    {
        System.out.println("篮球教练在吃饭");
    }
}
