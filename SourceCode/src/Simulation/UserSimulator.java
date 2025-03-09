package Simulation;

import Model.User;
import Model.Vinyl;
import Model.VinylLibrary;
import ViewModel.VinylViewModel;

import java.util.List;
import java.util.Random;

public class UserSimulator implements Runnable {
  private final VinylViewModel viewModel;
  private final VinylLibrary library;
  private final Random random = new Random();

  public UserSimulator(VinylViewModel viewModel, VinylLibrary library) {
    this.viewModel = viewModel;
    this.library = library;
  }

  @Override
  public void run() {
    while (true) {
      try {
        // Sleep between 2-5 seconds before performing an action
        Thread.sleep(2000 + random.nextInt(3000));
      } catch (InterruptedException e) {
        break; // exit if interrupted
      }

      List<Vinyl> vinyls = library.getVinylList();
      List<User> users = library.getUsers();

      if (vinyls.isEmpty() || users.isEmpty()) {
        continue; // skip if no data available
      }

      // Pick a random vinyl and user
      Vinyl vinyl = vinyls.get(random.nextInt(vinyls.size()));
      User user = users.get(random.nextInt(users.size()));

      // Choose a random action
      int action = random.nextInt(4); // 0 to 3
      switch (action) {
        case 0:
          // Simulate borrowing
          System.out.println("Simulated borrow: " + vinyl.getTitle() + " by " + user.getName());
          viewModel.borrowVinyl(vinyl, user);
          break;
        case 1:
          // Simulate returning
          System.out.println("Simulated return: " + vinyl.getTitle() + " by " + user.getName());
          viewModel.returnVinyl(vinyl);
          break;
        case 2:
          // Simulate reserving
          System.out.println("Simulated reserve: " + vinyl.getTitle() + " by " + user.getName());
          viewModel.reserveVinyl(vinyl, user);
          break;
        case 3:
          // Simulate unreserving
          System.out.println("Simulated cancelling reservation: " + vinyl.getTitle() + " by " + user.getName());
          viewModel.unreserveVinyl(vinyl, user);
          break;
        default:
          break;
      }
    }
  }
}
