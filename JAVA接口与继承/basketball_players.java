package Java_Stu;

public class basketball_players extends athletes
{

    public basketball_players()
    {
    }

    public basketball_players(String name, int age)
    {
        super(name, age);
    }

    @Override
    public void stu()
    {
        System.out.println("篮球运动员在训练");
    }

    @Override
    public void eat()
    {
        System.out.println("篮球运动员在吃饭");
    }
}

