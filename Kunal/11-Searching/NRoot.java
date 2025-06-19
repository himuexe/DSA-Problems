public class NRoot{
    public static long search(int number, int power){
        if(power == 1){
            return number;
        }
        if(number == 0 || number == 1){
            return number;
        }
        
        long left = 0;
        long right = number;
        
        while(left <= right){
            long mid = left + (right - left) / 2;
            long midPower = power(mid, power);
            
            if(midPower == number){
                return mid;
            }
            else if(midPower < number){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    private static long power(long base, int exp){
        long result = 1;
        for(int i = 0; i < exp; i++){
            if(result > Long.MAX_VALUE / base){
                return Long.MAX_VALUE;
            }
            result *= base;
        }
        return result;
    }
    
    public static void main(String[] args){
        System.out.println(search(27, 3));
        System.out.println(search(16, 4));
        System.out.println(search(32, 5));
        System.out.println(search(100, 2));
        System.out.println(search(8, 3));
        System.out.println(search(1, 5));
        System.out.println(search(0, 3));
    }
}