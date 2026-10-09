package Graph_图与拓扑排序;

// 火星词典
// 现有一种使用英语字母的火星语言
// 这门语言的字母顺序对你来说是未知的。
// 给你一个来自这种外星语言字典的字符串列表 words
// words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
// 如果这种说法是错误的，并且给出的 words 不能对应任何字母的顺序，则返回 ""
// 否则，返回一个按新语言规则的 字典递增顺序 排序的独特字符串
// 如果有多个解决方案，则返回其中任意一个
// words中的单词一定都是小写英文字母组成的
// 测试链接 : https://leetcode.cn/problems/alien-dictionary/
// 测试链接(不需要会员) : https://leetcode.cn/problems/Jf1JuT/

import javax.print.attribute.standard.RequestingUserName;
import java.util.ArrayList;
import java.util.Arrays;

public class Code04_AlienDictionary {

    public static String alienOrder(String[] words){
        // 创建入度表
        int[] indegree = new int[26] ; // 因为字母表就有26个
        Arrays.fill(indegree,-1); // 初始化入度表中全为-1，当发现有传入的字母时，入度表变为0
        for(String w : words) {
            for (int i = 0; i < w.length(); i++) {
                indegree[w.charAt(i) - 'a'] = 0 ;
            }
        }

        // 邻接表建图
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>() ;
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>()) ;
        }
        // 两个字符串对比
        for (int i = 0 , j , len ; i < words.length - 1 ; i++) {
            String cur = words[i] ;
            String next = words[i+1] ;

            j  = 0 ;
            // 从两个中选出长度最小的字符串
            len = Math.min(cur.length() , next.length()) ;

            for (; j < len ; j++) {
                // 如果不相等，就加入 拓扑排序，同时入度表中的相应位置加加
                if (cur.charAt(j) != next.charAt(j)) {
                    graph.get(cur.charAt(j) - 'a').add(next.charAt(j) - 'a') ;
                    indegree[next.charAt(j) - 'a']++ ;
                    break;
                }
            }
            // 如果 前几位相同的两个字符串，前一个字符串比后一个字符串长度长，那么返回""
            // 例如：["abc","ab"]
            if (j < cur.length() && j == next.length()) {
                return " " ;
            }
        }

        // 创建队列
        int[] queue = new int[26] ;
        int l = 0 , r = 0 ;
        int kinds = 0 ;
        for (int i = 0; i < 26; i++) {
            // 入度表中字母的数量
            if (indegree[i] != -1) {
                kinds ++ ;
            }
            // 入度表为0时，存入队列
            if (indegree[i] == 0) {
                queue[r++] = i ;
            }
        }
        // 将队列中的数据 存入 String
        StringBuilder ans = new StringBuilder() ;
        while (l < r) {
            int cur = queue[l++] ; // 队列弹出一个数
            ans.append((char) (cur + 'a')) ; // + 'a' 还原回之前的数
            // 检查cur中的next，入度表中的next位置是否为0。
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0){
                    queue[r++] = next ;
                }
            }
        }
        return ans.length() == kinds ? ans.toString() : " ";
    }
}
