package Test;

import Model.Vinyl;
import Model.User;
import Storage.XMLStorage;
import java.util.ArrayList;
import java.util.List;

public class TestXMLStorage {
  public static void main(String[] args) {
    // Create some test Vinyl objects
    List<Vinyl> vinyls = new ArrayList<>();
    vinyls.add(new Vinyl(1, "Album One", "Artist One", 1999));
    vinyls.add(new Vinyl(2, "Album Two", "Artist Two", 2005));

    // Create some test User objects
    List<User> users = new ArrayList<>();
    users.add(new User("Alice"));
    users.add(new User("Bob"));

    // Save the data to XML files
    XMLStorage.saveVinylsToXML("test_vinyls.xml", vinyls);
    XMLStorage.saveUsersToXML("test_users.xml", users);

    // Load the data from the XML files
    List<Vinyl> loadedVinyls = XMLStorage.loadVinylsFromXML("test_vinyls.xml");
    List<User> loadedUsers = XMLStorage.loadUsersFromXML("test_users.xml");

    // Print the data to verify they were saved and loaded correctly
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
