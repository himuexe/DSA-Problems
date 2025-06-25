import java.util.HashMap;

public class SubArray0Sum {
    public static int maxlen(int arr[] ,int n){
        int sum=0;
        int len=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            sum += arr[i];
            if(sum==0){
                len = i+1;
            }
            else if(map.containsKey(sum)){
                len = Math.max(len,i-map.get(sum));
            }
            else{
                map.put(sum,i);
            }
        }
        return len;
    }
}
