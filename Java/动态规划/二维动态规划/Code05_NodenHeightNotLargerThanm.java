package 动态规划.二维动态规划;

// 节点数为n高度不大于m的二叉树个数
// 现在有n个节点，计算出有多少个不同结构的二叉树
// 满足节点个数为n且树的高度不超过m的方案
// 因为答案很大，所以答案需要模上1000000007后输出
// 测试链接 : https://www.nowcoder.com/practice/aaefe5896cce4204b276e213e725f3ea

import java.io.*;

public class Code05_NodenHeightNotLargerThanm {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            out.println(f1(n, m));
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int MAXN = 51;

    public static int MOD = 1000000007;

    public static long[][] dp1 = new long[MAXN][MAXN];

    static {
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                dp1[i][j] = -1;
            }
        }
    }

    // 记忆化搜索
    // 节点数为n，高度不能超过m
    // 返回满足该条件的方案数
    public static int f1(int n , int m){
        if (n == 0){
            return 1; // 节点数为0，就是一个结构，为空结构
        }
        if (m == 0) {
            return 0;
        }
        if (dp1[n][m] != -1){
            return (int) dp1[n][m] ;
        }
        long ans = 0 ;
        // 如果左树占用k个，那么右树就占用n-k-1个，头结点是始终要减去。
        // 那么总体方案要 左树 与 右树 相乘才能是
        for (int k = 0 ; k < n ; k++){
            ans = (ans + ((long) f1(k,m-1) * f1(n-k-1 , m-1)) % MOD) % MOD ;
        }
        dp1[n][m] = ans ;
        return (int) ans ;
    }


    // 优化：严格位置依赖
    public static long[][] dp2 = new long[MAXN][MAXN];
    public static int f2(int n , int m){
        for (int i = 0; i <= m; i++) {
            dp2[0][i] = 1 ; // 相当于 n == 0,return 1
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m ; j++) {
                dp2[i][j] = 0 ;
                for (int k = 0 ; k < i ; k++) {
                    dp2[i][j] = (dp2[i][j] + dp2[k][j-1] * dp2[i-k-1][j-1] % MOD) % MOD ;
                }
            }
        }
        return (int) dp2[n][m] ;
    }

    // 空间压缩
    public static long[] dp3 = new long[MAXN];

    public static int f3(int n, int m) {
        dp3[0] = 1 ;
        for (int i = 1; i <= n; i++) {
            dp3[i] = 0 ;
        }
        // 一列一列的枚举，从左往右
        for (int j = 1; j <= m ; j++) {
            // 一行一行的枚举，从下往上
            for (int i = n; i >= 1 ; i--) {
                dp3[i] = 0 ;
                for (int k = 0; k < i; k++) {
                    // 枚举
                    dp3[i] = (dp3[i] + dp3[k] * dp3[i - k - 1] % MOD) % MOD;
                }
            }
        }
        return (int) dp3[n] ;
    }


}
