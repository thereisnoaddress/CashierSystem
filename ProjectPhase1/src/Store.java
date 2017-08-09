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
 */
class Store implements Serializable {

  OrderManager om;
  SaleManager sm;
  FinancialManager fm;
  ItemScanner is;
  ItemManager im;
  TimeManager tm ;
  StoreManager stm;
  UserManager um;
  ArrayList<Item> itemsList = new ArrayList<>();
  ArrayList<String> dailyProfits = new ArrayList<>();
  ArrayList<String> pendingOrders = new ArrayList<>();
  static Logger logger;
  HashMap<String, Item> UPCToItem = new HashMap<String, Item>();

  Store(String DataFileName, Logger logger) throws ClassNotFoundException, IOException {
    om = new OrderManager(this);
    sm = new SaleManager(this);
    fm = new FinancialManager(this, sm);
    is = new ItemScanner(this, om, fm);
    im = new ItemManager(this);
    tm = new TimeManager();
    um = new UserManager();

    Store.logger = logger;

    //
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
  Item getItem(String UPC) throws NullPointerException {

    if (UPCToItem.containsKey(UPC)) {
      return UPCToItem.get(UPC);
    }
    return null;
  }

  /**
   * This method is called during the initialization of the store. It takes
   * StoreItems.txt and converts every line into an Item
   *
   * @param fileName      A String of the file that contains the items list
   * @throws IOException  Throws an exception if file not found
   */
  private void processData(String fileName) throws IOException {

    try {
      BufferedReader fileInput = new BufferedReader(new FileReader(fileName));
      String line = fileInput.readLine();

      while (line != null) {
        // Divide this up into different constructors.
        Item i = makeNewItem(line);

        line = fileInput.readLine();
        logger.info(i.getName() + " has been added to the store.");
      }
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Cannot read from input.", e);
    }
  }

  /**
   * This method takes a string of the specified format from help.txt, and then parses
   * it to create a new Item object.
   *
   * @param itemInfo  A String of the format specified in help.txt
   * @return          A new Item
   */
  Item makeNewItem(String itemInfo){
    String[] data = itemInfo.split(",");

    // Divide this up into different constructors.
    Item temp = new Item(new ItemInventory(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]), new ItemAccounting(Double.parseDouble(data[5]), Double.parseDouble(data[6])), new ItemHistory());
    itemsList.add(temp);
    UPCToItem.put(temp.iv.UPC, temp);
    return temp;
  }

  // A method that takes the entire Events.txt file and calls it line by line,
  // sending every line to processEvent.

  /**
   * This method takes the events.txt and calls it line-by-line. It then executes
   * each line by sending it to processEvent()
   *
   * @param eventsFileName``A string path for events.txt
   * @throws IOException    Throws an exception if filepath is incorrect
   */
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

  /**
   * This method reads the command from Events.txt, and then executes the relevant case.
   *
   * @param instruction             The instruction that is to be executed
   * @throws NullPointerException   This occurs if the specified instruction does not exist
   */
  void processEvent(String instruction) throws NullPointerException {

    ArrayList<String> lineList = new ArrayList<>(Arrays.asList(instruction.split(",")));
    try {
      switch (lineList.get(0)) {
        // 0 closes out the daily values and maps them to daily revenues and daily profits.
        case "0":
          logger.info("Session saved.");
          stm.closeDailyTotals();
          break;

        case "1":
          logger.info(getItem(lineList.get(1)).getName());
          return;

        case "2":
          logger.info(getItem(lineList.get(1)).toString());
          return;

        case "3":
          logger.info(Double.toString(getItem(lineList.get(1)).getBoughtPrice()));
          return;

        case "4":
          logger.info(Double.toString(getItem(lineList.get(1)).getSellPrice()));
          return;

        case "5":
          logger.info(Boolean.toString(getItem(lineList.get(1)).getUnshelvedQuantity()));
          return;

        case "6":
          logger.info(Integer.toString(getItem(lineList.get(1)).getThreshold()));
          return;

        case "7":
          logger.info(Integer.toString(getItem(lineList.get(1)).getOrderSize()));
          return;

        case "8":
          logger.info(Boolean.toString(getItem(lineList.get(1)).ia.saleStatus));
          return;

        case "9":
          logger.info(Double.toString(getItem(lineList.get(1)).ia.salePrice));
          return;

        case "10":
          logger.info(getItem(lineList.get(1)).ia.saleStart);
          return;

        case "11":
          logger.info(getItem(lineList.get(1)).ia.saleEnd);
          return;

        case "12":
          logger.info(getItem(lineList.get(1)).getSupplier());
          return;

        case "13":
          logger.info(Integer.toString(getItem(lineList.get(1)).ia.soldToday));
          return;

        case "14":
          logger.info(Double.toString(getItem(lineList.get(1)).ia.revenueToday));
          return;

        case "15":
          logger.info(Double.toString(getItem(lineList.get(1)).ia.profitToday));
          return;

        case "16":
          logger.info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).ih.orderHistory));
          return;

        case "17":
          logger
              .info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).ih.pendingOrders));
          return;

        case "18":
          logger.info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).ih.salesHistory));
          return;

        case "19":
          logger.info(getItem(lineList.get(1)).infoToString(getItem(lineList.get(1)).ih.priceHistory));
          return;

        case "20":
          logger.info(is.getLocation(lineList.get(1)));
          return;

        case "21":
          im.setSection(lineList.get(1), lineList.get(2));
          logger.info(
              "I have set " + getItem(lineList.get(1)).getName() + " to section: " + lineList.get(2));
          return;

        case "22":
          im.setSubsection(lineList.get(1), lineList.get(2));
          logger.info(
              "I have set " + getItem(lineList.get(1)).getName() + " to subsection: " + lineList.get(2));
          return;

        case "23":
          im.setAisle(lineList.get(1), Integer.parseInt(lineList.get(2)));
          logger.info(
              "I have set " + getItem(lineList.get(1)).getName() + " to aisle number: " + lineList
                  .get(2));
          return;

        case "24":
          im.setBoughtPrice(lineList.get(1), Double.parseDouble(lineList.get(2)));
          logger.info(
              "I have set the bought price of " + getItem(lineList.get(1)).getName() + " to: " + lineList
                  .get(2));
          return;

        case "25":
          im.setSellPrice(lineList.get(1), Double.parseDouble(lineList.get(2)));
          logger.info(
              "I have set the sell price of " + getItem(lineList.get(1)).getName() + " to: " + lineList
                  .get(2));
          return;

        case "26":
          im.setQuantity(lineList.get(1), Integer.parseInt(lineList.get(2)));
          logger.info(
              "I have set the quantity of " + getItem(lineList.get(1)).getName() + " to: " + lineList
                  .get(2));
          return;

        case "27":
          im.setThreshold(lineList.get(1), Integer.parseInt(lineList.get(2)));
          logger.info(
              "I have set the threshold of " + getItem(lineList.get(1)).getName() + " to: " + lineList
                  .get(2));
          return;

        case "28":
          is.scanIn(lineList.get(1), Integer.parseInt(lineList.get(2)));
          logger.info(
              "I have scanned in" + lineList.get(2) + getItem(lineList.get(1)).getName()
                  + "to the store");
          return;

        case "29":
          is.sell(lineList.get(1));
          logger.info(getItem(lineList.get(1)).getName() + "is sold");
          return;

        case "30":
          is.returnItem(lineList.get(1), Integer.parseInt(lineList.get(2)));
          logger
              .info(
                  lineList.get(2) + getItem(lineList.get(1)).getName() + "have been returned to store");
          return;

        case "31":
          getItem(lineList.get(1)).setSupplier(lineList.get(2));
          logger.info(
              "I have set " + getItem(lineList.get(1)).getName() + " to supplier: " + lineList.get(2));
          return;

        case "32":
          om.customOrder(lineList.get(1), Integer.parseInt(lineList.get(2)));
          logger.info(
              lineList.get(2) + " units of " + getItem(lineList.get(1)).getName()
                  + " have been ordered ");
          return;

        case "33":
          om.cancelPendingOrder(lineList.get(1), Integer.parseInt(lineList.get(2)));
          logger.info(lineList.get(2) + " units of " + getItem(lineList.get(1)).getName()
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
              "I have set the sale price of " + getItem(lineList.get(1)).getName() + " to: " + lineList
                  .get(2));
          return;

        case "37":
          sm.addSale(lineList.get(1), lineList.get(2), lineList.get(3),
              Double.parseDouble(lineList.get(4)));
          logger.info(
              "I have set the " + getItem(lineList.get(1)).getName() + " to be on sale from " + lineList
                  .get(2) + " to" + lineList.get(3) +
                  " with sale price:" + lineList.get(4) + " on " + tm.timeStamp());
          return;

        case "38":
          sm.removeSale(lineList.get(1), lineList.get(2), lineList.get(3));
          logger.info(
              "The sale for " + getItem(lineList.get(1)).getName() + " from " + lineList.get(2) + " to "
                  + lineList.get(3) + " has been removed on"
                  + tm.timeStamp());
          return;

        case "39":
          logger.info("Sale duration is " + sm.getSaleDuration(lineList.get(1)));
          return;

        case "40":
          sm.setSaleStatusOff(lineList.get(1));
          logger.info(getItem(lineList.get(1)).getName() + "is no longer on sale on " + tm.timeStamp());

        case "41":
          logger.info(getItem(lineList.get(1)).getName() + " is on aisle " +
              Integer.toString(getItem(lineList.get(1)).getAisle()));
          return;

        case "42":
          logger.info(getItem(lineList.get(1)).getName() + " belongs to section " +
              getItem(lineList.get(1)).getSection());
          return;

        case "43":
          logger.info(getItem(lineList.get(1)).getName() + " belongs to subsection " +
              getItem(lineList.get(1)).getSubsection());
          return;

        case "44":
          logger.info(Double.toString(is.checkInStock(lineList.get(1))));
          return;

        case "45":
          logger.info(stm.getItemsList());
          return;

        case "46":
          logger.info(stm.getUnshelvedItemsList());
          return;

        case "47":
          logger.info(stm.getPendingOrders());
          return;

        case "48":
          logger.info(stm.getDailyProfits());
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
    } catch (NullPointerException e) {
      System.out.println("Item " + lineList.get(1) + " does not exist!");
      System.out.println("Please enter a command in proper format, as stated in events codes.");
    }
  }
}