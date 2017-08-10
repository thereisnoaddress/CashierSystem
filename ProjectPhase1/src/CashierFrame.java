//import com.sun.codemodel.internal.JOp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CashierFrame extends GenericFrame {

  private DefaultListModel selling;
  protected double price;
  private JTextField total;



  CashierFrame() throws IOException, ClassNotFoundException {
    super("Cashier");
    prepareButtons();
    prepareList();
  }

  private void prepareButtons() {

    JButton showStock = new JButton("Check stock");
    showStock.addActionListener(e -> {
      if (storeItems.getSelectedIndex() != -1) {
        JOptionPane.showMessageDialog(null, "There are " +
            ((Item)storeItems.getSelectedValue()).getQuantity() + " in stock.");
        Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
            " has " + ((Item)storeItems.getSelectedValue()).getQuantity() + " in stock.");
        Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
            " has " + ((Item) storeItems.getSelectedValue()).getQuantity() + " in stock.");
      }
    });

    JButton sellButton = new JButton("Sell");
    sellButton.addActionListener(e -> {
      if (storeItems.getSelectedIndex() != -1) {
        if (((Item) storeItems.getSelectedValue()).getQuantity() > 0) {
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(
            "How many do you want to sell?",
            JOptionPane.YES_NO_OPTION));
          s.is.sell(((Item) storeItems.getSelectedValue()).getUPC(), quantity);
            selling.addElement(quantity + " x " + ((Item) storeItems.getSelectedValue()).getName());
            price += ((Item) storeItems.getSelectedValue()).getSellPrice();
            total.setText("Total:" + Double.toString(price));
            JOptionPane.showMessageDialog(null,
                "You now have " + ((Item) storeItems.getSelectedValue()).getQuantity()
                    + " left over");
            Store.logger.info(quantity + " of " + ((Item) storeItems.getSelectedValue()).getName()
                + " has been sold.");
          } else {
            JOptionPane.showMessageDialog(null, "You don't "
                + "have any in stock!");
          }
      }
    });

    JButton changeQuantity = new JButton("Change quantity");
    changeQuantity.addActionListener(e -> {
      if (storeItems.getSelectedIndex() != -1) {
        ((Item) storeItems.getSelectedValue()).iv.quantity = Integer.parseInt(
            JOptionPane.showInputDialog("There are currently " +
                ((Item) storeItems.getSelectedValue()).getQuantity() + " of this item."
                + "How many do you want to change it to?", JOptionPane.YES_NO_OPTION));
        JOptionPane.showMessageDialog(null, "Now there are " +
            ((Item) storeItems.getSelectedValue()).getQuantity());

        Store.logger.info(((Item) storeItems.getSelectedValue()) + " now has " +
            ((Item) storeItems.getSelectedValue()).getQuantity() + " in stock.");
      }
    });

    JButton checkOut = new JButton("Check out");
    checkOut.addActionListener(e -> {
      Double received = Double.parseDouble(JOptionPane.showInputDialog("Your total is " +
          price + ". Enter paid amount:", JOptionPane.YES_NO_OPTION));
      JOptionPane.showMessageDialog(null, "Your change is " +
          (received - price));
      Store.logger.info(price + " worth of goods has been sold.");
      price = 0;
      total.setText("0.0");
      selling.removeAllElements();
    });

    JButton checkSaleDates = new JButton("Check sale dates");
    checkSaleDates.addActionListener(e -> {
      if (storeItems.getSelectedIndex() != -1) {
        JOptionPane.showMessageDialog(null, s.sm.getSaleDuration(
            ((Item) storeItems.getSelectedValue()).getUPC()));
        Store.logger.info("Sale duration for " + ((Item) storeItems.getSelectedValue()).getName()
            + " is " + s.sm.getSaleDuration(((Item) storeItems.getSelectedValue()).getUPC()));
      }
    });

    JButton resetDay = new JButton("New day");
    resetDay.addActionListener(e -> {
      JOptionPane.showMessageDialog(null, "Today's revenue is "
          + s.fm.revenueToday + " and today's profit is " + s.fm.profitToday + ".");
      Store.logger.info("A day has ended with profit " + s.fm.profitToday + ". A new day "
          + "has begun!");
      s.fm.revenueToday = 0;
      s.fm.profitToday = 0;
    });

    JButton scanOut = new JButton("Scan out");
    scanOut.addActionListener(e -> {
      try {
        openScanner();
        String UPC = JOptionPane.showInputDialog("What is the UPC of the "
            + "item that you want to sell?");
        if (s.UPCToItem.containsKey(UPC)) {
          int quantity = Integer.parseInt(JOptionPane.showInputDialog(
              "How many do you want to sell?",
              JOptionPane.YES_NO_OPTION));
          if (s.UPCToItem.get(UPC).getQuantity() > 0) {
            s.is.sell(UPC, quantity);
            selling.addElement(s.UPCToItem.get(UPC).getName());
            price += (s.UPCToItem.get(UPC).getSellPrice());
            total.setText("Total:" + Double.toString(price));
            JOptionPane.showMessageDialog(null,
                "You now have " + (s.UPCToItem.get(UPC).getQuantity()) + " left over");
            Store.logger.info(quantity + " of " + (s.UPCToItem.get(UPC).getName())
                + " has been sold.");
          } else {
            JOptionPane.showMessageDialog(null, "You don't "
                + "have any in stock!");
          }

        } else {
          JOptionPane.showMessageDialog(null, "You don't have this item!");
          Store.logger.info("Tried to scan out an item that was not in stock.");

        }

      } catch (ScriptException e1) {
        e1.printStackTrace();
      }

    });

    controlPanel.add(sellButton);
    controlPanel.add(changeQuantity);
    controlPanel.add(checkOut);
    controlPanel.add(checkSaleDates);
    controlPanel.add(resetDay);
    controlPanel.add(showStock);
    controlPanel.add(scanOut);



  }



  private void prepareList() {

    selling = new DefaultListModel();

    total = new JTextField(20);
    total.setText("0.0");
    controlPanel.add(total);

    JList sellingItems = new JList(selling);
    sellingItems.setVisibleRowCount(10);
    JScrollPane scrollpane = new JScrollPane(sellingItems);
    controlPanel.add(scrollpane);
  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    CashierFrame cf = new CashierFrame();
  }
}
