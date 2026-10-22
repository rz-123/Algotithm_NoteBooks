package 动态规划.一维动态规划;

// 环绕字符串中唯一的子字符串
// 定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串
// 所以 base 看起来是这样的：
// "..zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd.."
// 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现
// 测试链接 : https://leetcode.cn/problems/unique-substrings-in-wraparound-string/

public class Code07_UniqueSubstringsWraparoundString {

    public static int findSubstringInWraproundString(String str){
        int n = str.length() ;
        int[] s = new int[n] ;
        // 将传入的字符 转成 对应的数字
        for (int i = 0 ; i < n ; i++) {
            s[i] = str.charAt(i) - 'a' ;
        }
        // 例：dp[0]：在s的字符串中以‘a’结尾的子串，根据base串的规则最大延伸长度是多少。
        int[] dp = new int[26] ;


        dp[s[0]] = 1 ; // dp[0] = 1,也就是以'a'字符结尾的长度是1
        for (int i = 1 , cur , pre , len = 1 ; i < n ; i++) {
            cur = s[i] ;  // 当前字符
            pre = s[i-1] ; // 前一个字符
            // 主要判断：如果前一个字符为‘z’，25，并且当前字符是‘a’，0。
            //   或者 前一个字符比当前字符的ascii码少1，就能往前延伸一位
            if ( (pre == 25 && cur == 0) || pre + 1 == cur) {
                len++ ;
            } else {
                // 否则 长度重新归为1
                len = 1 ;
            }
            // 选最大
            dp[cur] = Math.max(dp[cur] , len) ;
        }
        int ans = 0;
        // 最后计算dp中的累加和
        for (int i = 0; i < 26; i++) {
            ans += dp[i];
        }
        return ans;
    }

}
