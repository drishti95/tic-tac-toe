import java.util.Scanner;

public class Player{
    String name ;
    String value;
    public Player(String name,String value)
    {
        this.name =name;
        this.value=value;
    }
    public String getName()
    {
        return name;
    }
    public String getValue()
    {
        return value;
    }
}