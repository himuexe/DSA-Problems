public class Tilling{
    public static int tillingProblem(int n){
        if(n==1 || n==0){
            return 1;
        }
        int fnm1 = tillingProblem(n-1);
        int fm2 = tillingProblem(n-2);
        return fnm1+fm2;
    }

    public static void main(String[] args){
        System.out.println(tillingProblem(3));
    }
}