package top.yuwenxin.leetcode.everyday;

public class _240614T2786 {
    /**
     * 状态转移方程：最后一个选中奇/偶数的最大值
     */
    public long maxScore(int[] nums, int x) {
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0] % 2] = nums[0]; // 第一个必选
        for (int i = 1; i < nums.length; i++) {
            int parity = nums[i] % 2;
            dp[parity] = Math.max(dp[parity] + nums[i], dp[1 - parity] - x + nums[i]);
        }
        return Math.max(dp[0], dp[1]);
    }
}
