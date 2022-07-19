package Java_Address;

public class student
{
    private String name;
    private int age;
    private int num;
    private String address;

    public student()
    {
    }

    public student(String name, int age, int num, String address)
    {
        this.name = name;
        this.age = age;
        this.num = num;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
