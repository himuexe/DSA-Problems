import java.util.*;
public class OnetoN {

    public static void printNumbers(int number){
        for(int i=1;i<=number;i++){
            System.err.println(i);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to print 1 to the number");
        int number = sc.nextInt();
        printNumbers(number);
        sc.close();
    }
    
}
