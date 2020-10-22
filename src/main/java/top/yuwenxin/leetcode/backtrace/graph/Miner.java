package top.yuwenxin.leetcode.backtrace.graph;

public class Miner {
    int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            // 规则 1
            board[x][y] = 'X';
        } else{
            dfs(board, x, y);
        }
        return board;
    }

    private void dfs(char[][] board, int row, int col) {
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            int tx = row + dirX[i];
            int ty = col + dirY[i];
            if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                continue;
            }
            // 不用判断 M，因为如果有 M 的话游戏已经结束了
            if (board[tx][ty] == 'M') {
                count++;
            }
        }
        if (count > 0) {
            board[row][col] = (char) (count + '0');
        } else {
            board[row][col] = 'B';
            for (int i = 0; i < 8; ++i) {
                int tx = row + dirX[i];
                int ty = col + dirY[i];
                // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E') {
                    continue;
                }
                // 在循环中使用dirX，dirY优雅地递归查询八邻域
                dfs(board, tx, ty);
            }
        }
    }
}
