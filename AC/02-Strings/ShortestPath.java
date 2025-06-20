
public class ShortestPath { 
    public static int calcPath(String str){
        int x=0, y=0;
        for(int i=0;i<str.length();i++){
            char dir = str.charAt(i);
            if(dir == 'W'){
                x--;
            }
            else if(dir == 'N'){
                y++;
            }
            else if(dir == 'S'){
                y--;
            }
            else{
                x++;
            }
        }
        return (int)Math.sqrt((x*x)+(y*y));
    }
    public static void main(String[] args){
    String path ="WNEENESENNN"   ;
    System.out.println(calcPath(path));
    }
}
