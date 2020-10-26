package top.yuwenxin.leetcode.halfsplit;

public class SearchMinFromRotatedArray {
    /**
     * 在旋转过的有序数组中查找最小值
     * 1,2,3,4,5 -> 3,4,5,1,2
     */
    public int search(int[] arr){
        int begin = 0, end = arr.length - 1;
        int mid;
        while (begin <= end){
            mid = begin + (end - begin) / 2;
            if (arr[mid] < arr[end]){ // 右边数组有序
                end = mid;
            }else if (arr[mid] > arr[end]){ // 左边数组
                begin = mid + 1;
            }else {
                end--;
            }
        }
        return arr[begin];
    }
}
