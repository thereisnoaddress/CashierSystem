import java.io.Serializable;

public class ItemManager implements Serializable {

  protected Store s;

  ItemManager(Store store) {
    s = store;
  }

  /**
   * This method takes an Item's UPC and desired section as parameters. Then, it
   * changes the section attribute to the desired section.
   *
   * @param UPC The UPC of the item whose section we would like to change
   * @param section The new section where we would like to place the Item
   */
  void setSection(String UPC, String section) {
    Item item = s.getItem(UPC);
    item.iv.section = section;
  }

  /**
   * This method takes an Item's UPC and desired subsection as parameters. Then, it
   * changes the subsection attribute to the desired subsection.
   *
   * @param UPC The UPC of the item whose subsection we would like to change
   * @param subsection The new subsection where we would like to place the Item
   */
  void setSubsection(String UPC, String subsection) {
    Item item = s.getItem(UPC);
    item.iv.subsection = subsection;
  }

  /**
   * This method takes an Item's UPC and desired aisle as parameters. Then, it
   * changes the aisle attribute to the desired aisle.
   *
   * @param UPC The UPC of the item whose aisle we would like to change
   * @param aisle The new aisle where we would like to place the Item
   */
  void setAisle(String UPC, int aisle) {
    Item item = s.getItem(UPC);
    item.iv.aisle = aisle;
  }

  /**
   * This method sets the new price at which our supplier sells us the Item with
   * the specified UPC.
   *
   * @param UPC The UPC of the Item whose boughtPrice we would like to change
   * @param price The new boughtPrice of the Item
   */
  void setBoughtPrice(String UPC, double price) {
    Item item = s.getItem(UPC);
    item.ia.boughtPrice = price;
  }

  /**
   * This method sets the sell price of the Item with the specified UPC.
   *
   * @param UPC The UPC of the item whose sellPrice we would like to change
   * @param price The new price at which we would like to sell the item
   */
  void setSellPrice(String UPC, double price) {
    Item item = s.getItem(UPC);
    item.ia.sellPrice = price;
    item.ih.priceHistory.add(price + "replace this String with a timestamp");
  }

  /**
   * This method is used to set teh quantity of the Item with the given UPC.
   * This method is useful when we do an inventory check and find that we must
   * change the recorded inventory to match the physical inventory on stock.
   *
   * @param UPC The UPC of the item whose quantity we would like to set
   * @param quantity The new quantity attribute that we would like to set for the Item
   */
  void setQuantity(String UPC, int quantity) {
    Item item = s.getItem(UPC);
    item.iv.quantity = quantity;
  }

  /**
   * This method sets the new threshold for the item with the given UPC. This
   * is useful for adjusting the threshold in order to ensure that the store is
   * stocked with the optimal quantity of the specified item. The order size is
   * also updated to correspond with the new threshold.
   *
   * @param UPC The UPC of the Item whose threshold we would like to change
   * @param threshold The new threshold of the Item
   */
  void setThreshold(String UPC, int threshold) {
    Item item = s.getItem(UPC);
    item.iv.threshold = threshold;
    item.iv.orderSize = threshold * 3;
  }
}
