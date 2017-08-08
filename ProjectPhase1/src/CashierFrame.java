import com.sun.codemodel.internal.JOp;
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

  protected JButton sellButton;
  protected DefaultListModel selling;
  protected JList sellingItems;
  protected double price;
  protected JTextField total;
  protected JButton changeQuantity;
  protected JButton checkOut;
  protected JButton checkSaleDates;
  protected JTextField textField;
  protected JButton resetDay;
  protected JButton showStock;
  protected JButton scanOut;


  CashierFrame() throws IOException, ClassNotFoundException {
    super("Cashier");
    prepareButtons();
    prepareList();
  }

  private void prepareButtons() {

    showStock = new JButton("Check stock");
    showStock.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null, "There are " +
              ((Item) storeItems.getSelectedValue()).quantity + " in stock.");
          Store.logger.info(((Item) storeItems.getSelectedValue()).name +
              " has " + ((Item) storeItems.getSelectedValue()).quantity + " in stock.");
        }
      }
    });

    sellButton = new JButton("Sell");
    sellButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          int quantity = Integer.parseInt(JOptionPane.showInputDialog(
              "How many do you want to sell?",
              JOptionPane.YES_NO_OPTION));
          if (((Item) storeItems.getSelectedValue()).quantity > 0) {
            s.is.sell(((Item) storeItems.getSelectedValue()).UPC,
                quantity);
            selling.addElement(((Item) storeItems.getSelectedValue()).name);
            price += ((Item) storeItems.getSelectedValue()).sellPrice;
            total.setText("Total:" + Double.toString(price));
            JOptionPane.showMessageDialog(null,
                "You now have " + ((Item) storeItems.getSelectedValue()).quantity
                    + " left over");
          } else {
            JOptionPane.showMessageDialog(null, "You don't "
                + "have any in stock!");
          }
        }
      }
    });

    changeQuantity = new JButton("Change quantity");
    changeQuantity.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          ((Item) storeItems.getSelectedValue()).quantity = Integer.parseInt(
              JOptionPane.showInputDialog("There are currently " +
                  ((Item) storeItems.getSelectedValue()).quantity + " of this item."
                  + "How many do you want to change it to?", JOptionPane.YES_NO_OPTION));
          JOptionPane.showMessageDialog(null, "Now there are " +
              ((Item) storeItems.getSelectedValue()).quantity);

          Store.logger.info(((Item) storeItems.getSelectedValue()) + " now has " +
              ((Item) storeItems.getSelectedValue()).quantity + " in stock.");
        }
      }
    });

    checkOut = new JButton("Check out");
    checkOut.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Double received = Double.parseDouble(JOptionPane.showInputDialog("Your total is " +
            price + ". Enter paid amount:", JOptionPane.YES_NO_OPTION));
        JOptionPane.showMessageDialog(null, "Your change is " +
            (received - price));
        Store.logger.info(price + " worth of goods has been sold.");
        price = 0;
        total.setText("0.0");
        selling.removeAllElements();
      }
    });

    checkSaleDates = new JButton("Check sale dates");
    checkSaleDates.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null, s.sm.getSaleDuration(
              ((Item) storeItems.getSelectedValue()).UPC));
          Store.logger.info("Sale duration for " + ((Item) storeItems.getSelectedValue()).name
              + " is " + s.sm.getSaleDuration(((Item) storeItems.getSelectedValue()).UPC));
        }
      }
    });

    resetDay = new JButton("New day");
    resetDay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Today's revenue is"
            + s.fm.revenueToday + " and today's profit is " + s.fm.profitToday + ".");
        Store.logger.info("A day has ended with profit " + s.fm.profitToday + ". A new day"
            + "has begun!");
        s.fm.revenueToday = 0;
        s.fm.profitToday = 0;

      }
    });

    scanOut = new JButton("Scan out");
    scanOut.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          openScanner();
          String UPC = JOptionPane.showInputDialog("What is the UPC of the "
              + "item that you want to sell?");
          if (s.UPCToItem.containsKey(UPC)) {
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(
                "How many do you want to sell?",
                JOptionPane.YES_NO_OPTION));
            if (s.UPCToItem.get(UPC).quantity > 0) {
              s.is.sell(UPC, quantity);
              selling.addElement(s.UPCToItem.get(UPC).name);
              price += (s.UPCToItem.get(UPC).sellPrice);
              total.setText("Total:" + Double.toString(price));
              JOptionPane.showMessageDialog(null,
                  "You now have " + (s.UPCToItem.get(UPC).quantity) + " left over");
              Store.logger.info(quantity + " of " + (s.UPCToItem.get(UPC).name)
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

    sellingItems = new JList(selling);
    sellingItems.setVisibleRowCount(10);
    JScrollPane scrollpane = new JScrollPane(sellingItems);
    controlPanel.add(scrollpane);
  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    CashierFrame cf = new CashierFrame();
  }
}
