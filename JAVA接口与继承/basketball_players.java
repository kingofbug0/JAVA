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
        System.out.println("�����˶�Ա��ѵ��");
    }

    @Override
    public void eat()
    {
        System.out.println("�����˶�Ա�ڳԷ�");
    }
}

