import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.scripts.JO;

/**
 * Created by Chris on 2017-08-05.
 */
public class ReceiverFrame extends GenericFrame {


  protected JButton add;
  protected JButton location;
  protected JButton cost;
  protected JButton priceHistory;
  protected JButton curPrice;


  public ReceiverFrame() throws IOException, ClassNotFoundException {
    super("Receiver");
    prepareButtons();
  }

  void prepareButtons() {
    add = new JButton("Scan in");
    add.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String UPC = JOptionPane.showInputDialog("Enter the UPC of the item "
            + "you want to scan in.", JOptionPane.YES_NO_OPTION);
        if (s.UPCToItem.containsKey(UPC)) {
          s.UPCToItem.get(UPC).quantity += Integer.parseInt(
              JOptionPane.showInputDialog("How many do you want to scan in?",
                  JOptionPane.YES_NO_OPTION));
          JOptionPane.showMessageDialog(null, "There are " +
              s.UPCToItem.get(UPC).quantity + " now.");
        } else {
          String item = JOptionPane.showInputDialog("It appears that you are trying to add"
                  + " a new item. Please enter its details according to help.txt",
              JOptionPane.YES_NO_OPTION);
          Item i = s.makeNewItem(item);
          JOptionPane.showMessageDialog(null, i.name + " has been added.");
          ReceiverFrame.super.items.removeAllElements();
          ReceiverFrame.super.addToList();
        }
      }
    });

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

    cost = new JButton("Show cost");
    cost.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null,
              "The bought price of the item is " +
                  ((Item) storeItems.getSelectedValue()).boughtPrice);
        }
      }
    });

    priceHistory = new JButton("Show price history");
    priceHistory.addActionListener(new ActionListener() {
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

    curPrice = new JButton("Show current price");
    curPrice.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1){
          JOptionPane.showMessageDialog(null,
              "The current price of this item is " +
                  ((Item) storeItems.getSelectedValue()).sellPrice);
        }

      }
    });

    controlPanel.add(add);
    controlPanel.add(location);
    controlPanel.add(cost);
    controlPanel.add(priceHistory);
    controlPanel.add(curPrice);
  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    ReceiverFrame rf = new ReceiverFrame();
  }


}
