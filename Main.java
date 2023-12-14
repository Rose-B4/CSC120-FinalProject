import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    static String userInput;
    static String[] inputAsList;
    public static boolean gameOver = false;
    public static boolean returnToCage = false;

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
    /**
     * Displays the asci art for the logo of the game
     */
    public static void displayLogo() {
        System.out.println("\n"+
        "░░████░░███░░█░░░░░█████░░░████░░███░░░░░░░░░███░░█░░░█░█████░░████░█████\n"+
        "░█░░░░░█░░░█░█░░░░░░░█░░░░█░░░░░█░░░█░░░░░░░█░░░█░█░░░█░█░░░░░█░░░░░░░█░░\n"+
        "░█░░░░░█████░█░░░░░░░█░░░░█░░░░░█░░░█░░░░░░░█░░░█░█░░░█░████░░░███░░░░█░░\n"+
        "░█░░░░░█░░░█░█░░░░░░░█░░░░█░░░░░█░░░█░░░░░░░█░░█░░█░░░█░█░░░░░░░░░█░░░█░░\n"+
        "░░████░█░░░█░█████░█████░░░████░░███░░░░░░░░░██░█░░███░░█████░████░░░░█░░\n");
        System.out.println("Type help for help.");
    }

    public static void main(String[] args) {
        displayLogo();
        Player player = new Player();
        RoomSetup.startSetup(player);

        player.currentRoom.displayRoom();
        while(!gameOver) {
            System.out.println();
            System.out.print(">> ");
            userInput = getInput();
            inputAsList = parseInput(userInput);
            player.currentRoom.takeAction(player, inputAsList);
            if(player.score == 5 && returnToCage == false) {
                System.out.println("You have successfully destroyed all of the breakable objects.\nYou can now go back to your cage and go to sleep.");
                returnToCage = true;
            }
        }
    }
}
