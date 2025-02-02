public class DiagonalSum {

    public static int diagonalSum(int[][] matrix) {
        int n = matrix.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            // Primary diagonal (top-left to bottom-right)
            sum += matrix[i][i];

            // Secondary diagonal (top-right to bottom-left)
            // Skip the middle element if the matrix has an odd size
            if (i != n - 1 - i) {
                sum += matrix[i][n - 1 - i];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int sum = diagonalSum(matrix);
        System.out.println("Sum of diagonals: " + sum);
    }
}