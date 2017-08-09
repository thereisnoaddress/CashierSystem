import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
  ItemInventory iv;
  ItemAccounting ia;
  ItemHistory ih;

  protected Item(ItemInventory iv, ItemAccounting ia, ItemHistory ih) {
    this.iv = iv;
    this.ia = ia;
    this.ih = ih;
  }

  String getName() { return iv.name; }
  String getUPC() { return iv.UPC; }
  String getSection() { return iv.section; }
  String getSubsection() { return iv.subsection; }
  int getAisle() { return iv.aisle; }
  int getQuantity() { return iv.quantity; }
  boolean getUnshelvedQuantity() { return iv.unshelvedQuantity; }
  int getThreshold() { return iv.threshold; }
  int getOrderSize() { return iv.orderSize; }
  String getSupplier() { return iv.supplier; }
  double getBoughtPrice() { return ia.boughtPrice; }
  double getSellPrice() { return ia.sellPrice; }

  void setName(String name) { iv.name = name; }
  void setUPC(String UPC) { iv.UPC = UPC; }
  void setSection(String section) { iv.section = section; }
  void setSubsection(String subsection) { iv.subsection = subsection; }
  void setAisle(int aisle) { iv.aisle = aisle; }
  void setQuantity(int quantity) { iv.quantity = quantity; }
  void setUnshelvedQuantity(boolean unshelvedQuantity) { iv.unshelvedQuantity = unshelvedQuantity; }
  void setThreshold(int threshold) { iv.threshold = threshold; }
  void setOrderSize(int orderSize) { iv.orderSize = orderSize; }
  void setSupplier(String supplier) { iv.supplier = supplier; }
  void setBoughtPrice(double boughtPrice) { ia.boughtPrice = boughtPrice; }
  void setSellPrice(double sellPrice) { ia.sellPrice = sellPrice; }

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
    return iv.name;
  }

  boolean equals(Item i) { return this.equals(i); }
}

