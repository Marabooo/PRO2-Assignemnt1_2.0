package View;

import Model.Vinyl;
import Model.User;
import ViewModel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VinylViewController {
  @FXML private TableView<Vinyl> vinylTable;
  @FXML private TableColumn<Vinyl, String> titleColumn;
  @FXML private TableColumn<Vinyl, String> artistColumn;
  @FXML private TableColumn<Vinyl, Integer> releaseYearColumn;
  @FXML private TableColumn<Vinyl, Integer> stateColumn;

  private ViewModel viewModel;

  public void initViewModel(ViewModel viewModel) {
    this.viewModel = viewModel;
    vinylTable.setItems(viewModel.getVinyls()); // Bind ViewModel to TableView
  }

  @FXML
  public void borrowVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      viewModel.borrowVinyl(selected.getId());
    }
  }

  @FXML
  public void reserveVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      viewModel.reserveVinyl(selected.getId(), 1);  // Assuming user ID is needed
    }

  }

  @FXML
  public void returnVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      viewModel.returnVinyl(selected.getId());
    }
  }
}
