import java.util.*;
public class BinaryToDecimal {
    public static int conversion(int binNum){
        int decNum =0;
        int pow=0;
        while(binNum>0){
            decNum += (binNum%10)*(int)Math.pow(2, pow);
            pow++;
            binNum /= 10;
        }
        return decNum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter binary number to convert: ");
        int number = sc.nextInt();
        System.out.println("The binary number "+number+" is "+conversion(number)+" in decimal");
        sc.close();
    }
}
