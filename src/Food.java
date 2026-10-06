public class Food extends Item{

    private int healthPoints;

    public Food (String itemName, String itemDescription, double itemWeight,int healthPoints) {
        super(itemName, itemDescription, itemWeight);
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints () {
        return  healthPoints;

    }
}
