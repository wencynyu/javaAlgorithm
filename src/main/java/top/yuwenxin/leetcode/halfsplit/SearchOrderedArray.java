package top.yuwenxin.leetcode.halfsplit;

public class SearchOrderedArray {
    /**
     * 最简单的二分查找，在有序数组中查找目标值的索引
     * @param arr
     * @param target
     * @return
     */
    public int search(int[] arr, int target){
        int begin = 0, end = arr.length - 1;
        int mid;
        while (begin <= end){
            mid = begin + (end - begin) / 2;
            int midVal = arr[mid];
            if (midVal == target){
                return mid;
            }else if (midVal > target){
                end = mid - 1;
            }else {
                begin = mid + 1;
            }
        }
        return -1;
    }
}
