import java.util.*;
class Solution {

    private boolean dfs(List<Long> list , String str , int start){
        if(start == str.length()){
            return list.size() >=2;
        }
        long num =0;
        for(int i=start;i<str.length();i++){
            num = num*10 + str.charAt(i)-'0';
            if(list.size()==0 || list.get(list.size()-1)-num == 1){
                list.add(num);
                if(dfs(list,str,i+1)) return true;
                list.remove(list.size()-1);
            }
        }
        return false;
    }

    public boolean splitString(String s) {
        return dfs(new ArrayList<>(),s,0);
    }
}