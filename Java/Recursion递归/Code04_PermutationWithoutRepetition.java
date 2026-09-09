package Recursion递归;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// 返回可能有重复值数组的全部排列，排列要求去重。时间复杂度O(n! * n)
// https://leetcode.cn/problems/permutations-ii/
public class Code04_PermutationWithoutRepetition {

    public static List<List<Integer>> permuteUnique(int[] nums){
        List<List<Integer>> ans = new ArrayList<>() ;
        f(nums , 0 , ans);
        return ans;
    }

    public static void f(int[] nums , int i , List<List<Integer>> ans){
        if (i == nums.length){
            List<Integer> cur = new ArrayList<>() ;
            for ( int num : nums) {
                cur.add(num) ;
            }
            ans.add(cur) ;
        } else {
            HashSet<Integer> set = new HashSet<>() ;
            for (int j = i ; j < nums.length ; j++){
                if (!set.contains(nums[j])){
                    set.add(nums[j]);
                    swap(nums,i,j);
                    f(nums,i+1,ans);
                    swap(nums,i,j);
                }
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }



}
