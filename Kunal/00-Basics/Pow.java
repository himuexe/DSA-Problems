public class Pow {
    public static double myPow(double x, int n) {
        double ans =1.0;
        long exp = n;
        if(exp<0){
            exp = exp* -1;
        }
        while(exp >0){
            if(exp %2 ==0){
                x = x*x;
                exp /= 2;
            }
            else{
                ans *= x;
                exp = exp-1;
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
