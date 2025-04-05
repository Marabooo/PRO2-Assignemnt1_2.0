package ViewModel;
import Model.Vinyl;

public class VinylDisplay {
  private final String title;
  private final String artist;
  private final String releaseYear;
  private final String status;
  private final String borrowedBy;
  private final String reservedBy;

  public VinylDisplay(Vinyl vinyl) {
    this.title = vinyl.getTitle();
    this.artist = vinyl.getArtist();
    this.releaseYear = String.valueOf(vinyl.getReleaseYear());
    this.status = vinyl.getStateString();
    this.borrowedBy = vinyl.getBorrowedBy() != null ? String.valueOf(vinyl.getBorrowedBy()) : "Not borrowed";
    this.reservedBy = vinyl.getReservedBy() != null ? String.valueOf(vinyl.getReservedBy()) : "Not reserved";
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

  public String getBorrowedBy() {
    return borrowedBy;
  }

  public String getReservedBy() {
    return reservedBy;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getTitle()).append(" - ").append(getArtist())
        .append(" (").append(getReleaseYear()).append(") [").append(getStatus()).append("]");

    if (!"Not borrowed".equals(borrowedBy)) {
      sb.append(" | Borrowed by: ").append(borrowedBy);
    }

    if (!"Not reserved".equals(reservedBy)) {
      sb.append(" | Reserved by: ").append(reservedBy);
    }

    return sb.toString();
  }

}

