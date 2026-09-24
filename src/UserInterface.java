// Brugergrænseflade
public class UserInterface {

    private Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    public void startAdventure() {
        boolean activeAdventure = true;
        IO.println("Welcome to the adventure game, you can move in 4 directions by typing n for north, e for east, s for south, w for west.");
        while (activeAdventure) {
            String command = IO.readln("\nWhich direction do you wanna go?");
            switch (command) {
                case "go north", "n" -> {
                    if (adventure.go("north")) {
                        IO.println("you go north");
                    } else {
                        IO.println("you cant go north");
                    }
                }
                case "s" -> {
                    if (adventure.go("south")) {
                        IO.println("you go south");
                    } else {
                        IO.println("you cant go south");
                    }
                }
                case "e" -> {
                    if (adventure.go("east")) {
                        IO.println("you go east");
                    } else {
                        IO.println("you cant go east");
                    }

                }
                case "w" -> {
                    if (adventure.go("west")) {
                        IO.println("you go west");
                    } else {
                        IO.println("you cant go west");
                    }


                }
                case "look" -> {
                    IO.println(adventure.look());
                    IO.println(adventure.makeMap());
                }
                case "Exit" -> {
                    IO.println("goodbye");
                    activeAdventure = false;
                }
                case "Help" -> {
                    IO.println("The commands are; n for north, e for east, w for west, s for south, Help for help, and Exit for exit.");
                }
                default -> {
                    IO.println("invalid input");
                }
            }
        }

    }
}
