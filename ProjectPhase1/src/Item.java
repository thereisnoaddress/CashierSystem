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
  protected String saleStart;
  protected String saleEnd;
  protected ArrayList<String> orderHistory = new ArrayList<String>();  //List of orders made (as well as cancellations)
  protected ArrayList<String> pendingOrders = new ArrayList<String>(); //List of pending orders
  protected ArrayList<String> salesHistory = new ArrayList<String>();  // History of units sold
  protected ArrayList<String> priceHistory = new ArrayList<String>();  // History of price
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


  public String InfoToString(ArrayList<String> info) {
    String returnString = "";
    if (info == null) {
      return "";
    } else {
      for (int i = 0; i < info.size(); i++) {
        returnString += info.get(i);
        returnString += ",";
      }
      return returnString;
    }

  }
}





