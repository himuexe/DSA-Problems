import java.util.*;

public class Armstrong {
    public static boolean isArmstrong(int number) {
        if (number < 0) return false; 

        int originalNumber = number;
        int sum = 0;
        int digits = countDigits(number);

        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }

        return sum == originalNumber;
    }

    public static int countDigits(int number) {
        if (number == 0) return 1;
        return (int)Math.log10(number)+1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to check if it's an Armstrong number: ");
        int number = sc.nextInt();

        if (isArmstrong(number)) {
            System.out.println(number + " is an Armstrong number.");
        } else {
            System.out.println(number + " is NOT an Armstrong number.");
        }
        sc.close();
    }
}