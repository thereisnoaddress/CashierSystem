//import com.sun.codemodel.internal.JOp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
  protected JButton scanIn;


  CashierFrame() throws IOException, ClassNotFoundException {
    super("Cashier");
    prepareButtons();
    prepareList();
  }

  private void prepareButtons(){

    showStock = new JButton("Check stock");
    showStock.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          JOptionPane.showMessageDialog(null, "There are " +
              ((Item)storeItems.getSelectedValue()).getQuantity() + " in stock.");
          Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
              " has " + ((Item)storeItems.getSelectedValue()).getQuantity() + " in stock.");
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
          if (((Item)storeItems.getSelectedValue()).getQuantity() > 0) {
            s.is.sell(((Item) storeItems.getSelectedValue()).getUPC(),
                quantity);
            selling.addElement(((Item) storeItems.getSelectedValue()).getName());
            price += ((Item) storeItems.getSelectedValue()).getSellPrice();
            total.setText("Total:" + Double.toString(price));
            JOptionPane.showMessageDialog(null,
                "You now have " + ((Item) storeItems.getSelectedValue()).getQuantity()
                    + " left over");
            Store.logger.info(quantity + " of " + ((Item)storeItems.getSelectedValue()).getName()
            + " has been sold.");
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
          ((Item) storeItems.getSelectedValue()).iv.quantity = Integer.parseInt(
              JOptionPane.showInputDialog("There are currently " +
                      ((Item) storeItems.getSelectedValue()).getQuantity() + " of this item."
                  + "How many do you want to change it to?", JOptionPane.YES_NO_OPTION));
          JOptionPane.showMessageDialog(null, "Now there are " +
              ((Item) storeItems.getSelectedValue()).getQuantity());

          Store.logger.info(((Item) storeItems.getSelectedValue()) + " now has " +
              ((Item) storeItems.getSelectedValue()).getQuantity() + " in stock.");
        }
      }
    });

    checkOut = new JButton("Check out");
    checkOut.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Double received = Double.parseDouble(JOptionPane.showInputDialog("Your total is " +
            price +". Enter paid amount:", JOptionPane.YES_NO_OPTION));
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
              ((Item) storeItems.getSelectedValue()).getUPC()));
          Store.logger.info("Sale duration for " + ((Item) storeItems.getSelectedValue()).getName()
           + " is " + s.sm.getSaleDuration(((Item) storeItems.getSelectedValue()).getUPC()));
        }
      }
    });

    resetDay = new JButton("New day");
    resetDay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Today's financials are: "
            + s.dailyProfits.get(-1) + ".");
        Store.logger.info("A day has ended with financials of: " + s.dailyProfits.get(-1) + ". A new day"
            + "has begun!");
      }
    });

    scanIn = new JButton("Scan in");
    scanIn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
            "This is where you put your scanned UPC.");
      }
    });


    controlPanel.add(sellButton);
    controlPanel.add(changeQuantity);
    controlPanel.add(checkOut);
    controlPanel.add(checkSaleDates);
    controlPanel.add(resetDay);
    controlPanel.add(showStock);
    controlPanel.add(scanIn);

  }

  private void showTextField(){
    textField = new JTextField(20);

    controlPanel.add(textField);

  }


  private void prepareList(){

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
