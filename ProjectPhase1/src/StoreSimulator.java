import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A class that simulates a store.
 */
public class StoreSimulator {

  private static final Logger logger = Logger.getLogger(StoreSimulator.class.getName());
  private static final Handler consoleHandler = new ConsoleHandler();


  public static void main(String[] args)
      throws IOException, FileNotFoundException, ClassNotFoundException {
    
    BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter a file path: ");
    String fileName = kbd.readLine();
    Store s = new Store(fileName);
    logger.info("a store has been created hoho");

  }
}






