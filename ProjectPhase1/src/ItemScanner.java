/**
 * Scans stuff
 */
class ItemScanner {
  protected Store s;
  protected OrderManager om;
  protected FinancialManager fm;
  protected TimeManager tm = new TimeManager();

  protected ItemScanner(Store store, OrderManager orderManager, FinancialManager financialManager) {
    s = store;
    om = orderManager;
    fm = financialManager;
  }

  protected String UPCLookup(String UPC) { return s.getItem(UPC).toString(); }

  protected int checkInStock(String UPC) { return s.getItem(UPC).quantity; }

  protected double getSellPrice(String UPC) { return s.getItem(UPC).sellPrice; }

  protected void scanIn(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    if (item != null) {
      item.quantity += quantity;
      item.unshelvedQuantity = true;
      om.cancelPendingOrder(UPC, quantity);
      addOrderHistory(quantity, UPC);
      String log = quantity + " item(s) of " + item + " have been scanned in on " + tm.timeStamp();
    } else {
      System.out.println("This UPC is not associated with any item in store!");
    }
  }
  void scanIn(String UPC) {
    scanIn(UPC, 1);
  }

  // Helper function
  void addOrderHistory(int quantity, String UPC) {
    om.addOrderHistory(UPC, quantity);
  }

  void sell(String UPC, int quantity) {
    Item item = s.getItem(UPC);

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

  /** I don't think we need to deal with human errors, but if you want, we could implement this throw/catch block for all the methods lol rip
  protected void sell(String UPC, int quantity) throws NoSuchElementException {
    if (getItem(UPC) != null) {
      getItem(UPC).sell(quantity);
    } else {
      throw new NoSuchElementException("There is no item with UPC " + UPC);
    }
  }


   // This is another way we can prevent human errors from shutting down the program. Or we could do a checkUPC() method that would be implemented in the first line of every method to streamline the code for us
   public String addInventory(String UPC, double quantity) {

   // Check if UPC corresponds to an existing item in itemsList
   // If so, then add quantity to it.

   if (getItem(UPC) != null) {
   getItem(UPC).addQuantity(quantity);
   return "I have added " + quantity + " of " + getItem(UPC).getName();



   */

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

