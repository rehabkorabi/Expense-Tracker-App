public class Category {
    String description; // name of category
    double amount; // spending limit of category

    public Category(String description, double amount) { // constructor
        this.description = description; 
        this.amount = amount;
    }
    public void setDescription(String description) { // set name of category (taken from user)
        this.description = description;
    }

    public void setAmount(double amount) { // set the spending limit (taken from user)
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item: "+ description +", Limit = "+ amount+" AED."; // printing format of category
    }
}













