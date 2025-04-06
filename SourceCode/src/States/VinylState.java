package States;

import Model.Vinyl;

public interface VinylState {
  void borrow(Vinyl vinyl, String userName);
  void returnVinyl(Vinyl vinyl, String userName);
  void reserve(Vinyl vinyl, String userName);
  void unreserve(Vinyl vinyl, String userName);

}