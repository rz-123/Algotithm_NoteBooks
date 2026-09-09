package Recursion递归;

import java.util.ArrayList;
import java.util.List;

// 返回没有重复值数组的全部排列。时间复杂度O(n! * n)
// https://leetcode.cn/problems/permutations/description/
public class Code03_Permutations {

    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> ans = new ArrayList<>() ;
        f(nums , 0 , ans) ;
        return ans ;
    }
    public static void f(int[] nums , int i , List<List<Integer>> ans){
        // 设置终止条件
        if (i == nums.length) {
            List<Integer> cur = new ArrayList<>() ;
            for (int num : nums) {
                cur.add(num) ;
            }
            ans.add(cur) ;
        } else {
            for (int j = i ; j < nums.length ; j++){ // j往后移
                swap(nums , i , j); // 俩数交换
                f(nums , i+1 , ans);  // 递归进行存储
                swap(nums,i,j); // 完成之后回到原样
            }
        }
    }
    public static void swap(int[] nums , int x , int y){
        int p = nums[x] ;
        nums[x] = nums[y] ;
        nums[y] = p ;
    }
}
