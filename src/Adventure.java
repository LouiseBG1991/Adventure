public class Adventure {
    private Room theFirstRoom;

    public Adventure(){
        makeLabyrinth();
    }
    public Room getTheFirstRoom(){
        return theFirstRoom;

    }


    public void makeLabyrinth(){
    Room room1 = new Room("Room 1", "A room with no distinct features, except two doors.");
    Room room2 = new Room("Room 2", "Water drips from the ceiling somewhere in the dark");
    Room room3 = new Room("Room 3", "");
    Room room4 = new Room("Room 4", "");
    Room room5 = new Room("Room 5", "");
    Room room6 = new Room("Room 6", "");
    Room room7 = new Room("Room 7", "");
    Room room8 = new Room("Room 8", "");
    Room room9 = new Room("Room 9", "");

    theFirstRoom = room1;

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
    }







}
