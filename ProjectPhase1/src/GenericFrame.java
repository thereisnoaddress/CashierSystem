  import java.awt.*;
  import java.awt.event.*;
  import java.io.IOException;
  import javax.swing.*;

  public class GenericFrame {
    protected JFrame mainFrame;
    protected JPanel controlPanel;
    protected JButton showButton;


    protected JList storeItems;
    protected JTextField textField;

    protected Store s;

    GenericFrame(String title) throws IOException, ClassNotFoundException {
      prepareGUI();
      startStore();
      showList();
      showButtons();
      showTextField();
      mainFrame.setTitle(title);

    }

    private void startStore() throws IOException, ClassNotFoundException {
      StoreSimulator ss = new StoreSimulator();
      s = ss.StoreSimulator();
    }


    private void prepareGUI(){

      mainFrame = new JFrame("Generic Frame");
      mainFrame.setSize(300,500);
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



      controlPanel.add(showButton);

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
      GenericFrame  swingControlDemo = new GenericFrame("Generic");
    }

  }