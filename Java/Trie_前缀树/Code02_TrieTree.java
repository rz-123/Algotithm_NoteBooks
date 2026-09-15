package Trie_前缀树;

import java.util.Arrays;

// 静态数组实现前缀树（二维数组）
public class Code02_TrieTree {
    public static int MAXN = 150001 ;
    public static int[][] tree = new int[MAXN][26];
    public static int[] end = new int[MAXN];
    public static int[] pass = new int[MAXN];
    public static int cnt;  //
    public static void build(){
        cnt = 1 ; // 相当于初始化头结点
    }

    // 在前缀树中增加字符串
    public static void insert(String word){
        int cur = 1 ; // 节点
        pass[cur] ++ ; // pass先加1
        for (int i = 0,path; i < word.length(); i++) {
            path = word.charAt(i) - 'a' ;
            // 如果tree中的位置为0，那么cnt+1赋值给该位置
            // [cur]:控制着哪个节点；[path]控制走哪条路
            if (tree[cur][path] == 0){
                tree[cur][path] = ++cnt ;  // 先将cnt加1再赋值给tree
            }
            cur = tree[cur][path]; // 移到下一个节点
            pass[cur]++ ;
        }
        end[cur]++; // 最后 end中加1
    }


    // 在前缀树中删除字符串
    public static void del(String word){
        if (search(word) > 0){
            int cur = 1;
            pass[cur]-- ;

            for (int i = 0 , path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                // 如果tree[cur][path]的pass为1，先--等于0 ，就直接返回
                if (-- pass[tree[cur][path]] == 0) {
                    tree[cur][path] = 0 ;
                    return;
                }
                cur = tree[cur][path] ;
            }
            end[cur]--; // 最后end--
        }
    }



    // 在前缀树中查看有多少以pre字符串为前缀的字符串
    public static int prefixNumber(String pre){
        int cur = 1 ;
        for (int i = 0 , path; i < pre.length(); i++) {
            path = pre.charAt(i) - 'a' ;
            if (tree[cur][path] == 0){
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    // 查看前缀树中的word字符串出现了多少次
    public static int search(String word){
        int cur = 1 ;
        for (int i = 0,path; i < word.length(); i++) {
            path = word.charAt(i) - 'a' ;
            if (tree[cur][path] == 0){
                return 0 ;
            }
            cur = tree[cur][path];
        }
        return end[cur];  // 返回end的值
    }
    // 前缀树清空
    public static void clear(){
        // 全部置为0
        for (int i = 0; i <= cnt ; i++) {
            Arrays.fill(tree[i] , 0);
            end[i] = 0;
            pass[i] = 0;
        }
    }



}
