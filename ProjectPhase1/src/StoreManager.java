public class StoreManager {
  protected Store s;
  protected TimeManager tm = new TimeManager();

  protected ItemScanner(Store store, OrderManager orderManager, FinancialManager financialManager) {
    s = store;
  }





  // This will go into the initial main method
  ////////////////////////////////////////////////////////protected void processData(String fileName) throws IOException {

    try {
    BufferedReader fileInput = new BufferedReader(new FileReader(fileName));
    String line = fileInput.readLine();

    while (line != null) {
      String[] data = line.split("\\,");
      Item temp = new Item(data[0], data[1], data[2], data[3],
          Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]),
          Integer.parseInt(data[7]), Integer.parseInt(data[8]), data[9]);
      dataList.add(temp);
      line = fileInput.readLine();

    }
  }catch (IOException e) {
    logger.log(Level.SEVERE, "Cannot read from input.", e);
  }
}

//TODO: for when the program closes; new itemslist file.
/////////////////////////////////////////////////////////////////////////////protected void SaveData(String fileName) {

  }

      /////////////////////////////////////////////////////////////////////////////////////public void showInventory() {
      for (Item i : dataList) {
      System.out.println(i);
      }
      }




      void resetStore() {
      ArrayList<Item> dataList = new ArrayList<Item>();
    ArrayList<Item> itemsList = new ArrayList<>();
    ArrayList<Item> unshelvedItemsList = new ArrayList<>();
    Map<String, Item> items;
    String log = "The store has been successfully reset on " + tm.timeStamp();
    }




    double
}
