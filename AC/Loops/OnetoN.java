import java.util.Scanner;

public class OnetoN {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        try {
            int n = input.nextInt(); 
            if (n <= 0) {
                System.out.println("Please enter a positive integer.");
            } else {
                System.out.println("Numbers from 1 to " + n + ":");
                for (int i = 1; i <= n; i++) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter an integer.");
        } finally {
            input.close(); 
        }
    }
}
