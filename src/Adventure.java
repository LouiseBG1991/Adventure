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

    // Sender take-kommandoen videre til Player
    public Item takeItem(String itemName) {
        return player.takeItem(itemName);
    }
    //Sender drop-kommandoen videre til Player
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
    public Room getCurrentRoom (){
        return gameMap.getCurrentRoom();
    }
    public Room getLight (){
        return getLight();
    }

    public boolean setLightTrue(){
        return getCurrentRoom().setLightTrue();
    }

    public boolean setLightFalse(){
        return getCurrentRoom().setLightTrue();
    }
    public int getHealth() {
        return player.getHealth();
    }
    public String getHealthDescription(){
        return player.healthDescription();
    }
    public String getItemName (){
        return getItemName();
    }

    public EatResult getEatResult(String food){
        return player.eat(food);
    }
    public Item findItemByName (String chosenItem){
        return player.findItemByName(chosenItem);
    }
    public EquipResult getEquip(String chosenEquipItem){
        return player.equip(chosenEquipItem);
    }
    public Item getEquippedItem(){
        return player.getEquippedItem();
    }
    }


