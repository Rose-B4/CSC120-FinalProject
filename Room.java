import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    public String name;
    public String description;
    public ArrayList<Item> itemsInRoom;
    public ArrayList<String> availableActions;
    public ArrayList<String> usableItems;

    public Room northRoom;
    public Room southRoom;
    public Room eastRoom;
    public Room westRoom;
    public Room roomAbove;
    public Room roomBelow;

    public boolean canGoNorth = false;
    public boolean canGoSouth = false;
    public boolean canGoEast = false;
    public boolean canGoWest = false;
    public boolean canGoUp = false;
    public boolean canGoDown = false;


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.itemsInRoom = new ArrayList<Item>();
        this.availableActions = new ArrayList<String>() {{
            add("help");
            add("look");
            add("bag");
            add("go");
            add("move");
            add("check");
            add("take");
            add("climb");
        }};
        this.usableItems = new ArrayList<String>();
    }
    public Room(String name, String description, Item[] items) {
        this(name, description);
        this.itemsInRoom = new ArrayList<>(Arrays.asList(items));
        
    }

    public void takeAction(Player player, String[] action) {
        if(action.length > 2) {
            System.out.println("Your input was too long, please try again with a 1 or 2 word input");
            return;
        }
        if(!availableActions.contains(action[0])) {
            System.out.println("Your action couldn't be recognized, please try again");
            return;
        }
        if(action.length == 1) {
            switch (action[0]) {
                case "help":
                    this.help();
                    break;
                case "look":
                    this.displayRoom();
                    break;
                case "bag":
                    player.checkInventory();
                    break;
                default:
                    System.out.println("Your action couldn't be recognized, please try again");
                    break;
            }
        }
        else if(action.length == 2) {
            switch (action[0]) {
                case "go":
                    this.go(player, action[1]);
                    break;
                case "check":
                    this.check(player, action[1]);
                    break;
                case "take":
                    this.take(player, action[1]);
                    break;
                case "climb":
                    this.go(player, action[1]);
                    break;
                case "move":
                    this.move(player, action[1]);
                    break;
                // case "use":
                //     this.use(action[1]);
                //     break;
                default:
                    System.out.println("Your action couldn't be recognized, please try again");
                    break;
            }
        }
    }

    private void help() {
        System.out.println("All commands are either 1 or 2 words, the game will not except longer commands");
        System.out.println("Here are the commands you can currently currently use:");
        System.out.println("  help (opens this menu)");
        System.out.println("  look (gives a description of the current room)");
        System.out.println("  bag (displays your current inventory)");
        System.out.println("  go <direction> (allows you to move north, south, east, or west by one room)");
        System.out.println("  climb <direction> (allows you to climb either up or down)");
        System.out.println("  check <item name> (gives a description of the inputted item)");
        System.out.println("  take <item name> (adds the inputted item to your inventory)");
        
        if(this.availableActions.contains("move")) {
            System.out.println("  move <item name> (allows you to move the inputted item out of the way)");
        }
        System.out.println();
        System.out.println("Items in current room:");
        for(int i=0; i<this.itemsInRoom.size(); i++) {
            System.out.println(this.itemsInRoom.get(i).name);
        }
    }

    public void displayRoom() {
        System.out.println("You are in "+this.name);
        System.out.println(this.description);
    }

    private void go(Player player, String direction) {
        boolean movedThisTurn = false;
        movedThisTurn = goDirection(player, direction, "north", northRoom, canGoNorth);
        movedThisTurn = goDirection(player, direction, "south", southRoom, canGoSouth) || movedThisTurn;
        movedThisTurn = goDirection(player, direction, "east", eastRoom, canGoEast) || movedThisTurn;
        movedThisTurn = goDirection(player, direction, "west", westRoom, canGoWest) || movedThisTurn;
        movedThisTurn = goDirection(player, direction, "up", roomAbove, canGoUp) || movedThisTurn;
        movedThisTurn = goDirection(player, direction, "down", roomBelow, canGoDown) || movedThisTurn;
        if(movedThisTurn == false) {
            System.out.println("Cannot go " + direction);
        }
    }
    private boolean goDirection(Player player, String inputDirection, String goDirection, Room targetRoom, boolean canMove) {
        if(inputDirection.equals(goDirection) && targetRoom!=null && canMove) {
            System.out.println("You left "+this.name);
            player.currentRoom = targetRoom;
            player.currentRoom.displayRoom();
            return true;
        }
        return false;
    }

    private void check(Player player, String itemName) {
        int index = this.searchForItem(itemName, this.itemsInRoom);
        if (index != -1) {
            System.out.println(this.itemsInRoom.get(index).description);
            return;
        }

        index = this.searchForItem(itemName, player.inventory);
        if (index != -1) {
            System.out.println(player.inventory.get(index).description);
            return;
        }

        System.out.println("Cannot find "+itemName);
    }

    private void take(Player player, String itemName) {
        int index = this.searchForItem(itemName, this.itemsInRoom);
        if (index == -1) {
            System.out.println("Cannot find "+itemName);
            return;
        }

        if(this.itemsInRoom.get(index) instanceof Collectable) {
            player.inventory.add(new Collectable(itemName, this.itemsInRoom.get(index).description));
            this.itemsInRoom.remove(index);
            System.out.println("You picked up the "+itemName+" and put it in your inventory");
        }
        else {
            System.out.println("Cannot take " +itemName);
        }
    }

    private void move(Player player, String itemName) {
        int index = this.searchForItem(itemName, this.itemsInRoom);
        if(index == -1) { 
            System.out.println("Cannot find "+itemName);
            return;
        }
        this.itemsInRoom.get(index).move(player, this);
    }

    private int searchForItem(String itemName, ArrayList<Item> items) {
        int index = -1;
        for(int i=0; i<items.size(); i++) {
            if(items.get(i).name.toLowerCase().equals(itemName)) {
                index = i;
            }
        }
        return index;
    }

    // private void debugCheck() {
    //     System.out.println("You are in the "+this.name);
    //     System.out.println(this.description);
    //     System.out.println("For testing: there are these items:");
    //     for(int i=0; i<this.itemsInRoom.size(); i++) {
    //         System.out.println("  "+this.itemsInRoom.get(i).toString());
    //     }
    //     System.out.println("For testing: there are these actions available:");
    //     for(int i=0; i<this.availableActions.size(); i++) {
    //         System.out.println("  "+this.availableActions.get(i).toString());
    //     }
    //     System.out.println();
    // }


    public static void main(String[] args) {
        Main.main(args);
        // Room centerRoom = new Room("Center Room", "This is a very cool room because....", new Item[]{new Item("Item 0", "This is an item")});
        // Room northRoom = new Room("North Room", "This a very northern room");
        // centerRoom.northRoom = northRoom;
        // northRoom.southRoom = centerRoom;
        // centerRoom.debugCheck();
        // Player player = new Player();
        // player.currentRoom = centerRoom;
        // player.currentRoom.takeAction(player, new String[]{"go","north"});
        // System.out.println();
        // player.currentRoom.takeAction(player, new String[]{"go","south"});
    }
}
