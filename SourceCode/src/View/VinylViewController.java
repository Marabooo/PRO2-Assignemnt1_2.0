package View;

import Model.User;
import Model.Vinyl;
import ViewModel.VinylDisplay;
import ViewModel.VinylViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class VinylViewController {

  @FXML private TableView<VinylDisplay> vinylTable;
  @FXML private TableColumn<VinylDisplay, String> titleColumn;
  @FXML private TableColumn<VinylDisplay, String> artistColumn;
  @FXML private TableColumn<VinylDisplay, String> releaseYearColumn;
  @FXML private TableColumn<VinylDisplay, String> stateColumn;
  @FXML private TableColumn<VinylDisplay, String> reservedByColumn;
  @FXML private TableColumn<VinylDisplay, String> borrowedByColumn;

  @FXML private Button markForRemovalButton;
  @FXML private Button unreserveButton;
  @FXML private Button borrowButton;
  @FXML private Button reserveButton;
  @FXML private Button returnButton;
  @FXML private Button unmarkForRemovalButton;
  @FXML private Button addVinylButton;
  @FXML private Button addUserButton;
  @FXML private TextArea logTextArea;
  @FXML private Label statusLabel;

  private static final Logger logger = Logger.getLogger(VinylViewController.class.getName());
  private VinylViewModel viewModel;

  public void initViewModel(VinylViewModel viewModel) {
    this.viewModel = viewModel;

    vinylTable.setItems(viewModel.getVinylDisplayObservableList());
    statusLabel.textProperty().bind(viewModel.statusMessageProperty());

    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
    releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
    stateColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    reservedByColumn.setCellValueFactory(new PropertyValueFactory<>("reservedBy"));
    borrowedByColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedBy"));
  }

  private void updateUI() {
    vinylTable.setItems(viewModel.getVinylDisplayObservableList());
    vinylTable.refresh();
  }

  private void log(String message) {
    logTextArea.appendText(message + "\n");
    logger.info(message);
  }

  private Vinyl getRealVinylFromDisplay(VinylDisplay display) {
    if (display == null) return null;

    return viewModel.getVinylLibrary().getVinyls().stream()
        .filter(v -> v.getTitle().equals(display.getTitle()) &&
            v.getArtist().equals(display.getArtist()) &&
            v.getReleaseYear() == Integer.parseInt(display.getReleaseYear()))
        .findFirst()
        .orElse(null);
  }

  @FXML public void onBorrowVinylButtonPress() {
    VinylDisplay selectedDisplay = vinylTable.getSelectionModel().getSelectedItem();
    Vinyl selected = getRealVinylFromDisplay(selectedDisplay);

    if (selected != null) {
      viewModel.borrowVinylVM(selected, User.adminUser);
      log("Borrowed: " + selected.getTitle());
      updateUI();
    }
  }

  @FXML public void onReserveButtonPress() {
    VinylDisplay selectedDisplay = vinylTable.getSelectionModel().getSelectedItem();
    Vinyl selected = getRealVinylFromDisplay(selectedDisplay);

    if (selected != null) {
      viewModel.reserveVinylVM(selected, User.adminUser);
      log("Reserved: " + selected.getTitle());
      updateUI();
    }
  }

  @FXML public void onReturnButtonPress() {
    VinylDisplay selectedDisplay = vinylTable.getSelectionModel().getSelectedItem();
    Vinyl selected = getRealVinylFromDisplay(selectedDisplay);

    if (selected != null) {
      viewModel.returnVinylVM(selected, User.adminUser);
      log("Returned: " + selected.getTitle());
      updateUI();
    }
  }

  @FXML public void onUnreserveButtonPress() {
    VinylDisplay selectedDisplay = vinylTable.getSelectionModel().getSelectedItem();
    Vinyl selected = getRealVinylFromDisplay(selectedDisplay);

    if (selected != null) {
      viewModel.unreserveVinylVM(selected, User.adminUser);
      log("Unreserved: " + selected.getTitle());
      updateUI();
    }
  }

  @FXML public void onMarkForRemovalButtonPress() {
    VinylDisplay selectedDisplay = vinylTable.getSelectionModel().getSelectedItem();
    Vinyl selected = getRealVinylFromDisplay(selectedDisplay);

    if (selected != null) {
      viewModel.markForRemovalVM(selected);
      log("Marked for removal: " + selected.getTitle());
      updateUI();
    }
  }

  @FXML public void onUnmarkForRemoval() {
    VinylDisplay selectedDisplay = vinylTable.getSelectionModel().getSelectedItem();
    Vinyl selected = getRealVinylFromDisplay(selectedDisplay);

    if (selected != null) {
      viewModel.unmarkForRemovalVM(selected);
      log("Unmarked for removal: " + selected.getTitle());
      updateUI();
    }
  }

  @FXML public void onRemoveVinylButtonPress() {
    VinylDisplay selectedDisplay = vinylTable.getSelectionModel().getSelectedItem();
    Vinyl selected = getRealVinylFromDisplay(selectedDisplay);

    if (selected != null) {
      viewModel.removeVinylVM(selected);
      log("Removed: " + selected.getTitle());
      updateUI();
    }
  }

  @FXML public void openAddUserWindow() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddUserView.fxml"));
      Parent root = loader.load();
      AddUserController controller = loader.getController();
      controller.initViewModel(viewModel);

      Stage stage = new Stage();
      stage.setTitle("Add User");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML public void openAddVinylWindow() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddVinylView.fxml"));
      Parent root = loader.load();
      AddVinylController controller = loader.getController();
      controller.initViewModel(viewModel);

      Stage stage = new Stage();
      stage.setTitle("Add Vinyl");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
