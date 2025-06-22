public class Factors {
    public static void printFactors(int n){
        for(int i=1;i<=Math.sqrt(n);i++){
            if(n%i==0){
            if(i==n/i){
                System.out.println(i);
            }
            else 
            System.out.println(i);
            System.err.println(n/i);
        }
        }
    }    
    public static void main(String[] args){
        int n =20;
        printFactors(n);
    }
}
