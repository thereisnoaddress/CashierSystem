import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

public class GenericFrame {

  private JFrame mainFrame;
  JPanel controlPanel;
  DefaultListModel items;
  private StoreSimulator ss;
  JList storeItems;
  protected Store s;

  GenericFrame(String title) throws IOException, ClassNotFoundException {

    startStore();
    prepareGUI();
    showList();


    mainFrame.setTitle(title);
    mainFrame.setVisible(true);
  }

  private void startStore() throws IOException, ClassNotFoundException {
    ss = new StoreSimulator();
    s = ss.StoreSimulator();
  }


  private void prepareGUI() {

    mainFrame = new JFrame("Generic Frame");
    mainFrame.setSize(300, 600);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        ss.saveStore();
        Store.logger.info(mainFrame.getTitle() + " has logged out and the store has been saved.");
        try {
          StartFrame sf = new StartFrame();
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    });

    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    controlPanel.setSize(mainFrame.getWidth(), mainFrame.getHeight());

    mainFrame.add(controlPanel);

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

  void openScanner() throws ScriptException {
    String script = "tell application \"Kinoni Barcode Reader\" to launch";
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("AppleScript");
    engine.eval(script);
  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    GenericFrame swingControlDemo = new GenericFrame("Generic");
  }

}