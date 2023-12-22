/**
 * The Item super class
 * This class works as both a way to add generic items to rooms, and as a parent to other items
 */
public class Item {
    public String name;
    public String description;
    public boolean canBeMoved = false;
    
    /**
     * The main constructor method for the item class
     * The object will default to not being moveable
     * @param name The name of the item
     * @param description The description of the item
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
    /**
     * An overloaded constructor method for the item class
     * Allows the player to specify if the object should be moveable or not
     * @param name The name of the item
     * @param description The description of the item
     * @param canBeMoved Whether or not the object is moveable
     */
    public Item(String name, String description, boolean canBeMoved) {
        this(name,description);
        this.canBeMoved = canBeMoved;
    }

    /**
     * An overridden toString() method
     * Returns the name and description nicely formatted
     */
    public String toString() {
        return this.name+": "+this.description;
    }

    /**
     * A method that will me called if the player tries to move this item
     * If the item is not moveable it will print flavor text and end the method
     * If the item is moveable, onMove() will be called
     * @param player The player's character object
     * @param room The room this object is located in
     */
    public void move(Player player, Room room) {
        if(!this.canBeMoved) {
            System.out.println("Cannot move "+this.name);
            return;
        }
        this.onMove(player, room);
    }
    /**
     * A helper method for move() which is called if this object is moveable
     * This method is never intended to be called and will throw a runtime exception
     * This method is only intended to be overridden by subclasses of Item
     * @param player The player's character object
     * @param room The current room
     */
    public void onMove(Player player, Room room) {
        throw new RuntimeException("Item without an overridden onMove() method was moved");
    }

    /**
     * Primarily made to be overridden by child classes
     * If there is no override, it calls the move method
     * @param player The player's character object
     * @param room The current room
     */
    public void use(Player player, Room room) {
        this.move(player, room);
    }
}
