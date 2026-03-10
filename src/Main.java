import java.util.Scanner;

public class UseCase10PalindromeCheckerApp {

    // Step 1: Normalize string - remove spaces, punctuation and convert to lowercase
    public static String normalize(String str) {
        String lower = str.toLowerCase();
        String cleaned = lower.replaceAll("[^a-z0-9]", "");
        return cleaned;
    }

    // Step 2: Check palindrome using two-pointer on normalized string
    public static boolean isPalindrome(String normalized) {
        int start = 0;
        int end = normalized.length() - 1;

        while (start < end) {
            if (normalized.charAt(start) != normalized.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println(" UC10: Palindrome Check - Case & Space Ignored");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // Step 1: Normalize
        System.out.println("\n--- Step 1: Normalizing String ---");
        System.out.println("  Original  : \"" + input + "\"");

        String lowered = input.toLowerCase();
        System.out.println("  Lowercase : \"" + lowered + "\"");

        String normalized = normalize(input);
        System.out.println("  Cleaned   : \"" + normalized + "\"");

        // Step 2: Apply palindrome logic with trace
        System.out.println("\n--- Step 2: Two-Pointer Comparison on Cleaned String ---");
        int start = 0;
        int end = normalized.length() - 1;
        boolean palindrome = true;
        int step = 1;

        while (start < end) {
            char left = normalized.charAt(start);
            char right = normalized.charAt(end);

            if (left == right) {
                System.out.println("  Step " + step + ": index [" + start + "] = '"
                        + left + "'  <-->  index [" + end + "] = '"
                        + right + "'  => Match!");
            } else {
                System.out.println("  Step " + step + ": index [" + start + "] = '"
                        + left + "'  <-->  index [" + end + "] = '"
                        + right + "'  => Mismatch!");
                palindrome = false;
                break;
            }
            start++;
            end--;
            step++;
        }

        if (palindrome && start >= end) {
            System.out.println("  All characters matched!");
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