package Trie_前缀树;

// 快速计算区域累加和,给定左右边界快速计算和
// https://leetcode.cn/problems/range-sum-query-immutable/
public class Code01_PrefixSumArray {

    class NumArray{
        public int[] sum ;
        // 计算前缀和
        public NumArray(int[] nums){
            sum = new int[nums.length + 1] ; // 存储前缀和的数组
            for (int i = 1; i <= nums.length; i++) {
                sum[i] = sum[i-1] + nums[i-1] ;  //所以 sum的下标是从1开始的
            }
        }
        public int sumRange(int l , int r){
            return sum[r+1] - sum[l] ;
        }
    }

}
