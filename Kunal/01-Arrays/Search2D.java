public class Search2D {

    static int[] searchArray(int[][] arr, int target) {
        for(int row = 0; row < arr.length; row++) {
            for(int col = 0; col < arr[row].length; col++) {
                if(arr[row][col] == target) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int arr[][] = {{1,2,3},{4,5,6},{7,8,9}};
        int target = 7;
        int[] result = searchArray(arr, target);
        System.out.println("Row: " + result[0] + ", Column: " + result[1]);
    }
}