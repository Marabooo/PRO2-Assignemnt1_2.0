package Model;
import States.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VinylList implements Serializable
{
  @Serial private static final long serialVersionUID = 1L;
  private List<Vinyl> vinyls;

  public VinylList()
  {
    this.vinyls = new ArrayList<Vinyl>();
  }

  public void addVinyl(Vinyl vinyl)
  {
    if (vinyl == null)
      {
        throw new IllegalArgumentException("Vinyl cannot be null");
      }
    this.vinyls.add(vinyl);
  }
  public void removeVinyl(Vinyl vinyl)
  {
    if (!vinyl.isMarkedForRemoval())
    {
      throw new IllegalArgumentException("Vinyl is not marked for removal");
    }
    this.vinyls.remove(vinyl);
  }
  public List<Vinyl> getVinylList()
  {
    return this.vinyls;
  }

  @Override
  public String toString()
  {
    StringBuilder s = new StringBuilder();
    for (Vinyl vinyl : vinyls)
    {
      s.append(vinyl.toString());
      s.append("\n");
    }
    return s.toString();
  }

}
