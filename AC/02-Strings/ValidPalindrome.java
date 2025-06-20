 public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                newStr.append(Character.toLowerCase(c));
            }
        }
        int left = 0;
        int right = newStr.length() - 1;
        while (left < right) {
            if (newStr.charAt(left) != newStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}