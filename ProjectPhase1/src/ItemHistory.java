

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class stores History-related attributes of class Item and should only be instantiated as a
 * part of it
 */

class ItemHistory implements Serializable {
  ArrayList<String> orderHistory = new ArrayList<>();  //List of orders made (as well as cancellations)
  ArrayList<String> pendingOrders = new ArrayList<>(); //List of pending orders
  ArrayList<String> salesHistory = new ArrayList<>();  // History of units sold
  ArrayList<String> priceHistory = new ArrayList<>();  // History of price
}
