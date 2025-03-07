package States;
import Model.*;

public interface VinylState {
  void onBorrowButtonPress(Vinyl vinyl);

  void onReturnButtonPress(Vinyl vinyl);

  void onReserveButtonPress(Vinyl vinyl);

  void onUnreserveButtonPress(Vinyl var1);

  void onMarkForRemovalButtonPress(Vinyl var1);

  void onUnmarkForRemovalButtonPress(Vinyl var1);
}