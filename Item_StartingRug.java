/**
 * A specific Item that has a special onMove
 * Does everything a normal Item does, but reveals a key Collectable when moved
 */
public class Item_StartingRug extends Item{
    /**
     * Constructor for Item_StartingRug
     * Takes no parameters since there are no variants of this item
     */
    public Item_StartingRug() {
        super("rug", "A small rug at the center of the cage, there is a small lump at the center");
        this.canBeMoved = true;
    }

    /**
     * Checks if the player has already moved this rug
     * If not, allows the player to move the rug revealing a key underneath
     */
    public void onMove(Player player, Room room) {
        if (!this.canBeMoved) {
            System.out.println("You have already moved the "+this.name);
            return;
        }
        this.canBeMoved = false;
        System.out.println("You slid the "+this.name+" out of the way revealing a key");
        room.itemsInRoom.add(
            new Collectable("key",
            "A small key, not much larger than your paw, looks like it would fit perfectly in the lock on your cage",
            "The Cage",
            "south",
            "You used the key to unlock the cage door"));
    }
}