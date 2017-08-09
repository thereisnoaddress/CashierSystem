import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that creates User instances
 */
public class User implements Serializable {
  String id;
  String password;
  ArrayList<String> loginHistory;

  public User(String id, String password) {
    this.id = id;
    this.password = password;
  }

  String getId() { return this.id; }
  String getPassword() { return this.password; }
}
