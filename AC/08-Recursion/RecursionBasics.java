public class RecursionBasics{

    public static void printInc(int n){
        if(n==1){
            System.out.print(n);
            return;
        }
        printInc(n-1);
        System.out.print(n);

    }

    public static void  printDec(int n){
        if(n==1){
            System.out.print(n);
            return;
        }
        System.out.print(n);
        printDec(n-1);
    }

    public static int factorial(int n){
        if(n ==0){
            return 1;
        }
        return n*factorial(n-1);
    }
    public static int sum(int n){
        if(n==1){
            return 1;
        }
        return n+sum(n-1);
    }
    public static int fib(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }
    public static int pow(int x, int n){
        if(n==1){
            return x;
        }
        return x*pow(x, n-1);
    }
    public static int optpow(int x , int n){
        if(n==0){
            return 1;
        }
        int halfPower = optpow(x, n/2);
        int halfPowerSq = halfPower*halfPower;
        if(n%2!=0){
            halfPowerSq *= x;
        }
        return halfPowerSq;
    }

    public static void main (String[] args){
        printInc(5);
        System.out.println();
        printDec(5);
        System.out.println();
        System.out.println(factorial(5));
        System.out.println(sum(5));
        System.out.println(fib(6));
        System.err.println(pow(2, 4));
        System.out.println(optpow(02, 5));
    }
}