class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = -1;
        for(int i = 1; i <= 6; i++){
            int currAns = findRotations(tops, bottoms, i);
            if(currAns != -1 && (ans == -1 || ans > currAns)){
                ans = currAns;
            }
        }
        return ans;
    }
    
    public int findRotations(int[] tops, int[] bottoms, int val){
        int ansTop = 0;
        int ansBottom = 0;
        for(int i = 0; i < tops.length; i++){
            if(tops[i] != val && bottoms[i] != val){
                return -1;
            }
            if(tops[i] != val){
                ansTop++;
            }
            if(bottoms[i] != val){
                ansBottom++;
            }
        }
        return Math.min(ansTop, ansBottom);
    }
}