import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Store class.
 *
 * Implement close() method that closes the daily values and puts them into storage.
 */
public class Store implements Serializable {

  private OrderManager om = new OrderManager(this);
  private SaleManager sm = new SaleManager(this);
  private FinancialManager fm = new FinancialManager(this, sm);
  private ItemScanner is = new ItemScanner(this, om, fm);
  private ItemManager im = new ItemManager(this);
  private TimeManager tm = new TimeManager();
  private ArrayList<Item> itemsList = new ArrayList<>();
  private ArrayList<String> dailyProfits = new ArrayList<>();
  private ArrayList<String> pendingOrders = new ArrayList<>();  // Will be updated in Phase 2
  static Logger logger;
  private HashMap<String, Item> UPCToItem = new HashMap<String, Item>();

  Store(String DataFileName, Logger logger) throws ClassNotFoundException, IOException {
    // Store constructor.

    this.logger = logger;

    File data = new File(DataFileName);
    if (data.exists()) {
      processData(DataFileName);
    } else {
      data.createNewFile();
    }

  }

  /**
   * This method gets the Item for the specified UPC.
   *
   * @param UPC The UPC of the Item we want ot get
   * @return The Item with the specified UPC
   */
  Item getItem(String UPC) {
    return UPCToItem.get(UPC);
  }

  // A method that is called during the initializing of the store.
  // Takes StoreItems.txt and converts every line into an Item.
  private void processData(String fileName) throws IOException {

    try {
      BufferedReader fileInput = new BufferedReader(new FileReader(fileName));
      String line = fileInput.readLine();

      while (line != null) {
        String[] data = line.split(",");
        Item temp = new Item(data[0], data[1], data[2], data[3],
            Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]),
            Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]);
        itemsList.add(temp);
        UPCToItem.put(temp.UPC, temp);
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
  public void processEvent(String instruction) {

    ArrayList<String> lineList = new ArrayList<>(Arrays.asList(instruction.split(",")));

    switch (lineList.get(0)) {
      // 0 closes out the daily values and maps them to daily revenues and daily profits.
      case "0":
        logger.info("Session saved.");
        closeDailyTotals();
        break;

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

      case "8":
        logger.info(Boolean.toString(getItem(lineList.get(1)).saleStatus));
        return;

      case "9":
        logger.info(Double.toString(getItem(lineList.get(1)).salePrice));
        return;

      case "10":
        logger.info(getItem(lineList.get(1)).saleStart);
        return;

      case "11":
        logger.info(getItem(lineList.get(1)).saleEnd);
        return;

      case "12":
        logger.info(getItem(lineList.get(1)).supplier);
        return;

      case "13":
        logger.info(Integer.toString(getItem(lineList.get(1)).soldToday));
        return;

      case "14":
        logger.info(Double.toString(getItem(lineList.get(1)).revenueToday));
        return;

      case "15":
        logger.info(Double.toString(getItem(lineList.get(1)).profitToday));
        return;

      case "16":
        logger.info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).orderHistory));
        return;

      case "17":
        logger.info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).pendingOrders));
        return;

      case "18":
        logger.info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).salesHistory));
        return;

      case "19":
        logger.info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).priceHistory));
        return;

      case "20":
        logger.info(is.getLocation(lineList.get(1)));
        return;

      case "21":
        im.setSection(lineList.get(1), lineList.get(2));
        logger.info(
            "I have set " + getItem(lineList.get(1)).name + " to section: " + lineList.get(2));
        return;

      case "22":
        im.setSubsection(lineList.get(1), lineList.get(2));
        logger.info(
            "I have set " + getItem(lineList.get(1)).name + " to subsection: " + lineList.get(2));
        return;

      case "23":
        im.setAisle(lineList.get(1), Integer.parseInt(lineList.get(2)));
        logger.info(
            "I have set " + getItem(lineList.get(1)).name + " to aisle number: " + lineList.get(2));
        return;

      case "24":
        im.setBoughtPrice(lineList.get(1), Double.parseDouble(lineList.get(2)));
        logger.info(
            "I have set the bought price of " + getItem(lineList.get(1)).name + " to: " + lineList
                .get(2));
        return;

      case "25":
        im.setSellPrice(lineList.get(1), Double.parseDouble(lineList.get(2)));
        logger.info(
            "I have set the sell price of " + getItem(lineList.get(1)).name + " to: " + lineList
                .get(2));
        return;

      case "26":
        im.setQuantity(lineList.get(1), Integer.parseInt(lineList.get(2)));
        logger.info(
            "I have set the quantity of " + getItem(lineList.get(1)).name + " to: " + lineList
                .get(2));
        return;

      case "27":
        im.setThreshold(lineList.get(1), Integer.parseInt(lineList.get(2)));
        logger.info(
            "I have set the threshold of " + getItem(lineList.get(1)).name + " to: " + lineList
                .get(2));
        return;

      case "28":
        is.scanIn(lineList.get(1), Integer.parseInt(lineList.get(2)));
        logger.info(
            "I have scanned in" + lineList.get(2) + getItem(lineList.get(1)).name + "to the store");
        return;

      case "29":
        is.sell(lineList.get(1));
        logger.info(getItem(lineList.get(1)).name + "is sold");
        return;

      case "30":
        is.returnItem(lineList.get(1), Integer.parseInt(lineList.get(2)));
        logger
            .info(lineList.get(2) + getItem(lineList.get(1)).name + "have been returned to store");
        return;

      case "31":
        om.setSupplier(lineList.get(1), lineList.get(2));
        logger.info(
            "I have set " + getItem(lineList.get(1)).name + " to supplier: " + lineList.get(2));
        return;

      case "32":
        om.customOrder(lineList.get(1), Integer.parseInt(lineList.get(2)));
        logger.info(
            lineList.get(2) + " units of " + getItem(lineList.get(1)).name + " have been ordered ");
        return;

      case "33":
        om.cancelPendingOrder(lineList.get(1), Integer.parseInt(lineList.get(2)));
        logger.info(lineList.get(2) + " units of " + getItem(lineList.get(1)).name
            + " have been canceled ");
        return;

      case "34":
        logger.info(om.viewPendingOrders(lineList.get(1)));
        return;

      case "35":
        logger.info(Boolean.toString(sm.checkSale(lineList.get(1))));
        return;

      case "36":
        sm.setSalePrice(lineList.get(1), Double.parseDouble(lineList.get(2)));
        logger.info(
            "I have set the sale price of " + getItem(lineList.get(1)).name + " to: " + lineList
                .get(2));
        return;

      case "37":
        sm.addSale(lineList.get(1), lineList.get(2), lineList.get(3),
            Double.parseDouble(lineList.get(4)));
        logger.info(
            "I have set the " + getItem(lineList.get(1)).name + " to be on sale from " + lineList
                .get(2) + " to" + lineList.get(3) +
                " with sale price:" + lineList.get(4) + " on " + tm.timeStamp());
        return;

      case "38":
        sm.removeSale(lineList.get(1), lineList.get(2), lineList.get(3));
        logger.info(
            "The sale for " + getItem(lineList.get(1)).name + " from " + lineList.get(2) + " to "
                + lineList.get(3) + " has been removed on"
                + tm.timeStamp());
        return;

      case "39":
        logger.info("Sale duration is" + sm.getSaleDuration(lineList.get(1)));
        return;

      case "40":
        sm.setSaleStatusOff(lineList.get(1));
        logger.info(getItem(lineList.get(1)).name + "is no longer on sale on " + tm.timeStamp());

      case "41":
        logger.info(getItem(lineList.get(1)).name + " is on aisle " +
            Integer.toString(getItem(lineList.get(1)).aisle));
        return;

      case "42":
        logger.info(getItem(lineList.get(1)).name + " belongs to section " +
            getItem(lineList.get(1)).section);
        return;

      case "43":
        logger.info(getItem(lineList.get(1)).name + " belongs to subsection " +
            getItem(lineList.get(1)).subsection);
        return;

      case "44":
        logger.info(Double.toString(is.checkInStock(lineList.get(1))));
        return;

      case "45":
        logger.info(this.getItemsList());
        return;

      case "46":
        logger.info(this.getUnshelvedItemsList());
        return;

      case "47":
        logger.info(this.getPendingOrders());
        return;

      case "48":
        logger.info(this.getDailyProfits());
        return;

      case "49":
        logger.info("Employee" + lineList.get(1) + " has signed in.");
        return;

      case "50":
        logger.info("Employee" + lineList.get(1) + " has signed out.");
        return;

      default:
        logger.info(" Error: unrecognized command.");
        break;

    }
  }

  /**
   * This method sums up the revenues and profits for each Item in the store.
   * It adds them up, and creates an entry in dailyProfits. Once this is done,
   * it sets the revenueToday and profitToday attributes for each item back to 0.0
   */
  private void closeDailyTotals() {
    String date = tm.toString();
    Double revenue = 0.0;
    Double profit = 0.0;

    for (Item i : itemsList) {
      revenue += i.revenueToday;
      profit += i.profitToday;
      i.revenueToday = 0.0;
      i.profitToday = 0.0;
    }
    String entry = "Revenue: " + revenue + ", Profit: " + profit;
    dailyProfits.add(date + " " + entry);
    logger.info("All of the daily revenues and profits have been closed out.");
  }

  /**
   * This method returns a formatted list of all the Items in Store.
   *
   * @return A formatted String of all the items.
   */
  private String getItemsList() {
    StringBuilder sb = new StringBuilder();
    for (Item i : itemsList) {
      sb.append(i.toString());
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  /**
   * This method returns a formatted list of all the Items with unshelvedQuantity
   * in Store.
   *
   * @return A formatted String of all the unshelved Items.
   */
  private String getUnshelvedItemsList() {
    StringBuilder sb = new StringBuilder();
    for (Item i : itemsList) {
      if (i.unshelvedQuantity) {
        sb.append(i.toString());
        sb.append(System.lineSeparator());
      }
    }
    return sb.toString();
  }

  /**
   * This method returns a formatted list of all the Items in
   * Store that have pendingOrders.
   *
   * @return A formatted String of all Items in pendingOrders.
   */
  private String getPendingOrders() {
    StringBuilder sb = new StringBuilder();
    for (String s : pendingOrders) {
      sb.append(s);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  /**
   * This method returns a formatted list of the dailyProfits history of the store,
   * by date.
   *
   * @return A formatted String that lists all the dailyProfits in the Store's history.
   */
  private String getDailyProfits() {
    StringBuilder sb = new StringBuilder();
    for (String s : dailyProfits) {
      sb.append(s);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }
}