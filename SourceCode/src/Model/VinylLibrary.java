package Model;

import java.util.ArrayList;
import java.util.List;

public class VinylLibrary
{
  private List<User> users;
  private List<Vinyl> vinyls;

  public VinylLibrary()
  {
    this.users = new ArrayList<>();
    this.vinyls = new ArrayList<>();
  }

  public void addUser(User user)
  {
    users.add(user);
  }

  public void addVinyl(Vinyl vinyl)
  {
    vinyls.add(vinyl);
  }

  public void removeUser(User user){
    users.remove(user);
  }

  public void removeVinyl(Vinyl vinyl){
    vinyls.remove(vinyl);
  }

}
