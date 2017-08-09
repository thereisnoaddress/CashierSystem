import java.io.Serializable;

class FinancialManager implements Serializable {

  Store s;
  private SaleManager sm;
  double revenueToday;
  double profitToday;

  FinancialManager(Store store, SaleManager saleManager) {
    s = store;
    sm = saleManager;
  }

  /**
   * This method takes the UPC of the item as well as the quantity sold
   * as parameters. It first checks whether it is on sale, and then it
   * adds the revenue and profit from this transaction to  the revenueToday
   * and profitToday attributes in the Item
   *
   * @param UPC The UPC code of the item to be recorded
   * @param quantity The quantity of the item
   */
  void recordSale(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    double price;
    if (item.ia.saleStatus) {
      price = s.getItem(UPC).ia.salePrice;
    } else {
      price = s.getItem(UPC).ia.sellPrice;
    }
    double revenue = quantity * price;
    double profit = quantity * (revenue - s.getItem(UPC).ia.boughtPrice);

    revenueToday += revenue;
    profitToday += profit;
  }
}

