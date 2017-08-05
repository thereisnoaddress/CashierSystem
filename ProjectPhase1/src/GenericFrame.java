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
      mainFrame.setLayout(new FlowLayout());
      mainFrame.setLocationRelativeTo(null);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      controlPanel.setSize(800,400);

      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);
    }

    private void showButtons(){
      showButton = new JButton("Show");
      showButton.setLayout(new BorderLayout());
      showButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (storeItems.getSelectedIndex() != -1) {
            textField.setText("Index selected: " + storeItems.getSelectedValue());
          }

        }
      });

      sellButton = new JButton("sell");
      sellButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (storeItems.getSelectedIndex() != -1) {
            //textField.setText("Index selected: " + storeItems.getSelectedIndex());
            System.out.println(((Item)storeItems.getSelectedValue()).quantity);
            s.is.sell(((Item)storeItems.getSelectedValue()).UPC);
            System.out.println(((Item)storeItems.getSelectedValue()).quantity);
          }

        }
      });

      controlPanel.add(showButton);
      controlPanel.add(sellButton);
    }

    private void showTextField(){
      textField = new JTextField(20);
      controlPanel.add(textField);
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