import java.util.*;

public class SumtoN{
    public static void calculateSum(int n){
        int sum =0;
        for(int i=1;i<=n;i++){
            sum+=i;
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter a number to calculate sum:");
            if(!sc.hasNextInt()){
                throw new Exception("Invalid Input");
            }
            int n = sc.nextInt();
            calculateSum(n);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
