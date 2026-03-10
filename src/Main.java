import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class UseCase13PalindromeCheckerApp {

    // ============================================================
    // Normalize: lowercase + remove non-alphanumeric characters
    // ============================================================
    public static String normalize(String str) {
        return str.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    // ============================================================
    // Algorithm 1: String Reversal (UC3)
    // ============================================================
    public static boolean checkStringReversal(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed = reversed + str.charAt(i);
        }
        return str.equals(reversed);
    }

    // ============================================================
    // Algorithm 2: Two-Pointer / char[] (UC4)
    // ============================================================
    public static boolean checkTwoPointer(String str) {
        char[] chars = str.toCharArray();
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

    // ============================================================
    // Algorithm 3: Stack (UC5)
    // ============================================================
    public static boolean checkStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    // ============================================================
    // Algorithm 4: Deque (UC7)
    // ============================================================
    public static boolean checkDeque(String str) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            deque.addLast(str.charAt(i));
        }
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    // ============================================================
    // Algorithm 5: Recursion (UC9)
    // ============================================================
    public static boolean checkRecursion(String str) {
        return checkRecursive(str, 0, str.length() - 1);
    }

    private static boolean checkRecursive(String str, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return checkRecursive(str, start + 1, end - 1);
    }

    // ============================================================
    // Timer helper: runs a check N times and returns avg nanoseconds
    // ============================================================
    interface CheckFunction {
        boolean run(String input);
    }

    public static long measureTime(CheckFunction fn, String input, int iterations) {
        long total = 0;
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            fn.run(input);
            long end = System.nanoTime();
            total += (end - start);
        }
        return total / iterations;
    }

    // ============================================================
    // Print a formatted result row
    // ============================================================
    public static void printRow(String algorithm, boolean result, long timeNs) {
        String outcome = result ? "Palindrome    " : "Not Palindrome";
        System.out.printf("  %-35s | %-14s | %,d ns%n", algorithm, outcome, timeNs);
    }

    // ============================================================
    // Main Application Entry Point
    // ============================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println(" UC13: Palindrome Check - Performance Compare");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        String normalized = normalize(input);

        System.out.println("\n  Original   : \"" + input + "\"");
        System.out.println("  Normalized : \"" + normalized + "\"");

        int iterations = 10000;
        System.out.println("  Iterations : " + iterations + " runs per algorithm");

        // --------------------------------------------------------
        // Warm-up JVM before timing (avoids JIT skewing results)
        // --------------------------------------------------------
        for (int i = 0; i < 500; i++) {
            checkTwoPointer(normalized);
            checkStack(normalized);
            checkDeque(normalized);
            checkRecursion(normalized);
            checkStringReversal(normalized);
        }

        // --------------------------------------------------------
        // Run and time each algorithm
        // --------------------------------------------------------
        System.out.println("\n--- Performance Results ---");
        System.out.printf("  %-35s | %-14s | %s%n", "Algorithm", "Result", "Avg Time");
        System.out.println("  " + "-".repeat(70));

        long t1 = measureTime(s -> checkStringReversal(s), normalized, iterations);
        printRow("UC3  - String Reversal", checkStringReversal(normalized), t1);

        long t2 = measureTime(s -> checkTwoPointer(s), normalized, iterations);
        printRow("UC4  - Two-Pointer (char[])", checkTwoPointer(normalized), t2);

        long t3 = measureTime(s -> checkStack(s), normalized, iterations);
        printRow("UC5  - Stack (LIFO)", checkStack(normalized), t3);

        long t4 = measureTime(s -> checkDeque(s), normalized, iterations);
        printRow("UC7  - Deque (Double Ended Queue)", checkDeque(normalized), t4);

        long t5 = measureTime(s -> checkRecursion(s), normalized, iterations);
        printRow("UC9  - Recursion (Call Stack)", checkRecursion(normalized), t5);

        // --------------------------------------------------------
        // Find fastest algorithm
        // --------------------------------------------------------
        String[] names = {
                "UC3  - String Reversal",
                "UC4  - Two-Pointer (char[])",
                "UC5  - Stack (LIFO)",
                "UC7  - Deque (Double Ended Queue)",
                "UC9  - Recursion (Call Stack)"
        };
        long[] times = {t1, t2, t3, t4, t5};

        long minTime = times[0];
        String fastest = names[0];
        for (int i = 1; i < times.length; i++) {
            if (times[i] < minTime) {
                minTime = times[i];
                fastest = names[i];
            }
        }

        System.out.println("  " + "-".repeat(70));
        System.out.println("\n  Fastest Algorithm : " + fastest);
        System.out.println("  Fastest Time      : " + String.format("%,d", minTime) + " ns");

        // --------------------------------------------------------
        // Final result
        // --------------------------------------------------------
        System.out.println("\n----------------------------------------------");
        if (checkTwoPointer(normalized)) {
            System.out.println("Result: \"" + input + "\" IS a palindrome.");
        } else {
            System.out.println("Result: \"" + input + "\" is NOT a palindrome.");
        }
        System.out.println("==============================================");

        scanner.close();
    }
}