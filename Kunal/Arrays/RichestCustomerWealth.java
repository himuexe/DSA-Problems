public class RichestCustomerWealth {
    static int calcMax(int[][] arr){
        int max = Integer.MIN_VALUE;
        for(int persons=0;persons<arr.length;persons++){
            int sum =0;
            for(int accounts=0;accounts<arr[persons].length;accounts++){
                sum += arr[persons][accounts];
            }
            if(sum >max){
                max = sum;
            }
        }
        return max;
    }
    public static void main(String[] args){
        int[][] arr = {
            {1,2,3},
            {4,5,6},
            {10,10,10}
        };
        System.out.println("Maximum wealth is: "+calcMax(arr));

    }
}
