package States;
import Model.*;

import javax.swing.plaf.multi.MultiSeparatorUI;

public class BorrowedAndReservedState implements VinylState {
    @Override
    public void borrow(Vinyl vinyl, int userId) {
      // Do nothing (already borrowed)
    }

    @Override
    public void returnVinyl(Vinyl vinyl) {
      vinyl.setBorrowedBy(null);
      vinyl.setState(new AvailableAndReservedState());
      //vinyl.changeToAvailableAndReservedState();
    }

    @Override
    public void reserve(Vinyl vinyl, int userId) {
      // Do nothing (already reserved);
    }

    public void unreserve(Vinyl vinyl) {
      vinyl.setReservedBy(null);
      vinyl.setState(new BorrowedState());
      //vinyl.changeToBorrowedState();
    }

    /*
    @Override
    public String getStatus(){
    return "Borrowed and Reserved";
     */


  }

