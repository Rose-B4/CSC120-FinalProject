import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    static String userInput;
    static String[] inputAsList;

    /**
     * Allows the game to take the user's input
     * Also will lowercase the user's input
     * @return returns the user's input after it has become fully lowercase
     */
    public static String getInput() {
        String currentInput = input.nextLine(); // get user input
        for(int i=0; i<currentInput.length(); i++) { // go through each letter
            try { // try making it lowercase
                currentInput = currentInput.replace(String.valueOf(currentInput.charAt(i)), String.valueOf(currentInput.charAt(i)).toLowerCase());
            } catch(Exception e){} // don't do anything if the character couldn't become lower case
        }
        return currentInput;
    }
    /**
     * Parses the user's input into a string array that can be interpreted by the Room class
     * Strings are parsed at every space character
     * @param input a string of the user's raw input
     * @return a string array of the user's input, split at each new word
     */
    public static String[] parseInput(String input) {
        String[] toReturn = input.split(" ");
        return toReturn;
    }

    public static void main(String[] args) {
        Room centerRoom = new Room("Center Room", "This is a very cool room because....", new Item[]{new Item("Item_0", "This is an item"), new Collectable("Collectable", "This is a collectable")});
        Room northRoom = new Room("North Room", "This a very northern room");
        centerRoom.northRoom = northRoom;
        northRoom.southRoom = centerRoom;
        Player player = new Player(centerRoom);
        // player.inventory.add(new Collectable("Item-Name", "Item-Desc"));

        boolean gameOver = false;
        player.currentRoom.displayRoom();
        while(gameOver == false) {
            System.out.print("Looking for user input: ");
            userInput = getInput();
            inputAsList = parseInput(userInput);
            player.currentRoom.takeAction(player, inputAsList);
            System.out.println();
        }
    }
}
