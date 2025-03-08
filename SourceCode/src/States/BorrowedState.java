package States;
import Model.*;

public class BorrowedState implements VinylState
{
  public void borrow(Vinyl vinyl, int userId)
  {
    // Do nothing (already borrowed by someone)
  }

  public void returnVinyl(Vinyl vinyl)
  {
    vinyl.setBorrowedBy(null);
    //vinyl.setState(new BorrowedAndReservedState());
    vinyl.changeToAvailableState();
  }

  public void reserve(Vinyl vinyl, int userId)
  {
    // Can only reserve vinyls that are not flagged for removal
    if (!vinyl.isMarkedForRemoval())
    {
      vinyl.setReservedBy(userId);
      //vinyl.setState(new BorrowedAndReservedState());
      vinyl.changeToBorrowedAndReservedState();
    }

  }

  public void unreserve(Vinyl vinyl)
  {
    // Do nothing (just borrowed, there is no reservation)
  }

  /*
  @Override public String getStatus()
  {
    return"Borrowed";
  }
   */
}
