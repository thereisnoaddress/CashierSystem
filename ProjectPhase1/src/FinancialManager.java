import java.io.Serializable;

public class FinancialManager implements Serializable{
  Store s;
  SaleManager sm;

  protected FinancialManager(Store store, SaleManager saleManager) {
    s = store;
    sm = saleManager;
  }

  /**
   * This method takes the UPC of the item as well as the quantity sold
   * as parameters. It first checks whether it is on sale, and then it
   * adds the revenue and profit from this transaction to  the revenueToday
   * and profitToday attributes in the Item
   *
   * @param UPC         The UPC code of the item to be recorded
   * @param quantity    The quantity of the item
   */
  protected void recordSale(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    double price;
    if (sm.checkSale(item.UPC)) {
      price = s.getItem(UPC).salePrice;
    }
    else {
      price = s.getItem(UPC).sellPrice;
    }
    double revenue = quantity * price;
    s.logger.info("A sale has been made. " + quantity + " of " + s.getItem(UPC));
    double profit = quantity * (revenue - s.getItem(UPC).boughtPrice);


    item.revenueToday += revenue;
    item.profitToday += profit;
  }
}

