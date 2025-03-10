package View;

import Model.*;
import Simulation.UserSimulator;
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
    if (loadedVinyls == null)
      {
        loadedVinyls = new ArrayList<>();
      }
    if (loadedUsers == null)
      {
        loadedUsers = new ArrayList<>();
      }
    // Set the next ID for the User and Vinyl classes based on the loaded data.
    if (!loadedUsers.isEmpty())
      {
        int maxUserId = loadedUsers.stream()
            .mapToInt(User::getId)
            .max()
            .orElse(0);
        User.setNextId(maxUserId + 1);
      }
    if (!loadedVinyls.isEmpty())
      {
        int maxVinylId = loadedVinyls.stream()
            .mapToInt(Vinyl::getId)
            .max()
            .orElse(0);
        Vinyl.setNextId(maxVinylId + 1);
      }


    // Create the library with persisted data.
    VinylLibrary library = new VinylLibrary(loadedUsers, loadedVinyls);
    XMLStorage.saveVinylsToXML("vinyls.xml", library.getVinylList());
    XMLStorage.saveUsersToXML("users.xml", library.getUsers());


    // Created ViewModel with the library
    VinylViewModel viewModel = new VinylViewModel(library);

    // Start the simulation in its own thread
    Thread simulationThread = new Thread(new UserSimulator(viewModel, library));
    simulationThread.setDaemon(true);  // so it doesn't block application exit
    simulationThread.start();


    FXMLLoader loader = null;
    Parent root = null;
    try
    {
      // Load your main view and set its view model via the controller
      loader = new FXMLLoader(
          getClass().getResource("/View/VinylLibrary.fxml"));
       root = loader.load();

    // Assuming your VinylViewController has an initViewModel method.
    View.VinylViewController controller = loader.getController();
    controller.initViewModel(viewModel);

    primaryStage.setTitle("Vinyl Library");
    primaryStage.setScene(new Scene(root));
    primaryStage.setResizable(true);
    primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }

    primaryStage.setOnCloseRequest(e -> {
      // Save the library when the window is closing.
      XMLStorage.saveVinylsToXML("vinyls.xml", library.getVinylList());
      XMLStorage.saveUsersToXML("users.xml", library.getUsers());
    });
  }

  public static void main(String[] args) {
    launch(args);
  }
}


