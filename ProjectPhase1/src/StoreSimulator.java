import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 * A class that simulates a store.
 */
class StoreSimulator implements Serializable {

  private static final Logger logger = Logger.getLogger(Store.class.getName());
  private static final Handler consoleHandler = new ConsoleHandler();
  private Store s;
  private String filename;


  Store StoreSimulator()
      throws IOException, ClassNotFoundException {
    filename = "store.ser";

    // If the store is being built for the first time
    FileInputStream fis;
    ObjectInputStream in;

    try {
      fis = new FileInputStream(filename);
      in = new ObjectInputStream(fis);
      s = (Store) in.readObject();
      in.close();
      Store.logger = logger;

    } catch (FileNotFoundException ex) {
      String path = getPath();
      s = initialStoreCreation(path);
    }


    final FileHandler fileHandler = new FileHandler("log.txt", true);
    logger.setLevel(Level.ALL);
    consoleHandler.setLevel(Level.ALL);
    logger.addHandler(fileHandler);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);

    /*
    // Phase 1: Ask the user for a file path for Events.txt and processes all the
    // events in the file using the Store object s.

    BufferedReader kbd2 = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the file path for events.txt.");
    String eventsPath = kbd2.readLine();
    s.processEvents(eventsPath);
    */

/*
    // Phase 2: Make the user input a line and return something.
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please enter a command in proper format, as stated in events codes.");
    String input = userInput.readLine();
    while (!input.equals("exit")) {
      s.processEvent(input);
      input = userInput.readLine();
    }
*/

    return s;
  }


  void saveStore(){
    // Save the store to store.ser
    FileOutputStream fos;
    ObjectOutputStream out;
    try {
      fos = new FileOutputStream(filename);
      out = new ObjectOutputStream(fos);
      out.writeObject(s);
      out.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  String getPath(){
    return JOptionPane.showInputDialog("This is the first time you're initializing a store. "
        + "Please enter the path to storeItems.txt.");

  }


  private static Store initialStoreCreation(String path) throws IOException, ClassNotFoundException {

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

    Store s = new Store(path, logger);
    logger.info("A new store has been created.");
    return s;
  }



}






