public abstract class Weapon extends Item {

    private int damagePerStrike;

    public Weapon(String itemName, String ItemDescription, int damagePerStrike) {
        super(itemName, ItemDescription);
        this.damagePerStrike = damagePerStrike;

    }

    public int getDamagePerStrike (Item equippedWeapon) {
        return damagePerStrike;
    }

    public abstract boolean canUse(); // Har våbnet ammunition?
    public abstract int use(); // Tæller ammunition
}
