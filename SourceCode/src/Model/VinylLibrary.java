package Model;

import States.AvailableState;

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

  public void reserveVinyl(User u, Vinyl v) {
    Vinyl vinyl = findVinylById(v.getId());
    vinyl.handleReserve(u.getId());
    pcs.firePropertyChange("vinylReserved", null, vinyl);
  }

  public void unreserveVinyl(User u, Vinyl v){
    Vinyl vinyl = findVinylById(v.getId());
    vinyl.handleUnreserve(u.getId());
    pcs.firePropertyChange("vinylUnreserved", null, vinyl);
  }

  public void borrowVinyl(User u, Vinyl v){
    Vinyl vinyl = findVinylById(v.getId());
    vinyl.handleBorrow(u.getId());
    pcs.firePropertyChange("vinylBorrowed", null, vinyl);
  }

  public void returnVinyl(User u, Vinyl v){
    Vinyl vinyl = findVinylById(v.getId());
    vinyl.handleReturn(u.getId());
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

  public void markForRemoval(Vinyl v) {
    Vinyl vinyl = findVinylById(v.getId());
    vinyl.markForRemoval();
    pcs.firePropertyChange("vinylMarkedForRemoval", null, vinyl);
  }

  public void unmarkForRemoval(Vinyl v) {
    Vinyl vinyl = findVinylById(v.getId());
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

  public void removeVinyl(Vinyl vinyl) {
    if (vinyl == null || !vinyl.isMarkedForRemoval() || !(vinyl.getState() instanceof AvailableState)) {
      throw new IllegalArgumentException("Vinyl is not marked for removal");
    }
    this.vinyls.remove(vinyl);
  }

  /*public String getVinylsAsString(Vinyl vinyl) {
    return vinyl.toString();
  }*/



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
