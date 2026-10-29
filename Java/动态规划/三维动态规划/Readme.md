# 三维动态规划

# Code01_OnesAndZeroes
* // 一和零(多维费用背包)
  // 给你一个二进制字符串数组 strs 和两个整数 m 和 n
  // 请你找出并返回 strs 的最大子集的长度
  // 该子集中 最多 有 m 个 0 和 n 个 1
  // 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集
  // 测试链接 : https://leetcode.cn/problems/ones-and-zeroes/

# Code02_ProfitableSchemes
* // 盈利计划(多维费用背包)
  // 集团里有 n 名员工，他们可以完成各种各样的工作创造利润
  // 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与
  // 如果成员参与了其中一项工作，就不能参与另一项工作
  // 工作的任何至少产生 minProfit 利润的子集称为 盈利计划
  // 并且工作的成员总数最多为 n
  // 有多少种计划可以选择？因为答案很大，答案对 1000000007 取模
  // 测试链接 : https://leetcode.cn/problems/profitable-schemes/

# Code03_KnightProbabilityInChessboard
* // 骑士在棋盘上的概率
  // n * n的国际象棋棋盘上，一个骑士从单元格(row, col)开始，并尝试进行 k 次移动
  // 行和列从0开始，所以左上单元格是 (0,0)，右下单元格是 (n-1, n-1)
  // 象棋骑士有8种可能的走法。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格
  // 每次骑士要移动时，它都会随机从8种可能的移动中选择一种，然后移动到那里
  // 骑士继续移动，直到它走了 k 步或离开了棋盘
  // 返回 骑士在棋盘停止移动后仍留在棋盘上的概率
  // 测试链接 : https://leetcode.cn/problems/knight-probability-in-chessboard/

# Code04_PathsDivisibleByK
* // 矩阵中和能被 K 整除的路径
  // 给一个下标从0开始的 n * m 整数矩阵 grid 和一个整数 k
  // 从起点(0,0)出发，每步只能往下或者往右，你想要到达终点(m-1, n-1)
  // 请你返回路径和能被 k 整除的路径数目
  // 答案对 1000000007 取模
  // 测试链接 : https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k/

# Code05_ScrambleString
* // 扰乱字符串
  // 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
  // 步骤1 : 如果字符串的长度为 1 ，算法停止
  // 步骤2 : 如果字符串的长度 > 1 ，执行下述步骤：
  //        在一个随机下标处将字符串分割成两个非空的子字符串
  //        已知字符串s，则可以将其分成两个子字符串x和y且满足s=x+y
  //        可以决定是要 交换两个子字符串 还是要 保持这两个子字符串的顺序不变
  //        即s可能是 s = x + y 或者 s = y + x
  //        在x和y这两个子字符串上继续从步骤1开始递归执行此算法
  // 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串
  // 如果是，返回true ；否则，返回false
  // 测试链接 : https://leetcode.cn/problems/scramble-string/

#
*
