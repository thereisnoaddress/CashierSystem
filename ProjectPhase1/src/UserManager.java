import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that manages user instances
 */
public class UserManager implements Serializable {
  TimeManager tm = new TimeManager();
  ArrayList<User> users;
  ArrayList<String> loginHistory;

  boolean login(String id, String password) {
    for (User u : users) {
      if(u.getId().equals(id) && u.getPassword().equals(password)) {
        u.loginHistory.add("Login on: " + tm.timeStamp());
        this.loginHistory.add(u.getId() + " logged in on: " + tm.timeStamp());
        return true;
      }
    }
    return false;
  }

  void logout(User u) {
    u.loginHistory.add("Logout on: " + tm.timeStamp());
    this.loginHistory.add(u.getId() + " logged out on: " + tm.timeStamp());
  }

  void register(String id, String password) {
    User u = new User(id, password);
    users.add(u);
    login(id, password);
  }

}
