import java.util.Scanner;

public class UseCase4PalindromeCheckerApp {

    // Method to check palindrome using char[] and two-pointer technique
    public static boolean isPalindrome(String str) {
        // Step 1: Convert string to character array
        char[] chars = str.toCharArray();

        // Step 2: Two-pointer approach
        int start = 0;
        int end = chars.length - 1;

        // Step 3: Compare start & end characters
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false; // Mismatch found, not a palindrome
            }
            start++; // Move start pointer forward
            end--;   // Move end pointer backward
        }
        return true; // All characters matched
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println(" UC4: Palindrome Check - Character Array");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // Convert to char[] and display it
        char[] charArray = input.toCharArray();
        System.out.print("Character Array     : ");
        System.out.print("[");
        for (int i = 0; i < charArray.length; i++) {
            System.out.print("'" + charArray[i] + "'");
            if (i < charArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // Show two-pointer comparison steps
        System.out.println("\n--- Two-Pointer Comparison ---");
        int start = 0;
        int end = charArray.length - 1;
        boolean palindrome = true;

        while (start < end) {
            System.out.println("Comparing index [" + start + "] = '" + charArray[start] +
                    "'  <-->  index [" + end + "] = '" + charArray[end] + "'");
            if (charArray[start] != charArray[end]) {
                System.out.println("  => Mismatch found!");
                palindrome = false;
                break;
            } else {
                System.out.println("  => Match!");
            }
            start++;
            end--;
        }

        // Display result
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