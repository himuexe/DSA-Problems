
public class MissingNumbers {
    public static int findMissing(int[] arr){
        sort(arr);
        for(int i=0;i<arr.length;i++){
            if(arr[i] != i){
                return i;
            }
        }
        return arr.length;
    }
    public static void sort(int[] arr){
        int i=0;
        while(i<arr.length){
            int correctPos=arr[i];
            if(arr[i] < arr.length && arr[i] != arr[correctPos]){
                swap(arr, i, correctPos);
            }
            else{
                i++;
            }
        }
    }
    public static void swap(int[] arr, int i, int j){
        int temp =arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void main(String[] args){
        int[] arr={3,0,2,1};
        System.out.println(findMissing(arr));
    }    
}
