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
    public int getPlayerHealth() {
        return player.getPlayerHealth();
    }
    public String getHealthDescription(){
        return player.healthDescription();
    }
    public String getItemName (){
        return getItemName();
    }

    public EatResult eat(String food){
        return player.eat(food);
    }

    public EquipResult equip(String chosenEquipWeapon) {
        return player.equip(chosenEquipWeapon);
    }

    public Item getEquippedWeapon() {
        return player.getEquippedWeapon();
    }

    public Item findItemByName (String chosenItem){
        return player.findItemByName(chosenItem);
    }

    public AttackResult attack () {
        return player.attack();
    }

    public int getShotsLeft () {
        return player.getShotsLeft();

    }

    }


