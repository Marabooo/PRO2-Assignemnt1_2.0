package Model;
import States.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class Vinyl
{
  private String title;
  private String artist;
  private int releaseYear;
  private static int nextId = 1;
  private int id;
  private Integer reservedBy;
  private Integer borrowedBy;
  private VinylState currentState;
  private boolean isMarkedForRemoval;
  //private VinylState state; //can lead to confusions
  private PropertyChangeSupport pcs = new PropertyChangeSupport(this);



  public Vinyl(String title, String artist, int releaseYear)
  {
    this.title = title;
    this.artist = artist;
    this.releaseYear = releaseYear;
    this.currentState = new AvailableState();
    this.reservedBy = null;
    this.borrowedBy = null;
    this.isMarkedForRemoval = false;
    this.id = nextId++;
  }

  //
  //setters and getters
  //

  public String getTitle()
  {
    return title;
  }
  public void setTitle(String title)
  {
    this.title = title;
  }
  public String getArtist()
  {
    return artist;
  }
  public void setArtist(String artist)
  {
    this.artist = artist;
  }
  public int getReleaseYear()
  {
    return releaseYear;
  }
  public void setReleaseYear(int releaseYear)
  {
    this.releaseYear = releaseYear;
  }
  public int getId()
  {
    return id;
  }



  public void setReservedBy( User user)
  {
    if (reservedBy != null || isMarkedForRemoval)
    {
      throw new IllegalArgumentException("Vinyl cannot be reserved.");
    }
    reservedBy = user.getId();
  }


  


  public void setBorrowed(User user)
  {
    if (borrowedBy != null || (reservedBy != null && reservedBy != user.getId())|| (isMarkedForRemoval && reservedBy == null))
    {
      throw new IllegalArgumentException("Vinyl cannot be borrowed.");
    }
    borrowedBy = user.getId();
  }

  public VinylState getState()
  {
    return currentState;
  }
  public boolean isMarkedForRemoval()
  {
    return isMarkedForRemoval;
  }
  public void setMarkedForRemoval(boolean markedForRemoval)
  {
    isMarkedForRemoval = markedForRemoval;
  }

  public void setState(VinylState state)
  {
    VinylState oldState = currentState;
    this.currentState = state;
    firePropertyChange("state", oldState, currentState);
  }

  //
  // State methods
  //

  public void changeToAvailableState()
  {
    VinylState oldState = currentState;
    currentState = new AvailableState();
    firePropertyChange("state", oldState, currentState);
  }

  public void changeToBorrowedState()
  {
    VinylState oldState = currentState;
    currentState = new BorrowedState(this);
    firePropertyChange("state", oldState, currentState);
  }

  public void changeToBorrowedAndReservedState()
  {
    VinylState oldState = currentState;
    currentState = new BorrowedAndReservedState(this);
    firePropertyChange("state", oldState, currentState);
  }

  public void changeToAvailableAndReservedState()
  {
    VinylState oldState = currentState;
    currentState = new AvailableAndReservedState(this);
    firePropertyChange("state", oldState, currentState);
  }

 //
 // Button methods
 //
  public void onBorrowButtonPress(){
    currentState.onBorrowButtonPress(this);
  }
  public void onReturnButtonPress(){
    currentState.onReturnButtonPress(this);
  }
  public void onReserveButtonPress(){
    currentState.onReserveButtonPress(this);
  }
  public void onUnreserveButtonPress(){
    currentState.onUnreserveButtonPress(this);
  }
  /*public void onMarkForRemovalButtonPress(){
    currentState.onMarkForRemovalButtonPress(this);
  }
  public void onUnmarkForRemovalButtonPress(){
    currentState.onUnmarkForRemovalButtonPress(this);
  }
*/

//
//Listener methods
//

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    pcs.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    pcs.removePropertyChangeListener(listener);
  }

  // Notify listeners about the change
  public void firePropertyChange(String propertyName, Object oldValue,
      Object newValue)
  {
    pcs.firePropertyChange(propertyName, oldValue, newValue);
  } //allows us to implement the Observer Pattern and separate the application logic from the graphical interface.


  //
// toString, equals and hashCode
//

  @Override public String toString()
  {
    return "Vinyl{" + "title='" + title + '\'' + ", artist='" + artist + '\''
        + ", releaseYear=" + releaseYear + ", id=" + id + ", state=" + currentState + '}';
  }

  // generate equals and hashCode
  

  // Mara and Ana

}
