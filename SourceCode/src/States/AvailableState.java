package States;
import Model.*;

public class AvailableState implements VinylState {
  public AvailableState() {
    System.out.println(" ");
    System.out.println(" Vinyl is Available ");
  }

  public void onBorrowButtonPress(Vinyl vinyl) {
    System.out.println(" Borrowing available vinyl ... ");
    vinyl.changeToBorrowedState();
  }

  public void onReturnButtonPress(Vinyl vinyl) {
    // do nothing
  }

  public void onReserveButtonPress(Vinyl vinyl) {
    System.out.println(" Reserving available vinyl ... ");
    vinyl.changeToAvailableAndReservedState();
  }

  public void onUnreserveButtonPress(Vinyl vinyl) {
    // do nothing
  }

  public void onMarkForRemovalButtonPress(Vinyl vinyl) {
    System.out.println("Changing flag to -> For REMOVAL ");
  }

  public void onUnmarkForRemovalButtonPress(Vinyl vinyl) {
    System.out.println(" Removing removal flag ");
  }
}
