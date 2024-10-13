package top.yuwenxin.leetcode.everyday;

public class _240607T3038 {
    public int maxOperations(int[] nums) {

        if (nums.length <= 1) {
            return 0;
        }
        int sum = nums[0] + nums[1];
        int res = 1;
        for (int i = 2; i < nums.length - 1; i+=2) {
            
            if ((nums[i - 1] + nums[i]) == sum) {
                res++;
            }
        }
        return res;
    }
}
