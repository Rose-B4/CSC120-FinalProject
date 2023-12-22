/**
 * The class of all breakable objects around the house
 * inherits from Item
 */
public class Glass extends Item {
    /**
     * The default constructor for Glass
     * @param name the name of the item
     * @param description the description of the item
     */
    public Glass(String name, String description) {
        super(name, description);
        this.canBeMoved = true;
    }

    /**
     * Overrides the onMove method from Item
     * increases the player's score, removes the item from the room, and displays some flavor text
     */
    public void onMove(Player player, Room room) {
        player.score++;
        System.out.println("The "+this.name+" falls to the ground and shatters\n"+player.score+" down");
        room.itemsInRoom.remove(this);
    }
}
