package View;

import Model.Vinyl;
import Model.User;
import ViewModel.VinylViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class VinylViewController
{

  public Button markForRemovalButton;
  public Button unreserveButton;
  public Button borrowButton;
  public Button reserveButton;
  public Button returnButton;
  public Button unmarkforRemovalButton;
  @FXML private TableView<Vinyl> vinylTable;
  @FXML private TextArea logTextArea;
  @FXML private TableColumn<Vinyl, String> titleColumn;
  @FXML private TableColumn<Vinyl, String> artistColumn;
  @FXML private TableColumn<Vinyl, Integer> releaseYearColumn;
  @FXML private TableColumn<Vinyl, Integer> stateColumn;
  @FXML private TableColumn<Vinyl, Integer> reservedByColumn;
  @FXML private TableColumn<Vinyl, Integer> borrowedByColumn;
  @FXML private TableColumn<Vinyl, Integer> markedForRemovalColumn;
  private static final Logger logger = Logger.getLogger(
      VinylViewController.class.getName());
  @FXML private Label statusLabel;
  private VinylViewModel viewModel;

  public void initViewModel(VinylViewModel viewModel) {
    this.viewModel = viewModel;
    vinylTable.setItems(viewModel.getVinyls());

    statusLabel.textProperty().bind(viewModel.statusMessageProperty());

    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
    releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
    stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
    reservedByColumn.setCellValueFactory(
        new PropertyValueFactory<>("reservedBy"));
    borrowedByColumn.setCellValueFactory(
        new PropertyValueFactory<>("borrowedBy"));
    markedForRemovalColumn.setCellValueFactory(
        new PropertyValueFactory<>("markedForRemoval"));
  }


  private void updateUI() {
    Platform.runLater(() -> vinylTable.refresh());
  }


  //if we add these methods in the viewmodel, it is NOT good, the View directly modifies the Model
  // The UI should not interact directly with Vinyl.
  // It should use the ViewModel as an intermediary. Correct - View Controller calls ViewModel
  public void onBorrowButtonPressed(Vinyl vinyl, User user) {
    viewModel.borrowVinyl(vinyl, user);
  }

  public void onReserveButtonPressed(Vinyl vinyl)
  {
    viewModel.reserveVinyl(vinyl, User.adminUser);
  }

  public void onReturnButtonPressed(Vinyl vinyl)
  {
    viewModel.returnVinyl(vinyl, User.adminUser);
  }

  public void onUnreserveButtonPressed(Vinyl vinyl, User user) {
    viewModel.unreserveVinyl(vinyl, User.adminUser);
  }

  public void onMarkForRemovalButtonPressed(Vinyl vinyl)
  {
    viewModel.markForRemoval(vinyl);
  }

  private void log(String message)
  {
    Platform.runLater(() -> logTextArea.appendText(message + "\n"));
    logger.info(message);
  }

  @FXML public void borrowVinyl()
  {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();

    if (selected != null)
    {
      new Thread(() -> {
        viewModel.borrowVinyl(selected, User.adminUser);
        log( "Vinyl: " +  selected.getTitle() + " Borrowed by: " + User.adminUser.getName());
        Platform.runLater(this::updateUI);
      }).start();
    }
  }

  @FXML public void reserveVinyl()
  {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      new Thread(() -> {
        viewModel.reserveVinyl(selected, User.adminUser);
        log( "Vinyl: " +  selected.getTitle() + " Reserved by: " + User.adminUser.getName());
        Platform.runLater(this::updateUI);
      }).start();
    }
  }

  @FXML public void returnVinyl()
  {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      new Thread(() -> {
        viewModel.returnVinyl(selected, User.adminUser);
        log( "Vinyl: " +  selected.getTitle() + " Returned by: " + User.adminUser.getName());
        Platform.runLater(this::updateUI);
      }).start();
    }
  }

  @FXML public void unreserveVinyl()
  {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      new Thread(() -> {
        viewModel.unreserveVinyl(selected, User.adminUser);
        log("Unreserved vinyl: " + selected.getTitle());
        Platform.runLater(this::updateUI);
      }).start();
    }
  }

  @FXML public void markForRemoval()
  {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      new Thread(() -> {
        viewModel.markForRemoval(selected);
        log( "Vinyl: " +  selected.getTitle() + " Marked for removal by: " + User.adminUser.getName());
        Platform.runLater(
            this::updateUI); //method is called on the JavaFX Application Thread
      }).start();
    }
  }

  @FXML public void unmarkForRemoval()
  {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      new Thread(() -> {
        viewModel.unmarkForRemoval(selected);
        log( "Vinyl: " +  selected.getTitle() + " Unmarked for removal: by: " + User.adminUser.getName());
        Platform.runLater(this::updateUI);
      }).start();
    }
  }

  @FXML public void openAddUserWindow()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/View/AddUserView.fxml"));
      Parent root = loader.load();
      AddUserController controller = loader.getController();
      controller.initViewModel(viewModel);

      Stage stage = new Stage();
      stage.setTitle("Add User");
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @FXML public void removeVinyl()
  {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      new Thread(() -> {
        viewModel.removeVinyl(selected);
        log( "Vinyl: " +  selected.getTitle() + " Removed by: " + User.adminUser.getName());
        Platform.runLater(() -> {
          vinylTable.getItems()
              .remove(selected); // Remove the item from the TableView
          updateUI(); // Refresh the TableView
        });
      }).start();
    }
  }

  @FXML public void openAddVinylWindow()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/View/AddVinylView.fxml"));
      Parent root = loader.load();
      AddVinylController controller = loader.getController();
      controller.initViewModel(viewModel);

      Stage stage = new Stage();
      stage.setTitle("Add Vinyl");
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}





