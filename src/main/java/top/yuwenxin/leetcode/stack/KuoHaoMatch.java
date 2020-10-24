package top.yuwenxin.leetcode.stack;

import java.util.Stack;

public class KuoHaoMatch {
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                stack.push('(');
            }else if (c == ')'){
                if (stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
