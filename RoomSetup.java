public class RoomSetup {
    public static void startSetup(Player player) {
        Room cage = new Room("The Cage",
            "There are thin metal bars blocking you exit to the south\nWithin the cage, there is a rug on the floor",
            new Item[]{ new Item_StartingRug() }
        );

        Room masterBedRoom = new Room("The Master Bedroom",
            "You can see a king sized bed at the center and a large mirror in the corner\nThere are doors to the south and east",
            new Item[] { new Item("Mirror", "You looked in the mirror and saw yourself. You are a small orange, black, and white calico cat\nYou have large beady eyes that have a hint of evil behind them")}
        );

        Room masterBath = new Room("The Master Bathroom",
            "You see a large shower between a toilet and a small sink. There are some towels hanging from the sink that look climbable\nThere is also a bar of soap on the floor",
            new Item[]{new Collectable("soap", "A large bar of apple scented soap, doesn't look like it gets used much")});

        Room masterBathSink = new Room("Atop the sink",
            "You can see multiple bottles of medications and a glass cup containing a toothbrush",
            new Item[] { new Glass("cup", "A glass cup about half your height containing a toothbrush, there is some dried toothpaste caked around the bottom")});

        linkRoomsNS(cage, masterBedRoom);
        // cage.canGoSouth = false;
        linkRoomsWE(masterBedRoom, masterBath);
        linkRoomUD(masterBathSink, masterBath);
        player.currentRoom = cage;
    }

    private static void linkRoomsNS(Room northernRoom, Room southernRoom) {
        northernRoom.southRoom = southernRoom;
        northernRoom.canGoSouth = true;
        southernRoom.northRoom = northernRoom;
        southernRoom.canGoNorth = true;
    }

    private static void linkRoomsWE(Room westernRoom, Room easternRoom) {
        westernRoom.eastRoom = easternRoom;
        westernRoom.canGoEast = true;
        easternRoom.westRoom = westernRoom;
        easternRoom.canGoWest = true;
    }

    private static void linkRoomUD(Room roomAbove, Room roomBelow) {
        roomAbove.roomBelow = roomBelow;
        roomAbove.canGoDown = true;
        roomBelow.roomAbove = roomAbove;
        roomBelow.canGoUp = true;
    }

    public static void main(String[] args) {
        Main.main(args);
    }
}
