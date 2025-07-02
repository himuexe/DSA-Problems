import java.util.ArrayList;

public class RatInMaze{

    public static void dfs(int[][] matrix , boolean[][] visited , ArrayList<String> res , int n , int r , int c , String currPath){
        if(r<0 || r>=n || c<0 || c>=n || matrix[r][c] == 0 || visited[r][c]) return;
        if(r == n-1 && c == n-1){
            res.add(currPath);
            return;
        }
        visited[r][c]=true;
        dfs(matrix,visited,res,n,r+1,c,currPath+"D");
        dfs(matrix, visited, res, n, r-1, c, currPath+"U");
        dfs(matrix, visited, res, n, r, c+1, currPath+"R");
        dfs(matrix, visited, res, n, r, c-1, currPath+"L");
        visited[r][c]=false;
    }

    public static ArrayList<String> findPath(int[][] m , int n){
        boolean[][] visited  = new boolean[n][n];
        ArrayList<String> res = new ArrayList<>();
        dfs(m,visited,res,n,0,0,"");
        return res;
    }
}