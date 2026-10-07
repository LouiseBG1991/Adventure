public class RangedWeapon extends Weapon {

    private int ammunition;

    public RangedWeapon(String itemName, String itemDescription, int ammunition, int damagePerStrike) {
        super(itemName, itemDescription, damagePerStrike);
        this.ammunition = ammunition;
    }

    @Override
    public int getDamagePerStrike(Item equippedWeapon) {
        return super.getDamagePerStrike(equippedWeapon);
    }

    @Override
    public boolean canUse() {
        return ammunition > 0;
    }

    @Override
    public int use () {
        if (canUse()) {
            ammunition--;
        }
        return ammunition;
    }
}
