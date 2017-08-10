import java.io.Serializable;


class ItemScanner implements Serializable {

  protected Store s;
  private OrderManager om;
  private FinancialManager fm;
  private TimeManager tm = new TimeManager();

  ItemScanner(Store store, OrderManager orderManager, FinancialManager financialManager) {
    s = store;
    om = orderManager;
    fm = financialManager;
  }

  /**
   * This method checks how much of the Item with the specified
   * UPC is in stock, and then returns that quantity.
   *
   * @param UPC The UPC of the Item whose quantity we'd like to check
   * @return The quantity of the Item
   */
  int checkInStock(String UPC) {
    return s.getItem(UPC).getQuantity();
  }

  /**
   * This method gives the location of the Item with the corresponding UPC. Useful
   * for stockers to find out where the item should go.
   *
   * @param UPC The UPC of the Item whose location we want
   * @return The formatted String location
   */
  String getLocation(String UPC) {
    Item item = s.getItem(UPC);
    return "Aisle: " + item.getAisle() + ", Section: " + item.getSection() + ", Subsection: "
        + item.getSubsection();
  }

  /**
   * This method scans in the specified quantity to the Item
   * corresponding to the specified UPC. It also set attribute
   * unshelvedQuantity to true, in order to indicate to the stockers
   * that there are Items received from the supplier that need to be
   * put on the shelves. Further, it adds this reception of Items
   * into the orderHistory.
   *
   * @param UPC The UPC of the Item being scanned in
   * @param quantity The quantity to be scanned in
   */
  void scanIn(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    if (item != null) {
      item.iv.quantity += quantity;
      item.iv.unshelvedQuantity = true;
      om.cancelPendingOrder(UPC, quantity);
      addOrderHistory(UPC, quantity);
    } else {
      System.out.println("This UPC is not associated with any item in store!");
    }
  }

  void scanIn(String UPC) {
    scanIn(UPC, 1);
  }

  /**
   * This method takes the Item with the corresponding UPC, and
   * adds the given quantity to its orderHistory. This method is
   * called whenever new quantity ships from the supplier.
   *
   * @param UPC The UPC of the Item whose orderHistory we'd like to update.
   * @param quantity The quantity associated with the order
   */
  private void addOrderHistory(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    item.ih.orderHistory.add(quantity + " items were added on " + tm.timeStamp());
  }

  /**
   * This method sells the given quantity of the Item with the specified UPC.
   * First, it checks whether this is possible by making sure we have the required
   * quantity. If we do, then it updates the corresponding attributes for revenue
   * and salesHistory.
   *
   * @param UPC The UPC of the Item to be sold
   * @param quantity The quantity of the Item sold
   */
  void sell(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    if (item.iv.quantity < item.iv.threshold) {
      om.autoOrder(UPC);
    }
    if (item.iv.quantity >= quantity) {
      item.iv.quantity -= quantity;
      item.ia.soldToday += quantity;
      item.ih.salesHistory.add(quantity + " units sold of " + item.getName());
      fm.recordSale(UPC, quantity);
    } else {
      System.out.println("Don't have this much inventory to scan out!");
    }
  }

  void sell(String UPC) {  // This method is run if no parameter is given
    sell(UPC, 1);
  }


  /**
   * This method returns the given quantity of the Item with the specified UPC. It also
   * updates the revenue calculations, and adds back the returned quantity.
   *
   * @param UPC The UPC of the Item being returned
   * @param quantity The quantity being returned
   */
  void returnItem(String UPC, int quantity) {
    Item item = s.getItem(UPC);

    item.iv.quantity += quantity;
    item.ia.soldToday -= quantity;
    item.ih.salesHistory.add(quantity + "unit(s) of " + item.getName() + " were returned on "
        + tm.timeStamp());
    fm.recordSale(UPC, -quantity);
  }


}

