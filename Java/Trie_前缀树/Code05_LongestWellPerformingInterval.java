package Trie_前缀树;

import class106.HashFunction;

import java.util.HashMap;

// 给一张工作表，大于8小时是劳累的一天，表现良好的时间段是 劳累的天数 要 大于 不劳累的天数
//* 返回 表现良好时间段的最大长度
//* https://leetcode.cn/problems/longest-well-performing-interval/
public class Code05_LongestWellPerformingInterval {

    public static int longestWPI(int[] hours){
        HashMap<Integer,Integer> map = new HashMap<>() ;
        map.put(0,-1);
        int ans = 0;
        for (int i = 0 ,sum=0; i < hours.length; i++) {
            // 大于8 就是劳累 为1 否则就是-1
            sum += hours[i] > 8 ? 1 : -1 ;
            //
            if (sum > 0){
                ans = i + 1 ; // 劳累的天数大于不劳累的天数 ans+1,
            } else {
                // sum <= 0
                // 找出sum-1出现的最早位置
                if (map.containsKey(sum-1)){
                    ans = Math.max(ans,i - map.get(sum-1));
                }
            }
            if (!map.containsKey(sum)){
                map.put(sum ,i ) ;
            }
        }
        return ans ;
    }
}
