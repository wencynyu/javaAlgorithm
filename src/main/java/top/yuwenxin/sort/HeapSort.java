package top.yuwenxin.sort;

public class HeapSort {
    public void heapSort(int[] arr){
        for (int i = arr.length - 1; i > 0; i--) {
            maxHeap(arr, i);

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }

    private void maxHeap(int[] arr, int n) {
        int child;
        // 从第一个"非叶子节点"开始维护堆
        for (int i = (n - 1) / 2; i >= 0; i--) {
            //左子节点位置
            child = 2 * i + 1;
            //右子节点存在且大于左子节点，child变成右子节点
            if (child != n && arr[child] < arr[child + 1]) {
                child++;
            }
            //交换父节点与左右子节点中的最大值
            if (arr[i] < arr[child]) {
                int temp = arr[i];
                arr[i] = arr[child];
                arr[child] = temp;
            }
        }
        
    }
}
