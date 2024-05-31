package top.yuwenxin.sort;

import top.yuwenxin.utils.ArrayUtil;
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
        int pivot = arr[begin];
        int pivotIndex = begin;
        for (int i = begin + 1; i <= end; i++) {
            if (arr[i] < pivot) {
                pivotIndex++;
                ArrayUtil.swap(arr, i, pivotIndex);
            }
        }
        ArrayUtil.swap(arr, pivotIndex, begin);
        return pivotIndex;
    }
}
