# 最小生成树：在无向有权图中，在保证连通性的情况下，选出边的总权重最小。

# Code01_Kruskal
* // Kruskal算法模版（洛谷）
  // 静态空间实现 
* // 测试链接 : https://www.luogu.com.cn/problem/P3366

# Code02_PrimDynamic
* // 动态空间实现 
* // 测试链接 : https://www.luogu.com.cn/problem/P3366

# Code03_OptimizeWaterDistribution
* / 水资源分配优化
  // 村里面一共有 n 栋房子。我们希望通过建造水井和铺设管道来为所有房子供水。
  // 对于每个房子 i，我们有两种可选的供水方案：一种是直接在房子内建造水井
  // 成本为 wells[i - 1] （注意 -1 ，因为 索引从0开始 ）
  // 另一种是从另一口井铺设管道引水，数组 pipes 给出了在房子间铺设管道的成本，
  // 其中每个 pipes[j] = [house1j, house2j, costj]
  // 代表用管道将 house1j 和 house2j连接在一起的成本。连接是双向的。
  // 请返回 为所有房子都供水的最低总成本
* https://leetcode.cn/problems/optimize-water-distribution-in-a-village/

# Code04_CheckingExistenceOfEdgeLengthLimit
* // 检查边长度限制的路径是否存在
  // 给你一个 n 个点组成的无向图边集 edgeList
  // 其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边
  // 请注意，两个点之间可能有 超过一条边 。
  // 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj]
  // 你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径
  // 且这条路径上的每一条边都 严格小于 limitj 。
  // 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length
  // 当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false
* https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths/

# Code05_BusyCities
* // 繁忙的都市
  // 一个非常繁忙的大都市，城市中的道路十分的拥挤，于是市长决定对其中的道路进行改造
  // 城市的道路是这样分布的：城市中有n个交叉路口，有些交叉路口之间有道路相连
  // 两个交叉路口之间最多有一条道路相连接，这些道路是双向的
  // 且把所有的交叉路口直接或间接的连接起来了
  // 每条道路都有一个分值，分值越小表示这个道路越繁忙，越需要进行改造
  // 但是市政府的资金有限，市长希望进行改造的道路越少越好，于是他提出下面的要求：
  // 1. 改造的那些道路能够把所有的交叉路口直接或间接的连通起来
  // 2. 在满足要求1的情况下，改造的道路尽量少
  // 3. 在满足要求1、2的情况下，改造的那些道路中分值最大的道路分值尽量小
  // 作为市规划局的你，应当作出最佳的决策，选择哪些道路应当被修建
  // 返回选出了几条道路 以及 分值最大的那条道路的分值是多少
* https://www.luogu.com.cn/problem/P2330

# 
*
*

# 
*
*

# 
*
*



