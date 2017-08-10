import java.io.Serializable;

/**
 * This class manages the general Store-level methods that a user would like to use
 */
public class StoreManager implements Serializable {

  protected Store s;

  StoreManager(Store store) {
    s = store;
  }

  /**
   * This method sums up the revenues and profits for each Item in the store.
   * It adds them up, and creates an entry in dailyProfits. Once this is done,
   * it sets the revenueToday and profitToday attributes for each item back to 0.0
   */
  void closeDailyTotals() {
    String date = s.tm.timeStamp();
    Double revenue = 0.0;
    Double profit = 0.0;

    for (Item i : s.itemsList) {
      revenue += i.ia.revenueToday;
      profit += i.ia.profitToday;
      i.ia.revenueToday = 0.0;
      i.ia.profitToday = 0.0;
    }
    String entry = "Revenue: " + revenue + ", Profit: " + profit;
    s.dailyProfits.add(date + " " + entry + System.lineSeparator());
  }

  /**
   * This method returns a formatted list of all the Items in Store.
   *
   * @return A formatted String of all the items.
   */
  String getItemsList() {
    StringBuilder sb = new StringBuilder();
    for (Item i : s.itemsList) {
      sb.append(i.toString());
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  /**
   * This method returns a formatted list of all the Items with unshelvedQuantity
   * in Store.
   *
   * @return A formatted String of all the unshelved Items.
   */
  String getUnshelvedItemsList() {
    StringBuilder sb = new StringBuilder();
    for (Item i : s.itemsList) {
      if (i.iv.unshelvedQuantity) {
        sb.append(i.toString());
        sb.append(System.lineSeparator());
      }
    }
    return sb.toString();
  }

  /**
   * This method returns a formatted list of all the Items in
   * Store that have pendingOrders.
   *
   * @return A formatted String of all Items in pendingOrders.
   */
  String getPendingOrders() {
    StringBuilder sb = new StringBuilder();
    for (String s : s.pendingOrders) {
      sb.append(s);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  /**
   * This method returns a formatted list of the dailyProfits history of the store,
   * by date.
   *
   * @return A formatted String that lists all the dailyProfits in the Store's history.
   */
  String getDailyProfits() {
    StringBuilder sb = new StringBuilder();
    for (String s : s.dailyProfits) {
      sb.append(s);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }
}
