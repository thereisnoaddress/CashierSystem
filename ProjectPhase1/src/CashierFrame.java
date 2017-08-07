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


  CashierFrame() throws IOException, ClassNotFoundException {
    super("Cashier");
    prepareButtons();
    prepareList();
  }

  private void prepareButtons(){

    sellButton = new JButton("Sell");
    sellButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {
          int quantity = Integer.parseInt(JOptionPane.showInputDialog(
              "How many do you want to sell?",
              JOptionPane.YES_NO_OPTION));
          s.is.sell(((Item)storeItems.getSelectedValue()).UPC,
              quantity);
          selling.addElement(((Item) storeItems.getSelectedValue()).name);
          price += ((Item) storeItems.getSelectedValue()).sellPrice;
          total.setText("Total:" + Double.toString(price));
          JOptionPane.showMessageDialog(null, "You now have " +((Item) storeItems.getSelectedValue()).quantity
              + " left over");
          textField.setText("");
          System.out.println("revenue " + s.fm.revenueToday);
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
        // TODO: Implement financial manager stuff
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
              ((Item) storeItems.getSelectedValue()).UPC
          ));
        }
      }
    });


    controlPanel.add(sellButton);
    controlPanel.add(changeQuantity);
    controlPanel.add(checkOut);
    controlPanel.add(checkSaleDates);

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
