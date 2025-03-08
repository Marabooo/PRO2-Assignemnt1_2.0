package States;

import Model.Vinyl;

public interface VinylState {
  void onBorrowButtonPress(Vinyl var1);

  void onReturnButtonPress(Vinyl var1);

  void onReserveButtonPress(Vinyl var1);

  void onUnreserveButtonPress(Vinyl var1);

  void onMarkForRemovalButtonPress(Vinyl var1);

  void onUnmarkForRemovalButtonPress(Vinyl var1);
}