public class Glass extends Item {
    public Glass(String name, String description) {
        super(name, description);
        this.canBeMoved = true;
    }

    public void onMove(Player player, Room room) {
        player.score ++;
        System.out.println("The "+this.name+" falls to the ground and shatters\n"+player.score+" down");
        room.itemsInRoom.remove(this);
    }
}
