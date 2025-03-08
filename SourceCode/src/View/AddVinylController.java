package View;

import ViewModel.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddVinylController {
  @FXML private TextField titleField;
  @FXML private TextField artistField;
  @FXML private TextField releaseYearField;
  @FXML private Button addVinylButton;
  @FXML private Label statusLabel;

  private VinylViewModel viewModel;

  public void initViewModel(VinylViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @FXML
  public void addVinyl() {
    String title = titleField.getText();
    String artist = artistField.getText();
    String releaseYearText = releaseYearField.getText();

    if (title.isEmpty() || artist.isEmpty() || releaseYearText.isEmpty()) {
      statusLabel.setText("All fields are required!");
      return;
    }

    try {
      int releaseYear = Integer.parseInt(releaseYearText);

      new Thread(() -> {
        viewModel.addVinyl(title, artist, releaseYear);
        Platform.runLater(() -> {
            statusLabel.setText("Vinyl added successfully!");
            titleField.clear();
            artistField.clear();
            releaseYearField.clear();
        });
      }).start();

    } catch (NumberFormatException e) {
      statusLabel.setText("Release year must be a number.");
    } catch (IllegalArgumentException e) {
      statusLabel.setText("Failed to add vinyl.");
    }
  }
}
