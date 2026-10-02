public class MeleeWeapon extends Weapon {

    public MeleeWeapon (String itemName, String itemDescription ) {
        super (itemName, itemDescription);

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
