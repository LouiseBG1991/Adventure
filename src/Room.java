import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private ArrayList<Item> roomItems;

    public Room (String name, String description) {
        this.name = name;
        this.description = description;
        this.roomItems = new ArrayList<>();
    }


    public Room getNorth (){
        return north;
    }

    public Room getEast () {
        return east;
    }

    public Room getSouth () {
        return south;
    }

    public Room getWest () {
        return west;
    }

    public void setNorth (Room north) {
        this.north = north;
    }

    public void setEast (Room east) {
        this.east = east;
    }

    public void setWest (Room west) {
        this.west = west;
    }

    public void setSouth (Room south) {
        this.south = south;
    }

    public String getName() {
        return name;

    }

    public String getDescription () {
        return description;
    }

    public void addItem(Item item){
        roomItems.add(item);
    }
    // Fjerner et item fra rummets liste
    public boolean removeItem(Item item) {
        return roomItems.remove(item);
    }
    public ArrayList<Item> getRoomItems(){
        return roomItems;
    }

    // Finder et item i rummet ud fra itemets korte navn
    public Item findItem(String itemName) {

        // Går igennem alle items, der ligger i rummet
        for (Item item : roomItems) {

            // Tjekker om itemets navn matcher det navn, vi leder efter
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }

        // Hvis vi har gennemgået hele listen uden at finde noget
        return null;
    }


//    public void addItemsToRoom(ArrayList Items, Room chosenRoom, Item item){
//        addItems(item);
//    }

}
