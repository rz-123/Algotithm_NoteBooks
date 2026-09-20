package Two_Pointers双指针;

// 盛最多水的容器
// 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
// 返回容器可以储存的最大水量
// 说明：你不能倾斜容器
// 测试链接 : https://leetcode.cn/problems/container-with-most-water/

public class Code05_ContainerWithMostWater {

    // 左右指针 向中间走 , 哪边矮 就与 高的位置下标减去矮的位置下标 相乘，最后取最大
    public static int maxArea(int[] height){
        int ans = 0 ;
        for (int l = 0 , r = height.length - 1 ; l < r ;){
            // 左右指针选出最小值与r-l相乘，然后更新ans
         ans = Math.max(ans , Math.min(height[l] , height[r]) * (r-l)) ;
         // 谁小 谁走一位
         if (height[l] <= height[r]){
             l++ ;
         } else {
             r-- ;
         }
        }
        return ans ;
    }

}
