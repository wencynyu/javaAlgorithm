package top.yuwenxin.leetcode.halfsplit;

public class SearchRotatedArray {
    /**
     * 在旋转过的有序数组中查找目标值
     * 1,2,3,4,5 -> 3,4,5,1,2 || 4,5,1,2,3
     */
    public int search(int[] arr, int target){
        int begin = 0, end = arr.length - 1;
        int mid;
        while (begin <= end){
            mid = begin + (end - begin) / 2;
            int midVal = arr[mid];
            if (midVal == target){
                return mid;
            }else {
                // 必有一半区间为有序的
                if (arr[mid] >= arr[end]){ // 左半区有序
                    if (target < arr[mid] && target >= arr[begin]){ // 在有序区间内进行二分
                        end = mid - 1;
                    }else {
                        begin = mid + 1;
                    }
                }else { // 右半区有序
                    if (target <= arr[end] && target > arr[mid]){
                        begin = mid + 1;
                    }else {
                        end = mid - 1;
                    }
                }
            }
        }
        return -1;
    }
}
