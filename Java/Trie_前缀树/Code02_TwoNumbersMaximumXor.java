package Trie_前缀树;

import java.util.HashSet;

// 返回数组中的两个数的最大的异或值
// https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/
public class Code02_TwoNumbersMaximumXor {

    // 前缀树实现
    // 主方法
    public static int findMaximumXOR1(int[] nums){
        build(nums) ; // 用nums构建前缀树
        int ans = 0 ;
        for (int num : nums){
            ans = Math.max(ans , maxXor(num)) ;
        }
        clear();
        return ans;
    }
    public static int MAXN = 3000001;

    public static int[][] tree = new int[MAXN][2];

    // 前缀树目前使用了多少空间
    public static int cnt;

    // 数字只需要从哪一位开始考虑
    public static int high;

    // 找最大值
    public static void build(int[] nums){
        cnt = 1;
        int max = Integer.MIN_VALUE ;
        // 找出数组中的最大值，查看有多少个前缀0，然后直接忽略，
        // 从31-最大值的前缀0开始异或
        for (int num : nums){
            max = Math.max(num,max) ;
        }
        high = 31 - Integer.numberOfLeadingZeros(max) ;
        for (int num : nums) {
            insert(num) ;
        }
    }

    // 构建 前缀树
    public static void insert(int num) {
        int cur = 1;
        for (int i = high, path; i >= 0; i--) {
            path = (num >> i) & 1;  // nums第i位的状态，
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
        }
    }

    // 异或操作方法
    public static int maxXor(int num){
        int ans = 0 ; // 最终返回的最大的异或结果
        int cur = 1 ; // 节点编号
        for (int i = high,ststus , want; i >= 0 ; i--) {
            ststus = (num >> i) & 1 ; // nums第i位的状态
            want = ststus ^ 1 ; // 希望遇到的状态
            // 查看前缀树中该位置是否有值
            if (tree[cur][want] == 0){
                want ^= 1 ; // 如果没有，就回到status状态
            }
            ans |= (ststus ^ want) << i ; // 如果有值，就异或，左移i位，或进ans中
            cur = tree[cur][want] ;
        }
        return ans ;
    }
    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            tree[i][0] = tree[i][1] = 0;
        }
    }


    // 哈希表实现
    public int findMaximumXOR2(int[] nums){
        int max = Integer.MIN_VALUE ;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int ans = 0 ; // 最终返回值
        HashSet<Integer> set = new HashSet<>() ;
        for (int i = 31 - Integer.numberOfLeadingZeros(max); i >= 0 ; i--) {
            int better = ans | (1 << i) ; // 希望达成的目标
            set.clear(); // 清空哈希表
            for (int num : nums){
                // 31-i位置的状态保留，其余都为0
                num = (num >> i) << i;
                set.add(num) ;
                // 直到 哈希表中存在better^num的结果，赋值给ans，结束本次循环
                if (set.contains(better ^ num)){
                    ans = better ;
                    break;
                }
            }
        }
        return ans ;
    }
}
