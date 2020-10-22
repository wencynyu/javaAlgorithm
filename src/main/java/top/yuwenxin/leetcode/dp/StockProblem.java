package top.yuwenxin.leetcode.dp;

public class StockProblem {
    /**
     * 不限次数
     */
    public int stock(int[] nums){
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i] - nums[i - 1];
            if (tmp > 0){
                res += tmp;
            }
        }
        return res;
    }

    /**
     * 交易1次
     */
    public int stock2(int[] nums){
        int min = nums[0];
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - min);
            min = Math.min(min, nums[i]);
        }
        return res;
    }

    /**
     * 交易k次
     */
    public int stock3(int[] nums, int k){
        int n = nums.length;
        int[][][] dp = new int[n + 1][k + 1][2];

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -nums[0];
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = dp[0].length; j > 0; j--) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + nums[i - 1]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - nums[i - 1]);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1][0];
    }
}
