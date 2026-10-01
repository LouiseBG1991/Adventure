// Brugergrænseflade
public class UserInterface {

    private Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    public void startAdventure() {
        boolean activeAdventure = true;
        IO.println("Welcome to the adventure game, you can move in 4 directions by typing n for north, e for east, s for south, w for west.");
        IO.println("Remember to turn on the light!");
        while (activeAdventure) {
            String command = IO.readln("\nWhich action do you wanna take? ");
            // Tjekker om brugeren skriver fx "take lamp"
            if (command.startsWith("take ")) {
                take(command);
                continue;
            }
            if (command.startsWith("drop ")) {
                drop(command);
                continue;
            }
            if (command.startsWith("eat")){
                eat(command);
                continue;
            }
            if (command.startsWith("equip")){
                equip(command);
                continue;
            }
            switch (command) {
                case "inventory" -> inventory();
                case "north", "n" -> goDirection("north");
                case "east", "e" -> goDirection("east");
                case "west", "w" -> goDirection("west");
                case "south", "s" -> goDirection("south");
                case "exit" -> {exit();activeAdventure = false;}
                case "help" -> help();
                case "look" -> look();
                case "light" -> light();
                case "health" -> IO.println(adventure.getHealthDescription());
                default -> IO.println("invalid input");
            }

        }

    }
    public void equip(String command) {
        String equipItem = command.substring(6);
        EquipResult result = adventure.getEquip(equipItem);
        if (result == EquipResult.NOT_FOUND) {
            IO.println("There is nothing like " + equipItem + " to equip around here");
        } else if (result == EquipResult.NOT_A_WEAPON) {
            IO.println("You cannot equip " + equipItem);
        } else if (result == EquipResult.EQUIPPED) {
            IO.println("You equip " + equipItem);
        }
    }
    public void take(String command) {

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

    }

    public void drop(String command) {
        String itemName = command.substring(5);
        Item item = adventure.dropItem(itemName);
        if (item == null) {
            IO.println("You don't have anything like " + itemName + " in your inventory");
        } else {
            IO.println("You have dropped " + item.getItemDescription());
        }

    }
    public void eat(String command){
        //det virker, men hvordan får jeg fat i itemDescription? Og hvordan bestemmer vi om mad er godt eller dårligt jf. opgavebeskrivelsen
        // tænker vi skal lave en metode i food-klassen eller enum??
        String foodName = command.substring(4);
        EatResult result = adventure.getEatResult(foodName);
        if (result == EatResult.NOT_FOUND) {
            IO.println("There is nothing like " + foodName + " to eat around here");
        } else if (result == EatResult.NOT_FOOD) {
            IO.println("You cannot eat " + foodName);
        } else if (result == EatResult.EATEN) {
            IO.println("You eat " + foodName);
        }
        }

    public void help() {
        IO.println("The commands are; n for north, e for east, w for west, s for south, Help for help, and Exit for exit.");
    }

    public void look() {
        if (adventure.getCurrentRoom().getLight()) {
            IO.println(adventure.look());
            IO.println(adventure.makeMap());
            IO.println(adventure.getEquippedItem());
        } else {
            IO.println("You cant see anything, you should turn on the light");
        }
    }

    public void exit() {
        IO.println("goodbye");


    }

    public void goDirection(String direction) {
        if (adventure.getCurrentRoom().getLight()) {
            if (adventure.go(direction)) {
                IO.println("you go " + direction);
            } else {
                IO.println("you cant go " + direction);
            }
        } else {
            IO.println("You cant see anything");
        }
    }

    public void light() {
        if (adventure.getCurrentRoom().getLight()) {
            adventure.setLightFalse();
            IO.println("You turned off the light");
        } else {
            adventure.setLightTrue();
            IO.println("You turned on the light");
        }
    }

    public void inventory() {
        if (adventure.getPlayerItems().isEmpty()) {
            IO.println("You are not carrying anything.");
        } else {
            String inventoryText = "You are carrying: ";
            // Går igennem alle items i inventory
            for (int i = 0; i < adventure.getPlayerItems().size(); i++) {

                // henter item og tilføjer beskrivelse til teksten
                inventoryText = inventoryText + adventure.getPlayerItems().get(i).getItemDescription();

                // kontrollerer om item er det sidste i inventory
                if (i < adventure.getPlayerItems().size() - 1) {

                    // Hvis flere items tilføjes et komma og mellemrum
                    inventoryText = inventoryText + ", ";
                }
            }

            // udskirver inventory
            IO.println(inventoryText);
        }

    }

}
