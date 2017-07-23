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


  private static final Logger logger = Logger.getLogger(Store.class.getName());
  private static final Handler consoleHandler = new ConsoleHandler();



  public static void main(String[] args)
      throws IOException, FileNotFoundException, ClassNotFoundException {

    // Initialize a FileHandler, a consoleHandler, and a logger to output
    // to log.txt
    final FileHandler fileHandler = new FileHandler("log.txt");
    logger.setLevel(Level.ALL);
    consoleHandler.setLevel(Level.ALL);
    logger.addHandler(fileHandler);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);

    // Ask the user for a file path for StoreItems.txt so that a Store can be
    // created with the file's items.
    BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter a file path: ");
    String fileName = kbd.readLine();
    Store s = new Store(fileName, logger);
    logger.info("A store has been created.");


    // Phase 1: Ask the user for a file path for Events.txt and processes all the
    // events in the file using the Store object s.
    BufferedReader kbd2 = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the file path for events.txt.");
    String eventsPath = kbd2.readLine();
    s.processEvents(eventsPath);

    }


}






