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

    // Viser spilleren, hvad der er i rummet
    public String look () {
        return player.look();
    }

    // Viser spilleren et visuelt kort over han/hun befinder sig
    public String makeMap () {
        return gameMap.makeMap();
    }

    }


