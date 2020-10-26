package top.yuwenxin.leetcode.array;

public class LongestMountain {
    /**
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     *
     * B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * @param nums
     * @return
     */

    // 暴力解，一共四重循环o（n^4），时间复杂度过高
    public int longestMountain(int[] nums){
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 2; j < nums.length; j++) {
                int dist = j - i + 1;
                if (dist > res && isMountain(nums, i, j)){
                    // System.out.println(res);
                    res = dist;
                }
            }
        }
        return res;
    }

    // 牛逼的双指针+dp解法，o（n）
    public int longestMountain2(int[] nums){
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        // 保存左山脊
        int[] left = new int[n];
        for (int i = 1; i < n; ++i) {
            left[i] = nums[i - 1] < nums[i] ? left[i - 1] + 1 : 0;
        }

        // 保存右山脊
        int[] right = new int[n];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = nums[i + 1] < nums[i] ? right[i + 1] + 1 : 0;
        }

        // 根据左右山脊查找当前索引是否为山峰，并且ans置为最大值
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans;
    }



    // nums[begin] ~ nums[end] 为山脉数组
    private boolean isMountain(int[] nums, int begin, int end) {
        int len = end - begin + 1; // 求出数组长
        for (int i = 1; i < len - 1; i++) { // 从第2个元素开始判定
            if (judgeLeft(nums, begin, i) && judgeRight(nums, begin + i, end)) return true;
        }
        return false;
    }

    private boolean judgeLeft(int[] nums, int begin, int n) {
        for (int i = 0; i < n; i++) {
            if (nums[begin + i] >= nums[begin + i + 1]) return false;
        }
        return true;
    }

    private boolean judgeRight(int[] nums, int begin, int end) {
        for (int i = begin; i < end; i++) {
            if (nums[i] <= nums[i + 1]) return false;
        }
        return true;
    }
}
