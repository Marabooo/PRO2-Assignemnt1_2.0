package States;
import Model.*;

public class AvailableAndReservedState implements VinylState {

  @Override
  public void borrow (Vinyl vinyl, int userId){
    // Only the reserver can borrow
    // Marked for Removal should not impact the reserver borrow their vinyl
    if (userId != vinyl.getReservedBy())
    {
      throw new IllegalStateException("Only the reserver can borrow");
    }
      vinyl.setBorrowedBy(userId);
      vinyl.unreserve(userId);
      vinyl.setState(new BorrowedState());

  }

  @Override
  public void returnVinyl (Vinyl vinyl, int userId){
    // Do nothing (Available vinyls can't be returned)
    System.out.println("Vinyl is not borrowed, so it cannot be returned.");
  }

  @Override
  public void reserve (Vinyl vinyl, int userId){
    // Do nothing (already reserved)
  }

  @Override
  public void unreserve (Vinyl vinyl, int userId){
    // Can only be returned by the reserver
    if(vinyl.getReservedBy() == null) {
      throw new IllegalStateException("Vinyl is not reserved");
    }
    if (vinyl.getReservedBy() != userId) {
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