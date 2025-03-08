package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable
{
  @Serial private static final long serialVersionUID = 1L;
  private String name;
  private static int nextId = 1;
  private int id;

  public static final User adminUser = new User("admin",0);//creating an admin user for testing purposes

  public User(String name)
  {
    this.name = name;
    this.id = nextId++;
  }
  public User (String name, int id)
  {
    this.name = name;
    this.id = id;
  }

  public User()
  {
    // Required for XML decoding;
    // gave it a default name, but it won't matter
    this("Lola Bunny"); //writing like this calls a user that gets an automatic id
  }


  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }

  public void setName(String name)
  {
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

  //
  //testing relevant methods
  //
  public static void resetCounter()
  {
    nextId = 1;
  }
}
