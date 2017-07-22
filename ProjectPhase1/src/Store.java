import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.xml.crypto.Data;


/**
 * The Store class.
 *
 * Implement close() method that closes the daily values and puts them into storage.
 */
public class Store {

  protected String name;
  protected TimeManager tm;
  protected ArrayList<Item> dataList = new ArrayList<>();
  protected ArrayList<Item> itemsList = new ArrayList<>();
  protected ArrayList<Item> unshelvedItemsList = new ArrayList<>();
  protected ArrayList<String> pendingOrders = new ArrayList<>();
  protected Map<String, Item> items;
  protected static final Logger logger = Logger.getLogger(Store.class.getName());
  protected static final Handler consoleHandler = new ConsoleHandler();
  final FileHandler fileHandler = new FileHandler("log.txt");




  protected Store(String DataFileName) throws ClassNotFoundException, IOException {
    // Store constructor.
    items = new HashMap<String, Item>();
    logger.setLevel(Level.ALL);
    consoleHandler.setLevel(Level.ALL);
    logger.addHandler(fileHandler);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);



    File data = new File(DataFileName);
    if (data.exists()) {
      processData(DataFileName);
      System.out.println("a store has been created.");
    } else {
      data.createNewFile();
    }
  }

  protected Item getItem(String UPC) {  // IE GET THE ITEM FROM THE UPC
    for (Item i : itemsList) {
      if (i.UPC.equals(UPC)) {
        return i;
      }
    }
    return null;
  }

  protected void processData(String fileName) throws IOException {

    try {
      BufferedReader fileInput = new BufferedReader(new FileReader(fileName));
      String line = fileInput.readLine();

      while (line != null) {
        String[] data = line.split("\\,");
        Item temp = new Item(data[0], data[1], data[2], data[3],
            Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]),
            Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]);
        dataList.add(temp);
        line = fileInput.readLine();
        logger.info( "I have added " + data[1] + " to the store");

      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot read from input.", e);
    }
  }

  //TODO: for when the program closes; new itemslist file.
/*
  protected void SaveData(String fileName) {

  }

  public void showInventory() {
    for (Item i : dataList) {
      System.out.println(i);
    }
  }
  */
}