import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private ArrayList<Item> roomItems;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.roomItems = new ArrayList<>();
    }

    public Room getNorth() {
        return north;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public String getName() {
        return name;

    }

    public String getDescription() {
        return description;

    }

    public String printRoomItems() {
        if (roomItems.isEmpty()) {
            return "There are no items in this room";
        }
        String items = "Here you see: ";
        for (int i = 0; i < roomItems.size(); i++) {
            items += roomItems.get(i).getItemDescription();
            if (i < roomItems.size() - 1) {
                items += ", ";
            }
        }
        return items;
    }


    //Tilføjer en genstand
    public void addItem(Item item) {
        roomItems.add(item);
    }

    // Fjerner en genstand med navn
    public Item removeItem(String itemName) {
        for (int i = 0; i < roomItems.size(); i++) {
            if (roomItems.get(i).getItemName().equals(itemName)) {
                return roomItems.remove(i);
            }
        }
        return null;
    }

    // Hele listen af genstande
    public ArrayList<Item> getRoomItems() {
        return roomItems;
    }


    public Item findItem(String itemName) {
        for (Item item : roomItems) {
            if (item.getItemName().equals(itemName)) {
                return item;
            }

        }
        return null;
    }
}

