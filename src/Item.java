// Repræsenterer en genstand, som skal placeres i et rum
public class Item {
    private final String itemName; // genstandens navn
    private final String itemDescription; // genstandens beskrivelse

    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    // Returnerer genstandens navn
    public String getItemName() {
        return itemName;
    }

    // Returnerer genstandens beskrivelse
    public String getItemDescription() {
        return itemDescription;
    }

    @Override // Hvad foregår der her?
    public boolean equals(Object obj) {
        Item other = (Item) obj;
        return this.itemName.equals(other.itemName) && this.itemDescription.equals(other.itemDescription);

    }

    public String toString () {
        return String.format("%s", itemDescription);
    }
}