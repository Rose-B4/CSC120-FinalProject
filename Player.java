import java.util.ArrayList;

/**
 * The player class
 * Does very little other than hold the inventory, score, and currentRoom pointer
 */
public class Player {
    protected ArrayList<Item> inventory;
    public Room currentRoom;
    public int score;

    /**
     * The constructor for the player class
     * sets the inventory to an empty list and the score to 0
     */
    public Player() {
        this.inventory = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Displays the player's current inventory
     */
    public void checkInventory() {
        System.out.println("Current Inventory:");
        for(int i=0; i< this.inventory.size(); i++) {
            System.out.println("    "+inventory.get(i).name);
        }
    }
    
}
