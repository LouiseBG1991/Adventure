public class Item {
    private final String itemName;
    private final String itemDescription;

    public Item (String itemName, String itemDescription){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
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
}