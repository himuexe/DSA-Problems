public class BitOddEven {
    public static void check(int n){
        int bitMask=1;
        if((n & bitMask) == 1){
            System.out.println("Odd number");
        }
        else{
            System.out.println("Even number");
        }
    }
    public static void main(String[] args){
        check(2);
        check(15);
        check(20);
    }
}
