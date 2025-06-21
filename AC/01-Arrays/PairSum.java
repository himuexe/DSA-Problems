import java.util.ArrayList;
import java.util.Arrays;

public class PairSum {
    public static int[] twoSum(ArrayList<Integer> list, int target){
        int breakingPoint = pivot(list);
        int left =breakingPoint+1;
        int right =breakingPoint;
        while(left != right){
            if(list.get(left)+list.get(right)==target){
                return new int[]{list.get(right),list.get(left)};
            }
            else if(list.get(left)+list.get(right)<target){
                left = (left+1)%list.size();
            }
            else{
                right = (list.size()+right-1)%list.size();
            }
        }
        return new int[]{-1,-1};
    }
    public static int pivot(ArrayList<Integer> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i)>list.get(i+1)){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);list.add(15);list.add(6);list.add(8);list.add(9);list.add(10);
        int target =16;
        System.out.println(Arrays.toString(twoSum(list,target)));
    }
}
