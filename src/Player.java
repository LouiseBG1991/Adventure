import java.util.ArrayList;

// Spilleren
public class Player {
    private Room currentRoom; // Det rum, som spilleren befinder sig i
    private ArrayList<Item> playerItems;
    private int health;

    // Konstruktør til at oprette en ny spiller i det første rum
    public Player (Room currentRoom) {
        this.currentRoom = currentRoom;
        this.playerItems = new ArrayList<>();
        this.health = 100;
    }

    //Henter rummet, som spilleren aktuelt befinder sig i
    public Room getCurrentRoom () {
        return currentRoom;
    }
    // Returnerer listen over de items spilleren bærer
    public ArrayList<Item> getPlayerItems() {
        return playerItems;
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
            roomDescription += "\n" + item.getItemDescription();
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
    public String healthDescription(){
        IO.println("Health: " + health);
        if(health >= 100) {
            return "You have full health";
        } else if (health < 100) {
            return "You're almost there";
        }else if (health <=60) {
            return "You should find some food";
        }else if (health == 0){
            return "You're dead";
        }else{
            return "You really should find some food";
        }
    }

    public int getHealth (){
        return health;
    }

    public EatResult eat (String chosenEatItem){
        Item foundItem = findItemByName(chosenEatItem);

        if (foundItem == null) {
            return EatResult.NOT_FOUND;
        } else if (foundItem instanceof Food food) {
            health += food.getHealthPoints();
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
