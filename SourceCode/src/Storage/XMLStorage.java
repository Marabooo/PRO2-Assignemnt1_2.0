package Storage;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.List;
import Model.*;

public class XMLStorage
{
  public static void saveToXML(String filename, List<Vinyl> vinyls)
    {
      try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename)))
      {
        encoder.writeObject(vinyls);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

  public static List<Vinyl> loadFromXML(String filename)
  {
    try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename)))
    {
      return (List<Vinyl>) decoder.readObject();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }
}
