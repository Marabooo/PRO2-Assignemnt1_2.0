package Model;
import States.*;

import java.util.Objects;

public class Vinyl
{
  private String title;
  private String artist;
  private int releaseYear;
  private static int nextId = 1;
  private int id;
  private boolean isReserved;
  private boolean isBorrowed;
<<<<<<< Updated upstream
  private VinylState currentState;
=======
  private boolean isMarkedForRemoval;
  private VinylState state;
>>>>>>> Stashed changes


  public Vinyl(String title, String artist, int releaseYear)
  {
    this.title = title;
    this.artist = artist;
    this.releaseYear = releaseYear;
    this.currentState = new AvailableState();
    this.isReserved = false;
    this.isBorrowed = false;
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

  public boolean isReserved(){
    return isReserved;
  }
  public void setReserved(boolean reserved)
  {
    isReserved = reserved;
  }

  public boolean isBorrowed()
  {
    return isBorrowed;
  }
  public void setBorrowed(boolean borrowed)
  {
    isBorrowed = borrowed;
  }

<<<<<<< Updated upstream
  public VinylState getState(){
    return currentState;
=======
  public boolean isMarkedForRemoval()
  {
    return isMarkedForRemoval;
  }
  public void setMarkedForRemoval(boolean markedForRemoval)
  {
    isMarkedForRemoval = markedForRemoval;
  }

  public VinylState getState()
  {
    return state;
  }

  public void setState(VinylState state)
  {
    this.state = state;
>>>>>>> Stashed changes
  }
  

  //
  // State methods
  //

  public void changeToAvailableState(){
    currentState = new AvailableState();
  }

  public void changeToBorrowedState(){
    currentState = new BorrowedState(this);
  }

  public void changeToBorrowedAndReservedState(){
    currentState = new BorrowedAndReservedState(this);
  }

  public void changeToAvailableAndReservedState(){
    currentState = new AvailableAndReservedState(this);
  }

 //
 // Button methods
 //


//
// toString, equals and hashCode
//

  @Override public String toString()
  {
    return "Vinyl{" + "title='" + title + '\'' + ", artist='" + artist + '\''
        + ", releaseYear=" + releaseYear + ", id=" + id + ", isReserved="
        + isReserved + ", isBorrowed=" + isBorrowed + ", state=" + currentState + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (o == null || getClass() != o.getClass())
      return false;
    Vinyl vinyl = (Vinyl) o;
    return releaseYear == vinyl.releaseYear && id == vinyl.id
        && isReserved == vinyl.isReserved && isBorrowed == vinyl.isBorrowed
        && Objects.equals(title, vinyl.title) && Objects.equals(artist,
        vinyl.artist) && Objects.equals(currentState, vinyl.currentState);
  }

  @Override public int hashCode()
  {
    return Objects.hash(title, artist, releaseYear, id, isReserved, isBorrowed,
        currentState);
  }
  

}
