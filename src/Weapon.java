public abstract class Weapon extends Item {

    public Weapon(String itemName, String ItemDescription) {
        super(itemName, ItemDescription);
    }

    public abstract boolean canUse();
    public abstract int use();
}
