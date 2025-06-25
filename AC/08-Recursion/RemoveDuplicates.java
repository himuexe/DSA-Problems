public class RemoveDuplicates {
    
    public static void removeDuplicates(String str , int idx ,StringBuilder newStr ,boolean[] map){
        if(idx==str.length()){
            return;
        }
        char curr = str.charAt(idx);
        if(map[curr-'a']){
            removeDuplicates(str, idx+1, newStr, map);
        }
        else{
            map[curr-'a'] = true;
            removeDuplicates(str, idx+1, newStr.append(curr), map);
        }
    }
    public static void main(String[] args){
        String str  = "meeoow";
        removeDuplicates(str, 0, new StringBuilder(""), new boolean[26]);
    }
}

