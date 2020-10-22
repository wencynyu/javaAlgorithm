package top.yuwenxin.leetcode.backtrace.tree;

import top.yuwenxin.stuct.tree.TreeNode;

public class SubTree {
    public boolean isSubTree(TreeNode n1, TreeNode n2){
        if (n1 != null && n2 != null){
            if (n1.val == n2.val && isSubTree2(n1, n2)){
                return true;
            }else {
                return isSubTree(n1.left, n2) || isSubTree(n1.right, n2);
            }
        }
        return false;
    }

    private boolean isSubTree2(TreeNode n1, TreeNode n2) {
        if (n2 == null){
            return true;
        }else {
            if (n1 == null){
                return false;
            }
            if (n1.val != n2.val){
                return false;
            }

            return isSubTree2(n1.left, n2.left) && isSubTree2(n1.right, n2.right);
        }
    }
}
