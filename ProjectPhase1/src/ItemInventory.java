import java.io.Serializable;

/**
 * This class stores Inventory-related attributes of class Item and should only be instantiated
 * as a part of it
 */

class ItemInventory implements Serializable {
  String name;
  String UPC;
  String section;
  String subsection;
  int aisle;
  int quantity;
  boolean unshelvedQuantity;
  int threshold;
  int orderSize;
  String supplier;

  ItemInventory(String UPC, String name, String section, String subsection, int aisle,
      int quantity, int threshold, String supplier) {
    this.name = name;
    this.UPC = UPC;
    this.section = section;
    this.subsection = subsection;
    this.aisle = aisle;
    this.quantity = quantity;
    this.unshelvedQuantity = true;
    this.threshold = threshold;
    this.orderSize = threshold * 3;
    this.supplier = supplier;
  }
}
