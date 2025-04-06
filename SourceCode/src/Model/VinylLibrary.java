package Model;

import States.AvailableState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class VinylLibrary implements Serializable
{
  @Serial private static final long serialVersionUID = 1L;
  private List<User> users;
  private List<Vinyl> vinyls;
  private transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  private final transient Lock lock = new ReentrantLock();

  public VinylLibrary() {
    this.users = new ArrayList<User>();
    this.vinyls = new ArrayList<Vinyl>();
  }

  public VinylLibrary (List<User> users, List<Vinyl> vinyls) {
    this.users = users;
    this.vinyls = vinyls;
  }




  public void reserveVinyl(User u, Vinyl v) {
    lock.lock();
    try {
      Vinyl vinyl = findVinylById(v.getId());
      vinyl.handleReserve(u.getName());
      pcs.firePropertyChange("vinylReserved", null, vinyl);
    } finally {
      lock.unlock();
    }
  }


  public void unreserveVinyl(User u, Vinyl v){
    lock.lock();
    try {
      Vinyl vinyl = findVinylById(v.getId());
      vinyl.handleUnreserve(u.getName());
      pcs.firePropertyChange("vinylUnreserved", null, vinyl);
    } finally {
      lock.unlock();
    }
  }

  public void borrowVinyl(User u, Vinyl v){
    lock.lock();
    try {
      Vinyl vinyl = findVinylById(v.getId());
      vinyl.handleBorrow(u.getName());
      pcs.firePropertyChange("vinylBorrowed", null, vinyl);
    } finally {
      lock.unlock();
    }
  }

  public void returnVinyl(User u, Vinyl v){
    lock.lock();
    try {
      Vinyl vinyl = findVinylById(v.getId());
      vinyl.handleReturn(u.getName());
      pcs.firePropertyChange("vinylReturned", null, vinyl);
    } finally {
      lock.unlock();
    }
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
    lock.lock();
    try {
      Vinyl vinyl = findVinylById(v.getId());
      vinyl.markForRemoval();
      pcs.firePropertyChange("vinylMarkedForRemoval", null, vinyl);
    } finally {
      lock.unlock();
    }
  }

  public void unmarkForRemoval(Vinyl v) {
    lock.lock();
    try {
      Vinyl vinyl = findVinylById(v.getId());
      vinyl.unmarkForRemoval();
      pcs.firePropertyChange("vinylUnmarkedForRemoval", null, vinyl);
    } finally {
      lock.unlock();
    }
  }

  public void removeVinyl(Vinyl vinyl) {
    lock.lock();
    try {
      if (vinyl == null || !vinyl.isMarkedForRemoval() || !(vinyl.getState() instanceof AvailableState)) {
        throw new IllegalArgumentException("Vinyl is not marked for removal");
      }
      this.vinyls.remove(vinyl);
    } finally {
      lock.unlock();
    }
  }


  public List<Vinyl> getVinyls() {
    return new ArrayList<>(vinyls); // Return a copy to avoid modification issues
  }

  public List<User> getUsers() {
    return users;
  }

  public void addUser(User user) {
    lock.lock();
    try {
      users.add(user);
    } finally {
      lock.unlock();
    }
  }


  public void addVinyl(Vinyl vinyl) {
    lock.lock();
    try {
      if (vinyl == null) {
        throw new IllegalArgumentException("Vinyl cannot be null");
      }
      this.vinyls.add(vinyl);
    } finally {
      lock.unlock();
    }
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

  public void clear() {
    lock.lock();
    try {
      users.clear();
      vinyls.clear();
    } finally {
      lock.unlock();
    }
  }

}
