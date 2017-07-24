import java.io.Serializable;

public class SaleManager implements Serializable {

  protected Store s;
  private TimeManager tm = new TimeManager();

  SaleManager(Store store) {
    s = store;
  }

  /**
   * This method checks whether or not the Item with the given UPC is on sale
   *
   * @param UPC The UPC of the Item we want to check is on sale
   * @return A true or false value to indicate saleStatus
   */
  boolean checkSale(String UPC) {
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

  /**
   * This method sets the salePrice, which is the price the Item will go on
   * once it is on sale.
   *
   * @param UPC The UPC of the Item whose salePrice we'd like to set
   * @param price The price at which the Item will be on sale
   */
  void setSalePrice(String UPC, double price) {
    Item item = s.getItem(UPC);
    item.salePrice = price;
    s.logger.info("The new sale price of " + s.getItem(UPC).name + " is " + price);
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
    item.saleStatus = true;
    item.saleStart = start;
    item.saleEnd = end;
    item.salePrice = price;
    s.logger.info("A sale has been set from " + start + " until " + end);
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
    if (item.saleStart.equals(start) && item.saleStart.equals(end)) {
      item.saleStart = null;
      item.saleEnd = null;
      s.logger.info("The sale from " + start + " until " + end + " has been removed.");
    } else {
      s.logger.info("No such sale exists! No changes have been made.");
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
    if (item.saleStart != null && item.saleEnd != null) {
      int start = Integer.parseInt(item.saleStart.split(" ")[2]);
      int end = Integer.parseInt(item.saleEnd.split(" ")[2]);
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
    item.saleStatus = true;
    item.priceHistory.add("" + item.name + " is now on sale. " + tm.timeStamp());
    s.logger.info("" + item.name + " is now on sale.");
  }

  /**
   * This method sets the sale status to off for the Item with
   * the given UPC.
   *
   * @param UPC The Item with this UPC.
   */
  void setSaleStatusOff(String UPC) {
    Item item = s.getItem(UPC);
    item.saleStatus = false;
    item.priceHistory.add("This item is no longer on sale. " + tm.timeStamp());
    s.logger.info("" + item.name + " is no longer on sale.");
  }
}