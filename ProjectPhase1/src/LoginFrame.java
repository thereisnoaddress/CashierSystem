import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * A frame that either logs the user into the system or lets the user register to enter the system.
 */

public class LoginFrame {

  private UserManager um;
  private boolean current;


  private LoginFrame() throws IOException, ClassNotFoundException {

    prepGUI();
    current = deserealize();

  }


  private void prepGUI() {
    JFrame mainFrame;

    // Default for all
    mainFrame = new JFrame("Please sign in or register");
    mainFrame.setSize(350, 450);
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
    mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        um.serealize();
        System.out.println("Users saved.");
      }
    });

    // Listener1
    loginButton.addActionListener(e -> {
      // Access the input
      String loginText = login.getText();
      String loginPasswordText = loginPassword.getText();
      if (current) {
        if (loginText.equals("") || loginPasswordText.equals("")) {
          JOptionPane.showMessageDialog(mainFrame, "Please enter the id and password!");
        } else if (um.loginID.containsKey(loginText) && um.loginID.get(loginText)
            .equals(loginPasswordText)) {
          try {
            StartFrame sf = new StartFrame();
          } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
          }
          mainFrame.setVisible(false);

        } else {
          JOptionPane.showMessageDialog(null, "You messed up");
        }
      }
    });

        registerButton.addActionListener(e -> {
          // Access the input
          String registrationText = registration.getText();
          String registrationPasswordText = registrationPassword.getText();

          if (registrationText.equals("") || registrationPasswordText.equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter the id and password!");
          } else {
            try {
              um.register(registrationText, registrationPasswordText);
              um.serealize();
              StartFrame sf = new StartFrame();
              mainFrame.setVisible(false);

            } catch (IOException | ClassNotFoundException el) {
              JOptionPane.showMessageDialog(mainFrame, "Cannot open StartFrame!");
            }
          }
        });
      }


      private boolean deserealize() {
        String filename = "users.ser";

        FileInputStream fis;
        ObjectInputStream in;

        try {
          fis = new FileInputStream(filename);
          in = new ObjectInputStream(fis);
          um = (UserManager) in.readObject();
          in.close();
          return true;

        } catch (FileNotFoundException ex) {
          this.um = new UserManager();

        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }

        return false;

      }


      public static void main(String[] args) throws IOException, ClassNotFoundException {
        LoginFrame lf = new LoginFrame();
      }
    }

