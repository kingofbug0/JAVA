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
      System.out.println("乒乓球教练在教学");
   }

   @Override
   public void eat()
   {
      System.out.println("乒乓球教练在吃饭");
   }

   @Override
   public void stu_english()
   {
      System.out.println("乒乓球教练在学习英语");
   }
}
