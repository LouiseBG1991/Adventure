import java.util.ArrayList;

// Spilleren
public class Player {
    private Room currentRoom; // Det rum, som spilleren befinder sig i
    private ArrayList<Item> playerItems;
    private int playerHealth;
    private Item equippedWeapon;
    private int shotsLeft;


    // Konstruktør til at oprette en ny spiller i det første rum
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        this.playerItems = new ArrayList<>();
        this.playerHealth = 100;
        this.equippedWeapon = null;
        this.shotsLeft = 0;
    }

    //Henter rummet, som spilleren aktuelt befinder sig i
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Returnerer listen over de items spilleren bærer
    public ArrayList<Item> getPlayerItems() {
        return playerItems;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getShotsLeft() {
        return shotsLeft;
    }

    // Finder et item i spillerens inventory ud fra det korte navn
    public Item findItem(String itemName) {

        // Går igennem alle items spilleren bærer
        for (Item item : playerItems) {

            // Tjekker om itemets korte navn matcher det, vi leder efter
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }

        // Hvis item'et ikke findes i inventory
        return null;
    }

    // Beskrivelse af det rum, som spilleren befinder sig i
    public String look() {

        // Starter teksten med rummets navn og beskrivelse
        String roomDescription =
                currentRoom.getName() + "\n" +
                        currentRoom.getDescription();

        // Går igennem alle items, der ligger i det aktuelle rum
        for (Item item : currentRoom.getRoomItems()) {

            // Tilføjer hvert items beskrivelse til teksten
            roomDescription += "\nHere you see: " + item.getItemDescription();

        }

        for (Enemy enemy : currentRoom.getEnemies()) {
            roomDescription += "\nBeware! Here lurks: " + enemy.getEnemyDescription();
        }
        // Returnerer hele teksten til UserInterface

        return roomDescription;
    }


    //Hvordan spilleren bevæger sig
    public boolean go(String direction) {
        Room nextRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "east" -> currentRoom.getEast();
            case "south" -> currentRoom.getSouth();
            case "west" -> currentRoom.getWest();
            default -> null;
        };
        if (nextRoom == null) {
            return false;
        }
        currentRoom = nextRoom;
        return true;
    }

    public Item takeItem(String itemName) {

        // Finder item'et i det rum spilleren står i
        Item item = currentRoom.findItem(itemName);

        // Hvis item'et ikke findes, returnerer vi null
        if (item == null) {
            return null;
        }

        // Fjerner item'et fra rummet
        currentRoom.removeItem(item);

        // Tilføjer item'et til spillerens inventory
        playerItems.add(item);

        // Returnerer item'et, så UserInterface kan skrive en besked
        return item;
    }

    // Forsøger at droppe et item fra spillerens inventory
    public Item dropItem(String itemName) {

        // Leder efter item'et i spillerens inventory
        Item item = findItem(itemName);

        // Hvis spilleren ikke har item'et, returnerer vi null
        if (item == null) {
            return null;
        }

        // Fjerner item'et fra spillerens inventory
        playerItems.remove(item);

        // Lægger item'et i det rum, spilleren står i
        currentRoom.addItem(item);

        // Returnerer item'et
        return item;
    }

    public EatResult eat(String chosenEatItem) {
        Item foundItem = findItemByName(chosenEatItem);

        if (foundItem == null) {
            return EatResult.NOT_FOUND;
        } else if (foundItem instanceof Food food) {
            playerHealth += food.getHealthPoints();
            playerItems.remove(food);
            getCurrentRoom().removeItem(food);
            return EatResult.EATEN;
        } else if (!(foundItem instanceof Food)) {
            return EatResult.NOT_FOOD;


        }
        return EatResult.NOT_FOUND;
    }

    public Item findItemByName(String chosenEatItem) {
        for (Item item : playerItems) {
            if (item.getItemName().equalsIgnoreCase(chosenEatItem)) {
                return item;
            }
        }
        for (Item item : getCurrentRoom().getRoomItems()) {
            if (item.getItemName().equalsIgnoreCase(chosenEatItem)) {
                return item;
            }
        }
        return null;
    }

    public void hit(int damage) {
        playerHealth -= damage;
    }

    public boolean isDead() {
        return playerHealth <= 0;
    }

    public AttackResult attack(Enemy chosenEnemy) {
        if (equippedWeapon == null) {
            return AttackResult.NO_WEAPON;
        }
        if (chosenEnemy == null) {
            return AttackResult.NO_ENEMY;
        }
        Weapon weapon = (Weapon) equippedWeapon;
        if (!weapon.canUse()) {
            return AttackResult.NO_AMMUNITION;
        }
        shotsLeft = weapon.use();
        chosenEnemy.hit(weapon.getDamagePerStrike(weapon));

        if (shotsLeft == -1) {
            return AttackResult.SWING;
        }  else {
            return AttackResult.FIRE;
        }
    }

    public String healthDescription() {
        if (playerHealth <= 0) return playerHealth + ": " + "You're dead";
        if (playerHealth <= 60) return playerHealth + ": " + "You should find some food";
        if (playerHealth < 100) return playerHealth + ": " + "You're almost there";
        return playerHealth + ": " +"You have full health";
    }

    public EquipResult equip(String chosenEquipWeapon) {
        Item foundItem = findItemByName(chosenEquipWeapon);
        if (foundItem == null) {
            return EquipResult.NOT_FOUND;
        } else if (foundItem instanceof Weapon) {
            equippedWeapon = foundItem;
            playerItems.remove(foundItem);
            getCurrentRoom().removeItem(foundItem);
            return EquipResult.EQUIPPED;
        }
        return EquipResult.NOT_WEAPON;
    }
}

