import java.util.Scanner;
public class SumN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        System.out.print("Enter the number: ");
        try{
            int n = sc.nextInt();
            for(int i = 1; i <= n; i++){
                sum += i;
            }
            System.out.println("Sum of first " + n + " natural numbers is: " + sum);
        }
        catch(Exception e){
            System.out.println("Invalid Input");
            System.exit(0);
        }
        finally{
            sc.close();
        }
    }
}
