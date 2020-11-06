package top.yuwenxin.sword2offer;

public class T29SpiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] res = new int[row * col];

        int up = 0, down = row - 1;
        int left = 0, right = col - 1;
        int ctl = 0;

        // 死循环，当遍历到中心数字时break
        while (true){
            // 左到右
            for (int i = left; i <= right; i++) {
                res[++ctl] = matrix[up][i];
            }
            if (++up >= down){
                break;
            }

            // 上到下
            for (int i = up; i <= down; i++) {
                res[++ctl] = matrix[i][right];
            }
            if (--right <= left){
                break;
            }

            // 右到左
            for (int i = right; i >= left; i--) {
                res[++ctl] = matrix[down][i];
            }
            if (--down <= up){
                break;
            }

            // 下到上
            for (int i = down; i >= up; i--) {
                res[++ctl] = matrix[i][left];
            }
            if (++left >= right){
                break;
            }
        }
        return res;
    }
}
