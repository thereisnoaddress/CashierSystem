import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {

  protected String name;
  protected String section;
  protected String subsection;
  protected int aisle;
  protected double boughtPrice;
  protected double sellPrice;
  protected String UPC;
  protected int quantity;
  protected boolean unshelvedQuantity;  // true if there are units in storage not on the shelves.  // Take out this attribute, and just have a list of unshelved items in store.
  protected int threshold;
  protected int orderSize;
  protected boolean saleStatus = false;
  protected double salePrice;
  protected String saleStart;  // We'll need to figure out how to implement time
  protected String saleEnd;
  protected ArrayList<String> orderHistory;  //List of orders made (as well as cancellations)
  protected ArrayList<String> pendingOrders; //List of pending orders
  protected ArrayList<String> salesHistory;  // History of units sold
  protected ArrayList<String> priceHistory;  // History of price
  protected String supplier;
  protected int soldToday;  // quantity sold today
  protected double revenueToday = 0.0;  // I have tp reset this, soldToday and profitToday daily somehow
  protected double profitToday = 0.0;

  protected Item(String UPC, String name, String section, String subsection, int aisle,
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

  public String toString() {
    return UPC + "," + name + "," + section + "," + subsection + "," + aisle + "," + boughtPrice
        + "," + sellPrice + "," + quantity + "," + threshold + "," + supplier;
  }

  public boolean equals(Item i) {
    return UPC.equals(i.UPC);
  }
}


  /** These methods are never used as I just access them directly - its more efficient, and I don't think we'll lose marks?
   * All the set methods are implemented in other classes as needed
   *
  protected String getUPC() {
    return UPC;
  }

  protected String getName() {
    return name;
  }

  protected int getAisle() {
    return this.aisle;
  }

  protected String getSection() {
    return this.section;
  }

  protected String getSubection() {
    return this.subsection;
  }

  double getBoughtPrice() {
    return boughtPrice;
  }

  protected double getSellPrice() {
    return sellPrice;
  }

  protected String getSaleStart() {
    return saleStart;
  }

  protected String getSaleEnd() {
    return saleEnd;
  }

  protected boolean getSaleStatus() {
    return saleStatus;
  }

  protected double getSalePrice() {
    return salePrice;
  }

  protected String getSupplier() {
    return supplier;
  }

  protected ArrayList<String> getOrderHistory() {
    return orderHistory;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getThreshold() {
    return threshold;
  }

  public int getOrderSize() {
    return this.orderSize;
  }

  protected int getSoldToday() {
    return soldToday;
  }

  protected double getRevenueToday() {
    return revenueToday;
  }

  protected double getProfitToday() {
    return profitToday;
  }

  protected boolean getUnshelvedQuantity() {
    return unshelvedQuantity;
  }

  protected ArrayList<String> getPendingOrders() {
    return pendingOrders;
  }

  protected ArrayList<String> getSalesHistory() {
    return salesHistory;
  }

  protected ArrayList<String> getPriceHistory() {
    return priceHistory;
  }

   */






