package ViewModel;
import Model.Vinyl;

public class VinylDisplay {
  private final String title;
  private final String artist;
  private final String releaseYear;
  private final String status;
  private final String reservedBy;
  private final String borrowedBy;


  public VinylDisplay(Vinyl vinyl) {
    this.title = vinyl.getTitle();
    this.artist = vinyl.getArtist();
    this.releaseYear = String.valueOf(vinyl.getReleaseYear());
    this.status = vinyl.getStateString();
    this.reservedBy = vinyl.getReservedBy();
    this.borrowedBy = vinyl.getBorrowedBy();
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public String getReleaseYear() {
    return releaseYear;
  }

  public String getStatus() {
    return status;
  }

  public String getReservedBy(){ return reservedBy; }

  public String getBorrowedBy(){ return borrowedBy; }



  @Override
  public String toString() {
    return title + " - " + artist + " (" + releaseYear + ") [" + status + "]";
  }
}

