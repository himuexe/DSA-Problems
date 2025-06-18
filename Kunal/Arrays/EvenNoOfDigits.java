import java.util.*;
public class EvenNoOfDigits {
    public static int calcDigits(int[] arr){
        int count =0;
        for(int nums: arr){
            if(even(nums)){
                count++;
            }
        }
        return count;
    }
    static boolean even(int nums){
        int digits = digits(nums);
        return digits %2 ==0;
    }
    static int digits(int nums){
        return (int)Math.log10(nums)+1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements of array: ");
        for(int i=0;i< arr.length;i++){
            arr[i]=sc.nextInt();
        }
        int result = calcDigits(arr);
        System.out.println("Even number of digits in this array is : "+result);
        sc.close();
    }
}
