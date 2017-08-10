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
 * A class that manages users and their passwords (user logins) and serializes all of them as
 * a hashmap.
 */

class UserManager implements Serializable {

  // Hashmap that stores users to their passwords

  HashMap<String, String> loginID = new HashMap<>();

  void serealize(){
    // Save the UserManager to store.ser
    FileOutputStream fos;
    ObjectOutputStream out;
    try {
      String filename = "users.ser";
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


}
