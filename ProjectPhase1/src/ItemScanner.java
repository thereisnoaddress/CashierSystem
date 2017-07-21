/**
 * Scans stuff
 */
class ItemScanner {

  void scanIn(String UPC, Store s) {

    if (s.getConsolidatedItem(UPC) != null) {
      s.getConsolidatedItem(UPC).setQuantity(s.getConsolidatedItem(UPC).getQuantity() + 1);
    } else {
      System.out.println("This UPC is not associated with any item in store!");
    }
  }

  void scanOut(String UPC, Store s) {
    if (s.getConsolidatedItem(UPC) != null) {
      s.getConsolidatedItem(UPC).setQuantity(s.getConsolidatedItem(UPC).getQuantity() - 1);
    } else {
      System.out.println("This UPC is not associated with any item in store!");
    }
  }

  void scanInMultiple(String UPC, int quantity, Store s) {
    if (s.getConsolidatedItem(UPC) != null) {
      s.getConsolidatedItem(UPC).setQuantity(s.getConsolidatedItem(UPC).getQuantity() + quantity);
    } else {
      System.out.println("This UPC is not associated with any item in store!");
    }
  }

  void scanOutMultiple(String UPC, int quantity, Store s) {
    if (s.getConsolidatedItem(UPC) != null) {
      s.getConsolidatedItem(UPC).setQuantity(s.getConsolidatedItem(UPC).getQuantity() - quantity);
    } else {
      System.out.println("This UPC is not associated with any item in store!");
    }
  }
}

