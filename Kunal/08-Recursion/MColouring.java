public class MColouring {

    public boolean isSafe(boolean[][] graph , int[] color , int currColor, int r){
        for(int c=0;c<graph[0].length;c++){
            if(graph[r][c]==true){
                if(color[c]==currColor) return false;
            }
        }
        return true;
    }

    public boolean dfs(boolean[][] graph , int[] color , int m , int r){
        if(r == graph.length) return true;
        for(int i=1;i<=m;i++){
            if(isSafe(graph,color,i,r)){
                color[r]=i;
                if(dfs(graph,color,i,r+1)) return true;
                color[r]=0;
            }
        }
        return false;
    }

    public boolean graphColouring(boolean graph[][] , int m , int n){
        int[] color = new int[n];
        return dfs(graph,color,m,0);
    }
}
