package States;
import Model.*;

public class BorrowedState implements VinylState
{
  public void borrow(Vinyl vinyl, String userName)
  {
    // Do nothing (already borrowed by someone)
  }

  public void returnVinyl(Vinyl vinyl, String userName)
  {
    // Can be returned only by the borrowing user
    if (!vinyl.getBorrowedBy().equals(userName)) {
      throw new IllegalArgumentException("Vinyl is not borrowed by this user");
    }
    vinyl.setBorrowedBy(null);
    vinyl.setState(new AvailableState());
  }

  public void reserve(Vinyl vinyl, String userName)
  {
    // Can only reserve vinyls that are not flagged for removal
    // Users who have the vinyl should not be able to reserve them
    if (vinyl.isMarkedForRemoval()) {
      throw new IllegalArgumentException("Vinyl is marked for removal");
    }
    if (vinyl.getBorrowedBy().equals(userName)){
      throw new IllegalArgumentException("User already owns it, why reserve it?");
    }
    vinyl.setReservedBy(userName);
    vinyl.setState(new BorrowedAndReservedState());
  }

  public void unreserve(Vinyl vinyl, String userName)
  {
    // Do nothing (Just borrowed, there is no reservation)
    // System.out.println("Vinyl is not reserved");
  }


  @Override public String toString()
  {
    return "Borrowed";
  }
}
