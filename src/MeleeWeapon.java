public class MeleeWeapon extends Weapon {

    public MeleeWeapon (String itemName, String itemDescription, int damagePerStrike) {
        super (itemName, itemDescription, damagePerStrike);

    }
    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public int use() {
        return -1;
    }
}
