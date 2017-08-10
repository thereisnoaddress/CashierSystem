import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ManagerFrame extends GenericFrame {

  JButton closedDailyTotals;
  JButton setSellPrice;
  JButton setSalePrice;
  JButton saleStart;
  JButton saleEnd;
  JButton salesHistory;

  ManagerFrame() throws IOException, ClassNotFoundException {
    super("Manager");
    prepareButtons();
  }


  private void prepareButtons(){

    JButton pendingOrders = new JButton("Show pending orders");
    pendingOrders.addActionListener(e -> {
      if (storeItems.getSelectedIndex() != -1) {

        String pending = ((Item) storeItems.getSelectedValue()).infoToString(
            ((Item) storeItems.getSelectedValue()).ih.pendingOrders);
        JOptionPane.showMessageDialog(null,
            "The pending orders of this item are " + pending);
        Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
            " has pending orders " + ((Item)storeItems.getSelectedValue()).ih.pendingOrders

        );

      }
    });

    JButton dailySales = new JButton("Show daily sales");
    dailySales.addActionListener(e -> {
      JOptionPane.showMessageDialog(null, "Today's sales revenue is " +
          s.fm.revenueToday);
      Store.logger.info("Today's sales revenue is " +  s.fm.revenueToday);

    });

    JButton dailyProfit = new JButton("Show daily profit");
    dailyProfit.addActionListener(e -> {
      JOptionPane.showMessageDialog(null, "Today's profit is "
          + s.fm.profitToday);
      Store.logger.info("Today's sales profit is " +  s.fm.profitToday);
    });

    closedDailyTotals = new JButton("Perform closing");
    closedDailyTotals.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        s.stm.closeDailyTotals();
        JOptionPane.showMessageDialog(null, "Store has been closed ");
        Store.logger.info("Store has been closed.");
      }

    });

    setSellPrice = new JButton("Change Sell Price");
    setSellPrice.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        double price = Double.parseDouble(JOptionPane.showInputDialog("Please enter a new price:"));
        JOptionPane.showMessageDialog(null, "The new sell price is: "
            + price);
        Store.logger.info("Sell price has been changed to: " +  price);
      }
    });

    setSalePrice = new JButton("Change Price During Sale");
    setSalePrice.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        double price = Double.parseDouble(JOptionPane.showInputDialog("Please enter a new price:"));
        JOptionPane.showMessageDialog(null, "The new sale price is: "
            + price);
        Store.logger.info("Sale price has been changed to: " +  price);
      }
    });

    saleStart = new JButton("Add Sale Start Date");
    saleStart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String start = JOptionPane.showInputDialog("Please enter the start date:");
        JOptionPane.showMessageDialog(null, "New sale start date is "
            + start);
        Store.logger.info("New sale start date is " +  start);
      }
    });

    saleEnd = new JButton("Add Sale End Date");
    saleEnd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String end = JOptionPane.showInputDialog("Please enter the end date:");
        JOptionPane.showMessageDialog(null, "New sale end date is  "
            + end);
        Store.logger.info("New sale end date is " +  end);
      }
    });

    salesHistory = new JButton("Show Sales History");
    salesHistory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        for (String entry : s.dailyProfits) {
          sb.append(entry);
        }
        String text = sb.toString();
        JOptionPane.showMessageDialog(null, text);
        Store.logger.info("Sales history was requested.");
      }
    });



    controlPanel.add(pendingOrders);
    controlPanel.add(dailyProfit);
    controlPanel.add(dailySales);
    controlPanel.add(closedDailyTotals);
    controlPanel.add(setSellPrice);
    controlPanel.add(setSalePrice);
    controlPanel.add(saleStart);
    controlPanel.add(saleEnd);
    controlPanel.add(salesHistory);




  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    ManagerFrame mf = new ManagerFrame();
  }

}
