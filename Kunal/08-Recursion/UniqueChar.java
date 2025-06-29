import java.util.*;
class Solution {

    private boolean isUnique(String str){
        int[] arr = new int[26];
        for(int i=0;i<str.length();i++){
            arr[str.charAt(i)-'a']++;
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i]>1){
                return false;
            }
        }
        return true;
    }

    private int dfs(List<String> arr , String str , int curr){
        if(curr == arr.size()){
            return str.length();
        }
        int left = 0 , right =0;
        String temp = str+arr.get(curr);
        if(isUnique(temp)){
            left = dfs(arr,temp,curr+1);
        }
        right = dfs(arr,str,curr+1);
        return Math.max(left,right);
    }
    public int maxLength(List<String> arr) {
        return dfs(arr,"",0);
    }
}