package Flood_Fill洪水填充;

// 被围绕的区域
// 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域
// 并将这些区域里所有的 'O' 用 'X' 填充。
// 测试链接 : https://leetcode.cn/problems/surrounded-regions/

public class Code02_SurroundedRegions {

    public static void solve(char[][] board){
        int n = board.length ;
        int m = board[0].length ;
        // 矩形的四周感染
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O'){
                dfs(board,n,m,0,i); // 第一行的所有'O'感染
            }
            if (board[n-1][i] == 'O'){
                dfs(board,n,m,n-1,i); // 最后一行的所有‘O’感染
            }
        }
        for (int j = 0 ; j < n - 1 ; j++){
            if (board[j][0] == 'O'){
                dfs(board,n,m,j,0); // 第一列所有'O'感染
            }
            if (board[j][m-1] == 'O') {
                dfs(board , n , m , j , m-1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O'){
                    board[i][j] = 'X' ; // 把剩余的'O'改为'X'
                }
                if (board[i][j] == 'F') {
                    board[i][j] = 'O' ; // 将所有边缘的'F'改回'O'
                }
            }
        }
    }

    // dfs
    public static void dfs(char[] [] board , int n , int m , int i , int j) {
        if (i < 0 || i == n || j < 0 || j == m || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'F'; // 将边缘的"O"改为“F”
        dfs(board, n, m, i + 1, j);
        dfs(board, n, m, i - 1, j);
        dfs(board, n, m, i, j + 1);
        dfs(board, n, m, i, j - 1);
    }
}
