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
    public boolean go(String direction) {
        return player.go(direction);
    }

    // Viser spilleren, hvad der er i rummet
    public String look() {
        return player.look();
    }


    public ArrayList<Item> lookItems() {
        return player.getCurrentRoom().getRoomItems();
    }

    public Item findPickupItem(String name) {
        return player.getCurrentRoom().findItemByName(name);
    }

    public Item findDropItem(String name) {
        return player.findDropItemByName(name);
    }

    public boolean pickupItem(Item chosenItem) {
        if (chosenItem == null) {
            return false;
        }
        if (player.getCurrentRoom().removeRoomItem(chosenItem)) {
            player.takeItem(chosenItem);
            return true;
        }
        return false;
    }

    public ArrayList<Item> getPlayerItems() {
        return player.getPlayerItems();
    }

    public Room[] getRooms() {
        return gameMap.getGameMapRooms();
    }

    public boolean dropItem(Item chosenItem) {
        return player.dropItem(chosenItem);
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }
}


