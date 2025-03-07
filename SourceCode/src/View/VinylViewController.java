package View;

import Model.Vinyl;
import Model.User;
import ViewModel.VinylViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VinylViewController {
  @FXML private TableView<Vinyl> vinylTable;
  @FXML private TableColumn<Vinyl, String> nameColumn;
  @FXML private TableColumn<Vinyl, String> artistColumn;
  @FXML private TableColumn<Vinyl, Integer> releaseYearColumn;
  @FXML private TableColumn<Vinyl, Integer> vinylIdColumn;

  private VinylViewModel viewModel;

  public void initViewModel(VinylViewModel viewModel) {
    this.viewModel = viewModel;
    vinylTable.setItems(viewModel.getVinyls()); // Bind ViewModel to TableView
  }

  @FXML
  public void borrowVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      viewModel.borrowVinyl(selected.getVinylId());
    }
  }

  @FXML
  public void reserveVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      User user = new User("Test User", selected.getReservingUserId()); // Hardcoded for now
      viewModel.reserveVinyl(selected.getVinylId(), user);
    }
  }

  @FXML
  public void returnVinyl() {
    Vinyl selected = vinylTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      viewModel.returnVinyl(selected.getVinylId());
    }
  }
}
