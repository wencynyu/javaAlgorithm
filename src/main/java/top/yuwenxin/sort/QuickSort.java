package top.yuwenxin.sort;

import top.yuwenxin.utils.SortUtil;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.arr;
        QuickSort sort = new QuickSort();
        sort.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr){
        partition(arr, 0, arr.length - 1);
    }

    private void partition(int[] arr, int begin, int end) {
        if (begin <= end){
            int mid = getMid(arr, begin, end);
            partition(arr, begin, mid - 1);
            partition(arr, mid + 1, end);
        }
    }

    private int getMid(int[] arr, int begin, int end) {
        int base = arr[begin];
        while (begin < end){
            while (begin < end && arr[end] > base) end--;
            arr[begin] = arr[end];
            while (begin < end && arr[begin] < base) begin++;
            arr[end] = arr[begin];
        }
        arr[begin] = base;
        return begin;
    }
}
