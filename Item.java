public class Item {
    public String name;
    public String description;
    public boolean canBeMoved = false;
    
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Item(String name, String description, boolean canBeMoved) {
        this.name = name;
        this.description = description;
        this.canBeMoved = canBeMoved;
    }

    public String toString() {
        return this.name+": "+this.description;
    }

    public void move(Player player, Room room) {
        if(!this.canBeMoved) {
            System.out.println("Cannot move "+this.name);
            return;
        }
        this.onMove(player, room);
    }
    public void onMove(Player player, Room room) {
        throw new RuntimeException("Item without an overridden onMove() method was moved");
    }

    public void use() {
        
    }
}
