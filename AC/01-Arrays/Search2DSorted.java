public class Search2DSorted {
    public static boolean search(int[][] arr , int key){
        int row=0;
        int col=arr[0].length-1;
        while(row < arr.length && col >=0){
            if(arr[row][col] == key){
                System.out.println("Key found at index: "+row+" "+col);
                return true;
            }
            else if(key <arr[row][col]){
                col--;
            }
            else{
                row++;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[][] arr ={
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        int key =5;
        search(arr,key);
    }
}
