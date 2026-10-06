public class MeleeWeapon extends Weapon {

    public MeleeWeapon (String itemName, String itemDescription, double itemWeight ) {
        super (itemName, itemDescription, itemWeight);

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
