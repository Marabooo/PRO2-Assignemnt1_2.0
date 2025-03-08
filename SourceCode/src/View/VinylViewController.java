package View;

import Model.Vinyl;
import Model.User;
import ViewModel.ViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VinylViewController {

  public Button markForRemovalButton;
  public Button unreserveButton;
  public Button borrowButton;
  public Button reserveButton;
  public Button returnButton;
  public Button unmarkforRemovalButton;
  @FXML private TableView<Vinyl> vinylTable;
  @FXML private TableColumn<Vinyl, String> titleColumn;
  @FXML private TableColumn<Vinyl, String> artistColumn;
  @FXML private TableColumn<Vinyl, Integer> releaseYearColumn;
  @FXML private TableColumn<Vinyl, Integer> stateColumn;

  private ViewModel viewModel;
  public void initViewModel(ViewModel viewModel) {
    this.viewModel = viewModel;
    vinylTable.setItems(viewModel.getVinyls());

    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
    releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
    stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

  }


  private void updateUI() {
    Platform.runLater(() -> vinylTable.refresh());
  }


  @FXML
  public void borrowVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      new Thread(() -> {
        viewModel.borrowVinyl(selected.getId());
        updateUI();
      }).start();
    }
  }

  @FXML
  public void reserveVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      new Thread(() -> {
        viewModel.reserveVinyl(selected.getId(), 1);
        updateUI();
      }).start();
    }
  }

  @FXML
  public void returnVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      new Thread(() -> {
        viewModel.returnVinyl(selected.getId());
        updateUI();
      }).start();
    }
  }

  @FXML
  public void unreserveVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      new Thread(() -> {
        viewModel.unreserveVinyl(selected.getId());
        updateUI();
      }).start();
    }
  }

  @FXML
  public void markForRemoval() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      new Thread(() -> {
        viewModel.markForRemoval(selected.getId());
        updateUI();
      }).start();
    }
  }

  @FXML
  public void unmarkForRemoval() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      new Thread(() -> {
        viewModel.unmarkForRemoval(selected.getId());
        updateUI();
      }).start();
    }
  }
}


