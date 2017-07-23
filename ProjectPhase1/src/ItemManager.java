import java.io.Serializable;

public class ItemManager implements Serializable{
  protected Store s;
  protected TimeManager tm = new TimeManager();

  protected ItemManager(Store store) {
    s = store;
  }

  void setSection(String UPC, String section) {
    Item item = s.getItem(UPC);
    item.section = section;
  }

  void setSubsection(String UPC, String subsection) {
    Item item = s.getItem(UPC);
    item.subsection = subsection;
  }

  void setAisle(String UPC, int aisle) {
    Item item = s.getItem(UPC);
    item.aisle = aisle;
  }

  void setBoughtPrice(String UPC, double price) {
    Item item = s.getItem(UPC);
    item.boughtPrice = price;
  }

  void setSellPrice(String UPC, double price) {
    Item item = s.getItem(UPC);
    item.sellPrice = price;
    item.priceHistory.add(price + "replace this String with a timestamp");
  }

  void setQuantity(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    item.quantity = quantity;
  }

  void setThreshold(String UPC, int threshold) {
    Item item = s.getItem(UPC);
    item.threshold = threshold;
  }
}
