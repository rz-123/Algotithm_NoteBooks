package Trie_前缀树;


import java.util.Arrays;

// 每个元音包含偶数次的最长子字符串
// 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度
// 每个元音字母，即 'a'，'e'，'i'，'o'，'u'
// 在子字符串中都恰好出现了偶数次。
// 测试链接 : https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts/
public class Code07_EvenCountsLongestSubarray {

    public static int findTheLongestSubstring(String s){
        int n = s.length() ;
        int [] map = new int[32] ; // 32个字符的字符串
        Arrays.fill(map,-2); // 从没出现过元音字符
        map[0] = -1 ;
        int ans = 0 ;
        // status：0-i上的元音字母奇偶性
        for (int i = 0 , status = 0 ,m ; i < n ; i++){
            m = move(s.charAt(i)) ;
            if (m != -1) { // m == -1 表示非元音字母，不参与奇偶统计
                status ^= 1 << m;  // 只把 status 的第 m 位取反（0→1，1→0）
            }
            if (map[status] != -2){
                ans = Math.max(ans , i - map[status]) ;
            } else {
                map[status] = i ;
            }
        }
        return ans ;
    }


    public static int move(char cha) {
        switch (cha) {
            case 'a': return 0;
            case 'e': return 1;
            case 'i': return 2;
            case 'o': return 3;
            case 'u': return 4;
            default: return -1;
        }
    }

}
