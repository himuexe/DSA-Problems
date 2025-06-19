public class Pow {
    public static double myPow(double x, int n) {
        double ans =1.0;
        long num = n;
        if(num<0){
            num = num* -1;
        }
        while(num >0){
            if(num %2 ==0){
                x = x*x;
                num /= 2;
            }
            else{
                ans *= x;
                num = num-1;
            }
        }
        if(n<0){
            return (double)(1.0)/(double)ans;
        }
        return ans;
    }
    public static void main(String[] args){
        System.out.println(myPow(4, 2));
    }
}
