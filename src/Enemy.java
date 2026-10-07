public class Enemy {

    private String enemyName;
    private String enemyDescription;
    private Item enemyEquippedWeapon;
    private int enemyHealth;
    private Room currentRoom;
    private int shotsLeftEnemy;



    public Enemy(String enemyName, String enemyDescription, Room currentRoom, Item enemyEquippedWeapon) {
        this.enemyName = enemyName;
        this.enemyDescription = enemyDescription;
        this.enemyHealth = 100;
        this.enemyEquippedWeapon = enemyEquippedWeapon;
        this.currentRoom = currentRoom;
        this.shotsLeftEnemy = 0;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getEnemyDescription() {
        return enemyDescription;
    }

    public int getEnemyHealth () {
        return enemyHealth;
    }

    public void hit(int damage) {
        enemyHealth -= damage;
    }

    public boolean isDead() {
        return enemyHealth <= 0;
    }

    public Item enemyDies() {
        Item dropped = enemyEquippedWeapon;
        if (dropped != null) {
            currentRoom.addItem(dropped);
        }
        currentRoom.removeEnemy(this);
        return dropped;
    }

    public AttackResult attackPlayer(Player player) {
        if (!(enemyEquippedWeapon instanceof Weapon weapon)) {
            return AttackResult.NO_WEAPON;
        }
        if (!weapon.canUse()) {
            return AttackResult.NO_AMMUNITION;
        }
        shotsLeftEnemy = weapon.use();
        player.hit(weapon.getDamagePerStrike(weapon));

        return (shotsLeftEnemy == -1) ? AttackResult.SWING : AttackResult.FIRE;
    }

}
