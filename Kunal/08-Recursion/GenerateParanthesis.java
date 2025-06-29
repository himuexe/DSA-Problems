import java.util.*;
class Solution {

    private void dfs(List<String> list , int max , String str, int open , int close){
        if(max*2==str.length()){
            list.add(str);
            return;
        }
        if(open<max){
            dfs(list,max,str+"(",open+1,close);
        }
        if(close<open){
            dfs(list,max,str+")",open,close+1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        dfs(list,n,"",0,0);
        return list;
    }
}