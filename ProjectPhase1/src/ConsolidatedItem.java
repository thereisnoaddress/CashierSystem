import java.util.ArrayList;

public class ConsolidatedItem {
  /**
   * A ConsolidatedItem object that is in the store.
   */

  private String name;
  private String section;
  private String subsection;
  private int aisle;
  private double boughtPrice;
  private double sellPrice;
  protected String UPC;
  private int quantity;
  private boolean unshelvedQuantity;  // true if there are units in storage not on the shelves
  private int threshold;
  private int orderSize;
  private boolean saleStatus = false;
  private double salePrice;
  private String saleStart;  // We'll need to figure out how to implement time
  private String saleEnd;
  private ArrayList<String> orderHistory;  //List of orders made (as well as cancellations)
  private ArrayList<String> pendingOrders; //List of pending orders
  private ArrayList<String> salesHistory;  // History of units sold
  private ArrayList<String> priceHistory;  // History of price
  private ArrayList<String> financialHistory;  // History of daily profits. Revenue, COGS, profit. sold today, profit today
  private String supplier;
  private int soldToday;  // quantity sold today
  private double revenueToday;  // I have tp reset this, soldToday and profitToday daily somehow
  private double profitToday;

  protected ConsolidatedItem(String UPC, String name, String section, String subsection, int aisle,
      double boughtPrice, double sellPrice, int quantity, int threshold,
      String supplier) {

    this.UPC = UPC;
    this.name = name;
    this.section = section;
    this.subsection = subsection;
    this.aisle = aisle;
    this.boughtPrice = boughtPrice;
    this.sellPrice = sellPrice;
    this.quantity = quantity;
    this.unshelvedQuantity = true;
    this.threshold = threshold;
    this.orderSize = threshold * 3;
    this.supplier = supplier;
  }

  void scanIn(int quantity) {
    this.quantity += quantity;
    unshelvedQuantity = true;
    cancelPendingOrder(quantity);
    orderHistory.add(quantity + "replace this String with a timestamp");
  }

  void scanIn() {  // This method is run if no parameter is given
    scanIn(1);
  }

  void sell(int quantity) {
    if (this.quantity >= quantity) {
      this.quantity -= quantity;
      soldToday += quantity;
      salesHistory.add(quantity + "units sold of " + name);
      if (saleStatus) {
        revenueToday += quantity * salePrice;
        profitToday += quantity * (salePrice - boughtPrice);
      }
      else {
        revenueToday += quantity * sellPrice;
        profitToday += quantity * (sellPrice - boughtPrice);
      }
    }
    else {
      System.out.println("Don't have this much inventory to scan out!");
    }
  }

  void sell() {  // This method is run if no parameter is given
    sell(1);
  }

  void returnItem(int quantity) {
    this.quantity += quantity;
    soldToday -= quantity;
    salesHistory.add(quantity + "unit(s) was returned on " + "replace this String with a timestamp");
    if (saleStatus) {
      revenueToday -= quantity * salePrice;
      profitToday -= quantity * (salePrice - boughtPrice);
    }
    else {
      revenueToday -= quantity * sellPrice;
      profitToday -= quantity * (sellPrice - boughtPrice);
    }
  }

  public void addSale(String start, String end, double price) {
    this.saleStatus = true;
    this.saleStart = start;
    this.saleEnd = end;
    this.salePrice = price;
  }

  void autoOrder() {
    if (quantity < threshold) {
      String order = threshold * 3 + " units of " + name + " have been ordered on " + "timestamp";
      pendingOrders.add(order);
    }
  }

  void customOrder(int quantity) {  // String date should be automatically
    String order = quantity + " units of " + name + " have been ordered on " + "timestamp";
    pendingOrders.add(order);
  }

  void cancelPendingOrder(int quantity) {
    for (String s : pendingOrders) {
      String[] temp = s.split(",");
      if (temp[0].equals(quantity)) {
        pendingOrders.remove(s);
        orderHistory.add(quantity + " units of " + name + " have been cancelled on " + "timestamp");
      }
    }
  }

  String viewPendingOrders() {
    StringBuilder sb = new StringBuilder();
    for (String s : pendingOrders) {
      sb.append(s);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }


  public String toString(){
    return UPC + "," + name + "," + section + "," + subsection + "," + aisle + "," + boughtPrice
        + "," +sellPrice + "," + quantity + "," + threshold + "," + supplier;
  }

  public void addQuantity(double quantity) {
    this.quantity += quantity;
  }

  public boolean equals(ConsolidatedItem ci) { return UPC.equals(ci.getUPC()); }

  protected String getUPC(){
    return UPC;
  }

  protected String getName() { return name; }

  private String getLocation() {
    return "Aisle: " + aisle + ", Section: " + section + ", Subsection: " + subsection;
  }

  private double getBoughtPrice() { return boughtPrice; }

  protected double getSellPrice() { return sellPrice; }

  private boolean getSaleStatus() { return saleStatus; }

  private double getSalePrice() { return salePrice; }

  private String getSaleDuration() {
    return "Sale start: " + saleStart + ", Sale end: " + saleEnd;
  }

  private String getSupplier() { return supplier; }

  private ArrayList<String> getOrderHistory() { return orderHistory; }

  public int getQuantity() { return quantity; }

  public int getThreshold(){ return threshold; }

  public int getOrderSize() { return this.orderSize; }

  protected int getSoldToday() { return soldToday; }

  protected double getRevenueToday() { return revenueToday; }

  protected double getProfitToday() { return profitToday; }

  private boolean getUnshelvedQuantity() {return getUnshelvedQuantity(); }

  private ArrayList<String> getPendingOrders() { return pendingOrders; }

  private ArrayList<String> getSalesHistory() { return salesHistory; }

  private ArrayList<String> getPriceHistory() { return orderHistory; }

  private ArrayList<String> getFinancialHistory() { return orderHistory; }

  void setSection(String section) { this.section = section; }

  void setSubsection(String subsection) { this.subsection = subsection; }

  void setAisle(int aisle) { this.aisle = aisle; }

  void setBoughtPrice(double price) { this.boughtPrice = price; }

  void setSellPrice(double price) {
    sellPrice = price;
    priceHistory.add(price + "replace this String with a timestamp");
  }

  void setUnshelvedQuantity(boolean b) { unshelvedQuantity = b; }

  void setQuantity(int quantity) { this.quantity = quantity; }

  void setThreshold(int threshold) { this.threshold = threshold; }

  void setSaleStatusOn() {
    this.saleStatus = true;
    priceHistory.add(salePrice + "replace this String with a timestamp");
  }

  void setSaleStatusOff() {
    this.saleStatus = false;
    priceHistory.add("Sale ended, " + "replace this String with a timestamp");
  }

  void setSalePrice(double price) { this.salePrice = price; }

  void setSaleStart(String start) { this.saleStart = start; }

  void setSaleEnd(String end) { this.saleEnd = end; }

  void setSupplier(String supplier) { this.supplier = supplier; }
}
