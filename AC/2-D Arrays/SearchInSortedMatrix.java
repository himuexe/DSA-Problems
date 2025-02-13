public class SearchInSortedMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false; // Edge case: empty matrix
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Start from the top-right corner of the matrix
        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true; // Target found
            } else if (matrix[row][col] < target) {
                row++; // Move down to the next row (since the current element is smaller than the target)
            } else {
                col--; // Move left to the previous column (since the current element is larger than the target)
            }
        }

        return false; // Target not found
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
        };

        int target = 29;
        boolean result = searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + result);
    }
}