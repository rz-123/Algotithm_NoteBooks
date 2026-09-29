package Flood_Fill洪水填充;

// 最大人工岛
// 给你一个大小为 n * n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
// 返回执行此操作后，grid 中最大的岛屿面积是多少？
// 岛屿 由一组上、下、左、右四个方向相连的 1 形成
// 测试链接 : https://leetcode.cn/problems/making-a-large-island/

public class Code03_MakingLargeIsland {

    public static int largestIsland(int[][] grid){
        int n = grid.length , m = grid[0].length ;
        int id = 2 ; // 因为最初的格子是0或1，所以id初始化为2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 如果格子里是1，那么用id去填充自己以及四周是1的格子
                // 随后，id+1
                if (grid[i][j] == 1){
                    dfs(grid , n , m , i , j , id++);
                }
            }
        }
        int[] sizes = new int[id] ; // 设置id的大小
        int ans = 0 ;
        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < m ; j++){
                // 如果该格子的id > 1 , 那么就统计有多少个这样的格子
                if (grid[i][j] > 1) {
                    ans = Math.max(ans , ++sizes[grid[i][j]]) ;
                }
            }
        }

        // 现在开始讨论 当前网格的每个0 变成 1 ，形成的最大岛有多大
        boolean[] visited = new boolean[id] ; // 避免重复值，数字计算过的就设为true
        int up , down , left , right , merge ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 如果该格子等于0 开始讨论
                if (grid[i][j] == 0) {
                    // 找出 上下左右 的格子
                    up = i > 0 ? grid[i-1][j] : 0 ;
                    down = i + 1 < n ? grid[i + 1][j] : 0;
                    left = j > 0 ? grid[i][j - 1] : 0;
                    right = j + 1 < m ? grid[i][j + 1] : 0;
                    visited[up] = true ;
                    merge = 1 + sizes[up] ; // 自己的1也加上
                    if (!visited[down]) {
                        merge += sizes[down] ;
                        visited[down] = true ; // 避免算重
                    }
                    if (!visited[left]) {
                        merge += sizes[left];
                        visited[left] = true;
                    }
                    if (!visited[right]) {
                        merge += sizes[right];
                        visited[right] = true;
                    }
                    ans = Math.max(ans, merge); // 选出最大面积
                    // 结束后，这四个位置全部置为false，再开始新的一轮循环
                    visited[up] = false;
                    visited[down] = false;
                    visited[left] = false;
                    visited[right] = false;
                }
            }
        }
        return ans ;
    }



    // dfs 填充id
    public static void dfs(int[][] grid, int n, int m, int i, int j, int id) {
        if (i < 0 || i == n || j < 0 || j == m || grid[i][j] != 1) {
            return;
        }
        //  grid[i][j] == 1
        grid[i][j] = id;
        dfs(grid, n, m, i - 1, j, id);
        dfs(grid, n, m, i + 1, j, id);
        dfs(grid, n, m, i, j - 1, id);
        dfs(grid, n, m, i, j + 1, id);
    }
}
