package Model;
import States.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Vinyl implements Serializable
{
  @Serial private static final long serialVersionUID = 1L;
  private String title;
  private String artist;
  private int releaseYear;
  private VinylState currentState;

  private static int nextId = 1;
  private int id;

  private Integer reservedBy;
  private Integer borrowedBy;
  private boolean isMarkedForRemoval;

  private transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  // Made transient so it won't be serialized

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

  public Vinyl() {
    // Required for XML decoding;
    // gave it default values, but they won't matter
    this("Wouldn't you like to hear this?", "Greatest Artist Who Ever Lived", 2424);
  }

  //
  // Setters and getters
  //

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getArtist() {
    return artist;
  }
  public void setArtist(String artist) {
    this.artist = artist;
  }
  public int getReleaseYear() {
    return releaseYear;
  }
  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Integer getReservedBy (){
    return reservedBy;
  }

  //public Integer getBorrowedBy { return borrowedBy };
  public int getId() {
    return id;
  }

  //
  // Setters (Used by States)
  //

  public void setReservedBy(Integer ID){
    Integer old = this.borrowedBy;
    this.borrowedBy = ID;
    pcs.firePropertyChange("borrowerId", old, ID);
  }



  /*void setReservedBy(Integer userId) {
    if (reservedBy != null || isMarkedForRemoval)
    {
      throw new IllegalArgumentException("Vinyl cannot be reserved.");
    }
    reservedBy = userId;
  }
  Integer getReservedBy ()
  {
    return reservedBy;
  }
  */


  public void setBorrowedBy(Integer userId) {
    if (borrowedBy != null || (reservedBy != null && reservedBy.equals(userId))|| (isMarkedForRemoval && reservedBy == null))
    {
      throw new IllegalArgumentException("Vinyl cannot be borrowed.");
    }
    borrowedBy = userId;
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



    //
   // State Delegation Methods
  //

  // ----- Mara: These 4 methods can be all merged into one Ana already made above this:

  public void setState(VinylState newState){
    VinylState oldState = currentState;
    currentState = newState;
    firePropertyChange("state", oldState, newState);
  }

  // The 4 methods merged:
  /*
  public void changeToAvailableState()
  {
    VinylState oldState = currentState;
    currentState = new AvailableState();
    firePropertyChange("state", oldState, currentState);
  }

  public void changeToBorrowedState()
  {
    VinylState oldState = currentState;
    currentState = new BorrowedState();
    firePropertyChange("state", oldState, currentState);
  }

  public void changeToBorrowedAndReservedState()
  {
    VinylState oldState = currentState;
    currentState = new BorrowedAndReservedState();
    firePropertyChange("state", oldState, currentState);
  }

  public void changeToAvailableAndReservedState()
  {
    VinylState oldState = currentState;
    currentState = new AvailableAndReservedState();
    firePropertyChange("state", oldState, currentState);
  }

   */


    //  - Observer Pattern Methods -
   // allows us to implement the Observer Pattern and separate the application logic from the graphical interface.
  //

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    pcs.removePropertyChangeListener(listener);
  }

  // Notify listeners about the change
  public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    pcs.firePropertyChange(propertyName, oldValue, newValue);
  }


    //
   // toString, equals and hashCode
  //

  @Override public String toString()
  {
    return "Vinyl{" + "title='" + title + '\'' + ", artist='" + artist + '\''
        + ", releaseYear=" + releaseYear + ", id=" + id + ", state=" + currentState + '}';
  }

  // generate equals and hashCode



  //
  // Testing relevant methods
  //
  public static void resetCounter() {
    nextId = 1;
  }
  // Mara and Ana

}
