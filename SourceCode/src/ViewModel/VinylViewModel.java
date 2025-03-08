package ViewModel;

//this code not done yet, still testing


import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class VinylViewModel {
  private final ObservableList<Vinyl> vinyls = FXCollections.observableArrayList();
  private final Random random = new Random();

  private User currentUser;

  public VinylViewModel() {
    Thread vinylAdderThread = new Thread(this::startVinylAdder);
    vinylAdderThread.setDaemon(true);
    vinylAdderThread.start();
  }

  public void setCurrentUser(User user) {
    this.currentUser = user;
  }


  public ObservableList<Vinyl> getVinyls() { return vinyls; }

  public void borrowVinyl(int id) { updateState(id, "Borrowed"); }
  public void returnVinyl(int id) { updateState(id, "Available"); }
  public void unreserveVinyl(int id) { updateState(id, "Unreserved"); }
  public void markForRemoval(int id) { updateState(id, "Marked for removal"); }
  public void unmarkForRemoval(int id) { updateState(id, "Unmarked for removal"); }

  private void updateState(int id, String state) {
    vinyls.stream()
        .filter(v -> v.getId() == id)
        .findFirst()
        .ifPresent(v -> {
          switch (state) {
            case "Borrowed":
              v.changeToBorrowedState();
              break;
            case "Available":
              v.changeToAvailableState();
              break;
            case "BorrowedAndReserved":
              v.changeToBorrowedAndReservedState();
              break;
            case "AvailableAndReserved":
              v.changeToAvailableAndReservedState();
              break;
            default:
              throw new IllegalArgumentException("Unknown state: " + state);
          }
        });
  }

  public void reserveVinyl(int vinylId, int userId)
  {
    vinyls.stream()
        .filter(v -> v.getId() == vinylId && v.getReservedBy() == null)
        .findFirst()
        .ifPresent(v -> v.setReservedBy(userId));
  }


  private void startVinylAdder() {
    while (true) {
      try {
        Thread.sleep(5000 + random.nextInt(5000));
        addRandomVinyl();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  private void addRandomVinyl() {
    Vinyl newVinyl = new Vinyl(random.nextInt(1000), "Random Album", "Random Artist", 2000 + random.nextInt(25));
    vinyls.add(newVinyl);
  }

  public void onReserveButtonClicked(int vinylId) {
    // currentUser is stored in the ViewModel as the active user.
    int currentUserId = currentUser.getId();
    reserveVinyl(vinylId, currentUserId);
  }


}