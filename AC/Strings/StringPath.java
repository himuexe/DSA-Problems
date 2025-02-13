public class StringPath {
    public static float shortestPath(String s){
        int n = s.length();
        int x=0,y=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='N') y++;
            else if(s.charAt(i)=='S') y--;
            else if(s.charAt(i)=='E') x++;
            else if(s.charAt(i)=='W') x--;
        }
        int x2 = x*x;
        int y2 = y*y;
        return (float)Math.sqrt(x2+y2);
    }
    public static void main(String[] args){
        String s ="WNEENESENNN";
        System.out.println(shortestPath(s));
    }
}