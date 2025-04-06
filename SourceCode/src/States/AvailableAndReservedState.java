package States;
import Model.*;

public class AvailableAndReservedState implements VinylState {

  @Override
  public void borrow (Vinyl vinyl, String userName){
    // Only the reserver can borrow
    // Marked for Removal should not impact the reserver borrow their vinyl
    if (!vinyl.getReservedBy().equals(userName))
    {
      throw new IllegalStateException("Only the reserver can borrow");
    }
      vinyl.setBorrowedBy(userName);
      vinyl.setReservedBy(null);
      //vinyl.unreserve(userId);
      vinyl.setState(new BorrowedState());

  }

  @Override
  public void returnVinyl (Vinyl vinyl, String userName){
    // Do nothing (Available vinyls can't be returned)
    System.out.println("Vinyl is not borrowed, so it cannot be returned.");
  }

  @Override
  public void reserve (Vinyl vinyl, String userName){
    // Do nothing (already reserved)
  }

  @Override
  public void unreserve (Vinyl vinyl, String userName){
    // Can only be returned by the reserver
    if(vinyl.getReservedBy() == null) {
      throw new IllegalStateException("Vinyl is not reserved");
    }
    if (!vinyl.getReservedBy().equals(userName)) {
      throw new IllegalStateException("Only the reserver can unreserve");
    }
    vinyl.setReservedBy(null);
    vinyl.setState(new AvailableState());
  }


  @Override
  public String toString(){
    return "Available and Reserved";
  }
}