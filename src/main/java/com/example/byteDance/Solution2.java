package com.example.byteDance;

public class Solution2 {
    public static void main(String[] args) {
        String s = "a plan ; b plan,nalpbna;lpa";
        System.out.println(isHuiwen(s));
    }

    /**
     * 忽略大小写和非数字字母，判断是否是回文字符串
      * @param s
     * @return
     */
    public static boolean isHuiwen(String s) {
        int left = 0,right = s.length() -1;
        while(left < right){
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
