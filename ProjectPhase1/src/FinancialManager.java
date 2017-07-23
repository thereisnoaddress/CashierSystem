import java.io.Serializable;

public class FinancialManager implements Serializable {
  Store s;
  SaleManager sm;

  protected FinancialManager(Store store, SaleManager saleManager) {
    s = store;
    sm = saleManager;
  }

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
    double profit = quantity * (revenue - s.getItem(UPC).boughtPrice);


    item.revenueToday += revenue;
    item.profitToday += profit;
  }
}

