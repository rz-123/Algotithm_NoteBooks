package 动态规划.二维动态规划;

// 最小路径和
// 给定一个包含非负整数的 m x n 网格 grid
// 请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 测试链接 : https://leetcode.cn/problems/minimum-path-sum/

public class Code01_MinimumPathSum {

    // 暴力递归：超时
    public static int minPathSum1(int[][] grid){
        // 递归返回 左上 到 右下角的最短路径之和
        return f1(grid , grid.length - 1 , grid[0].length - 1) ;
    }

    // 返回 最短路径之和，只能向右或者向下 走
    public static int f1(int[][] grid , int i , int j){
        if (i == 0 && j == 0) {
            return grid[0][0] ;
        }
        int up = Integer.MAX_VALUE ; // 上方格子
        int left = Integer.MAX_VALUE ; // 左边格子

        //
        if (i-1 >= 0) {
            // 第一行的值
            up = f1(grid,i-1 , j) ;
        }
        if (j - 1 >= 0) {
            // 第一列的值
            left = f1(grid,i,j-1) ;
        }
        // 返回的数值，要与上方跟左边选最小，然后 加上自己
        return grid[i][j] + Math.min(up,left) ;
    }

    // 优化1：记忆化搜索，用dp表
    public static int mianPathSum2(int[][] grid){
        int n = grid.length ;
        int m = grid[0].length ;
        // dp数组
        int[][] dp = new int[n][m] ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1 ; // 初始化全为-1
            }
        }
        return f2(grid , n-1 , m - 1 , dp) ;
    }
    public static int f2(int[][] grid , int i , int j , int[][] dp){
        // 如果dp表里有 直接返回该数值
        if (dp[i][j] != -1) {
            return dp[i][j] ;
        }
        int ans ;
        if (i == 0 && j == 0) {
            ans = grid[0][0] ;
        } else {
            int up = Integer.MAX_VALUE ;
            int left = Integer.MAX_VALUE ;
            if ( i - 1 >= 0) {
                up = f2(grid , i-1 , j ,dp) ;
            }
            if (j - 1 >= 0) {
                left = f2(grid , i , j-1 , dp) ;
            }
            ans = grid[i][j] + Math.min(up,left) ;
        }
        dp[i][j] = ans ;
        return  ans ;
    }

    // 优化2：严格位置依赖,dp数组，从左到右，从上到下
    public static int minPathSum3(int[][] grid){
        int n = grid.length , m = grid[0].length ;
        int[][] dp = new int[n][m] ;
        dp[0][0] = grid[0][0] ;
        // dp数组的第一行 主要依赖左边格子，i-1
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0] ;
        }
        // dp数组的第一列 主要依赖上方格子，j-1
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j] ;
        }
        // 除了 第一行跟第一列 的其他格子
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i-1][j] , dp[i][j-1]) + grid[i][j] ;
            }
        }
        return dp[n-1][m-1] ;
    }

    // 优化3：严格位置依赖 + 空间压缩，一维数组滚动实现
    public static int minPathSum(int[][] grid){
        int n = grid.length , m = grid[0].length ;
        int[] dp = new int[m] ;
        dp[0] = grid[0][0] ;
        // 第一行数据确定，依赖左边格子，j-1
        for (int j = 1 ; j < m ; j ++) {
            dp[j] = dp[j-1] + grid[0][j] ;
        }
        // 滚动数组，根据i的值变换行数
        for (int i = 1; i < n ; i++) {

            // 第一行第一列 依赖上方的格子 加上 原始表的自身格子
            dp[0] += grid[i][0] ;
            // 第2列，第3列......
            for (int j = 1; j < m ; j++) {
                // 依赖左边格子跟上方格子（此时的上方格子就是一维数组中的自身的格子）
                dp[j] = Math.min(dp[j-1] , dp[j]) + grid[i][j] ;
            }
        }
        return dp[m-1];
    }




}
