# 单调栈

# Code01_LeftRightLess
* 给定一组可能重复的数值，找到每一个i位置的左边跟右边离i位置最近且比arr[i]要小的位置
* 如果没有小的数 用-1表示，单调栈：小压大
* https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb

# Code02_DailyTemperatures
* 给一个每天的温度整数数组，找到对于第i天，下一个更高的温度出现在几天后。大压小
* https://leetcode.cn/problems/daily-temperatures/

# Code03_SumOfSubarrayMinimums
* 给一个整数数组，找到min(b)的总和，b的范围为arr的每个连续子数组。
* 就是拆成若干个连续子数组，求各个子数组中的min，然后加起来。单调栈：大压小
* https://leetcode.cn/problems/sum-of-subarray-minimums/

# Code04_LargestRectangleInHistogram
* 给定n个整数，表示柱子的高度，求这n个柱子能勾勒出来矩形 的最大面积是多少。
* 找最大，单调栈：大压小
* https://leetcode.cn/problems/largest-rectangle-in-histogram

# Code05_MaximalRectangle
* 最大矩形，给定一个只包含0和1   说的二维矩阵，求只包含1的最大矩阵面积
* 压缩数组（第0行为为底有1的最大面积，第1行为底有1的最大面积...），然后用第4题那个求柱子面积做
* https://leetcode.cn/problems/maximal-rectangle/


# Code01_MaximumWidthRamp
* 最大宽度坡，给定一个整数数组arr，坡是arr[i,j] ,必须 i < j 并且 arr[i] <= arr[j]。
* 坡的宽度为j-i,求数组中最大宽度。如果没有 返回0。单调栈：小压大
* https://leetcode.cn/problems/maximum-width-ramp/

# Code02_RemoveDuplicateLetters
* 给定一组字符串，要求取出重复字母，让每个字母只出现一次，不能打乱其他字符的相对位置。
* https://leetcode.cn/problems/remove-duplicate-letters/

# Code03_BigFishEatSmallFish
* 大鱼吃小鱼问题：给定一个体重数组，每轮每个数只能吃掉左边比它小的鱼，
* 吃鱼的行为是同时发生，B吃掉C的同时，B也有可能被A吃掉。
* 问吃多少轮后，鱼的数量就固定了
* https://leetcode.cn/problems/steps-to-make-array-non-decreasing/
* https://www.nowcoder.com/practice/77199defc4b74b24b8ebf6244e1793de

# Code04_CountSubmatricesWithAllOnes
* 统计全一矩形的数量，给定一个只有0跟1的m*矩形，返回有多少个子矩形的元素全是1
* https://leetcode.cn/problems/count-submatrices-with-all-ones/




