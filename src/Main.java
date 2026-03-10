iimport java.util.Scanner;
import java.util.Stack;

public class UseCase11PalindromeCheckerApp {

    // ============================================================
    // PalindromeChecker Class - Encapsulates all palindrome logic
    // Follows Single Responsibility Principle (SRP)
    // ============================================================
    static class PalindromeChecker {

        private String original;
        private String normalized;

        // Constructor - accepts and stores the input string
        public PalindromeChecker(String input) {
            this.original = input;
            this.normalized = normalize(input);
        }

        // Private helper: Normalize string (lowercase + remove non-alphanumeric)
        private String normalize(String str) {
            return str.toLowerCase().replaceAll("[^a-z0-9]", "");
        }

        // Getter for original input
        public String getOriginal() {
            return original;
        }

        // Getter for normalized string
        public String getNormalized() {
            return normalized;
        }

        // Method 1: Check using Two-Pointer (char array)
        public boolean checkWithTwoPointer() {
            char[] chars = normalized.toCharArray();
            int start = 0;
            int end = chars.length - 1;

            while (start < end) {
                if (chars[start] != chars[end]) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }

        // Method 2: Check using Stack (LIFO reversal)
        public boolean checkWithStack() {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < normalized.length(); i++) {
                stack.push(normalized.charAt(i));
            }

            for (int i = 0; i < normalized.length(); i++) {
                if (normalized.charAt(i) != stack.pop()) {
                    return false;
                }
            }
            return true;
        }

        // Method 3: Check using Recursion
        public boolean checkWithRecursion() {
            return checkRecursive(normalized, 0, normalized.length() - 1);
        }

        private boolean checkRecursive(String str, int start, int end) {
            if (start >= end) {
                return true;
            }
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            return checkRecursive(str, start + 1, end - 1);
        }

        // Main exposed method - runs all checks and returns overall result
        public boolean checkPalindrome() {
            return checkWithTwoPointer();
        }
    }

    // ============================================================
    // Main Application Entry Point
    // ============================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println(" UC11: Palindrome Check - OOP Service Class");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // Instantiate PalindromeChecker object
        PalindromeChecker checker = new PalindromeChecker(input);

        // Display normalization
        System.out.println("\n--- Encapsulated Preprocessing ---");
        System.out.println("  Original   : \"" + checker.getOriginal() + "\"");
        System.out.println("  Normalized : \"" + checker.getNormalized() + "\"");

        // Run all three internal methods
        System.out.println("\n--- Running All Palindrome Strategies ---");

        boolean twoPointerResult = checker.checkWithTwoPointer();
        System.out.println("  Two-Pointer Check : " + (twoPointerResult ? "Palindrome" : "Not a Palindrome"));

        boolean stackResult = checker.checkWithStack();
        System.out.println("  Stack Check       : " + (stackResult ? "Palindrome" : "Not a Palindrome"));

        boolean recursionResult = checker.checkWithRecursion();
        System.out.println("  Recursion Check   : " + (recursionResult ? "Palindrome" : "Not a Palindrome"));

        // Final result via main exposed method
        boolean finalResult = checker.checkPalindrome();

        System.out.println("\n----------------------------------------------");
        if (finalResult) {
            System.out.println("Result: \"" + input + "\" IS a palindrome.");
        } else {
            System.out.println("Result: \"" + input + "\" is NOT a palindrome.");
        }
        System.out.println("==============================================");

        scanner.close();
    }
}