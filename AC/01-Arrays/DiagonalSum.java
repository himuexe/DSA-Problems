import java.util.Scanner;

public class DiagonalSum {
    public static void scan(int[][] arr) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int diagonalSum(int[][] arr){
        //Bruteforce
        // int sum =0;
        // for(int i=0;i<arr.length;i++){
        //     for(int j=0;j<arr[0].length;j++){
        //         if(i==j){
        //             sum +=arr[i][j];
        //         }
        //         else if(i+j == arr.length-1){
        //             sum +=arr[i][j];
        //         }
        //     }
        // }
        int sum =0;
        for(int i=0;i<arr.length;i++){
            sum += arr[i][i];
            if(i != arr.length-i-1){
                sum += arr[i][arr.length-i-1];
            }
        }
        
        return sum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of matrix");
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        System.out.println("Enter the elements of array");
        scan(arr);
        System.out.println("Entered Matrix:");
        print(arr);
        System.out.println("Diagonal Sum :"+diagonalSum(arr));
        sc.close();
    }
}
