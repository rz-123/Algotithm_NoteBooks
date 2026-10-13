package Breadth_First_Search_BFS_宽度优先遍历;

// 地图分析
// 你现在手里有一份大小为 n x n 的 网格 grid
// 上面的每个 单元格 都用 0 和 1 标记好了其中 0 代表海洋，1 代表陆地。
// 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的
// 并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
// 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：
// (x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
// 测试链接 : https://leetcode.cn/problems/as-far-from-land-as-possible/

public class Code01_AsFarFromLandAsPossible {

    public static int MAXN = 101 , MAXM = 101;
    // 队列，存位置[m,n]
    public static int[][] queue = new int[MAXM * MAXN][2] ;
    public static int l , r ;

    // 二维数组，布尔类型，标识该位置是不是进过队列
    public static boolean[][] visited = new boolean[MAXN][MAXM] ;

    // 上下左右扩充 数组  x + move[i], y + move[i+1]  i 从0到3位置
    public static int[] move = new int[] {-1,0,1,0,-1} ;

    public static int maxDistance( int[][] grid){
        l = r = 0 ; // 队列清空
        int n = grid.length ;
        int m = grid[0].length ;
        // 为0节点的个数，海洋的个数
        int seas = 0 ;
        // 遍历传进来的网格
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    // 如果该位置为1时，将该位置加入队列，然后vis标识改为true
                    visited[i][j] = true ;
                    queue[r][0] = i ;
                    queue[r++][1] = j ;
                } else {
                    // 否则 就是为海洋，那么表示改为false，海洋数量++
                    visited[i][j] = false ;
                    seas ++ ;
                }
            }
        }
        // 如果 网格全部为海洋 或者 全为陆地，直接返回-1
        if (seas == 0 || seas == m * n){
            return -1 ;
        }

        int level = 0 ; // 向下遍历的层数
        // 队列
        while (l < r) {
            level++ ; // 层数加加
            int size = r - l ; // 按层弹出，计算当前队列中有多少个数
            for (int i = 0 , x , y , nx , ny ; i < size; i++) {
                // 队列从头开始弹出
                x = queue[l][0] ;
                y = queue[l++][1] ;
                // 上下左右扩充
                for (int j = 0; j < 4; j++) {
                    nx = x + move[j] ;
                    ny = y + move[j+1] ;

                    // 将四周的节点加入队列（如果没越界的话）
                    // 坐标没越界，并且该节点没进过队列
                    if ( nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]){
                        visited[nx][ny] = true ;
                        queue[r][0] = nx ;
                        queue[r++][1] = ny ;
                    }
                }
            }
        }
        return level-1 ;
    }
}
