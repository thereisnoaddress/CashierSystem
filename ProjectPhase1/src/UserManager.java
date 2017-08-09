import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that manages user instances
 */
public class UserManager implements Serializable {
  TimeManager tm = new TimeManager();
  ArrayList<User> users;
  ArrayList<String> loginHistory;
  HashMap<String, String> loginID = new HashMap<String, String>();
  private String filename = "users.ser";
  UserManager um;

  void serealize(){
    // Save the store to store.ser
    FileOutputStream fos;
    ObjectOutputStream out;
    try {
      fos = new FileOutputStream(filename);
      out = new ObjectOutputStream(fos);
      out.writeObject(this);
      out.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void register(String id, String password) {
    loginID.put(id, password);

  }

  public static void main(String[] args) {
    UserManager um = new UserManager();
  }

}
