package top.yuwenxin.leetcode.dp;

public class BagProblem {

    /**
     * 0-1背包
     */
    public int zeroOneBag(int[] weights, int[] values, int column){
        int num = weights.length;
        int[][] dp = new int[num + 1][column + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - weights[i] > 0){
                    dp[i][j] = Math.max(dp[i - 1][j], (dp[i - 1][j - weights[i]] + values[i]));
                }else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[num][column];
    }

    public int manyBag(int[] weights, int[] values, int[] nums, int column){
        int num = weights.length;
        int[][] dp = new int[num + 1][column + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - weights[i] > 0){
                    int maxChoseTime = Math.min(nums[i - 1], j / weights[i - 1]);
                    for (int k = 0; k < maxChoseTime + 1; k++) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1]);
                    }
                }else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[num][column];
    }


    /**
     * 零钱兑换问题
     * @param weights 钱币币值
     * @param column 目标值
     * @return 最多多少种兑换方式
     */
    public int completeBag(int[] weights, int column){
        int num = weights.length;
        int[][] dp = new int[num + 1][column + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j - weights[i - 1] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - weights[i - 1]];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[num][column];
    }

}
