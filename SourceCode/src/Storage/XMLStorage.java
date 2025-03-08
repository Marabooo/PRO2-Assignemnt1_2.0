package Storage;

import Model.*;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.List;

public class XMLStorage {

  // Save a list of Vinyl objects to an XML file.
  public static void saveVinylsToXML(String filename, List<Vinyl> vinyls) {
    try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename))) {
      encoder.writeObject(vinyls);
      encoder.flush();
      System.out.println("Vinyls saved successfully to " + filename);
    } catch (Exception e) {
      System.err.println("Error saving vinyls: " + e.getMessage());
      e.printStackTrace();
    }
  }

  // Load a list of Vinyl objects from an XML file.
  public static List<Vinyl> loadVinylsFromXML(String filename) {
    try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename))) {
      @SuppressWarnings("unchecked")
      List<Vinyl> vinyls = (List<Vinyl>) decoder.readObject();
      System.out.println("Vinyls loaded successfully from " + filename);
      return vinyls;
    } catch (Exception e) {
      System.err.println("Error loading vinyls: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  // Save a list of User objects to an XML file.
  public static void saveUsersToXML(String filename, List<User> users) {
    try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename))) {
      encoder.writeObject(users);
      encoder.flush();
      System.out.println("Users saved successfully to " + filename);
    } catch (Exception e) {
      System.err.println("Error saving users: " + e.getMessage());
      e.printStackTrace();
    }
  }

  // Load a list of User objects from an XML file.
  public static List<User> loadUsersFromXML(String filename) {
    try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename))) {
      @SuppressWarnings("unchecked")
      List<User> users = (List<User>) decoder.readObject();
      System.out.println("Users loaded successfully from " + filename);
      return users;
    } catch (Exception e) {
      System.err.println("Error loading users: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
}
