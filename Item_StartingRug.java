public class Item_StartingRug extends Item{
    public Item_StartingRug() {
        super("rug", "A small rug at the center of the cage, there is a small lump at the center");
        this.canBeMoved = true;
    }

    public void onMove(Player player, Room room) {
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