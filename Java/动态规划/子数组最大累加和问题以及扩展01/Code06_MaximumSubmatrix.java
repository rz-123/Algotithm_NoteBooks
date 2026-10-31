package 动态规划.子数组最大累加和问题以及扩展01;

// 子矩阵最大累加和问题
// 给定一个二维数组grid，找到其中子矩阵的最大累加和
// 返回拥有最大累加和的子矩阵左上角和右下角坐标
// 如果有多个子矩阵都有最大累加和，返回哪一个都可以
// 测试链接 : https://leetcode.cn/problems/max-submatrix-lcci/

import java.util.Arrays;

public class Code06_MaximumSubmatrix {

    // 压缩数组
    public static int[] getMaxMatrix(int[][] grid){
        int n = grid.length ;
        int m = grid[0].length ;
        int max = Integer.MIN_VALUE ;
        int a = 0 , b = 0 , c = 0 , d = 0 ; // 左上角 和 右下角的坐标
        // 一维滚动数组
        int[] nums = new int[m] ;
        for (int up = 0; up < n; up++) {
            Arrays.fill(nums, 0); // 每一次 滚动数组都清0
            // 第0行、第0行和第1行压缩、第0行和第一行、第二行压缩...
            for (int down = up; down < n; down++) {
                // 求最大累加和的子数组
                for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < m; r++) {
                    nums[r] += grid[down][r] ; // 相加 压缩
                    if (pre >= 0) {
                        pre += nums[r] ; // 往左扩
                    } else {
                        pre = nums[r] ; // 单独待着
                        l = r ; // 左边界调整到右边界
                    }

                    if (pre > max) {
                        max = pre ;
                        // 左上角
                        a = up ; // 行
                        b = l ;  // 列
                        // 右下角
                        c = down ; // 行
                        d = r ; // 列
                    }

                }

            }
        }
        return new int[] {a,b,c,d} ;
    }

}
