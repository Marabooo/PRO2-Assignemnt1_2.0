package ViewModel;

//this code not done yet, still testing


import Model.*;
import States.*;
import Storage.XMLStorage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class VinylViewModel {
  private final ObservableList<Vinyl> vinyls = FXCollections.observableArrayList();
  private final VinylLibrary vinylLibrary;
  private final StringProperty selectedVinylTitle = new SimpleStringProperty();
  private final StringProperty statusMessage = new SimpleStringProperty();

  public VinylViewModel(VinylLibrary library) {
    this.vinylLibrary = library;
    this.vinyls.addAll(library.getVinyls()); // add all vinyls from the library
 addPropertyChangeListeners();
  }

  //Expose Vinyl List to the View / binding
  public ObservableList<Vinyl> getVinyls() {
    return vinyls;
  }

  //Allows the binding of the selected Vinyl’s title to a label or text field in the GUI
  public StringProperty selectedVinylTitleProperty() {
    return selectedVinylTitle;
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

public void unreserveVinyl(Vinyl vinyl, User user) {
    if (vinyl != null) {
      vinyl.unreserve(user.getId());
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
      vinyl.borrow(user.getId());
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
  public void returnVinyl(Vinyl vinyl, User user) {
    if (vinyl == null || user.getId() != vinyl.getBorrowedBy()) {
      throw new IllegalArgumentException("Vinyl is not borrowed by this user");
    }
      vinyl.returnVinyl(vinyl.getBorrowedBy());
      updateVinyls();
      saveData();
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

  public StringProperty statusMessageProperty() {
    return statusMessage;
  }

  public void setStatusMessage(String message) {
    Platform.runLater(() -> statusMessage.set(message));
  }
  private void addPropertyChangeListeners()
  {
    for (Vinyl vinyl : vinyls)
    {
      vinyl.addPropertyChangeListener(evt -> {
        // Update an observable property with a description of the event.
        String message =
            "Vinyl " + vinyl.getTitle() + " changed: " + evt.getPropertyName()
                + " from " + evt.getOldValue() + " to " + evt.getNewValue();
        setStatusMessage(message);
      });
    }
  }


  public void updateVinyls() {
    vinyls.setAll(vinylLibrary.getVinyls());
    addPropertyChangeListeners();
  }

  // Save the data to XML
  public void saveData() {
    XMLStorage.saveVinylsToXML("vinyls.xml", vinylLibrary.getVinyls());
    XMLStorage.saveUsersToXML("users.xml", vinylLibrary.getUsers());
  }
}

