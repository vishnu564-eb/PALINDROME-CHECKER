import java.util.Scanner;
import java.util.Stack;

public class UseCase5PalindromeCheckerApp {

    // Method to check palindrome using Stack (LIFO)
    public static boolean isPalindrome(String str) {
        // Step 1: Push all characters into the stack
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        // Step 2: Pop characters and compare with original string
        for (int i = 0; i < str.length(); i++) {
            char popped = stack.pop(); // Pops in reverse order (LIFO)
            if (str.charAt(i) != popped) {
                return false; // Mismatch found, not a palindrome
            }
        }

        // Step 3: All characters matched
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("  UC5: Palindrome Check - Stack Based");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // Step 1: Push characters into the stack and display
        Stack<Character> stack = new Stack<>();
        System.out.println("\n--- Pushing Characters into Stack ---");
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            stack.push(c);
            System.out.println("  Pushed: '" + c + "'  --> Stack: " + stack);
        }

        // Step 2: Pop characters and compare
        System.out.println("\n--- Popping Characters & Comparing ---");
        boolean palindrome = true;
        Stack<Character> tempStack = (Stack<Character>) stack.clone(); // Clone for display

        for (int i = 0; i < input.length(); i++) {
            char popped = tempStack.pop();
            char original = input.charAt(i);
            System.out.println("  Original[" + i + "] = '" + original +
                    "'  <-->  Popped = '" + popped + "'" +
                    (original == popped ? "  => Match!" : "  => Mismatch!"));
            if (original != popped) {
                palindrome = false;
                break;
            }
        }

        // Step 3: Print result
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