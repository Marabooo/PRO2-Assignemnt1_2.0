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
  public VinylLibrary (List<User> users, List<Vinyl> vinyls)
  {
    this.users = users;
    this.vinyls = vinyls;
  }

  public List<Vinyl> getVinyls() {
    return new ArrayList<>(vinyls); // Return a copy to avoid modification issues
  }

  public void addUser(User user)
  {
    users.add(user);
  }
  public int getUserId()
  {
    return 0;
  }

  public void addVinyl(Vinyl vinyl)
  {
    if (vinyl == null)
      {
        throw new IllegalArgumentException("Vinyl cannot be null");
      }
    this.vinyls.add(vinyl);
  }
  public List<User> getUsers()
  {
    return users;
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
    return vinyls;
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
  //
  //testing relevant methods
  //
  public void clear()
  {
    users.clear();
    vinyls.clear();
  }
}
