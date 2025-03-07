package Model;

public class User
{
  private String name;
  private int id = 0;

  public User (String name)
  {
    this.name = null;
    id++;
  }

  public String getName (){
    return name;
  }

  public int getId()
  {
    return id;
  }

  public void setName(String name){
    this.name = name;
  }

    // to string
    // equals

}
