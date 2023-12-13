import java.util.ArrayList;

public class Player {
    protected ArrayList<Item> inventory;
    public Room currentRoom;
    public int score = 0;

    public Player() {
        this.inventory = new ArrayList<>();
    }

    public void checkInventory() {
        System.out.println("Current Inventory:");
        for(int i=0; i< this.inventory.size(); i++) {
            System.out.println("    "+inventory.get(i).name);
        }
    }
    
}
