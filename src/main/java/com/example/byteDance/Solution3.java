package com.example.byteDance;

import java.util.Stack;

public class Solution3 {
    public static void main(String[] args) {
        String s = "({}[]){}";
        System.out.println(isMatch(s));
    }

    public static boolean isMatch(String str){

        Stack<Character> stack = new Stack<>();
        for (int i =0;i < str.length();i++){
            if (str.charAt(i) =='{' || str.charAt(i) == '['|| str.charAt(i) == '('){
                stack.push(str.charAt(i));
            }
            if(str.charAt(i) == '}'){
                Character top = stack.peek();
                if(top == null || top != '{'){
                    return false;
                }
                stack.pop();
            }
            if(str.charAt(i) == ']'){
                Character top = stack.peek();
                if(top == null || top != '['){
                    return false;
                }
                stack.pop();
            }
            if(str.charAt(i) == ')'){
                Character top = stack.peek();
                if(top == null || top != '('){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

}
