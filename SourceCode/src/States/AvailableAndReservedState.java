package States;
import Model.*;

public class AvailableAndReservedState implements VinylState {

  @Override
  public void borrow (Vinyl vinyl, int userId){
    // Only the reserver can borrow
    if (userId == vinyl.getReservedBy()){
      vinyl.setBorrowedBy(userId);
      vinyl.setState(new BorrowedState());
      //vinyl.changeToBorrowedState();
    }
  }

  @Override
  public void returnVinyl (Vinyl vinyl){
    // Do nothing (Available vinyls can't be returned)
  }

  @Override
  public void reserve (Vinyl vinyl, int userId){
    // Do nothing (already reserved)
  }

  @Override
  public void unreserve (Vinyl vinyl){
    vinyl.setReservedBy(null);
    vinyl.setState(new AvailableState());
  }

  /*
  @Override
  public String getStatus() {
    return "Available and Reserved";
  }
  */

}