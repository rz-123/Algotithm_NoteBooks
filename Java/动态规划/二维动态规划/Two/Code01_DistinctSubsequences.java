package 动态规划.二维动态规划.Two;

// 不同的子序列
// 给你两个字符串s和t ，统计并返回在s的子序列中t出现的个数
// 答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/distinct-subsequences/

public class Code01_DistinctSubsequences {

    public static int MOD = 1000000007 ;

    // 直接动态规划
    public static int numDistinct1(String str , String target){
        // 字符串转成字符数组
        char[] s = str.toCharArray() ;
        char[] t = target.toCharArray() ;
        int n = s.length , m = t.length ;
        // dp[i][j]:s[前缀长度为i]的所有子序列中，有多少子序列等t[前缀和长度为j]
        int[][] dp = new int[n + 1][m + 1] ; // dp中的行列表示个数所以要+1

        // 首先填最简单的格子，0行跟0列
        //
        for (int i = 0; i <= n ; i++) {
            // 表示 s[前缀长度为i]的所有子序列中，有多少个子序列等于t[0]，
            // 所以 dp[i][0]=1
            dp[i][0] = 1 ;
        }

        // 从上往下，从左往右填格子
        for (int i = 1 ; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // s中不要最后一位
                dp[i][j] = dp[i-1][j] ;
                // 如果两个字符串的最后一位相等，那么就要最后一位
                if (s[i-1] == t[j-1]) {
                    // 加上左上的值
                    dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % MOD ;
                }
            }
        }
        return dp[n][m] ;
    }

    // 空间压缩
    // 一维数组滚动
    public static int numDistinct2(String str , String target){
        char[] s = str.toCharArray();
        char[] t = target.toCharArray();
        int n = s.length;
        int m = t.length;
        int[] dp = new int[m + 1];
        dp[0] = 1;
        // 因为要依赖左上的格子，所以我们要从右往左推，从上往下推
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                // 这一行就省略，变成一维数组后，当前位置的数没计算之前就是该位置的上方格子
                // dp[i][j] = dp[i-1][j]
                if (s[i-1] == t[j-1]) {
                    // 一维数组中的左边就是二维数组中的左上
                    dp[j] = (dp[j] + dp[j-1]) % MOD ;
                }
            }
        }
        return dp[m] ;
    }


}
