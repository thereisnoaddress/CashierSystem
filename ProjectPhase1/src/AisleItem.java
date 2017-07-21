import java.util.ArrayList;

public class AisleItem {

  private Item item;
  private double quantity = 0;
  private double threshold;
  private boolean sale = false;
  private String saleStart;
  private String saleEnd;
  private ArrayList<String> orderHistory;

  public AisleItem(Item i, double quantity, double threshold){
      this.item = i;
      this.quantity = quantity;
      this.threshold = threshold;
  }

  public Item getItem(){
    return this.item;
  }

  public int getUPC(){
    return item.getUPC();
  }

  public void addOrderHistory(String orderDate) {
    this.orderHistory.add(orderDate);
  }



  public void addQuantity(double quantity){
    this.quantity += quantity;
  }

  public void addSale(String start, String end) {
    this.sale = true;
    this.saleStart = start;
    this.saleEnd = end;
  }

  public String sell(double quantity) {
    if (this.quantity != 0) {
      this.quantity -= quantity;
      return "I've sold" + quantity + " of " + this.item.getName();
    } else {
      return "there's 0 of " + this.item.getName();
    }
  }

  public double getQuantity(){
    return this.quantity;
  }

  public double getThreshold(){
    return this.threshold;
  }

  public String toString(){
    return item.getName() + " " + item.getUPC() + " " + quantity;
  }


}
