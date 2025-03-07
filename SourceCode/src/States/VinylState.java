package States;
import Model.*;

public interface VinylState {
  void onBorrowButtonPress(Vinyl vinyl);
  void onReturnButtonPress(Vinyl vinyl);
  void onReserveButtonPress(Vinyl vinyl);
  void onUnreserveButtonPress(Vinyl vinyl);
  //void onMarkForRemovalButtonPress(Vinyl vinyl);
  //void onUnmarkForRemovalButtonPress(Vinyl vinyl);
}