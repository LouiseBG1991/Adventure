public class Enemy {

    private String enemyName;
    private String enemyDescription;
    private Item enemyEquippedWeapon;
    private int enemyHealth;
    private Room currentRoom;

    public Enemy(String enemyName, String enemyDescription, Room currentRoom, Item enemyEquippedWeapon) {
        this.enemyName = enemyName;
        this.enemyDescription = enemyDescription;
        this.enemyHealth = 100;
        this.enemyEquippedWeapon = enemyEquippedWeapon;
        this.currentRoom = currentRoom;

    }

    public String getEnemyName() {
        return enemyName;
    }

    public Item getEnemyEquippedWeapon () {
        return enemyEquippedWeapon;
    }

    public String getEnemyDescription() {
        return enemyDescription;
    }

    public Room getCurrentRoom () {
        return currentRoom;
    }

    public int getEnemyHealth () {
        return enemyHealth;
    }

    //Når fjenden dør, skal våbnet droppes og fjernes fra rummet, dvs. rummets liste over enemies
    public void enemyDies () {
        IO.println("The " + enemyName + "dies"); //fjenden dør
        if (enemyEquippedWeapon != null) { //hvis fjenden har et våben
            currentRoom.addItem(enemyEquippedWeapon); //våbnet tilføjes til rummet
            IO.println("The " + enemyName + " drops its " + enemyEquippedWeapon + "."); //besked om at våbnet tabes
        }

        currentRoom.removeEnemy(this); //fjenden fjernes fra rummet
    }

    //Fjenden angriber - den kan kun angribe, hvis den har et våben og dens health er over 0
    public void attackPlayer (Player player) {
        if (enemyHealth > 0 && enemyEquippedWeapon !=null) {

        }


    }

    public void hit (int damage) {
        enemyHealth -= damage;
        if (enemyHealth <=0) {
            enemyDies();
        }

    }

}
