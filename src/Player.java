import java.util.ArrayList;

// Spilleren
public class Player {
    private Room currentRoom; // Det rum, som spilleren befinder sig i
    private ArrayList<Item> inventory;

    // Konstruktør til at oprette en ny spiller i det første rum
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        this.inventory = new ArrayList<>();
    }

    //Henter rummet, som spilleren aktuelt befinder sig i
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Beskrivelse af det rum, som spilleren befinder sig i
    public String look() {
        Room currentRoom = getCurrentRoom();
        return  currentRoom.getName() + "\n" +
                currentRoom.getDescription() + "\n" +
                currentRoom.printRoomItems();
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

    //Tilføjer en genstand til spillerens inventarliste
    public void addItem(Item item) {
        inventory.add(item);
    }

    //Fjerner en genstand fra spillerens inventarliste
    public Item removeItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemName().equals(itemName)) {
                return inventory.remove(i);
            }
        }
        return null;
    }

    // Hele inventarlisten
    public ArrayList<Item> getInventory() {
        return inventory;

    }

    // Flytter en genstand fra rummet til spillerens inventarliste
    public Item takeItem (String itemName) {
        Item item = currentRoom.removeItem(itemName); // fjerner genstand fra rummet
        if (item != null) { // Hvis genstanden findes, tilføjes den til spillerens inventarliste
            inventory.add(item);
        }
        return item; // returnerer genstand eller null, hvis den ikke findes
    }

    //Placerer en genstand fra spillerens inventarliste til rummet
    public Item dropItem (String itemName) {
        Item item = removeItem(itemName); // fjerner genstand fra spillerens inventarliste
        if (item != null) { // Hvis spilleren har genstanden, lægges den i det nuværende rum
            currentRoom.addItem(item);
        }
        return item;
    }

    public Item findItem (String itemName) {
        for (Item item : inventory) {
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

}
