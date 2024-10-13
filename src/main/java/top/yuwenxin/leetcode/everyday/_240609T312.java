package top.yuwenxin.leetcode.everyday;

public class _240609T312 {

    /**
     * 定义 f[l][r] 为考虑将 (l,r) 范围内（不包含 l 和 r 边界）的气球消耗掉，所能取得的最大价值。
     *
     * 作者：宫水三叶
     * 链接：https://leetcode.cn/problems/burst-balloons/solutions/1930450/by-ac_oier-9r9c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);
        int[][] f = new int[n + 2][n + 2];
        for (int len = 3; len <= n + 2; len++) {
            for (int l = 0; l + len <= n + 2; l++) {
                int r = l + len - 1;
                for (int k = l + 1; k <= r - 1; k++) {
                    f[l][r] = Math.max(f[l][r], f[l][k] + f[k][r] + arr[l] * arr[k] * arr[r]);
                }
            }
        }
        return f[0][n + 1];
    }
}
