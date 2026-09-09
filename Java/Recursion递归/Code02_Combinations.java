package Recursion递归;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// 返回数组的所有组合，可以无视元素顺序,不能重复。时间复杂度O(2^n * n)
// https://leetcode.cn/problems/subsets-ii/description/
public class Code02_Combinations {

    public static List<List<Integer>> subsetsWithDup(int[] nums){
        List<List<Integer>> ans = new ArrayList<>() ;
        Arrays.sort(nums); // 排序
        f(nums , 0 , new int[nums.length] , 0 ,ans) ;
        return ans ;
    }

    // i:进行到第几个字符了；size是path的下标，控制加入ans中字符的数量
    public static void f(int[] nums , int i , int[] path , int size , List<List<Integer>> ans){
        if (i == nums.length){
            ArrayList<Integer> cur = new ArrayList<>() ;
            // 将 历史记录加到cur列表中
            for (int j = 0; j < size; j++) {
                cur.add(path[j]) ;
            }
            ans.add(cur) ; // 将cur加到ans
        } else {
            int j = i + 1 ;
            while(j < nums.length && nums[i] == nums[j]){
                j++ ;  // 寻找下一组数开始的位置
            }
            // 递归
            // 先要0个数
            f(nums , j , path , size , ans);
            // 后边 size每次加加
            for ( ; i < j ; i++){
                path[size++] = nums[i] ;
                f(nums , j , path , size , ans) ;
            }
        }
    }
}
