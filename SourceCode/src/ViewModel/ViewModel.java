package ViewModel;

//this code not done yet, still testing


import Model.Vinyl;
import States.VinylState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class ViewModel {
  private final ObservableList<Vinyl> vinyls = FXCollections.observableArrayList();
  private final Random random = new Random();

  public ViewModel() {
    Thread vinylAdderThread = new Thread(this::startVinylAdder);
    vinylAdderThread.setDaemon(true);
    vinylAdderThread.start();
  }

  public ObservableList<Vinyl> getVinyls() { return vinyls; }

  public void borrowVinyl(int id) { updateState(id, "Borrowed"); }
  public void returnVinyl(int id) { updateState(id, "Available"); }


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

  public void reserveVinyl(int id, int Id) {
    vinyls.stream()
        .filter(v -> v.getId() == id && !v.isReserved()) // Ensure isReserved() is correct
        .findFirst()
        .ifPresent(v -> v.setReserved(true)); // FIXED: Actually reserves it
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
}