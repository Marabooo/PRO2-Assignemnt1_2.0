package States;

public class BorrowedAndReservedState
{
  public class BorrowedAndReservedState implements VinylState {
    public BorrowedAndReservedState(Vinyl vinyl) {
      System.out.println(" ");
      System.out.println(" Vinyl is Borrowed by _ and reserved by _");
    }

    public void onBorrowButtonPress(Vinyl vinyl) {
    }

    public void onReturnButtonPress(Vinyl vinyl) {
      System.out.println(" Returning vinyl ...");
      vinyl.changeToAvailableAndReservedState();
    }

    public void onReserveButtonPress(Vinyl vinyl) {
    }

    public void onUnreserveButtonPress(Vinyl vinyl) {
      System.out.println(" Vinyl has been Unreserved ... ");
      vinyl.changeToBorrowedState();
    }

    public void onMarkForRemovalButtonPress(Vinyl vinyl) {
      System.out.println("Changing flag to -> For REMOVAL ");
    }

    public void onUnmarkForRemovalButtonPress(Vinyl vinyl) {
      System.out.println(" Removing removal flag ");
    }
  }

}
