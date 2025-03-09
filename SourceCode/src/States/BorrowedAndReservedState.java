package States;
import Model.*;

import javax.swing.plaf.multi.MultiSeparatorUI;

public class BorrowedAndReservedState implements VinylState {
    @Override
    public void borrow(Vinyl vinyl, int userId) {
      // Do nothing (already borrowed)
    }

    @Override
    public void returnVinyl(Vinyl vinyl, int userId)
    {
      if (vinyl.getBorrowedBy() != userId)
      {
        throw new IllegalArgumentException("Vinyl is not borrowed by this user");
      }
      vinyl.setBorrowedBy(null);
      vinyl.setState(new AvailableAndReservedState());
    }

    @Override
    public void reserve(Vinyl vinyl, int userId) {
      // Do nothing (already reserved);
      throw new IllegalArgumentException("Vinyl is already reserved");
    }

    public void unreserve(Vinyl vinyl, int userId) {
      if (vinyl.getReservedBy() != userId) {
        throw new IllegalArgumentException("Vinyl was not reserved by this user");
      }
      vinyl.setReservedBy(null);
      vinyl.setState(new BorrowedState());
    }

    /*
    @Override
    public String getStatus(){
    return "Borrowed and Reserved";
     */
  @Override
  public String toString() {
    return "Borrowed and Reserved";}

  }

