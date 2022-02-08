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

    public int getItemID() {
        return this.itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getVendorID() {
        return this.vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    

}