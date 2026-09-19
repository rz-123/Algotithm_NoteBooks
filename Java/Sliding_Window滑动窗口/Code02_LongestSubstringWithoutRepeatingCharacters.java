package Sliding_Window滑动窗口;

import java.util.Arrays;

// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
// https://leetcode.cn/problems/longest-substring-without-repeating-characters/
public class Code02_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String string){
        char[] chars = string.toCharArray() ; // 字符串变字符数组
        int n = chars.length ; // 长度
        // char 转化为 int 范围是0-255
        int[] last = new int[256] ; // 每种字符上次出现的位置
        // 把last数组初始化为-1，因为后边要跟l比最大值，
        // 如果之前没有出现过那么位置就是-1，-1+1 = 0 ，不干扰最大值
        Arrays.fill(last , -1);
        int ans = 0 ;
        for (int l = 0 , r = 0 ; r < n; r++) {
            // 当r的值在之前出现过就将l移到之前出现的位置加1的位置
            l = Math.max(l , last[chars[r]] + 1) ;
            // 不断更新ans
            ans = Math.max(ans , r - l + 1) ;
            // 更新当前字符的位置
            last[chars[r]] = r ;
        }
        return ans ;
    }


}
