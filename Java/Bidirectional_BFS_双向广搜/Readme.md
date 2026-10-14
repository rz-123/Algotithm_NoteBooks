# 双向广搜，A-Z的最短距离，A和 Z 都作为起点共同搜索

# Code01_WordLadder
* // 单词接龙
  // 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列
  // 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk ：
  // 每一对相邻的单词只差一个字母。
  // 对于 1 <= i <= k 时，每个 si 都在 wordList 中
  // 注意， beginWord 不需要在 wordList 中。sk == endWord
  // 给你两个单词 beginWord 和 endWord 和一个字典 wordList
  // 返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目
  // 如果不存在这样的转换序列，返回 0 。
  // 测试链接 : https://leetcode.cn/problems/word-ladder/


# Code02_SnacksWaysBuyTickets
* / 牛牛的背包问题 & 世界冰球锦标赛
  // 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w。
  // 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
  // 牛牛想知道在总体积不超过背包容量的情况下,他一共有多少种零食放法(总体积为0也算一种放法)。
  // 输入描述：
  // 输入包括两行
  // 第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量
  // 第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积
  // 输出描述：
  // 输出一个正整数, 表示牛牛一共有多少种零食放法。
  // 测试链接 : https://www.luogu.com.cn/problem/P4799

# Code03_ClosestSubsequenceSum
* // 最接近目标值的子序列和
  // 给你一个整数数组 nums 和一个目标值 goal
  // 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal
  // 也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal)
  // 返回 abs(sum - goal) 可能的 最小值
  // 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
  // 数据量描述:
  // 1 <= nums.length <= 40
  // -10^7 <= nums[i] <= 10^7
  // -10^9 <= goal <= 10^9
  // 测试链接 : https://leetcode.cn/problems/closest-subsequence-sum/

# Code04_PartitionMinimizeDifference
* // 将数组分成两个数组并最小化数组和的差
  // 这道题课上没讲，其实就是题目3加上了选取数字个数的限制
  // 比如一共30个数，分配到两个部分
  // 要求每个部分都是15个数，并且做到累加和差值最小
  // 那么就是题目3的代码，加上个数的考虑就可以，以下代码提交可以直接通过
  // 测试链接 : https://leetcode.cn/problems/partition-array-into-two-arrays-to-minimize-sum-difference/




