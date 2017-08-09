import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class StartFrame {

  private JFrame mainFrame;

  StartFrame() throws IOException, ClassNotFoundException {

    mainFrame = new JFrame("User Chooser");
    mainFrame.setSize(300,300);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    panel.setVisible(true);

    JButton goButton = new JButton("Go");
    panel.add(goButton);

    mainFrame.add(panel);

    String[] users = {"Manager", "Reshelver", "Receiver", "Cashier"};

    JList userList = new JList(users);
    userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    userList.setLayoutOrientation(JList.VERTICAL);

    JScrollPane listScroller = new JScrollPane(userList);
    listScroller.setPreferredSize(new Dimension(250, 80));

    goButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (userList.getSelectedIndex() != -1) {
          if (userList.getSelectedValue().equals("Manager")) {
            try {
              ManagerFrame mf = new ManagerFrame();
              mainFrame.setVisible(false);
            } catch (IOException | ClassNotFoundException e1) {
              e1.printStackTrace();
            }
          } else if (userList.getSelectedValue().equals("Receiver")){
            try {
              ReceiverFrame rf = new ReceiverFrame();
              mainFrame.setVisible(false);
            } catch (IOException | ClassNotFoundException e1) {
              e1.printStackTrace();
            }
          } else if (userList.getSelectedValue().equals("Cashier")) {
            try {
              CashierFrame cf = new CashierFrame();
              mainFrame.setVisible(false);
            } catch (IOException | ClassNotFoundException e1) {
              e1.printStackTrace();
            }
          } else if (userList.getSelectedValue().equals("Reshelver")) {
            try {
              ReshelverFrame rf = new ReshelverFrame();
              mainFrame.setVisible(false);
            } catch (IOException | ClassNotFoundException e1) {
              e1.printStackTrace();
            }
          }

          Store.logger.info("A " +userList.getSelectedValue() + " has logged in.");
        }

      }


    });

    panel.add(listScroller);

  }


  public static void main(String[] args) throws IOException, ClassNotFoundException {
    StartFrame sf = new StartFrame();
  }
}
