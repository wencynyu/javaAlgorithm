package top.yuwenxin.sort;

import top.yuwenxin.utils.ArrayUtil;
import top.yuwenxin.utils.SortUtil;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = SortUtil.arr;
        SelectSort selectSort = new SelectSort();
        selectSort.selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int tmp = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]){
                    tmp = j;
                }
            }
            ArrayUtil.swap(arr, i, tmp);
        }
    }
}
