public abstract class Weapon extends Item {

    public Weapon(String itemName, String itemDescription, double itemWeight) {
        super(itemName, itemDescription, itemWeight);
    }

    public abstract boolean canUse();
    public abstract int use();
}
