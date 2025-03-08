
package View;

import ViewModel.ViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddUserController {
  @FXML private TextField usernameField;
  @FXML private Button addUserButton;
  @FXML private Label statusLabel;

  private ViewModel viewModel;

  public void initViewModel(ViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @FXML
  public void addUser() {
    String username = usernameField.getText();

    if (username.isEmpty()) {
      statusLabel.setText("Username is required!");
      return;
    }

    new Thread(() -> {
      boolean success = viewModel.addUser(username);
      Platform.runLater(() -> {
        if (success) {
          statusLabel.setText("User added successfully!");
          usernameField.clear();
        } else {
          statusLabel.setText("Failed to add user.");
        }
      });
    }).start();
  }
}
