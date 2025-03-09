package ViewModel;

//this code not done yet, still testing


import Model.*;
import States.*;
import Storage.XMLStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import States.VinylState;


public class VinylViewModel {
  private final ObservableList<Vinyl> vinyls = FXCollections.observableArrayList();
  private final VinylLibrary vinylLibrary;
  private final StringProperty selectedVinylTitle = new SimpleStringProperty();

  public VinylViewModel(VinylLibrary library) {
    this.vinylLibrary = library;
    this.vinyls.addAll(library.getVinyls()); // add all vinyls from the library
  }

  //Expose Vinyl List to the View / binding
  public ObservableList<Vinyl> getVinyls() {
    return vinyls;
  }

  //Allows the binding of the selected Vinyl’s title to a label or text field in the GUI
  public StringProperty selectedVinylTitleProperty() {
    return selectedVinylTitle;
  }

  public void updateVinyls() {
    vinyls.setAll(vinylLibrary.getVinyls()); // Updating the UI When the Model Changes
  }

  public void addVinyl(String title, String artist, int releaseYear) {
    Vinyl newVinyl = new Vinyl(title, artist, releaseYear);
    vinylLibrary.addVinyl(newVinyl);
    updateVinyls();
    saveData();
  }
  public void addUser(String username) {
    User newUser = new User(username);
    vinylLibrary.addUser(newUser);
    updateVinyls();
    saveData();
  }
  public int getUserId()
  {
    return 0;
  }

public void unreserveVinyl(Vinyl vinyl) {
    if (vinyl != null) {
      vinyl.unreserve();
      updateVinyls();
      saveData();
    }
  }
   public void unmarkForRemoval(Vinyl vinyl)
   {
      if (vinyl != null)
      {
        vinyl.unmarkForRemoval();
        updateVinyls();
        saveData();
      }
   }


  public void borrowVinyl(Vinyl vinyl, User user) {
    if (vinyl != null) {
      vinyl.borrow(user.getId()
      );
      updateVinyls();
      saveData();
    }
  }

  public void reserveVinyl(Vinyl vinyl, User user) {
    if (vinyl != null) {
      vinyl.reserve(user.getId());
      updateVinyls();
      saveData();
    }
  }
  public void returnVinyl(Vinyl vinyl) {
    if (vinyl != null) {
      vinyl.returnVinyl();
      updateVinyls();
      saveData();
    }
  }
  public void markForRemoval(Vinyl vinyl) {
    if (vinyl != null) {
      vinyl.markForRemoval();
      updateVinyls();
      saveData();
    }
  }

  public void removeVinyl(Vinyl vinyl) {
    vinylLibrary.removeVinyl(vinyl);
    saveData();
  }


  // Save the data to XML
  public void saveData() {
    XMLStorage.saveVinylsToXML("vinyls.xml", vinylLibrary.getVinyls());
    XMLStorage.saveUsersToXML("users.xml", vinylLibrary.getUsers());
  }



  /*private final Random random = new Random();

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
              v.setState(new BorrowedState());
              break;
            case "Available":
              v.setState(new AvailableState());
              break;
            case "BorrowedAndReserved":
              v.setState(new BorrowedAndReservedState());
              break;
            case "AvailableAndReserved":
              v.setState(new AvailableAndReservedState());
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
    Vinyl newVinyl = new Vinyl( "Random Album", "Random Artist", 2000 + random.nextInt(25));
    vinyls.add(newVinyl);
  }

  public void onReserveButtonClicked(int vinylId) {
    // currentUser is stored in the ViewModel as the active user.
    int currentUserId = currentUser.getId();
    reserveVinyl(vinylId, currentUserId);
  }

  public boolean addUser(String username) {
    // Logic to add a user
    return true; // return true if successful
  }

  public boolean addVinyl(String title, String artist, int releaseYear) {
    // Logic to add a vinyl
    return true; // return true if successful
  }
*/

}

