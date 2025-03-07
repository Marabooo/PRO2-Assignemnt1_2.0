package States;
import Model.*;

public class BorrowedState implements VinylState {
  
  public BorrowedState(Vinyl vinyl) {
    System.out.println(" ");
    System.out.println(" Vinyl is Borrowed ");
  }

  public void onBorrowButtonPress(Vinyl vinyl) {
  }

  public void onReturnButtonPress(Vinyl vinyl) {
    System.out.println(" Returning vinyl ...");
    vinyl.changeToAvailableState();
  }

  public void onReserveButtonPress(Vinyl vinyl) {
    System.out.println(" Reserving vinyl ... ");
    vinyl.changeToBorrowedAndReservedState();
  }

  public void onUnreserveButtonPress(Vinyl vinyl) {
  }

  public void onMarkForRemovalButtonPress(Vinyl vinyl) {
    System.out.println("Changing flag to -> For REMOVAL ");
  }

  public void onUnmarkForRemovalButtonPress(Vinyl vinyl) {
    System.out.println(" Removing removal flag ");
  }
}
