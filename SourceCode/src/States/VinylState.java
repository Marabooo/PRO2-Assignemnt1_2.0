package States;

import Model.Vinyl;

public interface VinylState {
  void borrow(Vinyl vinyl, int userId);
  void returnVinyl(Vinyl vinyl, int userId);
  void reserve(Vinyl vinyl, int userId);
  void unreserve(Vinyl vinyl, int userId);

}