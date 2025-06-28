import java.util.Arrays;

public class MergeSort {
    
    public static void sort(int[] arr , int si ,int ei){
        if(si>=ei){
            return;
        }
        int mid = si + (ei-si)/2;
        sort(arr, si, mid);
        sort(arr, mid+1, ei);
        merge(arr,si,mid,ei);
    }
    public static void merge(int[] arr , int si , int mid , int ei){
        int[] temp = new int[ei-si+1];
        int i = si;
        int j = mid+1;
        int k=0;
        while(i<=mid && j<=ei){
            if(arr[i]<arr[j]){
                temp[k]=arr[i];
                i++;
            }
            else{
                temp[k]=arr[j];
                j++;
            }
            k++;
        }
        while(i<=mid){
            temp[k++]=arr[i++];
        }
        while(j<=ei){
            temp[k++]=arr[j++];
        }
        System.arraycopy(temp, 0, arr, si, temp.length);
    }

    public static void main(String[] args){
        int[] arr = {5,4,3,2,1};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
