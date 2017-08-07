  import java.awt.*;
  import java.awt.event.*;
  import java.io.IOException;
  import javax.swing.*;

  public class GenericFrame {
    protected JFrame mainFrame;
    protected JPanel controlPanel;
    protected DefaultListModel items;


    protected JList storeItems;


    protected Store s;

    GenericFrame(String title) throws IOException, ClassNotFoundException {
      prepareGUI();
      startStore();
      showList();

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




    private void showList() {


      items = new DefaultListModel();

      addToList();


      storeItems = new JList(items);

      storeItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      storeItems.setSelectedIndex(0);
      storeItems.setVisibleRowCount(10);
      storeItems.setLocation(300,0);

      JScrollPane storeItemsScrollPane = new JScrollPane(storeItems);

      controlPanel.add(storeItemsScrollPane);
    }

    void addToList(){
      for (Item i : s.itemsList) {
        items.addElement(i);
      }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
      GenericFrame  swingControlDemo = new GenericFrame("Generic");
    }

  }