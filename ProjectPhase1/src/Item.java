
class Item {
  /**
   * An Item object. This can be any item. An item object is not (yet) ready to be stored in the
   * Store.
   *
   * @param name the name of the Item.
   * @param section a string that describes the Item.
   * @param subsection a string that further describes the Item in more details.
   * @param aisle an integer representation of the aisle in which the Item can be found.
   * @param boughtPrice the price for which the Store buys the Item.
   * @param sellPrice the price for which the Store sells the Item.
   * @param UPC the Universal Product Code of the Item.
   * @param sale a boolean to denote if the Item is on sale.
   */


  private String name;
  private String section;
  private String subsection;
  private int aisle;
  private double boughtPrice;
  private double sellPrice;
  private int UPC;
  private boolean sale;


  Item(int UPC, String name, String section, String subsection, int aisle) {
    this.UPC = UPC;
    this.name = name;
    this.section = section;
    this.subsection = subsection;
    this.aisle = aisle;
    sale = false;
  }

  private double profit() {
    return sellPrice - boughtPrice;
  }

  public int getUPC() {
    return UPC;
  }

  public double getPrice() {
    return this.sellPrice;
  }

  public String getName(){
    return this.name;
  }

  public String toString(){
    return UPC + "," + name + "," + section + "," + subsection
        + "," + aisle;
  }


}
