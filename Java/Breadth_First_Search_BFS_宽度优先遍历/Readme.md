# 最小生成树：在无向有权图中，在保证连通性的情况下，选出边的总权重最小。

# Code01_AsFarFromLandAsPossible
* // 地图分析
  // 你现在手里有一份大小为 n x n 的 网格 grid
  // 上面的每个 单元格 都用 0 和 1 标记好了其中 0 代表海洋，1 代表陆地。
  // 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的
  // 并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
  // 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：
  // (x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
  // 测试链接 : https://leetcode.cn/problems/as-far-from-land-as-possible/


# Code02_StickersToSpellWord
* // 贴纸拼词
  // 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
  // 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们
  // 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
  // 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1
  // 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的
  // 并且 target 被选择为两个随机单词的连接。
  // 测试链接 : https://leetcode.cn/problems/stickers-to-spell-word/

# Code03_MinimumObstacleRemovalToReachCorner
* // 到达角落需要移除障碍物的最小数目
  // 给你一个下标从 0 开始的二维整数数组 grid ，数组大小为 m x n
  // 每个单元格都是两个值之一：
  // 0 表示一个 空 单元格，
  // 1 表示一个可以移除的 障碍物
  // 你可以向上、下、左、右移动，从一个空单元格移动到另一个空单元格。
  // 现在你需要从左上角 (0, 0) 移动到右下角 (m - 1, n - 1)
  // 返回需要移除的障碍物的最小数目
  // 测试链接 : https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/

# Code04_MinimumCostToMakeAtLeastOneValidPath
* // 使网格图至少有一条有效路径的最小代价
  // 给你一个 m * n 的网格图 grid 。 grid 中每个格子都有一个数字
  // 对应着从该格子出发下一步走的方向。 grid[i][j] 中的数字可能为以下几种情况：
  // 1 ，下一步往右走，也就是你会从 grid[i][j] 走到 grid[i][j + 1]
  // 2 ，下一步往左走，也就是你会从 grid[i][j] 走到 grid[i][j - 1]
  // 3 ，下一步往下走，也就是你会从 grid[i][j] 走到 grid[i + 1][j]
  // 4 ，下一步往上走，也就是你会从 grid[i][j] 走到 grid[i - 1][j]
  // 注意网格图中可能会有 无效数字 ，因为它们可能指向 grid 以外的区域
  // 一开始，你会从最左上角的格子 (0,0) 出发
  // 我们定义一条 有效路径 为从格子 (0,0) 出发，每一步都顺着数字对应方向走
  // 最终在最右下角的格子 (m - 1, n - 1) 结束的路径
  // 有效路径 不需要是最短路径
  // 你可以花费1的代价修改一个格子中的数字，但每个格子中的数字 只能修改一次
  // 请你返回让网格图至少有一条有效路径的最小代价
  // 测试链接 : https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/

# Code05_TrappingRainWaterII
* // 二维接雨水
  // 给你一个 m * n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度
  // 请计算图中形状最多能接多少体积的雨水。
  // 测试链接 : https://leetcode.cn/problems/trapping-rain-water-ii/

# Code06_WordLadderII
* // 单词接龙 II
  // 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化
  // 一个表示此过程的 转换序列 是形式上像
  // beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
  // 每对相邻的单词之间仅有单个字母不同
  // 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词
  // 注意，beginWord 不必是字典 wordList 中的单词
  // sk == endWord
  // 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList
  // 请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列
  // 如果不存在这样的转换序列，返回一个空列表
  // 每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回
  // 测试链接 : https://leetcode.cn/problems/word-ladder-ii/













