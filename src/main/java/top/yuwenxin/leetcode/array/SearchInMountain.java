package top.yuwenxin.leetcode.array;

// T1095的接口，用来获取山脉数组的值和长度
interface MountainArray {
    int get(int index);
    int length();
}

@SuppressWarnings("all")
public class SearchInMountain {

    /**
     * 整体山脉数组- -。。不是分段的，分段的太复杂了
     * 1,2,3,4,2,1
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peek = getPeek(mountainArr);
        int res = findInLeft(0, peek, target, mountainArr);
        return res == -1 ? findInRight(peek + 1, mountainArr.length() - 1, target, mountainArr) : res;
    }

    private int findInRight(int begin, int end, int target, MountainArray mountainArr) {
        while (begin <= end){
            int mid = begin + (end - begin) / 2;
            int midVal = mountainArr.get(mid);
            if (midVal == target){
                return mid;
            } else if (midVal < target){
                end = mid - 1;
            }else {
                begin = mid + 1;
            }
        }
        return -1;
    }

    private int findInLeft(int begin, int end, int target, MountainArray mountainArr) {
        while (begin <= end){
            int mid = begin + (end - begin) / 2;
            int midVal = mountainArr.get(mid);
            if (midVal == target){
                return mid;
            } else if (midVal > target){
                end = mid - 1;
            }else {
                begin = mid + 1;
            }
        }
        return -1;
    }

    private int getPeek(MountainArray mountainArr) {
        int begin = 0, end = mountainArr.length() - 1;
        int mid;
        while (begin < end){
            mid = begin + (end - begin) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)){
                begin = mid + 1;
            }else {
                end = mid;
            }
        }
        return begin;
    }
}
