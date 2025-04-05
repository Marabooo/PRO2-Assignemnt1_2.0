package ViewModel;

import Model.*;
import States.*;
import Storage.XMLStorage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeSupport;
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

  private transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);


  public VinylViewModel(VinylLibrary library) {
    this.vinylLibrary = library;
    this.vinyls.addAll(library.getVinyls());
  }

  //Expose Vinyl List to the View / binding
  public ObservableList<Vinyl> getVinyls() {
    return vinyls;
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
    if (vinyl != null) {
      vinylLibrary.unreserveVinyl(user.getId(), vinyl.getId());
      updateVinyls();
      saveData();
    }
  }
   public void unmarkForRemovalVM(Vinyl vinyl)
   {
      if (vinyl != null)
      {
        vinyl.unmarkForRemoval();
        updateVinyls();
        saveData();
      }
   }

  public void borrowVinylVM(Vinyl vinyl, User user) {
    if (vinyl != null) {
      vinylLibrary.borrowVinyl(user.getId(), vinyl.getId());
      updateVinyls();
      saveData();
    }
  }

  public void reserveVinylVM(Vinyl vinyl, User user) {
    if (vinyl != null) {
      vinylLibrary.reserveVinyl(user.getId(), vinyl.getId());
      updateVinyls();
      saveData();
    }
  }

  public void returnVinylVM(Vinyl vinyl, User user) {
    if (vinyl == null || user.getId() != vinyl.getBorrowedBy()) {
      throw new IllegalArgumentException("Vinyl is not borrowed by this user");
    }
      vinylLibrary.returnVinyl(vinyl.getBorrowedBy(), vinyl.getId());
      updateVinyls();
      saveData();
    }

  public void markForRemovalVM(Vinyl vinyl) {
    if (vinyl != null) {
      vinyl.markForRemoval();
      updateVinyls();
      saveData();
    }
  }

  public void removeVinylVM(Vinyl vinyl) {
    if(vinyl != null && vinyl.getState() instanceof AvailableState && vinyl.isMarkedForRemoval() ){
      vinylLibrary.removeVinyl(vinyl);
      saveData();
    }

  }

  public StringProperty statusMessageProperty() {
    return statusMessage;
  }

  public void setStatusMessage(String message) {
    Platform.runLater(() -> statusMessage.set(message));
  }

  /*private void firePropertyChange()

  {
    for (Vinyl vinyl : vinyls)
    {
      vinylLibrary.addPropertyChangeListener(evt -> {
        // Update an observable property with a description of the event.
        String message =
            "Vinyl " + vinyl.getTitle() + " changed: " + evt.getPropertyName()
                + " from " + evt.getOldValue() + " to " + evt.getNewValue();
        setStatusMessage(message);
      });
    }
  }
*/


  public void updateVinyls() {
    vinyls.setAll(vinylLibrary.getVinyls());

  }

  // Save the data to XML
  public void saveData() {
    XMLStorage.saveVinylsToXML("vinyls.xml", vinylLibrary.getVinyls());
    XMLStorage.saveUsersToXML("users.xml", vinylLibrary.getUsers());
  }
}

