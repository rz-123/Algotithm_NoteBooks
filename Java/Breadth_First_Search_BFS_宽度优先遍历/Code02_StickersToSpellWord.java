package Breadth_First_Search_BFS_宽度优先遍历;

// 贴纸拼词
// 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
// 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们
// 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
// 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1
// 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的
// 并且 target 被选择为两个随机单词的连接。
// 测试链接 : https://leetcode.cn/problems/stickers-to-spell-word/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Code02_StickersToSpellWord {

    public static int MAXN = 401 ;

    public static String[] queue = new String[MAXN] ;

    public static int l , r ;

    // 建图 存贴纸
    public static ArrayList<ArrayList<String>> graph = new ArrayList<>() ;
    static {
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>()) ;
        }
    }

    public static HashSet<String> visited = new HashSet<>() ;

    public static int minStickers(String[] stickers , String target){
        // 图清空，标识清空
        for (int i = 0; i < 26; i++) {
            graph.get(i).clear();
        }
        visited.clear();
        for (String str : stickers) {
            str = sort(str) ; // 贴纸字符串数组 排序
            for (int i = 0 ; i < str.length() ; i++) {
                // 如果 i位置的字符 不等于 i-1 的字符 ，那么就加入图
                // 比如“aaabb”，那么就在小标为a的位置加入一次，下标为b的位置加入一次
                if (i == 0 || str.charAt(i) != str.charAt(i - 1)) {
                    graph.get(str.charAt(i) - 'a').add(str) ;
                }
            }
        }
        // 目标字符串 排序
        target = sort(target) ;
        visited.add(target) ;

        l = r = 0 ;
        queue[r++] = target ; // 目标字符串加入队列
        int level = 1 ;

        // 队列 整层弹出
        while ( l < r ) {
            int size = r-l; // 计算队列中的个数
            // 弹出size个
            for (int i = 0; i < size; i++) {
                String cur = queue[l++]; // 弹出队列头的字符串

                // 在图中找到该位置，然后，用目标字符串与图中的贴纸进行对比
                for (String s : graph.get(cur.charAt(0) - 'a')) {
                    String next = next(cur , s) ;
                    // 如果 next方法返回“”, 就说明能全部贴完
                    if (next.equals("")) {
                        return level ; // 返回的层数 就是将用贴纸拼出目标单词的最小贴纸数量
                    }// 否则 如果 返回的字符串没有加入队列，那么将它加入队列
                    else if (! visited.contains(next)){
                        visited.add(next) ;
                        queue[r++] = next ;
                    }
                }
            }
            level ++ ; // 每一层遍历完成后，将层数++
        }
        return -1 ;
    }


    // 将字符串排序
    public static String sort(String str){
        char[] s = str.toCharArray() ; // 转成字符
        Arrays.sort(s); // 排序
        return String.valueOf(s) ; // 排完序后，转成字符串 返回
    }

    // 两个字符串对比 t为目标字符串，s为贴纸
    public static String next(String t , String s){
        StringBuilder builder = new StringBuilder() ;
        //
        for ( int i = 0 , j = 0 ; i < t.length() ; ) {
            // 如果 j 的长度等于贴纸的长度，那么就把目标字符串存入builder
            if ( j == s.length()) {
                builder.append(t.charAt(i++)) ;
            } else {
                if (t.charAt(i) < s.charAt(j)) {
                    // 如果t字符串的i位置的字符 小于 s字符串的j位置的字符
                    // 那么就代表 s字符串的j位置的字符贴不了t字符串
                    // 所以 将 t字符串的i位置的字符加入到builder中 最后返回
                    builder.append(t.charAt(i++)) ;
                } else if (t.charAt(i) > s.charAt(j)) {
                    j++ ;
                } else {
                    i++ ;
                    j++ ;
                }
            }
        }
        return builder.toString() ; // 返回对比后的字符串
    }





}
