package 动态规划.一维动态规划;

// 解码方法
// 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）
// 例如，"11106" 可以映射为："AAJF"、"KJF"
// 注意，消息不能分组为(1 11 06)，因为 "06" 不能映射为 "F"
// 这是由于 "6" 和 "06" 在映射中并不等价
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数
// 题目数据保证答案肯定是一个 32位 的整数
// 测试链接 : https://leetcode.cn/problems/decode-ways/

import java.util.Arrays;

public class Code03_DecodeWays {

    // 暴力递归
    public static int numDecodings1(String s){
        return f1(s.toCharArray() , 0) ;
    }

    public static int f1(char[] s , int i){
        // 如果i 等于 传进来的字符数组的长度，就说明该方案可行，返回1
        if (i == s.length) {
            return 1 ;
        }
        int ans ;
        if (s[i] == '0') {
            ans = 0 ;  // 该方案不行
        } else {
            // 第一种方案：一位一位的编码
            ans = f1(s,i+1) ;
            // 第二种方案：两位两位的编码
            // 两位两位的得满足条件。
            if (i + 1 < s.length && ((s[i] - '0') * 10 + s[i+1] - '0') <= 26) {
                ans += f1(s,i+2) ; // 两位两位的解码
            }
        }
        return ans ;
    }

    // 优化1：暴力递归改成记忆化搜索
    public static int numDecodings2(String s) {
        int[] dp = new int[s.length()] ;
        Arrays.fill(dp,-1);
        return f2(s.toCharArray() , 0 , dp) ;
    }
    public static int f2(char[] s, int i , int[] dp) {
        if ( i == s.length ) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i] ;
        }
        int ans;
        if (s[i] == '0') {
            ans = 0 ;
        } else{
            ans = f2(s,i+1,dp) ;
            // 两位两位的解码 得满足条件
            if ( i + 1 < s.length && ((s[i] - '0') * 10 + s[i+1] - '0') <= 26){
                ans += f2(s , i+1 , dp) ;
            }
        }
        dp[i] = ans ;
        return ans ;
    }

    // 优化2：将递归改成dp数组,从右往左推
    public static int numDecodings3(String str){
        char[] s = str.toCharArray() ; // 改成字符数组
        int n = s.length ;
        int[] dp = new int[n+1] ;
        dp[n] = 1 ;
        // 从右往左推
        for (int i = n - 1 ; i >= 0 ; i--) {
            if (s[i] == '0') {
                dp[i] = 0 ;
            } else {
                dp[i] = dp[i+1] ;
                if ( i + 1 < s.length && ((s[i] - '0') * 10 + s[i+1] - '0') <= 26) {
                    dp[i] += dp[i+1] ;
                }
            }
        }
        return dp[0] ;
    }

    // 优化3：用俩变量
    public static int numDecodings4(String s){
        int next = 1 ; // dp[i+1]
        int nextNext = 0 ; // dp[i+2]
        for (int i = s.length() - 1 , cur ; i >= 0 ; i--) {
            if (s.charAt(i) == '0') {
                cur = 0 ; // 一旦有0，该方案就作废
            } else {
                cur = next ;
                if (i + 1 < s.length() && ((s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0') <= 26) {
                    cur += nextNext;
                }
            }
            nextNext = next ;
            next = cur ;
        }
        return next ;
    }




}
