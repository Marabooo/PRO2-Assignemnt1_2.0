package Test;

import Model.User;
import Model.Vinyl;
import Model.VinylLibrary;
import Storage.XMLStorage;
import java.util.List;

public class TestXMLStorage {
  public static void main(String[] args) {
    // Create a new VinylLibrary instance and clear it and also the classes
    User.resetCounter();
    Vinyl.resetCounter();

    VinylLibrary library = new VinylLibrary();
    library.clear(); // Clears the internal users and vinyls lists

    // Add test Vinyl objects to the library
    library.addVinyl(new Vinyl("Album One", "Artist One", 1999));
    library.addVinyl(new Vinyl("Album Two", "Artist Two", 2005));
    library.addVinyl(new Vinyl("Greatest Album that Ever Was", "A guy with a guitar", 2020));

    // Add test User objects to the library
    library.addUser(new User("Alice"));
    library.addUser(new User("Bob"));
    library.addUser(new User("Bugs Bunny"));

    // Save the library data to XML files using the library's lists
    XMLStorage.saveVinylsToXML("test_vinyls.xml", library.getVinylList());
    XMLStorage.saveUsersToXML("test_users.xml", library.getUsers());

    // Load the data back from the XML files
    List<Vinyl> loadedVinyls = XMLStorage.loadVinylsFromXML("test_vinyls.xml");
    List<User> loadedUsers = XMLStorage.loadUsersFromXML("test_users.xml");

    // Print out the loaded data to verify it was saved and loaded correctly
    System.out.println("Loaded Vinyls:");
    if (loadedVinyls != null) {
      for (Vinyl v : loadedVinyls) {
        System.out.println(v);
      }
    } else {
      System.out.println("No Vinyls loaded.");
    }

    System.out.println("\nLoaded Users:");
    if (loadedUsers != null) {
      for (User u : loadedUsers) {
        System.out.println(u);
      }
    } else {
      System.out.println("No Users loaded.");
    }
  }
}
