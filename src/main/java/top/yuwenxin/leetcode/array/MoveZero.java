package top.yuwenxin.leetcode.array;

public class MoveZero {
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] != 0){
                        swap(nums, i, j);
                        break;
                    }
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
