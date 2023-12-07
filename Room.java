import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    public String name;
    public String description;
    ArrayList<Item> itemsInRoom;
    ArrayList<String> availableActions;

    Room northRoom;
    Room southRoom;
    Room eastRoom;
    Room westRoom;

    Room roomAbove;
    Room roomBelow;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.itemsInRoom = new ArrayList<>();
        this.availableActions = new ArrayList<String>() {{
            add("help");
            add("look");
            add("bag");
            add("go");
            add("check");
            add("take");
            add("climb");
        }};
    }
    public Room(String name, String description, Item[] items) {
        this(name, description);
        this.itemsInRoom = new ArrayList<>(Arrays.asList(items));
        
    }
    public Room(String name, String description, Item[] items, String[] extraActions) {
        this(name, description, items);
        for(int i=0; i<extraActions.length; i++) {
            this.availableActions.add(extraActions[i]);
        }
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
                    this.check(action[1]);
                    break;
                case "take":
                    this.take(player, action[1]);
                    break;
                // case "climb":
                //     this.climb(action[1]);
                //     break;
                // case "move":
                //     this.move(action[1]);
                //     break;
                // case "push":
                //     this.push(action[1]);
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
        System.out.println("Here are the commands you currently have access to:");
        System.out.println("  help (opens this menu)");
        System.out.println("  look (gives a description of the current room)");
        System.out.println("  bag (displays your current inventory)");
        System.out.println("  go <direction> (allows you to move north, south, east, or west by one room)");
        System.out.println("  climb <direction> (allows you to climb either up or down)");
        System.out.println("  check <item name> (gives a description of the inputted item)");
        System.out.println("  take <item name> (adds the inputted item to your inventory)");
        
        if(this.availableActions.contains("move")) {
            System.out.println("  move <item> (allows you to move the inputted item out of the way)");
        }
        if(this.availableActions.contains("push")) {
            System.out.println(  "push <item> (pushes the inputted item off of a ledge)");
        }
    }

    public void displayRoom() {
        System.out.println("You are in "+this.name);
        System.out.println(this.description);
    }

    private void go(Player player, String direction) {
        boolean movedThisTurn = false;
        movedThisTurn = goDirection(player, direction, "north", northRoom);
        movedThisTurn = goDirection(player, direction, "south", southRoom) || movedThisTurn;
        movedThisTurn = goDirection(player, direction, "east", eastRoom) || movedThisTurn;
        movedThisTurn = goDirection(player, direction, "west", westRoom) || movedThisTurn;
        if(movedThisTurn == false) {
            System.out.println("Cannot go " + direction);
        }
    }
    private boolean goDirection(Player player, String inputDirection, String goDirection, Room targetRoom) {
        if(inputDirection.equals(goDirection) && targetRoom!=null) {
            System.out.println("You left "+this.name);
            player.currentRoom = targetRoom;
            player.currentRoom.displayRoom();
            return true;
        }
        return false;
    }

    private void check(String itemName) {
        int index = -1;
        for(int i=0; i<this.itemsInRoom.size(); i++) {
            if(this.itemsInRoom.get(i).name.toLowerCase().equals(itemName)) {
                index = i;
            }
        }
        if(index != -1) {
            System.out.println(itemName);
            System.out.println(this.itemsInRoom.get(index).description);
        }
    }

    private void take(Player player, String itemName) {
        int index = -1;
        for(int i=0; i<this.itemsInRoom.size(); i++) {
            if(this.itemsInRoom.get(i).name.toLowerCase().equals(itemName)) {
                index = i;
            }
        }
        if(index != -1 && this.itemsInRoom.get(index) instanceof Collectable) {
            player.inventory.add(new Collectable(itemName, this.itemsInRoom.get(index).description));
            this.itemsInRoom.remove(index);
            System.out.println("You picked up the "+itemName+" and put it in your inventory");
        }
        else {
            System.out.println("Cannot take " +itemName);
        }
    }

    private void debugCheck() {
        System.out.println("You are in the "+this.name);
        System.out.println(this.description);
        System.out.println("For testing: there are these items:");
        for(int i=0; i<this.itemsInRoom.size(); i++) {
            System.out.println("  "+this.itemsInRoom.get(i).toString());
        }
        System.out.println("For testing: there are these actions available:");
        for(int i=0; i<this.availableActions.size(); i++) {
            System.out.println("  "+this.availableActions.get(i).toString());
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Room centerRoom = new Room("Center Room", "This is a very cool room because....", new Item[]{new Item("Item 0", "This is an item")});
        Room northRoom = new Room("North Room", "This a very northern room");
        centerRoom.northRoom = northRoom;
        northRoom.southRoom = centerRoom;
        // tRoom.debugCheck();
        Player player = new Player(centerRoom);

        player.currentRoom.takeAction(player, new String[]{"go","north"});
        System.out.println();
        player.currentRoom.takeAction(player, new String[]{"go","south"});
        // tRoom.help();
    }
}
