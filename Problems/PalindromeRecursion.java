import java.util.Scanner;

public class PalindromeRecursion {

    public static boolean isPalindromeRecursive(int i, String s) {
        if (i >= s.length() / 2) {
            return true;
        }
        if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
            return false;
        }
        return isPalindromeRecursive(i + 1, s);
    }

    public static String normalizeInput(String input) {
        // Remove spaces and convert to lowercase
        return input.replaceAll("\\s+", "").toLowerCase();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        
        String normalizedInput = normalizeInput(input);
        
        if (isPalindromeRecursive(0, normalizedInput)) {
            System.out.println("\"" + input + "\" is a palindrome");
        } else {
            System.out.println("\"" + input + "\" is not a palindrome");
        }
        
        sc.close(); // Close the scanner
    }
}