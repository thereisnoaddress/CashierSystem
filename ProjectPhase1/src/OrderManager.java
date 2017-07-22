public class OrderManager {

  protected Store s;
  protected TimeManager tm = new TimeManager();

  protected OrderManager(Store store) { s = store; }

  void setSupplier(String UPC, String supplier) {
    Item item = s.getItem(UPC);
    item.supplier = supplier;
  }

  void autoOrder(String UPC) {
    Item item = s.getItem(UPC);
    if (item.quantity < item.threshold) {
      String order = item.threshold * 3 + " units of " + item.name + " have been ordered on " + "timestamp";
      item.pendingOrders.add(order);
    }
  }

  void customOrder(String UPC, int quantity) {  // String date should be automatically
    Item item = s.getItem(UPC);
    String order = quantity + " units of " + item.name + " have been ordered on " + "timestamp";
    item.pendingOrders.add(order);
  }

  void cancelPendingOrder(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    for (String s : item.pendingOrders) {
      String[] temp = s.split(",");
      if (temp[0].equals(quantity)) {
        item.pendingOrders.remove(s);
        item.orderHistory.add(quantity + " units of " + item.name + " have been cancelled on " + "timestamp");
      }
    }
  }

  String viewPendingOrders(String UPC) {
    Item item = s.getItem(UPC);
    StringBuilder sb = new StringBuilder();
    for (String s : item.pendingOrders) {
      sb.append(s);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  void addOrderHistory(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    item.orderHistory.add(quantity + " items were added on " + tm.timeStamp());
  }
}
