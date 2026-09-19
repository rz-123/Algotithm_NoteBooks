package Sliding_Window滑动窗口;


import java.util.Arrays;

// 至少有K个重复字符的最长子串
// 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串
// 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度
// 如果不存在这样的子字符串，则返回 0。
// 测试链接 : https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/
public class Code07_LongestSubstringWithAtLeastKRepeating {

    public static int longestSubstring(String string , int k){
        char[] s = string.toCharArray() ;
        int n = s.length ;
        int[] cnts = new int[256] ;

        int ans = 0 ;

        // 枚举从1种字符一直到26种字符，每种字符都必须>=k次，这样的最长子串是多长
        for (int i = 1; i < 26; i++) {
            Arrays.fill(cnts , 0);  // cnts数组全部置为0

            // collect: 目前窗口中一共收集的种类数
            // satisf : 窗口中达标的种类数（>=k个）
            for (int l = 0 , r = 0 , collect = 0 , satisfy = 0 ; r < n; r++) {
                cnts[s[r]] ++ ;  // 开始 右边界的字符在cnts中的位置加1
                //  如果 cnts中的字符的词频等于1，那么种类加1
                if (cnts[s[r]] == 1){
                    collect ++ ;
                }
                // 如果 cnts中字符的词频等于k，那么达标的种类数+1
                if (cnts[s[r]] == k) {
                    satisfy ++ ;
                }
                // 如果 l-r位置上的种类数超了，那就l++
                while (collect > i) {
                    // 如果该字符的词频等于1，那么种类数--
                    if (cnts[s[l]] == 1){
                        collect -- ;
                    }
                    // 如果该字符的词频等于k , 那么 达标的--
                    if (cnts[s[l]] == k){
                        satisfy-- ;
                    }
                    // 该字符的词频-- ，然后 L后移一位
                    cnts[s[l++]]-- ;
                }
                // 当种类达标数 跟 字符数相等时，更新ans 选最大
                if (satisfy == i){
                    ans = Math.max(ans , r-l+1) ;
                }
            }
        }
        return ans ;
    }
}
