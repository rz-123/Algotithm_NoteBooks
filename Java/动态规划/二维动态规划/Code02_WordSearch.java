package 动态规划.二维动态规划;

// 单词搜索（无法改成动态规划）
// 给定一个 m x n 二维字符网格 board 和一个字符串单词 word
// 如果 word 存在于网格中，返回 true ；否则，返回 false 。
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成
// 其中"相邻"单元格是那些水平相邻或垂直相邻的单元格
// 同一个单元格内的字母不允许被重复使用
// 测试链接 : https://leetcode.cn/problems/word-search/

public class Code02_WordSearch {

    //
    public static boolean exist(char[][] board , String word){
        // 将字符串转换为字符数组
        char[] w = word.toCharArray() ;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 递归：问 传进 一个 二维数组，字符位置i和j,w为单词的字符数组，从w[0]开始走
                if (f(board , i , j , w , 0)){
                    return true ;
                }
            }
        }
        return false ;
    }
    // 传进 一个 二维数组，字符位置i和j,w为单词的字符数组，从w[0]开始走
    // 主要带路径，不能重复走
    public static boolean f(char[][] b , int i , int j , char[] w , int k){
        // 如果 k 等于 w的长度，说明走到了返回true
        if (k == w.length) {
            return true ;
        }
        // 如果 i和j为负数，越界，或者 此时所在的位置上的值 跟 w数组中位置上的值不相等，直接返回false，走错了
        if (i < 0 || i == b.length || j < 0 || j == b[0].length || b[i][j] != w[k]) {
            return false ;
        }
        // 临时变量 tmp 记录临时值
        char tmp = b[i][j] ;
        b[i][j] = 0 ; // 走过的设为0，防止重复走

        // 四周递归找答案
        boolean ans = f(b,i-1,j,w,k+1) || f(b,i+1,j,w,k+1) || f(b,i,j-1,w,k+1) || f(b,i,j+1,w,k+1);
        b[i][j] = tmp ; // 还原
        return ans ;
    }




}
