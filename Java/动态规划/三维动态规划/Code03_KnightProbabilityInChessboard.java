package 动态规划.三维动态规划;

// 骑士在棋盘上的概率
// n * n的国际象棋棋盘上，一个骑士从单元格(row, col)开始，并尝试进行 k 次移动
// 行和列从0开始，所以左上单元格是 (0,0)，右下单元格是 (n-1, n-1)
// 象棋骑士有8种可能的走法。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格
// 每次骑士要移动时，它都会随机从8种可能的移动中选择一种，然后移动到那里
// 骑士继续移动，直到它走了 k 步或离开了棋盘
// 返回 骑士在棋盘停止移动后仍留在棋盘上的概率
// 测试链接 : https://leetcode.cn/problems/knight-probability-in-chessboard/

public class Code03_KnightProbabilityInChessboard {
}
