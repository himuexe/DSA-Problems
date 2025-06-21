public class FastExp {
    public static int fastExpo(int n, int a){
        int ans=1;
        while(n>0){
            if((n&1)!=0){
                ans *=a;
            }
            a *=a;
            n = n>>1;
        }
        return ans;
    }
}
