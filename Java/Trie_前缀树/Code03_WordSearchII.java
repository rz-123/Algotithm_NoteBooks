package Trie_前缀树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;

// * 在二维字符数组中搜索可能的单词，
//* 给一个二维字符网格，一个单词列表，返回网格上所有的单词.
//* 同一个单元格内的字母在一个单词中不允许被重复使用。
//* https://leetcode.cn/problems/word-search-ii/
public class Code03_WordSearchII {

    // 用前缀树剪枝
    // board 二维数组    words 单词数组
    public static List<String> findWords(char[][] board , String[] words){
        build(words); // 单词数组构建前缀树
        List<String> ans = new ArrayList<>() ; // 最终返回的字符串列表
        // i行 j列 都得遍历到
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board , i , j , 1 , ans) ;
            }
        }
        clear();
        return ans;
    }

    //  传入：二维表格，当前来到i行j列，前缀树节点编号t，收集字符串ans
    public static int dfs(char[] [] board , int i , int j , int t , List<String> ans){
        // 判断边界，如果越界或者都回头路，直接返回0
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == 0) {
            return 0 ;
        }
        // 如果没越界，就用tmp记录当前字符，方便还原
        char tmp = board[i][j] ;
        int road = tmp - 'a' ; // 查看路的编号
        t = tree[t][road] ; // 来到此节点
        if (pass[t] == 0) {
            return 0;  // 证明没有路，或者已经采集过
        }
        int fix = 0 ;  // 收集了几个字符串
        if (end[t] != null) {
            fix ++ ;
            ans.add(end[t]) ; // 将end加到ans中
            end[t] = null ; // 将end[t]置为空
        }
        board[i][j] = 0 ; // 把该位置的字符改成0,后边就不来了
        fix += dfs(board , i - 1 , j , t , ans) ;
        fix += dfs(board , i + 1 , j , t , ans) ;
        fix += dfs(board , i , j -1 , t , ans) ;
        fix += dfs(board , i  , j + 1 , t , ans) ;
        pass[t] -= fix ; // pass减去fix
        board[i][j] = tmp ; // 还原
        return fix ;
    }

    public static int MAX = 10001 ;
    public static int[] [] tree = new int[MAX][26] ;
    public static int[] pass = new int[MAX] ;
    public static String[] end = new String[MAX] ;
    public static int cnt ;
    public static void build(String[] words){
        cnt = 1 ;
        for (String word : words) {
            int cur = 1;
            for (int i = 0 , path; i < word.length(); i++) {
                path = word.charAt(i) - 'a' ;
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path] ;
                pass[cur] ++ ;
            }
            end[cur] = word ;  // 结束节点存入单词
        }
    }
    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = null;
        }
    }
}
