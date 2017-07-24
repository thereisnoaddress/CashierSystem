import java.io.Serializable;

public class OrderManager implements Serializable{

  protected Store s;
  private TimeManager tm = new TimeManager();

  OrderManager(Store store) { s = store; }

  void setSupplier(String UPC, String supplier) {
    Item item = s.getItem(UPC);
    item.supplier = supplier;
    s.logger.info("The new supplier of " + s.getItem(UPC).name + " is " + supplier);
  }

  /**
   * This method is whenever the ItemScanner detects that the quantity of
   * the Item with the given UPC has fallen below the threshold quantity.
   * This order is then added to pending orders.
   *
   * @param UPC   The UPC of the Item that is to be auto ordered.
   */
  void autoOrder(String UPC) {
    Item item = s.getItem(UPC);
    String order = item.threshold * 3 + " units of " + item.name +
        " have been ordered on " + tm.timeStamp();
    item.pendingOrders.add(order);
    s.logger.info("" + item.orderSize + " of " + item.name + " have been auto-ordered.");
  }

  /**
   * This method allows the manager to make a custom order of the Item with the given
   * UPC
   *
   * @param UPC         The Item to be ordered
   * @param quantity    The quantity to be ordered
   */
  void customOrder(String UPC, int quantity) {  // String date should be automatically
    Item item = s.getItem(UPC);
    String order = quantity + " units of " + item.name + " have been ordered on " + "timestamp";
    item.pendingOrders.add(order);
  }

  /**
   * This method is used to cancel a pending order for the item with the given UPC.
   * The system checks if there are any pending orders with the specified quantity,
   * and then cancels the first match.
   *
   * @param UPC         The UPC of the Item whose order we want to cancel
   * @param quantity    The corresponding quantity associated with the order we want to cancel
   */
  void cancelPendingOrder(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    for (String st : item.pendingOrders) {
      String[] temp = st.split(",");
      if (temp[0].equals(quantity)) {
        item.pendingOrders.remove(st);
        s.logger.info("Order of " + quantity + " of " + UPC + " has been cancelled.");
      }
    }
  }

  /**
   * This method is used to view a formatted list of all pending orders for the
   * specified Item.
   *
   * @param UPC   The UPC of the Item whose pendingOrders we'd like to see
   * @return      The formatted String of pendingOrders
   */
  String viewPendingOrders(String UPC) {
    Item item = s.getItem(UPC);
    StringBuilder sb = new StringBuilder();
    for (String s : item.pendingOrders) {
      sb.append(s);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }
}
