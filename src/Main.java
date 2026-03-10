import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
 class UseCase7PalindromeCheckerApp {

    // Method to check palindrome using Deque (Double Ended Queue)
    public static boolean isPalindrome(String str) {

        // Step 1: Insert all characters into the Deque
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            deque.addLast(str.charAt(i));
        }

        // Step 2 & 3: Remove first & last, compare until deque has 0 or 1 element
        while (deque.size() > 1) {
            char front = deque.removeFirst();
            char rear = deque.removeLast();
            if (front != rear) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("  UC7: Palindrome Check - Deque Based");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // Step 1: Insert characters into Deque and display
        Deque<Character> deque = new ArrayDeque<>();
        System.out.println("\n--- Inserting Characters into Deque ---");
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            deque.addLast(c);
            System.out.println("  Inserted: '" + c + "'  --> Deque: " + deque);
        }

        // Step 2 & 3: Remove first & last, compare
        System.out.println("\n--- Comparing Front & Rear Characters ---");
        boolean palindrome = true;
        int step = 1;

        while (deque.size() > 1) {
            char front = deque.removeFirst();
            char rear = deque.removeLast();

            if (front == rear) {
                System.out.println("  Step " + step + ": Front = '" + front
                        + "'  <-->  Rear = '" + rear
                        + "'  => Match!   Remaining: " + deque);
            } else {
                System.out.println("  Step " + step + ": Front = '" + front
                        + "'  <-->  Rear = '" + rear
                        + "'  => Mismatch!");
                palindrome = false;
                break;
            }
            step++;
        }

        if (palindrome && deque.size() == 1) {
            System.out.println("  Middle character '" + deque.peekFirst()
                    + "' ignored (odd-length string).");
        }

        // Print result
        System.out.println("\n----------------------------------------------");
        if (palindrome) {
            System.out.println("Result: \"" + input + "\" IS a palindrome.");
        } else {
            System.out.println("Result: \"" + input + "\" is NOT a palindrome.");
        }
        System.out.println("==============================================");

        scanner.close();
    }
}