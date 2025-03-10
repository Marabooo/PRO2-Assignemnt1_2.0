package Test;

import Model.Vinyl;
import Model.User;
import Model.VinylLibrary;
import Storage.XMLStorage;

import java.util.List;

public class TestXMLStorage {
  public static void main(String[] args) {
// Reset ID counters for a fresh start.
//    User.resetCounter();
//    Vinyl.resetCounter();

    // Create a new VinylLibrary instance and clear its data.
    VinylLibrary testLibrary = new VinylLibrary();
    testLibrary.clear(); // Ensure this method clears both vinyls and users.

    // Add test Vinyl objects to the library.
    testLibrary.addVinyl(new Vinyl("Album One", "Artist One", 1999));
    testLibrary.addVinyl(new Vinyl("Album Two", "Artist Two", 2005));
    testLibrary.addVinyl(new Vinyl("Greatest Album That Ever Was", "A guy with a guitar", 2020));

    // Add test User objects to the library.
    testLibrary.addUser(new User("Alice"));
    testLibrary.addUser(new User("Bob"));
    testLibrary.addUser(new User("Bugs Bunny"));

    // Save the library data to XML files.
    // Using getVinylList() which returns the internal list.
    XMLStorage.saveVinylsToXML("test_vinyls.xml", testLibrary.getVinylList());
    XMLStorage.saveUsersToXML("test_users.xml", testLibrary.getUsers());

    // Load the data back from the XML files.
    List<Vinyl> loadedVinyls = XMLStorage.loadVinylsFromXML("test_vinyls.xml");
    List<User> loadedUsers = XMLStorage.loadUsersFromXML("test_users.xml");

    // Print out the loaded data to verify it was saved and loaded correctly.
    System.out.println("Loaded Vinyls:");
    if (loadedVinyls != null && !loadedVinyls.isEmpty()) {
      for (Vinyl v : loadedVinyls) {
        System.out.println(v);
      }
    } else {
      System.out.println("No Vinyls loaded.");
    }

    System.out.println("\nLoaded Users:");
    if (loadedUsers != null && !loadedUsers.isEmpty()) {
      for (User u : loadedUsers) {
        System.out.println(u);
      }
    } else {
      System.out.println("No Users loaded.");
    }
  }
}
