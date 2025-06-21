public class BitOperations {
     public static int getIthBit(int n, int i){
        int bitMask = 1<<i;
        if((n&bitMask)==0){
            return 0;
        }else{
            return 1;
        }
     }
     public static int setIthBit(int n, int i){
        int bitMask = 1<<i;
        return n | bitMask;
     }
     public static int clearIthBit(int n , int i){
        int bitMask = ~(1<<i);
        return n & bitMask;
     }
     public static int clearIBits(int n, int i){
        int bitMask = (~0)<<i;
        return n & bitMask;
     }
     public static int clearIBits(int n ,int i, int j ){
        int a =((~0)<<(j+1));
        int b=(1<<i)-1;
        int bitMask = a|b;
        return n & bitMask;
     }
}
