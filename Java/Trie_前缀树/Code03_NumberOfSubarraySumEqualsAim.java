package Trie_前缀树;


import class106.HashFunction;

import java.util.HashMap;

// 返回无序数组中累加和为给定值的子数组个数
// 测试链接 : https://leetcode.cn/problems/subarray-sum-equals-k/
public class Code03_NumberOfSubarraySumEqualsAim {


    public static int subarraySum(int[] nums , int aim){
        HashMap<Integer , Integer> map = new HashMap<>() ;
        map.put(0,1) ;
        int ans = 0 ;
        for (int i = 0 , sum = 0; i < nums.length; i++) {
            sum += nums[i] ; // 计算前缀和
            // 求 sum-aim累加和出现过几次
            ans += map.getOrDefault(sum - aim , 0) ;
            // 在map中如果有sum 就把value+1，如果没有就放进去value 为0+1
            map.put(sum , map.getOrDefault(sum , 0) + 1) ;
        }
        return ans ;
    }
}
