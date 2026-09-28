void main() {
    Adventure adventure = new Adventure();
    DrawMap drawMap = new DrawMap(adventure.getRooms(),adventure);

    UserInterface userInterface = new UserInterface(adventure, drawMap);
    userInterface.startAdventure();
}