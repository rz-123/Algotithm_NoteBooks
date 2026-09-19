package Sliding_Window滑动窗口;

// 最小覆盖子串
// 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串
// 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
// 测试链接 : https://leetcode.cn/problems/minimum-window-substring/

public class Code03_MinimumWindowSubstring {


    public static String minWindow(String str , String  tar){
        char[] s = str.toCharArray();
        char[] t = tar.toCharArray(); // 子串，注意是 tar 不是 str
        int[] cnt = new int[256] ; // 欠债表
        for (char cha : t){ // 表中的字符最开始全是负数
            cnt[cha]-- ;  // 子串中的每个字符有几个就表示负几。
        }
        int debt = t.length ; // 子串的个数
        int len = Integer.MAX_VALUE ; // 最终最小覆盖子串的长度
        int start = 0  ;   // 子串 开始的位置

        // 窗口滑动
        for (int l = 0 , r = 0 ; r < s.length; r++) {
            // 当r一直往右走时，
            // 判断该字符在欠债表中的数值是否小于0，
            // 因为子串中的元素在表中的数值都是负数，
            // 所以 如果该字符在表中<0，那么就说明该窗口覆盖了一个子串中的元素
            // 所以 子串的个数debt-1。
            // cnt[s[r]]++ 这个++ 是每次都加，没有小于0的判断条件限制
            // 所以cnt[s[r]]当>0时;
            // // 表示该字符子串里没有或者窗口中该字符的个数超过了子串中的该元素的个数，多余的字符，
            if (cnt[s[r]]++ < 0 ){
                debt--;
            }
            // 当子串的个数为0，说明该窗口中已经满足了包含t的子串
            if (debt == 0){
                // 满足条件后，当左边界的字符在欠债表中还大于0，说明这个多余了
                // 表中--，左边界向后移
                while (cnt[s[l]] > 0) {
                    cnt[s[l]] -- ;
                    l++ ;
                    // cnt[s[l++]]-- ;
                }
                //
                if (r-l+1 < len) {
                    len = r - l + 1 ; // 更新len
                    start = l ; // 更新start
                }
            }
        }
        return len == Integer.MAX_VALUE ? " " : str.substring(start , start+len) ;
    }
}
