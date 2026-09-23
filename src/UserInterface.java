public class UserInterface {

    private Adventure adventure;

    public UserInterface(Adventure adventure){
        this.adventure = adventure;
    }
    public void startAdventure() {
        boolean activeAdventure = true;
        IO.println("Welcome to the adventure game, you can move in 4 directions by typing n for north, e for east, s for south, w for west.");
        while (activeAdventure) {
            String command = IO.readln("hvilken retning vil du gå?");
            switch (command) {
                case "n" -> {
                    IO.println("you go north");
                }
                case "s" -> {
                    IO.println("you go south");
                }
                case "e" -> {
                    IO.println("you go east");
                }
                case "w" -> {
                    IO.println("you go west");
                }
                case "look" -> {
                    IO.println(adventure.look());
                }
                case "Exit" -> {
                    IO.println("goodbye");
                    activeAdventure = false;
                }
                case "Help" -> {
                    IO.println("The commands are; n for north, e for east, w for west, s for south, Help for help, and Exit for exit.");
                }
            }
        }

    }
}
