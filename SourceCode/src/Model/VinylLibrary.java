package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VinylLibrary implements Serializable
{
  @Serial private static final long serialVersionUID = 1L;
  private List<User> users;
  private List<Vinyl> vinyls;

  public VinylLibrary()
  {
    this.users = new ArrayList<User>();
    this.vinyls = new ArrayList<Vinyl>();
  }

  public void addUser(User user)
  {
    users.add(user);
  }

  public void addVinyl(Vinyl vinyl)
  {
    if (vinyl == null)
      {
        throw new IllegalArgumentException("Vinyl cannot be null");
      }
    this.vinyls.add(vinyl);
  }

  public void removeUser(User user){
    users.remove(user);
  }

  public void removeVinyl(Vinyl vinyl)
  {
    if (!vinyl.isMarkedForRemoval())
    {
      throw new IllegalArgumentException("Vinyl is not marked for removal");
    }
    this.vinyls.remove(vinyl);
  }

  public List<Vinyl> getVinylList()
  {
    return this.vinyls;
  }
  
  @Override
  public String toString()
  {
    StringBuilder s = new StringBuilder();
    for (Vinyl vinyl : vinyls)
    {
      s.append(vinyl.toString());
      s.append("\n");
    }
    return s.toString();
  }

}
