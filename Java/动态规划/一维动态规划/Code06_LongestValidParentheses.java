package 动态规划.一维动态规划;

// 最长有效括号
// 给你一个只包含 '(' 和 ')' 的字符串
// 找出最长有效（格式正确且连续）括号子串的长度。
// 测试链接 : https://leetcode.cn/problems/longest-valid-parentheses/

public class Code06_LongestValidParentheses {

    public static int longestValidParentheses(String str){
        char[] s = str.toCharArray() ;
        int[] dp = new int[s.length] ;
        int ans = 0 ;
        for (int i = 1 , p ; i < s.length ; i++) {
            // 如果是左括号 就没有讨论的必要，维持初始数组值0
            if (s[i] == ')') {
                // 找出p的位置
                p = i - dp[i-1] - 1 ;
                // p必须要在数组范围内，并且p的位置上的值为左括号
                if (p >= 0 && s[p] == '('){
                    // 那么 dp[i]的值 最少是 dp[i-1]+2的值，
                    // 如果p-1 在数组范围内，还要加上dp[p-1]的值
                    dp[i] = dp[i-1] + 2 + (p-1 >= 0 ? dp[p-1] : 0) ;
                }
            }
            ans = Math.max(ans , dp[i]) ;
        }
        return ans ;
    }



}
