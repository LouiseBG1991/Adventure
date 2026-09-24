//Kort over spillet
public class GameMap {
    private Player player; // Spilleren
    private Room currentRoom; // Det rum, spilleren befinder sig i
    private Room[][] map = new Room[3][3]; // Det visuelle kort

    //Konstruktør, som initialiserer og opretter labyrinten
    public GameMap() {
        makeLabyrinth();
    }

    //Sætter spilleren på kortet
    public void setPlayer (Player player) {
        this.player = player;
    }

    // Henter det rum som spilleren aktuelt befinder sig i
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Opretter alle rummene med beskrivelser i labyrinten
    // Forbindelsen mellem rummene bestemmes
    // Rummene placeres i et 2D-array, så vi kan lave et visuelt kort
    public void makeLabyrinth() {
        Room room1 = new Room("\nRoom 1", "A room with no distinct features, except two doors.");
        Room room2 = new Room("\nRoom 2", "Water drips from the ceiling somewhere in the dark");
        Room room3 = new Room("\nRoom 3", "Theres a small rock in the corner of the room");
        Room room4 = new Room("\nRoom 4", "Theres something or someone hiding in the corner...");
        Room room5 = new Room("\nRoom 5", "You got to the last room");
        Room room6 = new Room("\nRoom 6", "You should hurry");
        Room room7 = new Room("\nRoom 7", "The light flickers");
        Room room8 = new Room("\nRoom 8", "There is a spider in the entrance");
        Room room9 = new Room("\nRoom 9", "The water is rising");

        currentRoom = room1;

        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);

        map[0][0]=room1;
        map[1][0]=room4;
        map[2][0]=room7;
        map[0][1]=room2;
        map[1][1]=room5;
        map[2][1]=room8;
        map[0][2]=room3;
        map[1][2]=room6;
        map[2][2]=room9;
}

    // Udskriver et kort i konsollen, hvor spillerens aktuelle position markeres med X
    public String makeMap() {
        IO.println("\nMap that shows your position in the labyrinth:");
        IO.println();

        Room playerCurrentRoom = (player != null) ? player.getCurrentRoom() : currentRoom;
        // ternary operator (forkoret if/else)

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == playerCurrentRoom) {
                    IO.print("[x]");
                } else {
                    IO.print("[ ]");
                }
            }
            IO.println();
        }
        return "";
    }
}
