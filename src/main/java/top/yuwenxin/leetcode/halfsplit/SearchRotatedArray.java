package top.yuwenxin.leetcode.halfsplit;

public class SearchRotatedArray {
    /**
     * 在旋转过的有序数组中查找目标值
     */
    public int search(int[] arr, int target){
        int begin = 0, end = arr.length - 1;
        int mid;
        while (begin <= end){
            mid = begin + (end - begin) >> 2;
            int midVal = arr[mid];
            if (midVal == target){
                return mid;
            }else if (midVal > target){

            }else {

            }
        }
        return -1;
    }
}
