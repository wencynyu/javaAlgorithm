package top.yuwenxin.leetcode.everyday;

public class _240608T3040 {

    // TODO：剪枝 or dp优化
    public int maxOperations(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int res1 = backtrace(1, nums, 2, nums.length - 1, nums[0] + nums[1], nums[0] + nums[1]);
        int res2 = backtrace(1, nums, 1, nums.length - 2, nums[0] + nums[nums.length - 1], nums[0] + nums[nums.length - 1]);
        int res3 = backtrace(1, nums, 0, nums.length - 3, nums[nums.length - 1] + nums[nums.length - 2], nums[nums.length - 1] + nums[nums.length - 2]);
        System.out.printf("res1 = %d, res2 = %d, res3 = %d\n", res1, res2, res3);
        return Math.max(res1, Math.max(res2, res3));
    }

    private int backtrace(int res, int[] nums, int head, int tail, int choiceSum, int curSum) {
        if (curSum != choiceSum) {
            return res - 1;
        }
        if ((tail - head) < 1) {
            return res;
        }


        res++;
        int res1 = backtrace(res, nums, head + 2, tail, choiceSum, nums[head] + nums[head + 1]);
        int res2 = backtrace(res, nums, head + 1, tail - 1, choiceSum, nums[head] + nums[tail]);
        int res3 = backtrace(res, nums, head, tail - 2, choiceSum, nums[tail] + nums[tail - 1]);

        return Math.max(res1, Math.max(res2, res3));
    }


}
