package States;
import Model.*;

import javax.swing.plaf.multi.MultiSeparatorUI;

public class BorrowedAndReservedState implements VinylState {
    @Override
    public void borrow(Vinyl vinyl, String userName) {
      // Do nothing (already borrowed)
    }

    @Override
    public void returnVinyl(Vinyl vinyl, String userName) {
      // Can only be returned by the following user
      if (!vinyl.getBorrowedBy().equals(userName)) {
        throw new IllegalArgumentException("Vinyl is not borrowed by this user");
      }
      vinyl.setBorrowedBy(null);
      vinyl.setState(new AvailableAndReservedState());
    }

    @Override
    public void reserve(Vinyl vinyl, String userName) {
      // Do nothing (already reserved);
      throw new IllegalArgumentException("Vinyl is already reserved");
    }

    public void unreserve(Vinyl vinyl, String userName) {
      // Only by the reserving user
      if (vinyl.getReservedBy().equals(userName)) {
        throw new IllegalArgumentException("Vinyl was not reserved by this user");
      }
      vinyl.setReservedBy(null);
      vinyl.setState(new BorrowedState());
    }

  @Override
  public String toString() {
    return "Borrowed and Reserved";}
  }

