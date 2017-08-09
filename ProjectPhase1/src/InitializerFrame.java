import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


public class InitializerFrame  {
  protected static Store s;

  InitializerFrame(Store store) throws IOException, ClassNotFoundException {
    this.s = store;
    prepGUI();
  }


  void prepGUI() throws IOException, ClassNotFoundException {
    JFrame mainFrame;

    // Default for all
    mainFrame = new JFrame("Store Initialization");
    mainFrame.setSize(350, 450);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLocationRelativeTo(null);

    // Default panel
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.insets = new Insets(9, 9, 9, 9);

    // Add the elements to the panel
    // Creates the login portion
    JLabel title = new JLabel("Please enter the StoreItems.txt path");
    JLabel pathPrompt = new JLabel("Path");
    JTextField path = new JTextField(10);
    JButton pathButton = new JButton("Enter");

    gc.gridx = 1;
    gc.gridy = 0;
    panel.add(title, gc);

    gc.gridx = 0;
    gc.gridy = 1;
    panel.add(pathPrompt, gc);

    gc.gridx = 1;
    gc.gridy = 1;
    panel.add(path, gc);

    gc.gridx = 1;
    gc.gridy = 2;
    panel.add(pathButton, gc);


    // Default packing
    panel.setVisible(true);
    mainFrame.add(panel);
    mainFrame.setVisible(true);
    JOptionPane.showMessageDialog(mainFrame, "This is the first time you are using the program. Please enter the path for StoreItems.txt");



    pathButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Access the input
        String pathText = path.getText();

        // Use pathText here for StoreItems.txt initialization!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Once the above line is executed: LoginManager lm = new LoginManager(s);





      }
    });
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    InitializerFrame inf = new InitializerFrame(s);
  }
}

