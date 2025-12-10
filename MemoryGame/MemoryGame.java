import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MemoryGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create card list
        ArrayList<String> cards = new ArrayList<>();
        cards.add("A");
        cards.add("A");
        cards.add("B");
        cards.add("B");
        cards.add("C");
        cards.add("C");
        cards.add("D");
        cards.add("D");

        // Shuffle the cards randomly
        Collections.shuffle(cards);

        // Board setup
        String[] board = new String[cards.size()];
        boolean[] flipped = new boolean[cards.size()];

        // Initialize board with blanks instead of null
        for (int i = 0; i < board.length; i++) {
            board[i] = " ";
        }

        int pairsFound = 0;

        // Game loop
        while (pairsFound < 4) {

            printBoard(board);

            // First card selection
            int firstIndex = getCardIndex(scanner, flipped, "Enter index of first card to flip:");
            board[firstIndex] = cards.get(firstIndex); // reveal card temporarily

            printBoard(board);

            // Second card selection
            int secondIndex = getCardIndex(scanner, flipped, "Enter index of second card to flip:");
            board[secondIndex] = cards.get(secondIndex); // reveal card temporarily

            printBoard(board);

            // Check match
            if (cards.get(firstIndex).equals(cards.get(secondIndex))) {
                System.out.println("You found a pair!");
                flipped[firstIndex] = true;
                flipped[secondIndex] = true;
                pairsFound++;
            } else {
                System.out.println("Sorry, those cards don't match.");

                // Hide them again
                board[firstIndex] = " ";
                board[secondIndex] = " ";
            }
        }

        // Win message
        System.out.println("\nYou won! Congratulations!");
    }

    // Validate and take card index input
    public static int getCardIndex(Scanner scanner, boolean[] flipped, String prompt) {
        int index;

        while (true) {
            System.out.println(prompt);
            index = scanner.nextInt();

            if (index < 0 || index >= flipped.length) {
                System.out.println("Invalid index. Try again.");
            } 
            else if (flipped[index]) {
                System.out.println("Card already matched. Choose another.");
            }
            else {
                break;
            }
        }
        return index;
    }

    // Display the game board
    public static void printBoard(String[] board) {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| " + board[i] + " ");
        }
        System.out.println("|");

        // Show indexes for easier playing
        System.out.print("  ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.println("\n");
    }
}
