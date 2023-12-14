public class Collectable extends Item{
    String usedInRoom;
    String openDirection;
    String flavorTextOnUse;
    boolean hasBeenUsed = false;

    public Collectable(String name, String description, String usedInRoom, String openDirection, String flavorText) {
        super(name, description);
        this.usedInRoom = usedInRoom;
        this.openDirection = openDirection;
        this.flavorTextOnUse = flavorText;

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
