package ViewModel;
import Model.Vinyl;

public class VinylDisplay {
  private final String title;
  private final String artist;
  private final String releaseYear;
  private final String status;

  public VinylDisplay(Vinyl vinyl) {
    this.title = vinyl.getTitle();
    this.artist = vinyl.getArtist();
    this.releaseYear = String.valueOf(vinyl.getReleaseYear());
    this.status = vinyl.getStateString();
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

  @Override
  public String toString() {
    return title + " - " + artist + " (" + releaseYear + ") [" + status + "]";
  }
}

