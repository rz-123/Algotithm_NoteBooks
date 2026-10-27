package 动态规划.二维动态规划.Two;

// 有效涂色问题
// 给定n、m两个参数
// 一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选
// 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法
// 求一共有多少种有效的涂色方法
// 1 <= n, m <= 5000
// 结果比较大请 % 1000000007 之后返回
// 对数器验证

import java.util.Arrays;

public class Code04_FillCellsUseAllColorsWays {


    // 暴力枚举
    public static int ways1(int n, int m) {
        return f(new int[n], new boolean[m + 1], 0, n, m);
    }

    // 把所有填色的方法暴力枚举
    // 然后一个一个验证是否有效
    // 这是一个带路径的递归
    // 无法改成动态规划
    public static int f(int[] path, boolean[] set, int i, int n, int m) {
        if (i == n) {
            Arrays.fill(set, false);
            int colors = 0;
            for (int c : path) {
                if (!set[c]) {
                    set[c] = true;
                    colors++;
                }
            }
            return colors == m ? 1 : 0;
        } else {
            int ans = 0;
            for (int j = 1; j <= m; j++) {
                path[i] = j;
                ans += f(path, set, i + 1, n, m);
            }
            return ans;
        }
    }




    // 动态规划
    public static int MAXN = 5001;

    public static int[][] dp = new int[MAXN][MAXN];

    public static int mod = 1000000007;

    // dp[i][j] 一共有m种颜色，返回 前i个格子涂满j种的方法数
    public static int ways2(int n , int m){

        // dp表中的第一行跟第一列不管，直接从第二列开始，也就是dp[i][1]
        for (int i = 1; i <= n ; i++) {
            // 当j等于1时，始终有m种方法
            dp[i][1] = m ;
        }

        // 填充dp表从2开始
        for (int i = 2; i <= n ; i++) {
            for (int j = 2; j <= m ; j++) {
                // 除去最后一位，前面的格子都满足了j种颜色的情况下，要填最后一位的方法数（一共有j种颜色，所以要乘以j种）
                dp[i][j] = (int) (((long) dp[i - 1][j] * j) % mod);
                // 在最后一位凑齐j种颜色，
                dp[i][j] = (int) ((((long) dp[i - 1][j - 1] * (m - j + 1)) + dp[i][j]) % mod);
            }
        }
        return dp[n][m] ;
    }


    // 对数器

    public static void main(String[] args) {
        // 测试的数据量比较小
        // 那是因为数据量大了，暴力方法过不了
        // 但是这个数据量足够说明正式方法是正确的
        int N = 9;
        int M = 9;
        System.out.println("功能测试开始");
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                int ans1 = ways1(n, m);
                int ans2 = ways2(n, m);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                }
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 5000;
        int m = 4877;
        System.out.println("n : " + n);
        System.out.println("m : " + m);
        long start = System.currentTimeMillis();
        int ans = ways2(n, m);
        long end = System.currentTimeMillis();
        System.out.println("取模之后的结果 : " + ans);
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");
    }


}
