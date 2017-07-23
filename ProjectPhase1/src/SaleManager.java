import java.io.Serializable;

public class SaleManager implements Serializable {

  protected Store s;
  private TimeManager tm = new TimeManager();

  protected SaleManager(Store store) {
    s = store;
  }

  protected boolean checkSale(String UPC) {
    Item item = s.getItem(UPC);
    if (tm.after(item.saleStart) && tm.before(item.saleEnd)) {
      if (!item.saleStatus) {
        setSaleStatusOn(UPC);
      }
      return true;
    } else {
        if (item.saleStatus) {
          item.saleStatus = false;
        }
        return false;
      }
    }

  void setSalePrice(String UPC, double price) {
    Item item = s.getItem(UPC);
    item.salePrice = price;
  }

  protected void addSale(String UPC, String start, String end, double price) {
    Item item = s.getItem(UPC);
    item.saleStatus = true;
    item.saleStart = start;
    item.saleEnd = end;
    item.salePrice = price;
    String log = "A sale has been set from " + start + " until " + end + " on " + tm.timeStamp();
  }

  protected void removeSale(String UPC, String start, String end) {
    Item item = s.getItem(UPC);
    if (item.saleStart.equals(start) && item.saleStart.equals(end)) {
      item.saleStart = null;
      item.saleEnd = null;

      String log = "The sale from " + start + " until " + end + " has been removed on "
          + tm.timeStamp();
    } else {
      String log = "No such sale exists! No changes have been made.";
    }
  }

  protected String getSaleDuration(String UPC) {
    Item item = s.getItem(UPC);
    if (item.saleStart != null && item.saleEnd != null) {
      int start = Integer.parseInt(item.saleStart.split(" ")[2]);
      int end = Integer.parseInt(item.saleEnd.split(" ")[2]);
      return "" + (end - start);
    } else {
      return "There is no scheduled sale for the selected product!";
    }
  }

  private void setSaleStatusOn(String UPC) {
    Item item = s.getItem(UPC);
    item.saleStatus = true;
    item.priceHistory.add("This item is now on sale. " + tm.timeStamp());
  }

  void setSaleStatusOff(String UPC) {
    Item item = s.getItem(UPC);
    item.saleStatus = false;
    item.priceHistory.add("This item is no longer on sale. " + tm.timeStamp());
  }
}