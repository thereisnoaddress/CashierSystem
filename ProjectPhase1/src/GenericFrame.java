import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

public class GenericFrame {

  JFrame mainFrame;
  JPanel controlPanel;
  DefaultListModel items;
  UserManager um;
  StoreSimulator ss;


  protected JList storeItems;

  protected Store s;

  GenericFrame(String title) throws IOException, ClassNotFoundException {
    prepareGUI();
    startStore();
    showList();

    mainFrame.setTitle(title);

  }

  private void startStore() throws IOException, ClassNotFoundException {
    ss = new StoreSimulator();
    s = ss.StoreSimulator();
  }


  private void prepareGUI() {

    mainFrame = new JFrame("Generic Frame");
    mainFrame.setSize(300, 500);
    //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        ss.saveStore();
        Store.logger.info(mainFrame.getTitle() + " has logged out and the store has been saved.");
        System.exit(0);
      }
    });

    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    controlPanel.setSize(mainFrame.getWidth(), mainFrame.getHeight());

    mainFrame.add(controlPanel);
    mainFrame.setVisible(true);
  }


  private void showList() {

    items = new DefaultListModel();

    addToList();

    storeItems = new JList(items);

    storeItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    storeItems.setSelectedIndex(0);
    storeItems.setVisibleRowCount(15);
    storeItems.setLocation(300, 0);

    JScrollPane storeItemsScrollPane = new JScrollPane(storeItems);

    controlPanel.add(storeItemsScrollPane);
  }

  void addToList() {
    for (Item i : s.itemsList) {
      if (i.getQuantity() > 0) {
        items.addElement(i);
      }
    }
  }

  protected void openScanner() throws ScriptException {
    String script = "tell application \"Kinoni Barcode Reader\" to launch";
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("AppleScript");
    engine.eval(script);
  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    GenericFrame swingControlDemo = new GenericFrame("Generic");
  }

}