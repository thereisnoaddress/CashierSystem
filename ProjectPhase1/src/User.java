import java.util.ArrayList;

/**
 * A class that creates User instances
 */
public class User {
  String id;
  String password;
  String name;
  String title;
  ArrayList<String> loginHistory;

  public User(String id, String password, String name, String title) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.title = title;
  }

  String getId() { return this.id; }
  String getPassword() { return this.password; }
  String getName() { return this.name; }
  String getTitle() { return this.title; }

  void setPassword(String password) { this.password = password; }
}
