import java.util.Scanner;

public class UseCase3PalindromeCheckerApp {

    // Method to reverse a string using a for loop
    public static String reverseString(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed = reversed + str.charAt(i); // String concatenation
        }
        return reversed;
    }

    // Method to check if a string is a palindrome using string reverse
    public static boolean isPalindrome(String str) {
        String reversed = reverseString(str);
        return str.equals(reversed); // Using equals() to compare content
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("  UC3: Palindrome Check - String Reverse");
        System.out.println("========================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // Reverse the string
        String reversed = reverseString(input);
        System.out.println("Original String : " + input);
        System.out.println("Reversed String : " + reversed);

        // Compare and display result
        if (isPalindrome(input)) {
            System.out.println("Result: \"" + input + "\" IS a palindrome.");
        } else {
            System.out.println("Result: \"" + input + "\" is NOT a palindrome.");
        }

        scanner.close();
    }
}