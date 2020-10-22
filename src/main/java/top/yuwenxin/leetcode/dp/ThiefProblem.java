package top.yuwenxin.leetcode.dp;

import top.yuwenxin.stuct.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ThiefProblem {

    // 树的动态规划，第一次学习：
    Map<TreeNode, Integer> map = new HashMap<>();
    // 暴力解法
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (map.get(root) != null){
            return map.get(root);
        }

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        int res = Math.max(money, 0 + rob(root.left) + rob(root.right));
        map.put(root, res);
        // 返回偷当前节点或两个孩子节点的最大值
        return res;
    }
}
