import java.util.Scanner;

public class OneToNRecursion {

    public static void printOneToN(int n) {
        if (n <= 0) {
            return; // Base case: if n is 0 or negative, do nothing
        }
        printOneToN(n - 1); // Recursive call
        System.out.print(n + " "); // Print the current number after the recursive call
    }
    public static void printNToOne(int n){
        if(n<=0){
            return;
        }
        System.out.print(n+" ");
        printNToOne(n-1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter a positive integer: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int n = sc.nextInt();
            if (n < 1) {
                System.out.println("Please enter a positive integer greater than 0.");
                return;
            }
            System.out.print("Numbers from 1 to " + n + ": ");
            printOneToN(n);
            System.out.println(); // New line after printing the numbers

            System.out.print("Numbers from " + n + " to 1: ");
            printNToOne(n);
            System.out.println(); // New line after printing the numbers
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}