package top.yuwenxin.leetcode.everyday;

public class _241014T1884 {

    public int twoEggDrop(int n) {
        return superEggDrop(2, n); // 记忆化
    }

    // T887扩展题
    private int superEggDrop(int K, int N) {
        // 在k个鸡蛋，可操作n次时能处理的最大楼层数（问题转化）
        // 这里操作数必然<=楼层数，所以可以申请N+1的空间
        int[][] dp = new int[K + 1][N + 1];
        for (int m = 1; m <= N; m++) {
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;

                // 当可处理的最大楼层数 >= 目标楼层数N时，返回m结果即可
                if (dp[k][m] >= N) {
                    return m;
                }
            }
        }
        return dp[K][N];
    }
}
