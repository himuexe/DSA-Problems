public class FriendsPairing {
    
    public static int pairing(int n){
        if(n==1 || n==2){
            return n;
        }
        int fnm1 = pairing(n-1);
        int fm2 = pairing(n-2);
        int pairways = (n-1)*fm2;
        return fnm1 +pairways;
    }

    public static void main (String[] args){
        System.out.println(pairing(3));
    }
}
