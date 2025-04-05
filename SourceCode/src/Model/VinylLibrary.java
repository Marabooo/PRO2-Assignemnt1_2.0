package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VinylLibrary implements Serializable
{
  @Serial private static final long serialVersionUID = 1L;
  private List<User> users;
  private List<Vinyl> vinyls;
  private transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  public VinylLibrary() {
    this.users = new ArrayList<User>();
    this.vinyls = new ArrayList<Vinyl>();
  }

  public VinylLibrary (List<User> users, List<Vinyl> vinyls) {
    this.users = users;
    this.vinyls = vinyls;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
  }

  public void reserveVinyl(int userId, int vinylId) {
    Vinyl vinyl = findVinylById(vinylId);
    vinyl.handleReserve(userId);
    pcs.firePropertyChange("vinylReserved", null, vinyl);
  }

  public void unreserveVinyl(int userId, int vinylId){
    Vinyl vinyl = findVinylById(vinylId);
    vinyl.handleUnreserve(userId);
    pcs.firePropertyChange("vinylUnreserved", null, vinyl);
  }

  public void borrowVinyl(int userId, int vinylId){
    Vinyl vinyl = findVinylById(vinylId);
    vinyl.handleBorrow(userId);
    pcs.firePropertyChange("vinylBorrowed", null, vinyl);
  }

  public void returnVinyl(int userId, int vinylId){
    Vinyl vinyl = findVinylById(vinylId);
    vinyl.handleReturn(userId);
    pcs.firePropertyChange("vinylReturned", null, vinyl);
  }

  public Vinyl findVinylById(int vinylIdToFind) {
    for (Vinyl vinyl : vinyls) {
      if (vinyl.getId() == vinylIdToFind) {
        return vinyl;
      }
    }
    return null;
  }

  public void markForRemoval(int vinylId) {
    Vinyl vinyl = findVinylById(vinylId);
    vinyl.markForRemoval();
    pcs.firePropertyChange("vinylMarkedForRemoval", null, vinyl);
  }

  public void unmarkForRemoval(int vinylId) {
    Vinyl vinyl = findVinylById(vinylId);
    vinyl.unmarkForRemoval();
    pcs.firePropertyChange("vinylUnmarkedForRemoval", null, vinyl);
  }


  public List<Vinyl> getVinyls() {
    return new ArrayList<>(vinyls); // Return a copy to avoid modification issues
  }

  public void addUser(User user)
  {
    users.add(user);
  }
  /**public int getUserId()
  {
    return 0;
  }*/

    /*public void removeUser(User user){
    users.remove(user);
  }*/

  public List<User> getUsers()
  {
    return users;
  }

  public void addVinyl(Vinyl vinyl) {
    if (vinyl == null){
        throw new IllegalArgumentException("Vinyl cannot be null");
      }
    this.vinyls.add(vinyl);
  }

  /*public String getVinylsAsString(Vinyl vinyl) {
    return vinyl.toString();
  }*/

  public void removeVinyl(Vinyl vinyl) {
    if (!vinyl.isMarkedForRemoval()) {
      throw new IllegalArgumentException("Vinyl is not marked for removal");
    }
    this.vinyls.remove(vinyl);
  }

  //public List<Vinyl> getVinylList()
  //{
  //  return vinyls;
  //}
  
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
