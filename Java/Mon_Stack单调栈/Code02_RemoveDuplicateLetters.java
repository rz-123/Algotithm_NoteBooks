package Mon_Stack单调栈;

// 去除重复字母保证剩余字符串的字典序最小
// 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次
// 需保证 返回结果的字典序最小
// 要求不能打乱其他字符的相对位置
// 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/

import java.util.Arrays;

public class Code02_RemoveDuplicateLetters {

    public static int MAX = 26 ;

    public static int[] cnts = new int[MAX] ; // 记录字符词频

    public static boolean[] enter = new boolean[MAX] ; // 该字符目前是否进栈

    public static char[] stack = new char[MAX] ;

    public static int r ;

    public static String removeDuplicateLetters(String str){
        r = 0 ; // 栈空
        Arrays.fill(cnts , 0);
        Arrays.fill(enter , false);
        char[] s = str.toCharArray() ; // 字符串转为字符数组
        for (char x : s){
            cnts[x-'a']++ ; // 计算各个字符的词频
        }
        // 从左往右遍历字符
        for (char cur : s) {
            // 如果没进栈，才能进行下边的判断
            if (!enter[cur - 'a']){
                // 1、栈内不为空
                // 2、栈顶元素 大于 即将进栈的元素
                // 3、栈顶元素的该字符的词频不为0 （这里有个点：如果词频为0了，那么该字符的唯一一个就在栈内，就不弹出了，让他在里边，）
                while (r > 0 && stack[r-1] > cur && cnts[stack[r-1] - 'a'] > 0){
                    enter[stack[r-1] - 'a'] = false ; // 栈顶元素弹出，把该字符是否进栈改为false
                    r-- ;
                }
                stack[r++] = cur ; // 压入栈
                enter[cur-'a'] = true ;
            }
            cnts[cur - 'a']-- ; // 该字符词频减减
        }
        // 最后 返回 栈内的元素 0 到 r位置的数 变为字符串返回
        return String.valueOf(stack , 0 , r) ;
    }

}
