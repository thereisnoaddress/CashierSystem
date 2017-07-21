import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class that simulates a store.
 */
public class StoreSimulator {

  public static void main(String[] args)
      throws IOException, FileNotFoundException, ClassNotFoundException {
    BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter a file path: ");
    String fileName = kbd.readLine();
    Store s = new Store(fileName);

    System.out.println("What would you like to do? Type \'exit\' to exit.");
    String input = kbd.readLine();
    while (!(input.equals("exit"))) {
      if (input.equals("UPC")) {
        System.out.println("Please enter the UPC:");
          String k = kbd.readLine();
        while (!(k.equals("exit"))) {
          System.out.println(s.UPCLookup(k));
          System.out.println("Please enter the UPC:");
          k = kbd.readLine();
        }
      } else if (input.equals("add")) {
        System.out.println("Please enter the UPC and the quantity. For example, "
            + "10000,2");
        String k = kbd.readLine();
        while (!(k.equals("exit"))){
          String[] thingToAdd = k.split("\\,");
          System.out.println(
              s.addInventory(thingToAdd[0], Double.parseDouble(thingToAdd[1])));
          System.out.println("Please enter the UPC and the quantity. For example, "
              + "10000,2");
          k = kbd.readLine();
        }

      }
    }
  }
}



