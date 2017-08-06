import java.io.Serializable;
import java.util.ArrayList;

// Should there be an Iitem interface?


public class Item implements Serializable {

  protected String name;
  String UPC;
  String section;
  String subsection;
  int aisle;
  int quantity;
  boolean unshelvedQuantity;
  int threshold;

  double boughtPrice;
  double sellPrice;
  int orderSize;
  boolean saleStatus = false;
  double salePrice;
  String saleStart;
  String saleEnd;
  String supplier;
  int soldToday;  // quantity sold today
  double revenueToday = 0.0;  // I have tp reset this, soldToday and profitToday daily somehow
  double profitToday = 0.0;

  ArrayList<String> orderHistory = new ArrayList<>();  //List of orders made (as well as cancellations)
  ArrayList<String> pendingOrders = new ArrayList<>(); //List of pending orders
  ArrayList<String> salesHistory = new ArrayList<>();  // History of units sold
  ArrayList<String> priceHistory = new ArrayList<>();  // History of price

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

  String infoToString(ArrayList<String> info) {

    if (info == null) {
      return "";
    } else {
      StringBuilder sb = new StringBuilder();
      for (String i : info) {
        sb.append(i);
        sb.append(", ");
      }
      return sb.toString();
    }
  }

  public String toString() {
    return name;
  }


  protected boolean equals(Item i) {
    return UPC.equals(i.UPC);
  }



}

