package top.yuwenxin.sort;

import top.yuwenxin.utils.SortUtil;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.arr;
        MergeSort sort = new MergeSort();
        sort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] arr){
        partition(arr, 0, arr.length - 1);
    }

    private void partition(int[] arr, int begin, int end) {
        if (begin < end){
            int mid = begin + (end - begin) / 2;
            partition(arr, begin, mid);
            partition(arr, mid + 1, end);
            merge(arr, begin, mid, end);
        }
    }

    private void merge(int[] arr, int begin, int mid, int end) {
        int[] tmp = new int[end - begin + 1];
        int i = begin, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end){
            if (arr[i] < arr[j]){
                tmp[k++] = arr[i++];
            }else {
                tmp[k++] = arr[j++];
            }
        }

        while (i <= mid){
            tmp[k++] = arr[i++];
        }
        while (j <= end){
            tmp[k++] = arr[j++];
        }

        for (int t = 0; t < tmp.length; t++) {
            arr[begin + t] = tmp[t];
        }
    }
}
