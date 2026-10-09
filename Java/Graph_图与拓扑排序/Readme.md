# 图

# Code01_CreateGraph
* 三种构建图的方式以及遍历（邻接矩阵、邻接表、链式前向星）
* 

# Code02_TopoSortDynamicLeetcode
* 拓扑结构模版题 邻接表建图（动态方式）
* https://leetcode.cn/problems/course-schedule-ii/

# Code02_TopoSortStaticNowcoder
* 拓扑结构模版题 链式前向星建图（静态方式）
* https://www.nowcoder.com/practice/88f7e156ca7d43a1a535f619cd3f495c

# Code02_TopoSortDynamicNowcoder
* 动态邻接表建图
* https://www.nowcoder.com/practice/88f7e156ca7d43a1a535f619cd3f495c

# Code03_TopoSortStaticLuogu
* 字典序最小的拓扑排序
* // 要求返回所有正确的拓扑排序中 字典序最小 的结果
  // 建图请使用链式前向星方式，因为比赛平台用其他建图方式会卡空间
* https://www.luogu.com.cn/problem/U107394

# Code04_AlienDictionary
* 火星词典
* // 现有一种使用英语字母的火星语言
// 这门语言的字母顺序对你来说是未知的。
// 给你一个来自这种外星语言字典的字符串列表 words
// words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
// 如果这种说法是错误的，并且给出的 words 不能对应任何字母的顺序，则返回 ""
// 否则，返回一个按新语言规则的 字典递增顺序 排序的独特字符串
// 如果有多个解决方案，则返回其中任意一个
// words中的单词一定都是小写英文字母组成的
* https://leetcode.cn/problems/Jf1JuT/description/

# Code05_StampingTheSequence
* // 戳印序列
  // 你想最终得到"abcbc"，认为初始序列为"?????"。印章是"abc"
  // 那么可以先用印章盖出"??abc"的状态，
  // 然后用印章最左字符和序列的0位置对齐，就盖出了"abcbc"
  // 这个过程中，"??abc"中的a字符，被印章中的c字符覆盖了
  // 每次盖章的时候，印章必须完全盖在序列内
  // 给定一个字符串target是最终的目标，长度为n，认为初始序列为n个'?'
  // 给定一个印章字符串stamp，目标是最终盖出target
  // 但是印章的使用次数必须在10*n次以内
  // 返回一个数组，该数组由每个回合中被印下的最左边字母的索引组成
  // 上面的例子返回[2,0]，表示印章最左字符依次和序列2位置、序列0位置对齐盖下去，就得到了target
  // 如果不能在10*n次内印出序列，就返回一个空数组
* https://leetcode.cn/problems/stamping-the-sequence/

# Code01_FoodLines
* // 最大食物链计数
  // a -> b，代表a在食物链中被b捕食
  // 给定一个有向无环图，返回
  // 这个图中从最初级动物到最顶级捕食者的食物链有几条
  // 测试链接 : https://www.luogu.com.cn/problem/P4017
  // 请同学们务必参考如下代码中关于输入、输出的处理
  // 这是输入输出处理效率很高的写法
  // 提交以下所有代码，把主类名改成Main，可以直接通过
* https://www.luogu.com.cn/problem/P4017

# Code02_LoudAndRich
* // 喧闹和富有
  // 从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值
  // 给你一个数组richer，其中richer[i] = [ai, bi] 表示
  // person ai 比 person bi 更有钱
  // 还有一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值
  // richer 中所给出的数据 逻辑自洽
  // 也就是说，在 person x 比 person y 更有钱的同时，不会出现
  // person y 比 person x 更有钱的情况
  // 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是,
  // 在所有拥有的钱肯定不少于 person x 的人中，
  // person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
* https://leetcode.cn/problems/loud-and-rich/

# Code03_ParallelCoursesIII
* // 并行课程 III
  // 给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n
  // 同时给你一个二维整数数组 relations ，
  // 其中 relations[j] = [prevCoursej, nextCoursej]
  // 表示课程 prevCoursej 必须在课程 nextCoursej 之前 完成（先修课的关系）
  // 同时给你一个下标从 0 开始的整数数组 time
  // 其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。
  // 请你根据以下规则算出完成所有课程所需要的 最少 月份数：
  // 如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
  // 你可以 同时 上 任意门课程 。请你返回完成所有课程所需要的 最少 月份数。
  // 注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）
* https://leetcode.cn/problems/parallel-courses-iii/description/

# Code04_MaximumEmployeesToBeInvitedToAMeeting
* // 参加会议的最多员工数
  // 一个公司准备组织一场会议，邀请名单上有 n 位员工
  // 公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工
  // 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工
  // 每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议
  // 每位员工喜欢的员工 不会 是他自己。给你一个下标从 0 开始的整数数组 favorite
  // 其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目
* https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/description/













