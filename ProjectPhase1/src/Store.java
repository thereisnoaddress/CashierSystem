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
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.crypto.Data;


/**
 * The Store class.
 *
 * We should also implement a dictionary where the UPC is the code.
 * This can be used for scanning. The ArrayList can be used for other methods where we cant use a dictionary
 */
public class Store {

  private ArrayList<ConsolidatedItem> dataList = new ArrayList<>();
  private ArrayList<ConsolidatedItem> itemsList = new ArrayList<>();
  private ArrayList<ConsolidatedItem> unshelvedItemsList = new ArrayList<>();
  private Map<String, ConsolidatedItem> items;
  private static final Logger logger = Logger.getLogger(Store.class.getName());
  private static final Handler consoleHandler = new ConsoleHandler();


  protected Store(String DataFileName) throws ClassNotFoundException, IOException {
    // Store constructor.
    items = new HashMap<String, ConsolidatedItem>();

    logger.setLevel(Level.ALL);
    consoleHandler.setLevel(Level.ALL);
    logger.addHandler(consoleHandler);

    File data = new File(DataFileName);
    if (data.exists()) {
      processData(DataFileName);
      System.out.println("a store has been created.");
    } else {
      data.createNewFile();
    }
  }


  public String addInventory(String UPC, double quantity) {

    // Check if UPC corresponds to an existing item in itemsList
    // If so, then add quantity to it.

    if (getConsolidatedItem(UPC) != null) {
      getConsolidatedItem(UPC).addQuantity(quantity);
      return "I have added " + quantity + " of " + getConsolidatedItem(UPC).getName();

    }

    // If UPC is not found in itemsList, create a new item and add it to the list.
    //
    else if (UPCLookup(UPC) != null) {
      String[] data = UPCLookup(UPC).split("\\,");
      ConsolidatedItem ci = new ConsolidatedItem(data[0], data[1], data[2], data[3],
          Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]),
          Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]);
      itemsList.add(ci);
      return "I have added " + quantity + " of " + ci.getName();
    }
    throw new NoSuchElementException("I couldn't find this!");
  }


  private void sell(String UPC, int quantity) throws NoSuchElementException {
    if (getConsolidatedItem(UPC) != null) {
      getConsolidatedItem(UPC).sell(quantity);
    } else {
      throw new NoSuchElementException("There is no item with UPC " + UPC);
    }
  }

  private boolean checkInStock(String UPC) {
    return getConsolidatedItem(UPC) != null;
  }

  private double getQuantity(String UPC) {
    return getConsolidatedItem(UPC) != null ? getConsolidatedItem(UPC).getQuantity() : 0;
  }

  private double getSellPrice(String UPC) {
    return getConsolidatedItem(UPC) != null ? getConsolidatedItem(UPC).getSellPrice() : null;
  }

  // Returns the ConsolidatedItem if it exists in itemsList, otherwise null.
  public ConsolidatedItem getConsolidatedItem(String UPC) {
    for (ConsolidatedItem ci : itemsList) {
      if (ci.getUPC().equals(UPC)) {
        return ci;
      }
    }
    return null;
  }


  // Return a string in the following format:
  // [UPC, name, section, subsection, aisle]
  public String UPCLookup(String UPC) {
    for (ConsolidatedItem ci : dataList) {
      if (ci.getUPC().equals(UPC)) {
        return (ci.toString());
      }
    }
    return null;
  }


  // This will go into the initial main method
  private void processData(String fileName) throws IOException {

    try {
      BufferedReader fileInput = new BufferedReader(new FileReader(fileName));
      String line = fileInput.readLine();

      while (line != null) {
        String[] data = line.split("\\,");
        ConsolidatedItem temp = new ConsolidatedItem(data[0], data[1], data[2], data[3],
            Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]),
            Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]);
        dataList.add(temp);
        line = fileInput.readLine();

      }
    }catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot read from input.", e);
    }
  }

  //TODO: for when the program closes; new itemslist file.
  private void SaveData(String fileName) {

  }

  public void showData() {
    for (ConsolidatedItem i : dataList) {
      System.out.println(i);
    }
  }


  private int thresholdLookup(String UPC) {
    if (getConsolidatedItem(UPC) != null) {
      return getConsolidatedItem(UPC).getThreshold();
    }
    return 0;
  }

}