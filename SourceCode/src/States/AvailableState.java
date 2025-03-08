package States;
import Model.*;

public class AvailableState implements VinylState {
  /*public AvailableState() {
    System.out.println(" ");
    System.out.println(" Vinyl is Available ");
  }*/

  @Override
  public void borrow(Vinyl vinyl, int userId) {
    // Allow borrow if:
    // - Not flagged for removal, OR
    // - Flagged but user is the reserver
    if (!vinyl.isMarkedForRemoval() || userId == vinyl.getReservedBy()){
      vinyl.setBorrowedBy(userId);
      vinyl.setState(new BorrowedState());
      //vinyl.changeToBorrowedState();
    }

  }

  public void returnVinyl(Vinyl vinyl, int userId) {
    // Do nothing (Available vinyls can't be returned)
  }

  @Override
  public void reserve(Vinyl vinyl, int userId) {
    if (!vinyl.isMarkedForRemoval()){
      vinyl.setReservedBy(userId);
      vinyl.setState(new AvailableAndReservedState());
      //vinyl.changeToAvailableAndReservedState();
    }
  }

  public void unreserve(Vinyl vinyl) {
    // Do nothing
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
