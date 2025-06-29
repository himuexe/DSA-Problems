import java.util.*;
class Solution {

    private void dfs(int[] nums, List<List<Integer>> list , List<Integer> perm){
        if(perm.size()==nums.length){
            list.add(new ArrayList<>(perm));
        }
        for(int i=0;i<nums.length;i++){
            if(perm.contains(nums[i])) continue;
            perm.add(nums[i]);
            dfs(nums,list,perm);
            perm.remove(perm.size()-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        dfs(nums,list,new ArrayList<>());
        return list;
    }
}