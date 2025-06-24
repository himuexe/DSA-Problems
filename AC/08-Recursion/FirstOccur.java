
public class FirstOccur {
    public static int firstindex(int[] arr, int i, int target){
        if(i==arr.length){
            return -1;
        }
        if(arr[i]==target){
            return i;
        }
        return firstindex(arr, i+1, target);
    }    
    public static int lastindex(int[] arr, int i ,int target){
        if(i==0){
            return -1;
        }
        if(arr[i]==target){
            return i;
        }
        return lastindex(arr, i-1, target);
    }
    public static void main(String[] args){
        int[] arr= {1,2,3,4,5,3,7,8};
        System.out.println(firstindex(arr, 0, 3));
        System.err.println(lastindex(arr, arr.length-1, 3));
    }
}
