public class Item_Rock extends Collectable {
    public Item_Rock() {
        super("rock", "A small, sharp rock. You could definitely throw this with enough force to break something", "The Kitchen", null, null);
    }

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
