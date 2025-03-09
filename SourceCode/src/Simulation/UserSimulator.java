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

  public UserSimulator(VinylViewModel viewModel, VinylLibrary library)
  {
    this.viewModel = viewModel;
    this.library = library;
  }

  @Override
  public void run() {
    while (true) {
      try {
        // wait 1-3 seconds
        Thread.sleep(1000 + random.nextInt(2000));
      } catch (InterruptedException e) {
        break;
      }

      List<Vinyl> vinyls = library.getVinylList();
      List<User> users = library.getUsers();

      if (vinyls.isEmpty() || users.isEmpty()) {
        continue; // skip if no data
      }

      // Pick a random vinyl and user.
      Vinyl vinyl = vinyls.get(random.nextInt(vinyls.size()));
      User user = users.get(random.nextInt(users.size()));

      // Choose a random action.
      int action = random.nextInt(4); // 0 to 3
      switch (action) {
        case 0:
          // Simulate borrowing.
          System.out.println("Simulated borrow: " + vinyl.getTitle() + " by " + user.getName());
          try {
            viewModel.borrowVinyl(vinyl, user);
            System.out.println("Borrow action successful.");
          } catch (Exception e) {
            System.out.println("Borrow action failed: " + e.getMessage());
          }
          break;
        case 1:
          // Simulate returning.
          System.out.println("Simulated return: " + vinyl.getTitle() + " by " + user.getName());
          try {
            viewModel.returnVinyl(vinyl, user);
            System.out.println("Return action successful.");
          } catch (Exception e) {
            System.out.println("Return action failed: " + e.getMessage());
          }
          break;
        case 2:
          // Simulate reserving.
          System.out.println("Simulated reserve: " + vinyl.getTitle() + " by " + user.getName());
          try {
            viewModel.reserveVinyl(vinyl, user);
            System.out.println("Reserve action successful.");
          } catch (Exception e) {
            System.out.println("Reserve action failed: " + e.getMessage());
          }
          break;
        case 3:
          // Simulate unreserving.
          System.out.println("Simulated cancelling reservation: " + vinyl.getTitle() + " by " + user.getName());
          try {
            viewModel.unreserveVinyl(vinyl, user);
            System.out.println("Unreserve action successful.");
          } catch (Exception e) {
            System.out.println("Unreserve action failed: " + e.getMessage());
          }
          break;
        default:
          break;
      }
    }
  }
}

