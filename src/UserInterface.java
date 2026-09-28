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
            String command = IO.readln("\nWhich action do you wanna take? ");
            // Tjekker om brugeren skriver fx "take lamp"
            if (command.startsWith("take ")) {

                // Henter item-navnet fra kommandoen
                // "take lamp" bliver til "lamp"
                String itemName = command.substring(5);

                // Beder Adventure om at forsøge at tage item'et
                Item item = adventure.takeItem(itemName);

                // Hvis takeItem returnerer null, blev item'et ikke fundet
                if (item == null) {
                    IO.println("There is nothing like " + itemName + " to take around here");
                } else {
                    // Ellers blev item'et fundet og fjernet fra rummet
                    IO.println("You have taken " + item.getItemDescription());
                }

                continue;
            }
            if (command.startsWith("drop ")) {
                String itemName = command.substring(5);
                Item item = adventure.dropItem(itemName);
                if (item == null) {
                    IO.println("You don't have anything like " + itemName + " in your inventory");
                } else {
                    IO.println("You have dropped " + item.getItemDescription());
                }
                continue;
            }
            switch (command) {
                case "inventory" -> inventory();
                case "north" -> goDirection("north");
                case "east" -> goDirection("east");
                case "west" -> goDirection("west");
                case "south" -> goDirection("south");
                case "exit" -> exit(activeAdventure);
                case "help" -> help();
                case "look" -> look();
                default -> IO.println("invalid input");
            }
//            switch (command) {
//                case "go north", "n" -> {
//                    if (adventure.go("north")) {
//                        IO.println("you go north");
//                    } else {
//                        IO.println("you cant go north");
//                    }
//                }
//                case "s" -> {
//                    if (adventure.go("south")) {
//                        IO.println("you go south");
//                    } else {
//                        IO.println("you cant go south");
//                    }
//                }
//                case "e" -> {
//                    if (adventure.go("east")) {
//                        IO.println("you go east");
//                    } else {
//                        IO.println("you cant go east");
//                    }
//
//                }
//                case "w" -> {
//                    if (adventure.go("west")) {
//                        IO.println("you go west");
//                    } else {
//                        IO.println("you cant go west");
//                    }
//
//
//                }
//                case "look" -> {
//                    IO.println(adventure.look());
//                    IO.println(adventure.makeMap());
//                }
//                case "Exit" -> {
//                    IO.println("goodbye");
//                    activeAdventure = false;
//                }
//                case "Help" -> {
//                    IO.println("The commands are; n for north, e for east, w for west, s for south, Help for help, and Exit for exit.");
//                }
//                case "inventory", "inv" -> {
//                    inventory();
//                }
//                default -> {
//                    IO.println("invalid input");
//                }
//            }
        }

    }
    public void help (){
        IO.println("The commands are; n for north, e for east, w for west, s for south, Help for help, and Exit for exit.");
    }
    public void look (){
        IO.println(adventure.look());
        IO.println(adventure.makeMap());
    }
    public void exit (boolean activeAdventure){
        IO.println("goodbye");
        activeAdventure = false;
    }

    public void goDirection(String direction){
        if (adventure.go(direction)) {
            IO.println("you go "+ direction);
        } else {
            IO.println("you cant go "+ direction);
        }

    }
    public void inventory() {

        // Går igennem alle items spilleren bærer
        for (Item item : adventure.getPlayerItems()) {

            // Udskriver beskrivelsen af hvert item
            IO.println(item.getItemDescription());
        }
    }

}
