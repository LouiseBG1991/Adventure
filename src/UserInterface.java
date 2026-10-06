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
            if (command.startsWith("equip")) {
                equip(command);
                continue;
            }
            if (command.startsWith("attack")) {
                attack(command);
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

    public void take(String command) {
        String itemName = command.substring(5).trim();
        TakeResult result = adventure.takeItem(itemName);

        switch (result) {
            case TAKE -> {
                Item item = adventure.findItemByName(itemName);
                if (item != null) {
                    IO.println("You have taken the " + item.getItemDescription());
                } else {
                    IO.println("You have taken " + itemName);
                }
            }
            case TOO_HEAVY -> {
                IO.println("You cannot carry that much weight! Drop something first.");
            }
            case NOT_FOUND -> {
                IO.println("There is nothing like " + itemName + " to take around here");
            }
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
        String foodName = command.substring(4);
        EatResult result = adventure.eat(foodName);
        if (result == EatResult.NOT_FOUND) {
            IO.println("There is nothing like " + foodName + " to eat around here.");
        } else if (result == EatResult.NOT_FOOD) {
            IO.println("You cannot eat " + foodName + ".");
        } else if (result == EatResult.EATEN) {
            IO.println("You eat " + foodName + ".");
        }
        }
        public void equip(String command) {
        String weaponName = command.substring(6);
        EquipResult result = adventure.equip(weaponName);
        if (result == EquipResult.NOT_FOUND) {
            IO.println("You don't have " + weaponName + " in your inventory.");
        } else if (result == EquipResult.NOT_WEAPON) {
            IO.println("The " + weaponName + " is not a weapon!");
        } else if (result == EquipResult.EQUIPPED) {
            IO.println("You have equipped " + weaponName + ".");
        }
        }

        public void attack(String command) {
        AttackResult result = adventure.attack();
        Item weapon = adventure.getEquippedWeapon();
        if (result == AttackResult.NO_WEAPON) {
            IO.println("You don't have an equipped weapon.");
        } else if (result == AttackResult.NO_AMMUNITION) {
            IO.println ("You don't have any ammunition left.");
        } else if (result == AttackResult.SWING) {
            IO.println("You swing " + weapon.getItemDescription() + " into the air");
        } else if (result == AttackResult.FIRE) {
            int shots = adventure.getShotsleft();
            IO.println("You fire " + weapon.getItemDescription() + " into the empty air. " + shots + " shots left");
        }
        }

    public void help() {
        IO.println("The commands are; n for north, e for east, w for west, s for south, Help for help, and Exit for exit.");
    }

    public void look() {
        if (adventure.getCurrentRoom().getLight()) {
            IO.println(adventure.look());
            IO.println(adventure.makeMap());
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

            Item equippedWeapon = adventure.getEquippedWeapon();
            if (equippedWeapon != null) {
                IO.println("Equipped: " + equippedWeapon.getItemDescription());
            }
        }

    }

}
