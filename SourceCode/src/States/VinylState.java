package States;

import Model.Vinyl;

public interface VinylState {
  void borrow(Vinyl vinyl, int userId);
  void returnVinyl(Vinyl vinyl);
  void reserve(Vinyl vinyl, int userId);
  void unreserve(Vinyl vinyl);

}