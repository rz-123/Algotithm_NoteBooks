package Trie_前缀树;

import java.util.HashMap;

// https://leetcode.cn/problems/make-sum-divisible-by-p/
// 使数组和能被P整除
// 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空）
// 使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
// 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
// 子数组 定义为原数组中连续的一组元素。
public class Code06_MakeSumDivisibleByP {

    public static int minSubarray(int[] nums , int p){
        int mod = 0 ; // 整体余数
        for (int num : nums){
            mod = (mod + num) % p ;
        }
        if (mod == 0){
            return 0 ;
        }
        // key:前缀和%p的余数，value:最晚出现的位置（因为要求返回最短）
        HashMap<Integer,Integer> map = new HashMap<>() ;
        map.put(0,-1);
        int ans = Integer.MAX_VALUE ;
        for (int i = 0 , cur = 0 , find; i < nums.length; i++) {
            cur = (cur + nums[i]) % p ; // 0-i前缀和的余数
            find = cur >= mod ? (cur - mod) : (cur + p - mod);
            // 在map中寻找find，最晚出现的位置
            if (map.containsKey(find)){
                ans = Math.min(ans , i - map.get(find)) ;
            }
            map.put(cur , i) ;
        }
        return ans == nums.length ? -1 : ans ;
    }

}
