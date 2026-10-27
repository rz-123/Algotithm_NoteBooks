package 动态规划.二维动态规划.Two;

// 交错字符串
// 给定三个字符串 s1、s2、s3
// 请帮忙验证s3是否由s1和s2交错组成
// 测试链接 : https://leetcode.cn/problems/interleaving-string/

public class Code03_InterleavingString {

    public static boolean isInterleave(String str1 , String str2 , String str3) {
        if (str1.length() + str2.length() != str3.length()) {
            return false ;
        }
        char [] s1 = str1.toCharArray() , s2 = str2.toCharArray() , s3 = str3.toCharArray() ;
        int n = s1.length , m = s2.length ;
        return isInterleave1(s1,s2,s3,n,m);
    }

    // 动态规划
    public static boolean isInterleave1(char[] s1 , char[] s2 , char[] s3 , int n , int m) {

        // 该dp表为布尔类型，表示s1[前缀长度为i]和s2[前缀长度为j]，能否交错组成s3[前缀长度为i+j]
        boolean[][] dp = new boolean[n+1][m+1] ;
        dp[0][0] = true ; // 00 能拼出 00

        // 填充第一行第一列
        for (int i = 1; i <= n; i++) {
            // 当s2为0时，s1的长度跟s3的每一位必须相等，不相等直接跳出循环
            if (s1[i-1] != s3[i-1]){
                break;
            }
            dp[i][0] = true ; // 当s1[i]，s2[0]时，都能拼出s3[i+j]
        }
        for (int j = 1; j <= m; j++) {
            // 当s1为0时，s2的长度跟s3的每一位必须相等，不相等直接跳出循环
            if (s2[j-1] != s3[j-1]){
                break;
            }
            dp[0][j] = true ; // 当s1[0]，s2[j]时，都能拼出s3[i+j]
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 以下两个条件，满足任何一个，dp[i][j]为true

                // s1的最后一个字符与s3的最后一个字符相等，并且 dp表中的上方的格子也得满足
                dp[i][j] = (s1[i - 1] == s3[i + j - 1] && dp[i - 1][j])
                        // s2的最后一个字符与s3的最后一个字符相等，并且 dp表中的左边的格子也得满足
                        || (s2[j - 1] == s3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[n][m] ;
    }

    // 空间压缩，一维数组滚动
    public static boolean isInterleave2(char[] s1 , char[] s2 , char[] s3 , int n , int m) {

        boolean[] dp = new boolean[m+1] ;
        dp[0] = true ; // 第一位
        // 填充一维数组
        for (int j = 1; j <= m; j++) {
            if (s2[j-1] != s3[j-1]){
                break;
            }
            dp[j] = true ;
        }

        // 滚动一维数组
        for (int i = 1; i <= n; i++) {
            // 第一个位置的布尔值，相当于：当s1[i]，s2[0]时，s1和s3的每一位必须相等，才能拼出s3[i+j]
            dp[0] = s1[i-1] == s3[i-1] && dp[0] ;
            for (int j = 1; j <= m; j++) {
                dp[j] = (s1[i-1] == s3[i+j-1] && dp[j])
                        ||
                        (s2[j - 1] == s3[i + j - 1] && dp[j - 1]);
            }
        }
        return dp[m] ;
    }


}

