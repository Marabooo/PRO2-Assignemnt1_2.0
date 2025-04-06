package ViewModel;

import Model.*;
import Storage.XMLStorage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class VinylViewModel {
  private final ObservableList<Vinyl> vinyls = FXCollections.observableArrayList();
  private final VinylLibrary vinylLibrary;
  private List<VinylDisplay> vinylDisplayList = new ArrayList<>();
  private final StringProperty selectedVinylTitle = new SimpleStringProperty();
  private final StringProperty statusMessage = new SimpleStringProperty();

  public VinylViewModel(VinylLibrary library) {
    this.vinylLibrary = library;
    this.vinyls.addAll(library.getVinyls());
  }

  //Expose Vinyl List to the View / binding
  public ObservableList<VinylDisplay> getVinylDisplayObservableList() {
    List<User> users = vinylLibrary.getUsers();
    return FXCollections.observableArrayList(
        vinylLibrary.getVinyls().stream()
            .map(VinylDisplay::new)
            .toList()
    );
  }


  //Allows the binding of the selected Vinyl’s title to a label or text field in the GUI
  public StringProperty selectedVinylTitleProperty() {
    return selectedVinylTitle;
  }

  //VinylDisplay related methods
  private void refreshDisplayList() {
    vinylDisplayList = vinylLibrary.getVinyls().stream()
        .map(VinylDisplay::new)
        .collect(Collectors.toList());
  }

  public List<VinylDisplay> getVinylDisplayList() {
    return new ArrayList<>(vinylDisplayList);
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

public void unreserveVinylVM(Vinyl vinyl, User user) {
    if (vinyl == null) {
      throw new IllegalArgumentException("Trying to unreserve NULL vinyl");
    }
  vinylLibrary.unreserveVinyl(user, vinyl);
  updateVinyls();
  saveData();
  }
   public void unmarkForRemovalVM(Vinyl vinyl) {
      if (vinyl == null) {
        throw new IllegalArgumentException("Trying to unmark NULL vinyl");
      }
     vinylLibrary.unmarkForRemoval(vinyl);
     updateVinyls();
     saveData();
   }

  public void borrowVinylVM(Vinyl vinyl, User user) {
    if (vinyl == null) {
      throw new IllegalArgumentException("Trying to borrow a NULL vinyl");
    }
    vinylLibrary.borrowVinyl(user, vinyl);
    updateVinyls();
    saveData();
  }

  public void reserveVinylVM(Vinyl vinyl, User user) {
    if (vinyl == null) {
      throw new IllegalArgumentException("Trying to reserve a NULL vinyl");
    }
    vinylLibrary.reserveVinyl(user, vinyl);
    updateVinyls();
    saveData();
  }

  public void returnVinylVM(Vinyl vinyl, User user) {
    if (vinyl == null) {
      throw new IllegalArgumentException("Trying to return a NULL vinyl");
    }
    vinylLibrary.returnVinyl(user, vinyl);
    updateVinyls();
    saveData();
  }

  public void markForRemovalVM(Vinyl vinyl) {
    if (vinyl == null) {
      throw new IllegalArgumentException("Trying to mark a NULL vinyl");
    }
    vinylLibrary.markForRemoval(vinyl);
    updateVinyls();
    saveData();
  }

  public void removeVinylVM(Vinyl vinyl) {
    if(vinyl == null){
      throw new IllegalArgumentException("Tring to remove a NULL vinyl");
    }
    vinylLibrary.removeVinyl(vinyl);
    saveData();

  }

  public StringProperty statusMessageProperty() {
    return statusMessage;
  }

  public void setStatusMessage(String message) {
    Platform.runLater(() -> statusMessage.set(message));
  }


  public VinylLibrary getVinylLibrary() {
    return vinylLibrary;
  }


  public void updateVinyls() {
    vinyls.setAll(vinylLibrary.getVinyls());

  }

  // Save the data to XML
  public void saveData() {
    XMLStorage.saveVinylsToXML("vinyls.xml", vinylLibrary.getVinyls());
    XMLStorage.saveUsersToXML("users.xml", vinylLibrary.getUsers());
  }
}

