/**
 * A specific Collectable that acts more like a Glass
 * Does everything a normal Collectable does, but increases the player's score instead of opening a door
 */
public class Item_Rock extends Collectable {
    /**
     * Constructor for Item_Rock
     * Takes no parameters since there are no variants of this item
     */
    public Item_Rock() {
        super("rock", "A small, sharp rock. You could definitely throw this with enough force to break something", "The Kitchen", null, null);
    }

    /**
     * Overrides use from Collectable
     * checks if the player is in the desired room, then increases the player's score and marks it as used
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
            System.out.println("You have already used the "+this.name);
            return;
        }
        player.score++;
        System.out.println("The coffee pot falls to the ground and shatters\n"+player.score+" down");
        this.hasBeenUsed = true;
    }
}
