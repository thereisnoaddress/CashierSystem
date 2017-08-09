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

public class LoginFrame  {
  UserManager um;

  LoginFrame() throws IOException, ClassNotFoundException {
    UserManager um = new UserManager();
    prepGUI();
  }


  void prepGUI() {
    JFrame mainFrame;

    // Default for all
    mainFrame = new JFrame("Please sign in or register");
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
    JLabel loginTitle = new JLabel("Existing Users");
    JLabel loginPrompt = new JLabel("User id");
    JTextField login = new JTextField(10);
    JLabel passwordPrompt = new JLabel("Password");
    JPasswordField loginPassword = new JPasswordField(10);
    JButton loginButton = new JButton("Login");

    gc.gridx = 1;
    gc.gridy = 0;
    panel.add(loginTitle, gc);

    gc.gridx = 0;
    gc.gridy = 1;
    panel.add(loginPrompt, gc);

    gc.gridx = 1;
    gc.gridy = 1;
    panel.add(login, gc);

    gc.gridx = 0;
    gc.gridy = 2;
    panel.add(passwordPrompt, gc);

    gc.gridx = 1;
    gc.gridy = 2;
    panel.add(loginPassword, gc);

    gc.gridx = 1;
    gc.gridy = 3;
    panel.add(loginButton, gc);

    // Creates the registration portion
    JLabel registrationTitle = new JLabel("New Users");
    JLabel registrationPrompt = new JLabel("User id");
    JTextField registration = new JTextField(10);
    JLabel rPasswordPrompt = new JLabel("Password");
    JPasswordField registrationPassword = new JPasswordField(10);
    JButton registerButton = new JButton("Register");

    gc.gridx = 1;
    gc.gridy = 6;
    panel.add(registrationTitle, gc);

    gc.gridx = 0;
    gc.gridy = 7;
    panel.add(registrationPrompt, gc);

    gc.gridx = 1;
    gc.gridy = 7;
    panel.add(registration, gc);

    gc.gridx = 0;
    gc.gridy = 8;
    panel.add(rPasswordPrompt, gc);

    gc.gridx = 1;
    gc.gridy = 8;
    panel.add(registrationPassword, gc);

    gc.gridx = 1;
    gc.gridy = 9;
    panel.add(registerButton, gc);

    // Default packing
    panel.setVisible(true);
    mainFrame.add(panel);
    mainFrame.setVisible(true);

    // Listener1
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Access the input
          String loginText = login.getText();
          String loginPasswordText = loginPassword.getText();

          if (loginText.equals("") || loginPasswordText.equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter the id and password!");
          } else {
            try {
              mainFrame.setVisible(false);
              if (um.login(loginText, loginPasswordText)) {
                StartFrame sf = new StartFrame();
                mainFrame.setVisible(false);

              }
            } catch (IOException | ClassNotFoundException el) {
              JOptionPane.showMessageDialog(mainFrame, "Invalid id or password!");
            }


          }
        }
      }
    );

    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Access the input
        String registrationText = registration.getText();
        String registrationPasswordText = registrationPassword.getText();

        if (registrationText.equals("") || registrationPasswordText.equals("")) {
          JOptionPane.showMessageDialog(mainFrame, "Please enter the id and password!");
        } else {
          try {
            StartFrame sf = new StartFrame();
//            Store.logger.info("logging some info");
            mainFrame.setVisible(false);

          } catch (IOException | ClassNotFoundException el) {
            JOptionPane.showMessageDialog(mainFrame, "Cannot open StartFrame!");
          }
        }
      }
    });
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    LoginFrame lf = new LoginFrame();
  }
}

