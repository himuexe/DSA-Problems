import java.util.Arrays;

public class MergeSort {
    

    public static int[] sort(int[] arr){
        if(arr.length==1){
            return arr;
        }
        int mid = arr.length/2;
        int[] left = sort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = sort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left,right);
    }

    public static int[] merge(int[] first , int[] second){
        int[] newArr = new int[first.length+second.length];
        int i =0, j=0 , k=0;
        while(i< first.length && j<second.length){
            if(first[i]<second[j]){
                newArr[k]=first[i];
                i++;
            }
            else{
                newArr[k]=second[j];
                j++;
            }
            k++;
        } 
        while (i < first.length){
            newArr[k]=first[i];
            i++;
            k++;
        }
        while(j< second.length){
            newArr[k]=second[j];
            j++;
            k++;
        }
        return newArr;
    }

    public static void sortInPlace(int[] arr , int s , int e){
        if(e-s==1){
            return;
        }
        int mid = s + (e-s)/2;
        sortInPlace(arr, s, mid);
        sortInPlace(arr, mid, e);
        mergeInPlace(arr, s, mid ,e);
        
    }

    public static void mergeInPlace(int[] arr , int s , int mid  , int e){
        int[] newArr = new int[e-s];
        int i = s , j=mid , k=0;
        while(i< mid && j<e){
            if(arr[i]<arr[j]){
                newArr[k]=arr[i];
                i++;
            }
            else{
                newArr[k]=arr[j];
                j++;
            }
            k++;
        } 
        while (i < mid){
            newArr[k]=arr[i];
            i++;
            k++;
        }
        while(j< e){
            newArr[k]=arr[j];
            j++;
            k++;
        }
        System.arraycopy(newArr, 0, arr, s, newArr.length);
    }


    public static void main(String[] args){
        int arr[] = {5,4,3,2,1};
        sortInPlace(arr, 0, arr.length);
        // int[] sortedArr = sort(arr);
        System.err.println(Arrays.toString(arr));
    }

}
