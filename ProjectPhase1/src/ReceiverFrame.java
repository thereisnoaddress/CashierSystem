import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.script.ScriptException;
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
        try {
          openScanner();
        } catch (ScriptException e1) {
          e1.printStackTrace();
        }
        String UPC = JOptionPane.showInputDialog("Enter the UPC of the item "
            + "you want to scan in.", JOptionPane.YES_NO_OPTION);
        if (s.UPCToItem.containsKey(UPC)) {
          s.UPCToItem.get(UPC).iv.quantity += Integer.parseInt(
              JOptionPane.showInputDialog("How many do you want to scan in?",
                  JOptionPane.YES_NO_OPTION));
          JOptionPane.showMessageDialog(null, "There are " +
              s.UPCToItem.get(UPC).iv.quantity + " now.");
          Store.logger.info("There are " +
              s.UPCToItem.get(UPC).iv.quantity + s.UPCToItem.get(UPC).iv.name + "(s) now.");

        } else {
          String name = JOptionPane.showInputDialog("It appears that you are trying to add"
                  + " a new item with UPC " + UPC + ". What is its name?" ,
              JOptionPane.YES_NO_OPTION);
          String section = JOptionPane.showInputDialog("What section is " + name + " in?");
          String subsection = JOptionPane.showInputDialog("What subsection is " + name + " in?");
          String aisle = JOptionPane.showInputDialog("Which aisle is " + name + " in?");
          String boughtPrice = JOptionPane.showInputDialog("What is" + name + "'s bought price?");
          String sellPrice = JOptionPane.showInputDialog("What is " + name + "'s sell price?");
          String quantity = JOptionPane.showInputDialog("How many are there?");
          String threshold = JOptionPane.showInputDialog("What is " + name + "'s threshold?");
          String supplier = JOptionPane.showInputDialog("Who is " + name + "'s supplier?");
          String item = UPC + "," + name + "," + section + "," + subsection + "," + aisle + ","
              + boughtPrice + "," + sellPrice + "," + quantity + "," + threshold + "," + supplier;
          Item i = s.makeNewItem(item);
          JOptionPane.showMessageDialog(null, i.iv.name + " has been added.");
          Store.logger.info(i.iv.name + " has been added with quantity" + i.iv.quantity);
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
              "The item is in aisle " + ((Item) storeItems.getSelectedValue()).iv.aisle);
          Store.logger.info(((Item) storeItems.getSelectedValue()).iv.name +
              " is in aisle " + ((Item) storeItems.getSelectedValue()).iv.aisle);
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
                  ((Item) storeItems.getSelectedValue()).ia.boughtPrice);
          Store.logger.info(((Item) storeItems.getSelectedValue()).iv.name +
              " has bought price " + ((Item) storeItems.getSelectedValue()).ia.boughtPrice);
        }
      }
    });

    priceHistory = new JButton("Show price history");
    priceHistory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {

          String history = ((Item) storeItems.getSelectedValue()).infoToString(
              ((Item) storeItems.getSelectedValue()).ih.priceHistory);
          JOptionPane.showMessageDialog(null,
              "The price history of this item is " + history);
          Store.logger.info(((Item) storeItems.getSelectedValue()).iv.name +
              " has price history " + ((Item) storeItems.getSelectedValue()).ih.priceHistory);
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
                  ((Item) storeItems.getSelectedValue()).ia.sellPrice);
          Store.logger.info(((Item) storeItems.getSelectedValue()).iv.name +
              " has current price " + ((Item) storeItems.getSelectedValue()).ia.sellPrice);
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
