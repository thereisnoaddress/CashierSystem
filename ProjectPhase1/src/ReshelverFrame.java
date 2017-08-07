import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ReshelverFrame extends GenericFrame {

  protected JButton location;
  protected JButton orderHistory;
  protected JButton quantity;

  private ReshelverFrame() throws IOException, ClassNotFoundException {
    super("Receiver");
    prepareButtons();
  }

  void prepareButtons() {


    location = new JButton("Show location");
    location.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null,
              "The item is in aisle " + ((Item) storeItems.getSelectedValue()).aisle);
        }
      }
    });


    orderHistory = new JButton("Show order history");
    orderHistory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {

          String history = ((Item) storeItems.getSelectedValue()).infoToString(
              ((Item) storeItems.getSelectedValue()).priceHistory);
          JOptionPane.showMessageDialog(null,
              "The price history of this item is " + history);
        }
      }
    });

    quantity = new JButton("Show quantity");
    quantity.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null,
              "There are " + ((Item) storeItems.getSelectedValue()).quantity +
          " of this item.");

        }
      }
    });


    controlPanel.add(location);
    controlPanel.add(orderHistory);
    controlPanel.add(quantity);

  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    ReshelverFrame rf = new ReshelverFrame();
  }


}
