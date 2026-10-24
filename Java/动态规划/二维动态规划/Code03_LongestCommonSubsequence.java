package 动态规划.二维动态规划;

// 最长公共子序列
// 给定两个字符串text1和text2
// 返回这两个字符串的最长 公共子序列 的长度
// 如果不存在公共子序列，返回0
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列
// 测试链接 : https://leetcode.cn/problems/longest-common-subsequence/

public class Code03_LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String str1, String str2) {
        char[] s1 = str1.toCharArray(), s2 = str2.toCharArray();
        int n = s1.length, m = s2.length;
        return f1(s1, s2, n - 1, m - 1);
    }

    // 传进s1[0....i1]、s2[0.....i2]长度的数组，返回最长公共子序列的长度
    public static int f1(char[] s1, char[] s2, int i1, int i2) {
        if (i1 < 0 || i2 < 0) {
            return 0;
        }
        // 两个数组都不要最后一位
        int p1 = f1(s1, s2, i1 - 1, i2 - 1);
        // 最后一位，一个数组不要，另一个数组要
        int p2 = f1(s1, s2, i1, i2 - 1);
        int p3 = f1(s1, s2, i1 - 1, i2);
        // 如果俩数组的最后一位相等，那么 P1 就 + 一个 最后一位
        int p4 = s1[i1] == s2[i2] ? (p1 + 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // 用长度来处理递归
    public static int longestCommonSubsequence2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        return f2(s1, s2, n, m);
    }

    // len1、len2分别为s1、s2的长度
    public static int f2(char[] s1, char[] s2, int len1, int len2) {
        if (len2 == 0 || len1 == 0) {
            return 0;
        }
        int ans;
        // 如果俩数组最后一位相等，那就调递归，看看里边的相不相等，最后加上最后一位 + 1
        if (s1[len1 - 1] == s2[len2 - 1]) {
            ans = f2(s1, s2, len1 - 1, len2 - 1) + 1;
        } else {  // 否则就是最后一位不相等，那么就要一个要，一个不要，选最大
            ans = Math.max(f2(s1, s2, len1 - 1, len2), f2(s1, s2, len1, len2 - 1));
        }
        return ans;
    }

    // 优化1：记忆搜索，用dp存储
    public static int longestCommonSubsequence3(String str1, String str2) {
        char[] s1 = str1.toCharArray(), s2 = str2.toCharArray();
        int n = s1.length, m = s2.length;
        int[][] dp = new int[n + 1][m + 1]; // 因为递归中用长度，所以要加1
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        return f3(s1, s2, n, m, dp);
    }

    public static int f3(char[] s1, char[] s2, int len1, int len2, int[][] dp) {

        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        // 如果不等于-1 直接返回该数
        if (dp[len1][len2] != -1) {
            return dp[len1][len2];
        }
        int ans;
        if (s1[len1 - 1] == s2[len2 - 1]) {
            ans = f3(s1, s2, len1 - 1, len2 - 1, dp) + 1;
        } else {
            ans = Math.max(f3(s1, s2, len1 - 1, len2, dp), f3(s1, s2, len1, len2 - 1, dp));
        }
        // 存储
        dp[len1][len2] = ans;
        return ans;
    }

    // 优化2：严格位置依赖，左上、左、上
    public static int longestCommonSubsequence4(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n + 1][m + 1];

        // 行、列循环
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                }
            }
        }
        return dp[n][m];
    }

    // 优化3：严格位置依赖+空间压缩
    public static int longestCommonSubsequence5(String str1, String str2) {
        char[] s1,s2 ;
        // 因为要用一维数组滚动，所以这俩数组哪个短 ， 哪个是行，节省空间
        if (str1.length() >= str2.length()) {
            s1 = str1.toCharArray() ;
            s2 = str2.toCharArray() ;
        } else {
            s1 = str2.toCharArray();
            s2 = str1.toCharArray();
        }
        int n = s1.length;
        int m = s2.length;
        int[] dp = new int[m + 1];

        for (int len1 = 1 ; len1 <= n ; len1++) {
            int leftUp = 0 , backup ; // 左上变量 以及 临时变量
            for (int len2 = 1 ; len2 <= m ; len2++) {
                // 记住该位置的变量
                backup = dp[len2] ;

                if (s1[len1-1] == s2[len2-1]) {
                    dp[len2] = 1 + leftUp ; // 相当于；dp[i - 1][j - 1] + 1
                } else {
                    dp[len2] = Math.max(dp[len2] , dp[len2-1]) ;
                }
                // 将临时变量 赋值给 左上角变量
                leftUp = backup ;
            }
        }
        return dp[m] ;
    }



}