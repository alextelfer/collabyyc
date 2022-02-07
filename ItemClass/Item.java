public class Item {

    int itemID;
    int vendorID;
    String name;
    double price;
    int quantity;
    String category;
    
    //a class that has the Connection Pool(can be renamed)
    //ItemService is = new ItemService();

    public void addItem(int itemID, int vendorID, String name, double price, int quantity, String category) {
        //adds a new item to the database
        //is.insert(itemID, vendorID, name, price, quantity, category);
    }

}