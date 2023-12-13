public class RoomSetup {
    public static void startSetup(Player player) {
        Room cage = new Room("The Cage",
            "There are thin metal bars blocking you exit to the south\nWithin the cage, there is a rug on the floor",
            new Item[]{ new Item_StartingRug(), new Collectable("TEST", "THIS IS A TEST", "", "", "") }
        );

        Room masterBedRoom = new Room("The Master Bedroom",
            "You can see a king sized bed at the center and a large mirror in the corner\nThere are doors to the south and west",
            new Item[] { new Item("Mirror", "You looked in the mirror and saw yourself. You are a small orange, black, and white calico cat\nYou have large beady eyes that have a hint of evil behind them")}
        );

        Room masterBath = new Room("The Master Bathroom",
            "You see a large shower between a toilet and a small sink. There are some towels hanging from the sink that look climbable\nThere is also a bar of soap on the floor",
            new Item[]{ new Collectable("soap",
            "A large bar of apple scented soap, doesn't look like it gets used much",
            "The Living Room",
            "west",
            "You used the soap to loosen the door handle to the west")}
        );

        Room masterBathSink = new Room("Atop the sink",
            "You can see multiple bottles of medications and a glass cup containing a toothbrush",
            new Item[] { new Glass("cup", "A glass cup about half your height containing a toothbrush, there is some dried toothpaste caked around the bottom")}
        );

        Room frontHall = new Room("The Front Hall",
            "You see large, mostly empty room with a large rug on the floor and a lamp in the corner\nThere are doors to the west and south"
        );

        Room smallBedRoom = new Room("The Small Bedroom",
            "You see a small bed in the corner, a dresser against one wall, and several posters hung up\nThere are also some fragile looking toys on the floor and a door to the east",
            new Item[] { new Glass("toys", "Small action figures that look almost hand painted") }
        );

        Room livingRoom = new Room("The Living Room",
            "A large room with some couches surrounding a coffee table with a few objects on top\nThere are doors to the north and south as well as a locked door to the west"
        );

        Room coffeeTable = new Room("Atop the coffee table",
            "A small table with a glass mug at the center",
            new Item[] { new Glass("mug", "A large blue mug with some coffee grounds at the bottom")}
        );

        Room porch = new Room("The Porch",
            "You can see the line of trees just beyond your porch, you will never go there though because then you would have to touch the snow...\nThere is a rock on the ground and a door to the east",
            new Item[] {new Collectable("rock", "A small, sharp rock", "The Kitchen", "none", "You threw the rock at the glass, it shattered")}
        );

        Room diningRoom = new Room("The Dining Room",
            "You see a large table at the center of the room, too high for you to climb\nThere are doors to the north, south, and west"
        );

        Room kitchen = new Room("The Kitchen",
            "You can see large countertops lining the walls, too high for you to climb\nThere is a glass coffee pot on the counter and a door to the east"
        );

        linkRoomsNS(cage, masterBedRoom);
        cage.canGoSouth = false;
        linkRoomsWE(masterBath, masterBedRoom);
        linkRoomsUD(masterBathSink, masterBath);
        linkRoomsNS(masterBedRoom, frontHall);
        linkRoomsWE(smallBedRoom, frontHall);
        linkRoomsNS(frontHall, livingRoom);
        linkRoomsWE(porch, livingRoom);
        livingRoom.canGoWest = false;
        linkRoomsUD(coffeeTable, livingRoom);
        linkRoomsNS(livingRoom, diningRoom);
        linkRoomsWE(kitchen, diningRoom);
        // linkRoomsNS(diningRoom, bathRoom);
        // linkRoomsUD(bathroom, medCabinet);
        player.currentRoom = cage;
    }

    /**
     * A method that links room together from their north and south doors
     * Also sets the rooms "canGo<direction>" booleans to true
     * @param northernRoom The room that will end up being to the north
     * @param southernRoom The room that will end up being to the south
     */
    private static void linkRoomsNS(Room northernRoom, Room southernRoom) {
        northernRoom.southRoom = southernRoom;
        northernRoom.canGoSouth = true;
        southernRoom.northRoom = northernRoom;
        southernRoom.canGoNorth = true;
    }

    /**
     * A method that links room together from their west and east doors
     * Also sets the rooms "canGo<direction>" booleans to true
     * @param westernRoom The room that will end up being to the west
     * @param easternRoom The room that will end up being to the east
     */
    private static void linkRoomsWE(Room westernRoom, Room easternRoom) {
        westernRoom.eastRoom = easternRoom;
        westernRoom.canGoEast = true;
        easternRoom.westRoom = westernRoom;
        easternRoom.canGoWest = true;
    }

    /**
     * A method that links room together vertically
     * Also sets the rooms "canGo<direction>" booleans to true
     * @param roomAbove The room that will end up being on top
     * @param roomBelow The room that will end up being below
     */
    private static void linkRoomsUD(Room roomAbove, Room roomBelow) {
        roomAbove.roomBelow = roomBelow;
        roomAbove.canGoDown = true;
        roomBelow.roomAbove = roomAbove;
        roomBelow.canGoUp = true;
    }

    public static void main(String[] args) {
        Main.main(args);
    }
}
