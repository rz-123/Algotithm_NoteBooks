package Breadth_First_Search_BFS_宽度优先遍历;

// 二维接雨水
// 给你一个 m * n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度
// 请计算图中形状最多能接多少体积的雨水。
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water-ii/

import java.util.PriorityQueue;

public class Code05_TrappingRainWaterII {

    public static int trapRainWater(int[][] height){
        int[] move = new int[] {-1,0,1,0,-1} ; // 上下左右扩充
        int n = height.length ;
        int m = height[0].length ;

        // 创建小根堆，根据水线来排序，水线小的在前，[行，列，水线]
        PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a, int[] b) -> a[2] - b[2]) ;
        // 是否进过小根堆
        boolean[][] visited = new boolean[n][m] ;
        // 遍历整个二维数组
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                // 把二维数组的四周加入小根堆
                if (i == 0 | i == n - 1 || j == 0 || j == m - 1) {
                    heap.add(new int[] {i,j,height[i][j]}) ;
                    visited[i][j] = true ;
                } else {
                    visited[i][j] = false ;
                }
            }
        }
        int ans = 0 ; // 最终返回的雨水体积
        // 小根堆不为空 就继续
        while (!heap.isEmpty()) {
            int[] poll = heap.poll(); // 弹出一个
            int r = poll[0] , c = poll[1] , w = poll[2] ;
            ans += w - height[r][c] ;  // 小根堆弹出的 水线 减去 该位置的高 获得该位置雨水的体积

            // 从弹出的节点起始 往四周扩充
            for (int i = 0 , nr , nc; i < 4; i++) {
                nr = r + move[i] ;
                nc = c + move[i + 1] ;

                // 扩充时，如果扩充节点没越界 并且 没进过小根堆，那么就加入小根堆，并且 将 是否进入小根堆的类型变为true
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
                    // 此时的水线 要跟 （扩充节点的高度 与 前一个节点水线的高度） 选最大值
                    heap.add(new int[] {nr , nc , Math.max(height[nr][nc] , w)}) ;
                    visited[nr][nc] = true ;
                }
            }
        }
        return ans ;
    }
}
