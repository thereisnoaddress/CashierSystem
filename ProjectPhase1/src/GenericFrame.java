import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GenericFrame {
  private JFrame mainFrame;
  private JPanel controlPanel;

  public GenericFrame(){
    prepareGUI();
  }

  private void prepareGUI(){
    mainFrame = new JFrame("Generic Frame");
    mainFrame.setSize(800,400);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new FlowLayout());


    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    controlPanel.setSize(800,400);

    mainFrame.add(controlPanel);
    mainFrame.setVisible(true);
  }

  public void showButtons(){
    JButton showButton = new JButton("Show");
    showButton.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                      System.out.println("hello");
                                   }
                                 });

    showButton.setLayout(new BorderLayout());
    showButton.setLocation(0, 200);


    JButton viewButton = new JButton("view");
    viewButton.setLocation(showButton.getX(), showButton.getY() + 100);


    controlPanel.add(showButton);
    controlPanel.add(viewButton);
  }

  public void showList() {

    DefaultListModel items = new DefaultListModel();

    items.addElement("chocolate");
    items.addElement("chips");
    items.addElement("milk");

    JList storeItems = new JList(items);
    storeItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    storeItems.setSelectedIndex(0);
    storeItems.setVisibleRowCount(10);
    storeItems.setLocation(300,0);

    JScrollPane storeItemsScrollPane = new JScrollPane(storeItems);

/*
    showButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String data = "";
        if (storeItems.getSelectedIndex() != -1) {
          data = "Item Selected: " + storeItems.getSelectedValue();
          statusLabel.setText(data);
        }
        statusLabel.setText(data);
      }

    });
    */

    controlPanel.add(storeItemsScrollPane);
  }



  public static void main(String[] args){
    GenericFrame  swingControlDemo = new GenericFrame();
    //swingControlDemo.showList();
    swingControlDemo.showButtons();
  }

}