import java.util.ArrayList;

public class Player {
    protected ArrayList<Collectable> inventory;
    public Room currentRoom;

    public Player(Room startingRoom) {
        this.inventory = new ArrayList<>();
        this.currentRoom = startingRoom;
    }

    public void checkInventory() {
        System.out.println("Current Inventory:");
        for(int i=0; i< this.inventory.size(); i++) {
            System.out.println("    "+inventory.get(i));
        }
    }
    
}
