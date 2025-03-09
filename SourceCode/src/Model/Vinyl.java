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
  private String state; //optional, descriptive text for UI purposes

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
    this.state = "Available";
  }

  public Vinyl() {
    // Required for XML decoding;
    // empty because beans require a no-arg constructor
  }

    //
   // - State Delegation Methods -
  //

  public void borrow(Integer userId){
    currentState.borrow(this, userId);
  }


  public void returnVinyl(Integer userId){
    if(borrowedBy == null || !borrowedBy.equals(userId)){
      throw new IllegalStateException("Cannot return vinyl. It is not borrowed by this user.");
    }
    currentState.returnVinyl(this, borrowedBy);
  }

  public void reserve(Integer userId){
    currentState.reserve(this, userId);
  }
  public void unreserve(Integer userId){
    currentState.unreserve(this, userId);
  }

    //
   // - Flagged for Removal Methods
  //

  public void markForRemoval(){
    if(!isMarkedForRemoval){
      isMarkedForRemoval = true;
      firePropertyChange("markedForRemoval", false, true);
    }
  }
  public void unmarkForRemoval(){
    if (isMarkedForRemoval){
      isMarkedForRemoval = false;
      firePropertyChange("markedForRemoval", true, false);
    }
  }

    //
   // - Setters and getters -
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
  public Integer getBorrowedBy() { return borrowedBy; };
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }// needed for XML serialization
  public static void setNextId(int next)
  {
    nextId = next;
  }//XML again

  public VinylState getState() {
    return currentState;
  }
  public boolean isMarkedForRemoval()
  {
    return isMarkedForRemoval;
  }

    //
   // - Setters (Used by States) -
  //

  public void setReservedBy(Integer userId){
    Integer old = this.reservedBy;
    this.reservedBy = userId;
    pcs.firePropertyChange("reservedBy", old, userId);
  }

  public void setBorrowedBy(Integer userId) {
    Integer old = this.borrowedBy;
    this.borrowedBy = userId;
    pcs.firePropertyChange("borrowedBy", old, userId);
  }

  public void setState(VinylState newState){
   VinylState  oldState = this.currentState;
    this.currentState = newState;
    pcs.firePropertyChange("state", oldState, newState);
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
    return "Viny: " + "title: '" + this.getTitle() + ", artist: " + this.getArtist() + '.'
        + ", releaseYear=" + this.getReleaseYear() + ", id=" + this.getId() + ", state=" + currentState + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (o == null || getClass() != o.getClass())
      return false;
    Vinyl vinyl = (Vinyl) o;
    return releaseYear == vinyl.releaseYear && id == vinyl.id
        && isMarkedForRemoval == vinyl.isMarkedForRemoval && Objects.equals(
        title, vinyl.title) && Objects.equals(artist, vinyl.artist)
        && Objects.equals(currentState, vinyl.currentState) && Objects.equals(
        reservedBy, vinyl.reservedBy) && Objects.equals(borrowedBy,
        vinyl.borrowedBy) && Objects.equals(pcs, vinyl.pcs);
  }

  @Override public int hashCode()
  {
    return Objects.hash(title, artist, releaseYear, currentState, id,
        reservedBy, borrowedBy, isMarkedForRemoval, pcs);
  }

  //
   // Testing/Utility Methods
  //

  public static void resetCounter() {
    nextId = 1;
  }
  // Mara and Ana

}
