package Java_Stu;

public class pingpong_coach extends coach implements english_stu
{
   public pingpong_coach() {
   }

   public pingpong_coach(String name, int age) {
      super(name, age);
   }

   @Override
   public void teach()
   {
      System.out.println("ƹ��������ڽ�ѧ");
   }

   @Override
   public void eat()
   {
      System.out.println("ƹ��������ڳԷ�");
   }

   @Override
   public void stu_english()
   {
      System.out.println("ƹ���������ѧϰӢ��");
   }
}
