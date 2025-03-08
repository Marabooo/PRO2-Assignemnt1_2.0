package States;
import Model.*;

public class BorrowedState implements VinylState
{
  public void borrow(Vinyl vinyl, int userId)
  {
    // Do nothing (already borrowed by someone)
  }

  public void returnVinyl(Vinyl vinyl, int userId)
  {
    if (vinyl.getBorrowedBy() != userId)
    {
      throw new IllegalArgumentException("Vinyl is not borrowed by this user");
    }
    vinyl.setBorrowedBy(null);
    vinyl.setState(new AvailableState());
  }

  public void reserve(Vinyl vinyl, int userId)
  {
    // Can only reserve vinyls that are not flagged for removal
    if (vinyl.isMarkedForRemoval())
    {
      throw new IllegalArgumentException("Vinyl is marked for removal");
    }
    vinyl.setReservedBy(userId);
    vinyl.setState(new BorrowedAndReservedState());
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

  @Override public String toString()
  {
    return "Borrowed";
  }
}
