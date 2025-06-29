class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int n = haystack.length();
        int m = needle.length();
        if (n < m) {
            return -1;
        }
        for (int i = 0; i <= n - m; i++) {
            String str = haystack.substring(i, i + m);
            if (str.equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}