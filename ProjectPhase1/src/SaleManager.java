import java.io.Serializable;

public class SaleManager implements Serializable {

  protected Store s;
  private TimeManager tm = new TimeManager();

  SaleManager(Store store) { s = store; }

  /**
   * This method checks whether or not the Item with the given UPC is on sale
   *
   * @param UPC The UPC of the Item we want to check is on sale
   * @return A true or false value to indicate saleStatus
   */
  boolean checkSale(String UPC) {
    Item item = s.getItem(UPC);
    if ((item.ia.saleStart == null) ||
    (tm.after(item.ia.saleStart) && tm.before(item.ia.saleEnd)) ){
      if (!item.ia.saleStatus) {
        setSaleStatusOn(UPC);
      }
      return true;
    } else {
      if (item.ia.saleStatus) {
        item.ia.saleStatus = false;
      }
      return false;
    }
  }

  /**
   * This method sets the salePrice, which is the price the Item will go on
   * once it is on sale.
   *
   * @param UPC The UPC of the Item whose salePrice we'd like to set
   * @param price The price at which the Item will be on sale
   */
  void setSalePrice(String UPC, double price) {
    Item item = s.getItem(UPC);
    item.ia.salePrice = price;
  }

  /**
   * This method adds a sale to the Item with the given UPC. It specifies the start,
   * end, and price of the Item while on sale
   *
   * Note: only one sale may be set at a time
   *
   * @param UPC The UPC of the Item to which we'd like to add a sale
   * @param start The start date of the sale The format is: www mmm dd hh:mm:ss TMZ yyyy for
   * example: Sat Jul 22 18:27:06 EDT 2017
   * @param end The end date of the sale The format is: www mmm dd hh:mm:ss TMZ yyyy for example:
   * Sat Jul 22 18:27:06 EDT 2017
   * @param price The price of the Item while on sale
   */
  void addSale(String UPC, String start, String end, double price) {
    Item item = s.getItem(UPC);
    item.ia.saleStart = start;
    item.ia.saleEnd = end;
    item.ia.salePrice = price;
  }

  /**
   * This method removes a sale from the Item with the specified UPC.
   *
   * @param UPC The UPC of the Item we would like to remove the sale
   * @param start The start date of the sale The format is: www mmm dd hh:mm:ss TMZ yyyy for
   * example: Sat Jul 22 18:27:06 EDT 2017
   * @param end The end date of the sale The format is: www mmm dd hh:mm:ss TMZ yyyy for example:
   * Sat Jul 22 18:27:06 EDT 2017
   */
  void removeSale(String UPC, String start, String end) {
    Item item = s.getItem(UPC);
    if (item.ia.saleStart.equals(start) && item.ia.saleStart.equals(end)) {
      item.ia.saleStart = null;
      item.ia.saleEnd = null;
    } else {
      System.out.println("No such sale exists!");
    }
  }

  /**
   * This method returns the sale duration, in days, of the Item with the
   * specified UPC.
   *
   * @param UPC The UPC of the item to be set on sale
   * @return The number of days of the sale
   */
  String getSaleDuration(String UPC) {
    Item item = s.getItem(UPC);
    if (item.ia.saleStart != null && item.ia.saleEnd != null) {
      int start = Integer.parseInt(item.ia.saleStart.split(" ")[2]);
      int end = Integer.parseInt(item.ia.saleEnd.split(" ")[2]);
      return "" + (end - start);
    } else {
      return "There is no scheduled sale for the selected product!";
    }
  }

  /**
   * This method sets the sale status to on for the Item
   * with the given UPC.
   *
   * @param UPC The Item with this UPC
   */
  private void setSaleStatusOn(String UPC) {
    Item item = s.getItem(UPC);
    item.ia.saleStatus = true;
    item.ih.priceHistory.add("" + item.getName() + " is now on sale. " + tm.timeStamp());
  }

  /**
   * This method sets the sale status to off for the Item with
   * the given UPC.
   *
   * @param UPC The Item with this UPC.
   */
  void setSaleStatusOff(String UPC) {
    Item item = s.getItem(UPC);
    item.ia.saleStatus = false;
    item.ih.priceHistory.add("This item is no longer on sale. " + tm.timeStamp());
  }
}