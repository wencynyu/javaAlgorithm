package top.yuwenxin.leetcode.everyday;

public class _240611T419 {
    public int countBattleships(char[][] board) {
        int res = 0;
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X') {
                    res++;
                    if (i > 0 && board[i - 1][j] == 'X') res--;
                    if (j > 0 && board[i][j - 1] == 'X') res--;
                }
            }
        }
        return res;
    }
}
