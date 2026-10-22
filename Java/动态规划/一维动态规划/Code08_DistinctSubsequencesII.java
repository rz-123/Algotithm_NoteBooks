package 动态规划.一维动态规划;

// 不同的子序列 II
// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
// 因为结果可能很大，答案对 1000000007 取模
// 字符串的 子序列 是经由原字符串删除一些（也可能不删除）
// 字符但不改变剩余字符相对位置的一个新字符串
// 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是
// 测试链接 : https://leetcode.cn/problems/distinct-subsequences-ii/

public class Code08_DistinctSubsequencesII {

    public static int distinctSubseqII( String s ){
        int mod = 1000000007;
        char[] str = s.toCharArray() ;
        int[] cnt = new int[26] ;
        int all = 1 , newAdd ; // newAdd：纯新增
        // 将传进来的字符 挨个试
        for (char x : str) {
            // all - 当前字符的上一次记录  得到  纯新增
            // +mod 然后 模除mod，确保newAdd是正数
            newAdd = (all - cnt[x-'a'] + mod) % mod ;
            // 当前字符的记录
            cnt[x-'a'] = (cnt[x-'a'] + newAdd) % mod ;
            // 得到all
            all = (all + newAdd) % mod ;
        }
        return (all - 1 + mod) % mod ; // 因为最开始有一个空字符串，所以要减1

    }



}
