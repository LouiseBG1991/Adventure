public class Item {
    private final String itemName;
    private final String itemDescription;
    private final double itemWeight;

    public Item (String itemName, String itemDescription, double itemWeight){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
    }

    public String getItemName () {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    @Override
    public boolean equals(Object obj) {
        Item other = (Item) obj;
        return this.itemName.equals(other.itemName) && this.itemDescription.equals(other.itemDescription);
    }
    public double getItemWeight() {
        return itemWeight;
    }

}