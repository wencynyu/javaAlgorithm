package top.yuwenxin.leetcode.greedy;

@SuppressWarnings("all")
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
}
