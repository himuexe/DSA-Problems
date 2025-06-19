public class InfiniteArray {

    public static void main(String[] args){
        int arr[] = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(ans(arr, 9));
        System.out.println(ans(arr, 5));
        System.out.println(ans(arr, 1));
        System.out.println(ans(arr, 15));
    }

    static int ans(int arr[], int target){
        int start = 0;
        int end = 1;
        
        while(target > getValue(arr, end)){
            int newStart = end + 1;
            end = end + (end - start + 1) * 2;
            start = newStart;
        }
        
        return binarySearch(arr, start, end, target);
    }
    
    static int getValue(int arr[], int index){
        try{
            return arr[index];
        }
        catch(ArrayIndexOutOfBoundsException e){
            return Integer.MAX_VALUE;
        }
    }
    
    static int binarySearch(int arr[], int start, int end, int target){
        while(start <= end){
            int mid = start + (end - start) / 2;
            int midValue = getValue(arr, mid);
            
            if(midValue > target){
                end = mid - 1;
            }
            else if(midValue < target){
                start = mid + 1;
            }
            else{
                return mid;
            }
        }
        return -1;
    }
}