import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    public String name;
    public String description;
    public ArrayList<Item> itemsInRoom;

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

    /**
     * The default constructor method for the room class
     * Creates a room with all of default actions that are available in every room
     * @param name The name of the room that will be displayed when entering
     * @param description The description of the room that will be displayed when the look command is used
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.itemsInRoom = new ArrayList<Item>();
    }
    /**
     * An overloaded version of the room constructor, does all of the same things as the default constructor, but takes an array of items that will be available in the room
     * @param name see default constructor
     * @param description see default constructor
     * @param items an array of items that will be added to the room on creation
     */
    public Room(String name, String description, Item[] items) {
        this(name, description);
        this.itemsInRoom = new ArrayList<>(Arrays.asList(items));
        
    }

    /**
     * The main method of the room class that will be called
     * This method is used to take the player's input and convert it into actions
     * This method is used to call almost every other method in this class
     * @param player the player's character object
     * @param action the array of strings that the player inputted
     */
    public void takeAction(Player player, String[] action) {
        if(action.length > 2) {
            System.out.println("Your input was too long, please try again with a 1 or 2 word input\nType \"help\" for help");
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
                case "sleep":
                    this.sleep(player);
                    break;
                default:
                    System.out.println("Your action couldn't be recognized, please try again.\nType \"help\" for help");
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
                case "drop":
                    this.drop(player, action[1]);
                    break;
                case "climb":
                    this.go(player, action[1]);
                    break;
                case "move":
                    this.move(player, action[1]);
                    break;
                case "use":
                    this.use(player, action[1]);
                    break;
                default:
                    System.out.println("Your action couldn't be recognized, please try again.\nType \"help\" for help");
                    break;
            }
        }
    }

    /**
     * One of the player's possible inputs called when the player uses "help"
     * displays all of the available actions and items
     */
    private void help() {
        System.out.println("All commands are either 1 or 2 words, the game will not except longer commands");
        System.out.println("Here are the commands you can currently currently use:");
        System.out.println("  help (opens this menu)");
        System.out.println("  look (gives a description of the current room)");
        System.out.println("  bag (displays your current inventory)");
        System.out.println("  sleep (go to sleep and finish your mission)");
        System.out.println("  go <direction> (allows you to move north, south, east, or west by one room)");
        System.out.println("  climb <direction> (allows you to climb either up or down)");
        System.out.println("  check <item name> (gives a description of the inputted item)");
        System.out.println("  take <item name> (adds the inputted item to your inventory)");
        System.out.println("  move <item name> (allows you to move the inputted item out of the way)");

        System.out.println();
        System.out.println("Items in current room:");
        for(int i=0; i<this.itemsInRoom.size(); i++) {
            System.out.println(this.itemsInRoom.get(i).name);
        }
    }

    /**
     * A method that is used to display the name and description of the room
     * This is called both when the player enters a new room and when they use "look"
     */
    public void displayRoom() {
        System.out.println("You are in "+this.name);
        System.out.println(this.description);
    }

    public void sleep(Player player) {
        if(player.score < 5) {
            System.out.println("Can't sleep now, there are still things to break");
        }
        else if(!this.name.equals("The Cage")) {
            System.out.println("You must return to your cage to go back to sleep");
        }
        else {
            System.out.println("You have completed your mission and are now going back to sleep after a long strenuous day of work.");
            System.out.println("Game Over");
            Main.gameOver = true;
        }
    }

    /**
     * A method that is used to move the player between rooms
     * This is called when the player uses "go" or "climb"
     * @param player the player's character object
     * @param direction the string representing which direction they chose to go
     */
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
    /**
     * A helper method for go()
     * This method is used to check whether or not the player is allowed to go in a specific direction
     * This method will set the player's current room to the new room if the move is successful and displays the new room's information
     * @param player The player's character object
     * @param inputDirection The string of the direction the player chose to go
     * @param goDirection The direction the method is currently checking
     * @param targetRoom The room the player will end up in if the move is successful
     * @param canMove Whether or not the player is allowed to move in the inputted direction
     * @return returns a boolean of whether or not the move was successful
     */
    private boolean goDirection(Player player, String inputDirection, String goDirection, Room targetRoom, boolean canMove) {
        if(inputDirection.equals(goDirection) && targetRoom!=null && canMove) {
            System.out.println("You left "+this.name);
            player.currentRoom = targetRoom;
            player.currentRoom.displayRoom();
            return true;
        }
        return false;
    }

    /**
     * A method that displays the information of a specific item
     * Can check items from both the current room and the player's inventory
     * @param player The player's character object
     * @param itemName The name of the item the player would like to inspect
     */
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

        System.out.println("Cannot find "+itemName+"\nType \"help\" for help");
    }

    /**
     * A method that allows the player to take an item from the room and add it to their inventory
     * The item must be a collectable for the player to be able to take it 
     * @param player The player's character object
     * @param itemName The name of the item the player would like to take
     */
    private void take(Player player, String itemName) {
        int index = this.searchForItem(itemName, this.itemsInRoom);
        if (index == -1) {
            System.out.println("Cannot find "+itemName);
            return;
        }

        if(this.itemsInRoom.get(index) instanceof Collectable) {
            player.inventory.add(this.itemsInRoom.get(index));
            this.itemsInRoom.remove(index);
            System.out.println("You picked up the "+itemName+" and put it in your inventory");
        }
        else {
            System.out.println("Cannot take " +itemName+"\nType \"help\" for help");
        }
    }

    /**
     * Allows the player to drop an item in the current room
     * The dropped item will be added to the items list of the room
     * @param player The player's character object
     * @param itemName The name of the item that will be dropped
     */
    private void drop(Player player, String itemName) {
        int index = this.searchForItem(itemName, player.inventory);
        if (index == -1) {
            System.out.println("Cannot find "+itemName+"\nType \"help\" for help");
            return;
        }
        this.itemsInRoom.add(player.inventory.get(index));
        player.inventory.remove(player.inventory.get(index));
    }

    /**
     * A method that allows the player to move items in the room
     * This can be though of more like an interact method, but move makes more sense in the context of this game
     * If this method is called on a glass object, it will be smashed and the player's score will increase
     * @param player The player's character object
     * @param itemName The name of the item the player would like to move
     */
    private void move(Player player, String itemName) {
        int index = this.searchForItem(itemName, this.itemsInRoom);
        if(index == -1) { 
            System.out.println("Cannot find "+itemName+"\nType \"help\" for help");
            return;
        }
        this.itemsInRoom.get(index).move(player, this);
    }

    private void use(Player player, String itemName) {
        int index = this.searchForItem(itemName, player.inventory);
        if(index == -1) { 
            System.out.println("Cannot find "+itemName+"\nType \"help\" for help");
            return;
        }
        player.inventory.get(index).use(player, this);
    }

    /**
     * A helper method for most other methods in this class
     * Allows other methods to more easily find where an item is in any given inventory (usually the room's item list but sometimes the player's inventory)
     * @param itemName The name of the item we are searching for
     * @param items The array list of items that will be searched
     * @return The index of the desired item in the list
     * Note: if the index returned is -1 then the item was not found
     */
    private int searchForItem(String itemName, ArrayList<Item> items) {
        int index = -1;
        for(int i=0; i<items.size(); i++) {
            if(items.get(i).name.toLowerCase().equals(itemName)) {
                index = i;
            }
        }
        return index;
    }


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