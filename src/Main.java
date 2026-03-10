import java.util.Scanner;

public class UseCase9PalindromeCheckerApp {

    // Recursive method to check palindrome
    // Compares characters at start and end indices, moving inward each call
    public static boolean isPalindrome(String str, int start, int end) {

        // Base Condition 1: Single character or empty middle - always a palindrome
        if (start >= end) {
            return true;
        }

        // Compare current start and end characters
        if (str.charAt(start) != str.charAt(end)) {
            return false; // Mismatch found, not a palindrome
        }

        // Recursive call: move start forward and end backward
        return isPalindrome(str, start + 1, end - 1);
    }

    // Recursive method with step-by-step display
    public static boolean isPalindromeVerbose(String str, int start, int end, int depth) {
        String indent = "  ".repeat(depth);

        // Base Condition
        if (start >= end) {
            System.out.println(indent + "Base case reached (start=" + start
                    + " >= end=" + end + ")  => Palindrome confirmed!");
            return true;
        }

        char left = str.charAt(start);
        char right = str.charAt(end);

        System.out.print(indent + "Call " + depth + ": Comparing index ["
                + start + "] = '" + left + "'  <-->  index ["
                + end + "] = '" + right + "'");

        if (left != right) {
            System.out.println("  => Mismatch! Returning false.");
            return false;
        }

        System.out.println("  => Match! Recurse inward.");

        // Recursive call
        return isPalindromeVerbose(str, start + 1, end - 1, depth + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("  UC9: Palindrome Check - Recursive");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        System.out.println("\n--- Recursive Call Stack Trace ---");
        boolean palindrome = isPalindromeVerbose(input, 0, input.length() - 1, 1);

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