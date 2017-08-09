import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that manages user instances
 */
public class UserManager implements Serializable {
  Store s;
  ArrayList<User> users;
  ArrayList<String> loginHistory;

  public UserManager(Store s) {
    this.s = s;
  }

  boolean login(String id, String password) {
    for (User u : users) {
      if(u.getId().equals(id) && u.getPassword().equals(password)) {
        u.loginHistory.add("Login on: " + s.tm.timeStamp());
        this.loginHistory.add(u.getName() + " logged in on: " + s.tm.timeStamp());
        return true;
      }
    }
    return false;
  }

  void logout(User u) {
    u.loginHistory.add("Logout on: " + s.tm.timeStamp());
    this.loginHistory.add(u.getName() + " logged out on: " + s.tm.timeStamp());
  }

}
