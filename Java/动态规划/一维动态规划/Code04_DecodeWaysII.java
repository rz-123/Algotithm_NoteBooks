package 动态规划.一维动态规划;

// 解码方法 II
// 一条包含字母 A-Z 的消息通过以下的方式进行了 编码 ：
// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// 要 解码 一条已编码的消息，所有的数字都必须分组
// 然后按原来的编码方案反向映射回字母（可能存在多种方式）
// 例如，"11106" 可以映射为："AAJF"、"KJF"
// 注意，像 (1 11 06) 这样的分组是无效的，"06"不可以映射为'F'
// 除了上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符
// 可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）
// 例如，"1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19"
// 对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息
// 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目
// 由于答案数目可能非常大，答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/decode-ways-ii/

import java.util.Arrays;

public class Code04_DecodeWaysII {

    public static long mod = 1000000007;

    public int numDecodings1(String string) {
        return (int) f1(string.toCharArray() , 0) ;
    }
    public static long f1(char[] s , int i){
        if (i == s.length){
            return 1; // i到头了 一种方案
        }
        if (s[i] =='0') {
            return 0 ;
        }
        // 第一种情况：i想自己转,如果i是*就是九种，i不是*就是一种
        long ans = f1(s,i+1) * (s[i] == '*' ? 9 : 1) ;
        // 第二种情况：i 和 i+1 一块转
        if (i + 1 < s.length) {
            // 该情况中又分为两种情况
            // 两个数都没有*
            if (s[i] != '*'){
                if (s[i+1] != '*') {
                    if (( s[i] - '0' ) * 10 + s[i+1] - '0' <= 26) {
                        ans += f1(s,i+2) ;
                    }
                } else {
                    // 这是 第一位是数字，第二位是*

                    // 如果第一位为1，那么就乘以9不超26
                    if (s[i] == '1') {
                        ans += f1(s,i+2) * 9 ;
                    }
                    // 如果第一位为2，那么就乘以6不超26
                    if (s[i] == '2') {
                        ans += f1(s,i+2) * 6 ;
                    }
                }
            } else {
                // 否则就是第一位为*
                // 第二位不为*
                if (s[i+1] != '*') {
                    // 要保证不能超过26，所以如果第二位小于等于6，那么后边方案乘以2
                    if (s[i+1] <= '6') {
                        ans += f1(s,i+2) * 2 ;
                    } else {
                        // 否则第二位就是 大于6的,那么就有一种方案
                        ans += f1(s,i + 2) ;
                    }
                } else {
                    // 上边的都不是 就是两位都是*，
                    // * 的范围是1到9，所以11-26一共有十五种可能性
                    ans += f1(s,i+2) * 15 ;
                }
            }
        }
        ans %= mod ;
        return ans ;
    }

    // 优化1：用dp数组存值，用到直接取
    public static int numDecodings2(String str){
        char[] s = str.toCharArray() ;
        long[] dp = new long[s.length] ;
        Arrays.fill(dp , -1);
        return (int) f2(s,0,dp) ;
    }
    public static long f2(char[] s , int i , long[] dp){
        if (i == s.length) {
            return 1 ;
        }
        if (s[i] == '0') {
            return 0 ;
        }
        if (dp[i] != -1) {
            return dp[i] ;
        }
        // 一位一位的解码，是*或不是*，是*就是九种可能性，不是*就是1种可能性
        long ans = f2(s,i+1 , dp) * (s[i] == '*' ? 9 :1) ;
        if (i + 1 < s.length) {
            if (s[i] != '*') {
                if (s[i + 1] != '*') {
                    // 两位都不是*
                    if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                        ans += f2(s, i + 2, dp);
                    }
                } else {
                    // 第一位不是*，第二位是*，同时要保证不能超过26
                    if (s[i] == '1') {
                        ans += f2(s, i + 2, dp) * 9;
                    }
                    if (s[i] == '2') {
                        ans += f2(s, i + 2, dp) * 6;
                    }
                }
            } else {
                // 否则就是 第一位是*
                if (s[i + 1] != '*') {
                    // 不能超过26
                    if (s[i + 1] <= '6') {
                        ans += f2(s, i + 2, dp) * 2;
                    } else {
                        // 否则 第二位>6 那么就是1种可能
                        ans += f2(s, i + 2, dp);
                    }
                } else {
                    // 两位都是*
                    ans += f2(s, i + 2, dp) * 15;
                }
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }

    // 优化2：用dp数组代替递归
    public static int numDecodings3(String str) {
        char[] s = str.toCharArray() ;
        int n = s.length ;
        long[] dp = new long[n + 1] ;
        dp[n] = 1 ;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                dp[i] = (s[i] == '*' ? 9 : 1) * dp[i + 1];
                if (i + 1 < n) {
                    if (s[i] != '*') {
                        if (s[i + 1] != '*') {
                            if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                                dp[i] += dp[i + 2];
                            }
                        } else {
                            if (s[i] == '1') {
                                dp[i] += dp[i + 2] * 9;
                            }
                            if (s[i] == '2') {
                                dp[i] += dp[i + 2] * 6;
                            }
                        }
                    } else {
                        if (s[i + 1] != '*') {
                            if (s[i + 1] <= '6') {
                                dp[i] += dp[i + 2] * 2;
                            } else {
                                dp[i] += dp[i + 2];
                            }
                        } else {
                            dp[i] += dp[i + 2] * 15;
                        }
                    }
                }
                dp[i] %= mod;
            }
        }
        return (int) dp[0] ;
    }


    // 优化3：用俩变量来回倒
    public static int numDecodings4(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long cur = 0, next = 1, nextNext = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                cur = (s[i] == '*' ? 9 : 1) * next;
                if (i + 1 < n) {
                    if (s[i] != '*') {
                        if (s[i + 1] != '*') {
                            if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                                cur += nextNext;
                            }
                        } else {
                            if (s[i] == '1') {
                                cur += nextNext * 9;
                            }
                            if (s[i] == '2') {
                                cur += nextNext * 6;
                            }
                        }
                    } else {
                        if (s[i + 1] != '*') {
                            if (s[i + 1] <= '6') {
                                cur += nextNext * 2;
                            } else {
                                cur += nextNext;
                            }
                        } else {
                            cur += nextNext * 15;
                        }
                    }
                }
                cur %= mod;
            }
            nextNext = next;
            next = cur;
            cur = 0;
        }
        return (int) next;
    }


}
