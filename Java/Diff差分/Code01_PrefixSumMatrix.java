package Diff差分;


// 二维前缀和数组模版
// https://leetcode.cn/problems/range-sum-query-2d-immutable/
public class Code01_PrefixSumMatrix {

    class NumMatrix {

        public int[] [] sum ;

        public NumMatrix(int[] [] matrix) {
            int n = matrix.length;  // 行数
            int m = matrix[0] .length ;  // 列数
            // 原本长度加个1，为了保证计算前缀和事不过界
            // 也就是将sum外围包一圈0
            sum = new int[n + 1][m + 1] ;
            for (int i = 1 , c = 0; i < n; i++ , c++) {
                for (int j = 1 , d = 0; j < m; j++ , d++) {
                    sum[i][j] = matrix[c][d] ; // i行j列，从1开始
                }
            }
            // 计算每个位置的前缀和
            for (int i = 1; i <= n ; i++) {
                for (int j = 1; j <= m ; j++) {
                    // 左+上-左上
                    sum[i][j] += sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] ;
                }
            }
        }
        public int sumRegion(int a , int b , int c , int d){
            c++;
            d++; // cd各加1，下边 ab不用减一了就
            return sum[c][d] - sum[c][b] - sum[a][d] + sum[a][b];
        }
    }

}
