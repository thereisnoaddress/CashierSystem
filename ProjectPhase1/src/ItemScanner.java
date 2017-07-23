import java.io.Serializable;

/**
 * Scans stuff
 */
class ItemScanner implements Serializable {
  protected Store s;
  private OrderManager om;
  private FinancialManager fm;
  private TimeManager tm = new TimeManager();

  protected ItemScanner(Store store, OrderManager orderManager, FinancialManager financialManager) {
    s = store;
    om = orderManager;
    fm = financialManager;
  }

  protected String UPCLookup(String UPC) {
    s.logger.info("You have looed up UPC " + UPC);
    return s.getItem(UPC).toString();
  }

  protected double checkInStock(String UPC) {
    s.logger.info("You have checked if UPC " + UPC + " is in stock.");
    return s.getItem(UPC).quantity;
  }

  protected double getSellPrice(String UPC) {
    s.logger.info("You have cheked the sell price of " + UPC);
    return s.getItem(UPC).sellPrice;
  }


  private void scanIn(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    if (item != null) {
      item.quantity += quantity;
      item.unshelvedQuantity = true;
      om.cancelPendingOrder(UPC, quantity);
      addOrderHistory(UPC, quantity);
      s.logger.info(quantity + " item(s) of " + item + " have been scanned in on " + tm.timeStamp());
    } else {
      System.out.println("This UPC is not associated with any item in store!");
    }
  }
  void scanIn(String UPC) {
    scanIn(UPC, 1);
  }

  void addOrderHistory(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    item.orderHistory.add(quantity + " items were added on " + tm.timeStamp());
  }

  void sell(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    if (item.quantity < item.threshold) {
      om.autoOrder(UPC);
    }
    if (item.quantity >= quantity) {
      item.quantity -= quantity;
      item.soldToday += quantity;
      item.salesHistory.add(quantity + "units sold of " + item.name);
      fm.recordSale(UPC, quantity);
      String log = quantity + " item(s) of " + item.name + "have been sold on " + tm.timeStamp();
      }
    else {
      System.out.println("Don't have this much inventory to scan out!");
      String log = "Error: insufficient quantity of " + item.name + ". Scan was rejected on " + tm.timeStamp();
    }
  }

  void sell(String UPC) {  // This method is run if no parameter is given
    sell(UPC, 1);
  }


  void returnItem(String UPC, int quantity) {
    Item item = s.getItem(UPC);

    item.quantity += quantity;
    item.soldToday -= quantity;
    item.salesHistory.add(quantity + "unit(s) of " + item.name + " were returned on "
        + tm.timeStamp());
    String log = quantity + "unit(s) of " + item.name + "were returned on "
        + tm.timeStamp();

    fm.recordSale(UPC, -quantity);
  }

  String getLocation(String UPC) {
    Item item = s.getItem(UPC);
    return "Aisle: " + item.aisle + ", Section: " + item.section + ", Subsection: "
        + item.subsection;
  }
}

