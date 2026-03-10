import java.util.Scanner;

public class UseCase8PalindromeCheckerApp {

    // Node class for Singly Linked List
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    // Step 1: Convert string to linked list, return head node
    public static Node buildLinkedList(String str) {
        Node head = null;
        Node tail = null;

        for (int i = 0; i < str.length(); i++) {
            Node newNode = new Node(str.charAt(i));
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }

    // Step 2: Find middle using Fast and Slow pointer technique
    public static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Step 3: Reverse second half of linked list in-place
    public static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    // Print linked list nodes
    public static void printList(Node head) {
        Node curr = head;
        System.out.print("[");
        while (curr != null) {
            System.out.print("'" + curr.data + "'");
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println("]");
    }

    // Main palindrome check method
    public static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        // Find middle of the linked list
        Node middle = findMiddle(head);

        // Reverse second half starting from middle
        Node secondHalf = reverseList(middle);

        // Compare first and second halves
        Node first = head;
        Node second = secondHalf;

        boolean result = true;
        while (second != null) {
            if (first.data != second.data) {
                result = false;
                break;
            }
            first = first.next;
            second = second.next;
        }

        // Restore the list (reverse second half back)
        reverseList(secondHalf);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("  UC8: Palindrome Check - Linked List Based");
        System.out.println("==============================================");
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();

        // Step 1: Build linked list from string
        Node head = buildLinkedList(input);
        System.out.println("\n--- Step 1: Linked List Built ---");
        System.out.print("  List: ");
        printList(head);

        // Step 2: Find middle node
        Node middle = findMiddle(head);
        System.out.println("\n--- Step 2: Finding Middle (Fast & Slow Pointer) ---");
        System.out.println("  Middle Node: '" + middle.data + "'");

        // Step 3: Reverse second half
        Node secondHalf = reverseList(middle);
        System.out.println("\n--- Step 3: Reversed Second Half ---");
        System.out.print("  Reversed Half: ");
        printList(secondHalf);

        // Compare both halves
        System.out.println("\n--- Step 4: Comparing First & Second Halves ---");
        Node first = head;
        Node second = secondHalf;
        boolean palindrome = true;
        int step = 1;

        while (second != null) {
            if (first.data == second.data) {
                System.out.println("  Step " + step + ": '"
                        + first.data + "'  <-->  '"
                        + second.data + "'  => Match!");
            } else {
                System.out.println("  Step " + step + ": '"
                        + first.data + "'  <-->  '"
                        + second.data + "'  => Mismatch!");
                palindrome = false;
                break;
            }
            first = first.next;
            second = second.next;
            step++;
        }

        // Restore the original list
        reverseList(secondHalf);

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