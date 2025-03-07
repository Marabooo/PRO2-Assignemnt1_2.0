package Model;

import java.util.Objects;

public class User
{
  private String name;
  private static int nextId = 1;
  private int id;

  public User (String name)
  {
    this.name = name;
    this.id = nextId++;
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

  @Override public String toString()
  {
    return "User{" + "name: '" + name + '\'' + " id: " + id + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return id == user.id && Objects.equals(name, user.name);
  }

  @Override public int hashCode()
  {
    return Objects.hash(name, id);
  }
  // to string
    // equals

}
