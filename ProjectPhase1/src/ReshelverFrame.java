import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ReshelverFrame extends GenericFrame {

  protected JButton quantity;

  ReshelverFrame() throws IOException, ClassNotFoundException {
    super("Receiver");
    prepareButtons();
  }

  private void prepareButtons() {

    JButton location = new JButton("Show location");
    location.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null,
              "The item is in aisle " + ((Item) storeItems.getSelectedValue()).getAisle());
          Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
              " is in aisle " + ((Item) storeItems.getSelectedValue()).getAisle());
        }
      }
    });

    JButton orderHistory = new JButton("Show order history");
    orderHistory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {

          String history = ((Item) storeItems.getSelectedValue()).infoToString(
              ((Item) storeItems.getSelectedValue()).ih.orderHistory);
          JOptionPane.showMessageDialog(null,
              "The order history of this item is " + history);
          Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
              " has order history " + ((Item) storeItems.getSelectedValue()).ih.orderHistory);
        }
      }
    });

    quantity = new JButton("Show quantity");
    quantity.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null,
              "There are " + ((Item) storeItems.getSelectedValue()).getQuantity() +
          " of this item.");
          Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
              " has quantity " + ((Item) storeItems.getSelectedValue()).getQuantity());

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
