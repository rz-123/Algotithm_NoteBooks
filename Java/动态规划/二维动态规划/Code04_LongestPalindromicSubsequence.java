package 动态规划.二维动态规划;

// 最长回文子序列
// 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度
// 测试链接 : https://leetcode.cn/problems/longest-palindromic-subsequence/

public class Code04_LongestPalindromicSubsequence {

    // 区间dp
    public static int longestPalindromeSubseq1(String str){
        char[] s = str.toCharArray() ;
        int n = s.length ;
        return f1(s,0,n-1);
    }
    // s[l....r]的最长回文子序列长度
    public static int f1(char[] s , int l , int r){

        // 如果 左边界 == 右边界，说明长度就是1
        if (l == r) {
            return 1 ;
        }
        // 左右边界长度为2，所以如果s[l] == s[r]，那么返回2，否则返回1
        if (l+1 == r) {
            return s[l] == s[r] ? 2 : 1 ;
        }

        if (s[l] == s[r]){ // 如果左边界的值跟右边界的值相等，那么就递归看看里边的有多长，然后加上左右边界，2
            return f1(s,l+1,r-1) + 2 ;
        } else {
            // 否则就是，要么不要开头，要么不要最后，俩选最大。
            return Math.max(f1(s,l+1,r) , f1(s,l,r-1)) ;
        }
    }

    // 优化1：用dp数组存储
    public static int longestPalindromeSubseq2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        return f2(s, 0, n - 1, dp);
    }

    public static int f2(char[] s, int l, int r, int[][] dp) {
        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return s[l] == s[r] ? 2 : 1;
        }
        if (dp[l][r] != 0) {
            return dp[l][r];
        }
        int ans;
        if (s[l] == s[r]) {
            ans = 2 + f2(s, l + 1, r - 1, dp);
        } else {
            ans = Math.max(f2(s, l + 1, r, dp), f2(s, l, r - 1, dp));
        }
        dp[l][r] = ans;
        return ans;
    }

    // 优化2：严格位置依赖
    public static int longestPalindromeSubseq3(String str){
        char[] s = str.toCharArray() ;
        int n = str.length() ;
        int[][] dp = new int[n][n] ;
        // 首先表格中的 l 不可能大于 R，所以从左上角到右下角的对角线以上才是我们要计算的
        // 而对角线[l][l] == 1
        for (int l = n-1 ; l >= 0 ; l--) {
            dp[l][l] = 1 ;
            if (l+1 < n) {
                dp[l][l+1] = s[l] == s[l+1] ? 2 : 1 ;
            }
            for (int r = l + 2 ; r < n ; r++) {
                if (s[l] == s[r]) {
                    dp[l][r] = 2 + dp[l+1][r-1] ;
                } else {
                    dp[l][r] = Math.max(dp[l+1][r] , dp[l][r-1]) ;
                }
            }
        }
        return dp[0][n-1] ;
    }

    // 优化3：严格位置依赖+空间压缩
    public static int longestPalindromeSubseq4(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n];
        for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {
            // dp[l] : 想象中的dp[l][l]
            dp[l] = 1;
            if (l + 1 < n) {
                leftDown = dp[l + 1];
                // dp[l+1] : 想象中的dp[l][l+1]
                dp[l + 1] = s[l] == s[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                backup = dp[r];
                if (s[l] == s[r]) {
                    dp[r] = 2 + leftDown;
                } else {
                    dp[r] = Math.max(dp[r], dp[r - 1]);
                }
                leftDown = backup;
            }
        }
        return dp[n - 1];
    }




}
