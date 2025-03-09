package States;
import Model.*;

public class AvailableState implements VinylState {
  /*public AvailableState() {
    System.out.println(" ");
    System.out.println(" Vinyl is Available ");
  }*/

  @Override
  public void borrow(Vinyl vinyl, int userId)
  {
    // Allow borrow if:
    // - Not flagged for removal, OR
    // - Flagged but user is the reserver
    if (vinyl.isMarkedForRemoval())
    {
      throw new IllegalArgumentException("Vinyl is not available for borrowing");
    }
      vinyl.setBorrowedBy(userId);
      vinyl.setState(new BorrowedState());
    }



  public void returnVinyl(Vinyl vinyl, int userId) {
    // Do nothing (Available vinyls can't be returned)
    throw new IllegalArgumentException("Vinyl is not borrowed.");
  }

  @Override
  public void reserve(Vinyl vinyl, int userId) {
    if (!vinyl.isMarkedForRemoval()){
      vinyl.setReservedBy(userId);
      vinyl.setState(new AvailableAndReservedState());
    }
  }

  public void unreserve(Vinyl vinyl, int userId) {
    // Do nothing
    System.out.println("Vinyl is not reserved");
  }

  /*
  @Override
  public String getStatus() //i need to fix this in Vinyl
  {
    return "Available";
  }


   */
  @Override
  public String toString() {
    return "Available";
  }

}
