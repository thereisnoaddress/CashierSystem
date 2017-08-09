import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ManagerFrame extends GenericFrame {
  ManagerFrame() throws IOException, ClassNotFoundException {
    super("Manager");
    prepareButtons();
  }

  protected JButton pendingOrders;
  protected JButton dailySales;
  protected JButton dailyProfit;


  private void prepareButtons(){

    pendingOrders = new JButton("Show pending orders");
    pendingOrders.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (storeItems.getSelectedIndex() != -1) {

          String pending = ((Item) storeItems.getSelectedValue()).infoToString(
              ((Item) storeItems.getSelectedValue()).ih.pendingOrders);
          JOptionPane.showMessageDialog(null,
              "The pending orders of this item are " + pending);
          Store.logger.info(((Item) storeItems.getSelectedValue()).getName() +
              " has pending orders " + ((Item)storeItems.getSelectedValue()).ih.pendingOrders);

        }
      }
    });

    dailySales = new JButton("Show daily sales");
    dailySales.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Today's financials are " +
            s.dailyProfits.get(-1));
        Store.logger.info("Today's financials are " +  s.dailyProfits.get(-1));

      }
    });


    dailyProfit = new JButton("Show daily profit");
    dailyProfit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Today's financials are "
            + s.dailyProfits.get(-1));
        Store.logger.info("Today's financials are " +  s.dailyProfits.get(-1));
      }
    });


    controlPanel.add(pendingOrders);
    controlPanel.add(dailyProfit);
    controlPanel.add(dailySales);


  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    ManagerFrame mf = new ManagerFrame();
  }

}
