import java.io.Serializable;

/**
 * This class stores Accounting-related attributes of class Item and should only be instantiated
 * as a part of it
 */

public class ItemAccounting implements Serializable {
  double boughtPrice;
  double sellPrice;
  boolean saleStatus = false;
  double salePrice;
  String saleStart;
  String saleEnd;
  int soldToday;  // quantity sold today
  double revenueToday = 0.0;  // I have tp reset this, soldToday and profitToday daily somehow
  double profitToday = 0.0;

  protected ItemAccounting(double boughtPrice, double sellPrice) {

    this.boughtPrice = boughtPrice;
    this.sellPrice = sellPrice;
  }
}
