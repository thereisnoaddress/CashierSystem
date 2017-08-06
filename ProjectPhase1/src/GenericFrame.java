  import java.awt.*;
  import java.awt.event.*;
  import java.io.IOException;
  import javax.swing.*;

  public class GenericFrame {
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JButton showButton;
    private JButton sellButton;

    private JList storeItems;
    private JTextField textField;
    private JTextField textField2;
    private Store s;

    private GenericFrame() throws IOException, ClassNotFoundException {
      prepareGUI();
      startStore();
      showList();
      showButtons();
      showTextField();

    }

    private void startStore() throws IOException, ClassNotFoundException {
      StoreSimulator ss = new StoreSimulator();
      s = ss.StoreSimulator();
    }

    private void prepareGUI(){

      mainFrame = new JFrame("Generic Frame");
      mainFrame.setSize(800,400);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setLocationRelativeTo(null);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      controlPanel.setSize(mainFrame.getWidth(),mainFrame.getHeight());

      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);
    }

    private void showButtons(){
      showButton = new JButton("Show");
      showButton.setLayout(new BorderLayout());
      showButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (storeItems.getSelectedIndex() != -1) {
            textField.setText("You have " + ((Item) storeItems.getSelectedValue()).quantity + ""
                + " right now.");
          }

        }
      });

      sellButton = new JButton("sell");
      sellButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (storeItems.getSelectedIndex() != -1) {
            //textField.setText("Index selected: " + storeItems.getSelectedIndex());

            s.is.sell(((Item)storeItems.getSelectedValue()).UPC,
                Integer.parseInt(textField2.getText()));
            textField.setText("You now have " +((Item) storeItems.getSelectedValue()).quantity
             + " left over");
          }

        }
      });

      controlPanel.add(showButton);
      controlPanel.add(sellButton);
    }

    private void showTextField(){
      textField = new JTextField(20);
      textField2 = new JTextField(30);
      textField2.setText("Enter how many you want to sell!");
      controlPanel.add(textField);
      controlPanel.add(textField2);
    }

    private void showList() {


      DefaultListModel items = new DefaultListModel();

      for (Item i : s.itemsList) {
        items.addElement(i);
      }


      storeItems = new JList(items);

      storeItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      storeItems.setSelectedIndex(0);
      storeItems.setVisibleRowCount(10);
      storeItems.setLocation(300,0);

      JScrollPane storeItemsScrollPane = new JScrollPane(storeItems);

      controlPanel.add(storeItemsScrollPane);
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
      GenericFrame  swingControlDemo = new GenericFrame();
    }

  }