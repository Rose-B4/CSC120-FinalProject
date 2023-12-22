/**
 * The collectable subclass of item
 * This is ued to create all items that the player can pickup and use
 */
public class Collectable extends Item{
    String usedInRoom;
    String openDirection;
    String flavorTextOnUse;
    boolean hasBeenUsed = false;

    /**
     * Default constructor for collectable
     * @param name name of the item
     * @param description description of item
     * @param usedInRoom the name of the room the item can be used in
     * @param openDirection the direction of the door that will open when this item is used
     * @param flavorText the flavor text that will be displayed when the item is used
     */
    public Collectable(String name, String description, String usedInRoom, String openDirection, String flavorText) {
        super(name, description);
        this.usedInRoom = usedInRoom;
        this.openDirection = openDirection;
        this.flavorTextOnUse = flavorText;

    }

    /**
     * The method to use any given item
     * Checks if this is the correct room, then if the item has already been used
     * If both checks pass it unlocks the door it is set to unlock and marks the item as used
     */
    public void use(Player player, Room room) {
        boolean canBeUsed = false;
        if(this.usedInRoom.equals(room.name)) {
            canBeUsed = true;
        }
        if(canBeUsed == false) { 
            System.out.println("Cannot use "+this.name+" here");
            return;
        }
        if(this.hasBeenUsed) {
            System.out.println("You have already used "+this.name);
            return;
        }
        System.out.println(this.flavorTextOnUse);
        switch (openDirection) {
            case "north":
                room.canGoNorth = true;
                break;
            case "south":
                room.canGoSouth = true;
                break;
            case "east":
                room.canGoEast = true;
                break;
            case "west":
                room.canGoWest = true;
                break;
            case "up":
                room.canGoUp = true;
                break;
            case "down":
                room.canGoDown = true;
                break;
        }
        this.hasBeenUsed = true;
    }
}
