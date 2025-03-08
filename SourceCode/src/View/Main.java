package View;

import Model.*;
import Storage.XMLStorage;
import ViewModel.VinylViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    // Create your VinylLibrary (repository)
    VinylLibrary library = new VinylLibrary();

    // Create your ViewModel with the library
    VinylViewModel viewModel = new VinylViewModel(library);

    // Load your main view and set its view model via the controller
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/View/VinylView.fxml"));
    Parent root = loader.load();
    // Assuming your VinylViewController has an initViewModel method.
    View.VinylViewController controller = loader.getController();
    controller.initViewModel(viewModel);

    primaryStage.setTitle("Vinyl Library");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

//    // Optionally add a close handler if you want to do additional processing.
//    primaryStage.setOnCloseRequest(e -> {
//      // Save data when the window closes.
//      viewModel.saveData();
//    });
  }


  public static void main(String[] args) {
    launch(args);
  }
}





//package View;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//  @Override
//  public void start(Stage primaryStage) throws Exception {
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/VinylView.fxml"));
//    Parent root = loader.load();
//    primaryStage.setTitle("Vinyl Library");
//    primaryStage.setScene(new Scene(root));
//    primaryStage.show();
//  }
//
//  public static void main(String[] args) {
//    launch(args);
//  }
//}
