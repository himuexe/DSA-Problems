import java.util.*;

public class SpiralMatrix {
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

    public static List<Integer> spiralOrder(int[][] arr) {
        List<Integer> list = new ArrayList<>();
        if (arr == null || arr.length == 0)
            return list;

        int firstRow = 0;
        int firstCol = 0;
        int lastRow = arr.length - 1;
        int lastCol = arr[0].length - 1;

        while (firstRow <= lastRow && firstCol <= lastCol) {
            for (int i = firstCol; i <= lastCol; i++) {
                list.add(arr[firstRow][i]);
            }
            firstRow++;

            for (int i = firstRow; i <= lastRow; i++) {
                list.add(arr[i][lastCol]);
            }
            lastCol--;

            if (firstRow <= lastRow) {
                for (int i = lastCol; i >= firstCol; i--) {
                    list.add(arr[lastRow][i]);
                }
                lastRow--;
            }

            if (firstCol <= lastCol) {
                for (int i = lastRow; i >= firstRow; i--) {
                    list.add(arr[i][firstCol]);
                }
                firstCol++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of matrix");
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        System.out.println("Enter the elements of array");
        scan(arr);
        System.out.println("Entered Matrix:");
        print(arr);
        System.out.println("Spiral Matrix:");
        System.out.println(spiralOrder(arr));
        sc.close();
    }
}