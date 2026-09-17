package Diff差分;


// 给你一个由若干 0 和 1 组成的二维网格 grid
// 请你找出边界全部由 1 组成的最大 正方形 子网格
// 并返回该子网格中的元素数量。如果不存在，则返回 0。
// 测试链接 : https://leetcode.cn/problems/largest-1-bordered-square/
public class Code02_LargestOneBorderedSquare {

    public static int largest1BorderedSquare(int[] [] g){
        int n = g.length ; // 行
        int m = g[0].length ; // 列
        build(n,m,g);
        // 如果该正方形最左上角跟最右下角的和==0，那么证明该数组中就没有1
        if (sum(g,0,0,n-1,m-1) == 0){
            return 0 ;
        }
        int ans = 1; // 边界为1的正方形的边长，初始值为1
        for (int a = 0; a < n ; a++){
            for (int b= 0; b < m ; b++){
                // 枚举左右可能的正方形。
                // +ans是剪枝，
                // 比如起点(a,b)开始找，找到边长k为3的边界为1的正方形。那么下次循环从(a+1，b+1)位置以边长4往下找
                for (int c = a + ans, d = b + ans, k = ans + 1; c < n && d < m; c++, d++, k++) {
                    // 如果当前范围的累加和  减去 当前范围累加和缩小一圈  == 周长，那么该正方形的边界全部为1
                    if (sum(g,a,b,c,d) - sum(g , a+1 , b+1 , c-1 , d-1) == (k-1) << 2) {
                        ans = k ;
                    }
                }
            }
        }
        return ans * ans ;
    }

    // 在原始数组中计算构建前缀和数组
    public static void build(int n , int m , int[] [] g){
        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < m ; j++){
                g[i][j] += get(g,i,j-1) + get(g , i-1 ,j) - get(g,i-1,j-1) ;
            }
        }
    }

    // 求特定范围和,(a,b)到(c,d)范围的和
    public static int sum(int[][] g, int a, int b, int c, int d){
        // 若 前边的行 大于 后边的行 直接返回0，否则按照公式来求
        return a > c ? 0 : g[c][d] - get(g , c , b-1) - get(g , a-1 ,d) + get(g , a-1 , b-1)  ;
    }

    // 判断 i和j 以及他们-1越没越界,如果i，j小于0,该位置返回0
    public static int get(int[] [] g , int i , int j){
        return (i < 0 || j < 0) ? 0 : g[i][j] ;
    }


}
