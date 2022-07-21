package Java_Stu;

public class pingpong_players extends athletes implements english_stu
{
    public pingpong_players() {
    }

    public pingpong_players(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat()
    {
        System.out.println("乒乓球运动员在吃饭");
    }

    @Override
    public void stu()
    {
        System.out.println("乒乓球运动员在训练");
    }

    @Override
    public void stu_english()
    {
        System.out.println("乒乓球运动员在学习英文");
    }
}
