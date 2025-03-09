package View;

import Model.*;
import Storage.XMLStorage;
import ViewModel.VinylViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    // Created VinylLibrary (repository) and xmlStorage lists
    List<Vinyl> loadedVinyls = XMLStorage.loadVinylsFromXML("vinyls.xml");
    List<User> loadedUsers = XMLStorage.loadUsersFromXML("users.xml");

    // If no data was loaded, use empty lists.
    if (loadedVinyls == null) {
      loadedVinyls = new ArrayList<>();
    }
    if (loadedUsers == null) {
      loadedUsers = new ArrayList<>();
    }

    // Create the library with persisted data.
    VinylLibrary library = new VinylLibrary(loadedUsers, loadedVinyls);
    XMLStorage.saveVinylsToXML("vinyls.xml", library.getVinylList());
    XMLStorage.saveUsersToXML("users.xml", library.getUsers());


    // Created ViewModel with the library
    VinylViewModel viewModel = new VinylViewModel(library);


    // Load your main view and set its view model via the controller
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/View/VinylLibrary.fxml"));
    Parent root = loader.load();
    // Assuming your VinylViewController has an initViewModel method.
    View.VinylViewController controller = loader.getController();
    controller.initViewModel(viewModel);

    primaryStage.setTitle("Vinyl Library");
    primaryStage.setScene(new Scene(root));

    primaryStage.show();

    primaryStage.setOnCloseRequest(e -> {
      // Save the library when the window is closing.
      XMLStorage.saveVinylsToXML("vinyls.xml", library.getVinylList());
      XMLStorage.saveUsersToXML("users.xml", library.getUsers());
    });


    controller.initViewModel(viewModel);
  }


  public static void main(String[] args) {
    launch(args);
  }


}


