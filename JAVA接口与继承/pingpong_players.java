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
        System.out.println("ƹ�����˶�Ա�ڳԷ�");
    }

    @Override
    public void stu()
    {
        System.out.println("ƹ�����˶�Ա��ѵ��");
    }

    @Override
    public void stu_english()
    {
        System.out.println("ƹ�����˶�Ա��ѧϰӢ��");
    }
}
