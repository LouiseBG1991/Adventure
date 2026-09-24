// Spilleren
public class Player {
    private Room currentRoom; // Det rum, som spilleren befinder sig i

    // Konstruktør til at oprette en ny spiller i det første rum
    public Player (Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    //Henter rummet, som spilleren aktuelt befinder sig i
    public Room getCurrentRoom () {
        return currentRoom;
    }

    // Beskrivelse af det rum, som spilleren befinder sig i
    public String look() {
        return currentRoom.getName() + "\n" + currentRoom.getDescription();
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
}
