import java.util.ArrayList;
// denne klasse fungerer som en slags controller, der sender GameMap og Player-metoderne videre til UserInterface
public class Adventure {
    private GameMap gameMap;
    private Player player;

    public Adventure() {
        gameMap = new GameMap();
        player = new Player(gameMap.getCurrentRoom());
        gameMap.setPlayer(player);

    }

    //Styrer hvordan spilleren bevæger sig
    public boolean go (String direction) {
        return player.go(direction);
    }

    // Sender take-kommandoen videre til UI
    public Item takeItem(String itemName) {
        return player.takeItem(itemName);
    }
    //Sender drop-kommandoen videre til UI
    public Item dropItem(String itemName) {
        return player.dropItem(itemName);
    }

    // Viser spilleren, hvad der er i rummet
    public String look () {
        return player.look();
    }

    // Viser spilleren et visuelt kort over han/hun befinder sig
    public String makeMap () {
        return gameMap.makeMap();
    }
    // Henter spillerens inventory
    public ArrayList<Item> getPlayerItems() {
        return player.getPlayerItems();
    }
    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public boolean setLightFalse() {
        return getCurrentRoom().setLightFalse();

    }
    public int getPlayerHealth() {
        return player.getPlayerHealth();
    }
    public String getHealthDescription(){
        return player.healthDescription();
    }

    public EquipResult equip(String chosenEquipWeapon) {
        return player.equip(chosenEquipWeapon);
    }

    public Item getEquippedWeapon() {
        return player.getEquippedWeapon();
    }
    public boolean isPlayerDead() {
        return player.isDead();
    }

    public String attack(String enemyName) {
        Room room = player.getCurrentRoom();
        Enemy enemy = null;

        if (enemyName.equals("")) {
            // Ingen valgt: tag den første fjende i rummet
            if (!room.getEnemies().isEmpty()) {
                enemy = room.getEnemies().get(0);
            }
        } else {
            enemy = room.findEnemyByName(enemyName);
        }
        int enemyHealthBefore = 0;
        if (enemy != null) {
            enemyHealthBefore = enemy.getEnemyHealth();
        }

        AttackResult result = player.attack(enemy);

        if (result == AttackResult.NO_WEAPON) {
            return "You don't have an equipped weapon.";
        }
        if (result == AttackResult.NO_AMMUNITION) {
            return "You don't have any ammunition left.";
        }
        if (result == AttackResult.NO_ENEMY) {
            return "There is no enemy like '" + enemyName + "' here to attack.";
        }
        // Spillerens angreb
        String weaponTotalDescription = player.getEquippedWeapon().getItemDescription();
        String attackSceneDescription;
        if (result == AttackResult.SWING) {
            attackSceneDescription = "You swing " + weaponTotalDescription + " at the " + enemy.getEnemyName() + ".";
        } else {
            attackSceneDescription = "You fire " + weaponTotalDescription + " at the " + enemy.getEnemyName()
                    + ". " + player.getShotsLeft() + " shots left.";
        }
        int damageDealt = enemyHealthBefore - enemy.getEnemyHealth();
        attackSceneDescription += "\nYou deal " + damageDealt + " dmg.";

        // Fjenden dør og kan ikke slå igen
        if (enemy.isDead()) {
            Item dropped = enemy.enemyDies();
            attackSceneDescription += "\nThe " + enemy.getEnemyName() + " dies.";
            if (dropped != null) {
                attackSceneDescription += "\nIt drops " + dropped.getItemDescription() + ".";
            }
            return attackSceneDescription;
        }
        // Fjenden overlever og angriber igen
        attackSceneDescription += "\nThe " + enemy.getEnemyName() + " has " + enemy.getEnemyHealth() + " health left.";

        int playerHealthBefore = player.getPlayerHealth();
        AttackResult counter = enemy.attackPlayer(player);

        if (counter == AttackResult.SWING || counter == AttackResult.FIRE) {
            int damageTaken = playerHealthBefore - player.getPlayerHealth();
            attackSceneDescription += "\nThe " + enemy.getEnemyName() + " hits you for " + damageTaken + " dmg.";
            attackSceneDescription += "\nYou have " + player.getPlayerHealth() + " health.";
        } else {
            attackSceneDescription += "\nThe " + enemy.getEnemyName() + " can't attack back.";
        }

        if (player.isDead()) {
            attackSceneDescription += "\nYou're dead. Game over!";
        }
        return attackSceneDescription;
    }

    public boolean setLight (){
        if (getCurrentRoom().getLight() == false){
            getCurrentRoom().setLightTrue();
            return true;
        } else {
            getCurrentRoom().setLightFalse();
            return false;
        }
    }
    public String eat(String foodName){
        EatResult result = player.eat(foodName);
        if (result == EatResult.NOT_FOUND) {
            return "There is nothing like " + foodName + " to eat around here.";
        } else if (result == EatResult.NOT_FOOD) {
            return "You cannot eat " + foodName + ".";
        } else if (result == EatResult.EATEN) {
            return  "You eat " + foodName + ".";
        }
        return null;
    }
}





