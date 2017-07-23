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
import java.util.Arrays;
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
  protected ArrayList<Item> itemsList = new ArrayList<>();
  protected ArrayList<Item> unshelvedItemsList = new ArrayList<>();
  protected ArrayList<String> pendingOrders = new ArrayList<>();
  protected Map<String, Item> items;
  protected Map<String, String> dailyProfits;
  protected Logger logger;

  protected TimeManager tm = new TimeManager();
  protected ItemManager im = new ItemManager(this);
  protected OrderManager om = new OrderManager(this);
  protected SaleManager sm = new SaleManager(this);
  protected FinancialManager fm = new FinancialManager(this, sm);
  protected ItemScanner is = new ItemScanner(this, om, fm);

  // Store constructor.
  Store(String DataFileName, Logger logger) throws ClassNotFoundException, IOException {

    this.logger = logger;

    File data = new File(DataFileName);
    if (data.exists()) {
      processData(DataFileName);
    } else {
      data.createNewFile();
    }

  }

  // A method that is called during the initializing of the store.
  // Takes StoreItems.txt and converts every line into an Item.

  private void processData(String fileName) throws IOException {

    try {
      BufferedReader fileInput = new BufferedReader(new FileReader(fileName));
      String line = fileInput.readLine();

      while (line != null) {
        String[] data = line.split("\\,");
        Item temp = new Item(data[0], data[1], data[2], data[3],
            Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]),
            Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]);
        itemsList.add(temp);
        line = fileInput.readLine();
        logger.info(data[1] + " has been added to the store.");
      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot read from input.", e);
    }
  }

  // A method that takes the entire Events.txt file and calls it line by line,
  // sending every line to processEvent.
  void processEvents(String eventsFileName) throws IOException {

    try {
      BufferedReader events = new BufferedReader(new FileReader(eventsFileName));
      String line = events.readLine();
      while (line != null) {
        processEvent(line);
        line = events.readLine();
      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot read from input.", e);
    }
    }


  // A method that reads every line of Events.txt and processes the user's command.
  private void processEvent(String instruction) {

    ArrayList<String> lineList = new ArrayList<String>(Arrays.asList(instruction.split("\\,")));

    switch (lineList.get(0)) {

      case "1":
        logger.info(getItem(lineList.get(1)).name);
        return;

      case "2":
        logger.info(getItem(lineList.get(1)).toString());
        return;

      case "3":
        logger.info(Double.toString(getItem(lineList.get(1)).boughtPrice));
        return;

      case "4":
        logger.info(Double.toString(getItem(lineList.get(1)).sellPrice));
        return;

      case "5":
        logger.info(Boolean.toString(getItem(lineList.get(1)).unshelvedQuantity));
        return;

      case "6":
        logger.info(Integer.toString(getItem(lineList.get(1)).threshold));
        return;

      case "7":
        logger.info(Integer.toString(getItem(lineList.get(1)).orderSize));
        return;
    }
  }

  // Reimplement using the map
  Item getItem(String UPC) {
    for (Item i : itemsList) {
      if (i.UPC.equals(UPC)) {
        return i;
      }
    }
    return null;
  }

  // This takes all the daily profits, takes them to zero
  protected void close() {
    String date = tm.toString();
    Double revenue = 0.0;
    Double profit = 0.0;

    for (Item i : itemsList) {
      revenue += i.revenueToday;
      profit += i.profitToday;
      i.revenueToday = 0;
      i.profitToday = 0;
    }
    String entry = "Revenue: " + revenue + ", Profit: " + profit;
    dailyProfits.put(date, entry);
  }
}