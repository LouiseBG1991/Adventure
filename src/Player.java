import java.util.ArrayList;

// Spilleren
public class Player {
    private Room currentRoom; // Det rum, som spilleren befinder sig i
    private ArrayList<Item> playerItems;
    private int playerHealth;
    private Item equippedWeapon;
    private int shotsLeft;

    // Konstruktør til at oprette en ny spiller i det første rum
    public Player (Room currentRoom) {
        this.currentRoom = currentRoom;
        this.playerItems = new ArrayList<>();
        this.playerHealth = 100;
        this.equippedWeapon = null;
        this.shotsLeft = 0;
    }

    //Henter rummet, som spilleren aktuelt befinder sig i
    public Room getCurrentRoom () {
        return currentRoom;
    }
    // Returnerer listen over de items spilleren bærer
    public ArrayList<Item> getPlayerItems() {
        return playerItems;
    }

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public int getPlayerHealth (){
        return playerHealth;
    }

    public int getShotsLeft () {
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
    public void takeItem(Item item ){
        playerItems.add(item);
    }
    public boolean removeItem(Item item){
        return playerItems.remove(item);

    }
    public String healthDescription(){ //fix
        IO.println("Health: " + playerHealth);
        if(playerHealth >= 100) {
            return "You have full health";
        } else if (playerHealth < 100) {
            return "You're almost there";
        }else if (playerHealth <=60) {
            return "You should find some food";
        }else if (playerHealth == 0){
            return "You're dead";
        }else{
            return "You really should find some food";
        }
    }

    public void playerDies () {
        IO.println("You're dead. Game over!");
    }

    public void hit (int damage) {
        playerHealth -= damage;
        if (playerHealth <=0) {
            playerDies();
        }
    }
    // Fjenden skal "findes", fjenden skal angribes, hvis fjenden overlever, angriber den
    public AttackResult attack () {
        if (equippedWeapon == null) {
            return AttackResult.NO_WEAPON;
        }
        Weapon weapon = (Weapon) equippedWeapon;
        if (!weapon.canUse()) {
            return AttackResult.NO_AMMUNITION;
        }
        shotsLeft = weapon.use();
        if (shotsLeft == -1) {
            return AttackResult.SWING;
        } else {
            return AttackResult.FIRE;
        }
    }

    public EquipResult equip (String chosenEquipWeapon) {
        Item foundItem = findItemByName(chosenEquipWeapon);
        if (foundItem == null) {
            return EquipResult.NOT_FOUND;
        } else if (foundItem instanceof Weapon) {
            equippedWeapon = foundItem;
            getCurrentRoom().removeItem(foundItem);
            return EquipResult.EQUIPPED;
        } else if (!(foundItem instanceof Weapon)) {
            return EquipResult.NOT_WEAPON;

        }
        return EquipResult.NOT_FOUND;
    }

    public EatResult eat (String chosenEatItem){
        Item foundItem = findItemByName(chosenEatItem);

        if (foundItem == null) {
            return EatResult.NOT_FOUND;
        } else if (foundItem instanceof Food food) {
            playerHealth += food.getHealthPoints();
            playerItems.remove(food);
            getCurrentRoom().removeItem(food);
            return EatResult.EATEN;
        } else if (! (foundItem instanceof Food)) {
            return EatResult.NOT_FOOD;


        }
        return EatResult.NOT_FOUND;
    }

    public Item findItemByName (String chosenEatItem){
        for (Item item : playerItems) {
            if (item.getItemName().equalsIgnoreCase(chosenEatItem)) {
                    return item;
            }
        }
        for (Item item : getCurrentRoom().getRoomItems()){
            if(item.getItemName().equalsIgnoreCase(chosenEatItem)){
                    return item;
            }
        }
        return null;
    }
}
