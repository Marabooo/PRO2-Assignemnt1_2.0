package View;

import ViewModel.VinylViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddUserController {
  @FXML private TextField usernameField;
  @FXML private Button addUserButton;
  @FXML private Label statusLabel;

  private VinylViewModel viewModel;

  public void initViewModel(VinylViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @FXML
  public void addUser() {
    String username = usernameField.getText();

    if (username.isEmpty()) {
      statusLabel.setText("Username is required!");
      return;
    }

    try {
      viewModel.addUser(username);
      statusLabel.setText("User added successfully!");
      usernameField.clear();
    } catch (IllegalArgumentException e) {
      statusLabel.setText("Failed to add user.");
    }
  }

}