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
        if(health <= 100){
            return "You have full health";
        }else if (health <=60) {
            return "You should find some food";
        }else if (health == 0){
            return "youre dead";
        }else{
            return "You really should find some food";
        }
    }

    public int getHealth (){
        return health;
    }

    public EatResult eat (String chosenEatItem){
        for (Item item : playerItems) {
            if (item.getItemName().equalsIgnoreCase(chosenEatItem)) {
                if (item instanceof Food food) {
                    playerItems.remove(food); // fjerner maden fra inventory, når den er blevet spist
                    return EatResult.EATEN;
                }if (!(item instanceof Food) ){ // Er den ikke redundant, altså kan man ikke bare skrive else?, det virker i hvert fald, når man kører programmet :)
                    return EatResult.NOT_FOOD;
                }
            }
        }
        for (Item item : getCurrentRoom().getRoomItems()){
            if(item.getItemName().equalsIgnoreCase(chosenEatItem)){
                if (item instanceof Food food){
                    currentRoom.removeItem(food); // fjerner maden fra rummet, når den er blevet spist
                    return EatResult.EATEN;
                } if (!(item instanceof Food)){ // rettede til Food fra Item, da den sprang NOT_FOOD over i kommandoen, men er den ikke også redundant
                    return EatResult.NOT_FOOD;
                }
            }
        }
        return EatResult.NOT_FOUND;
    }
}
