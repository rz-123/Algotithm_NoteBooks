package Diff差分;

//  贴邮票问题
// 给你一个只有0跟1的二维数组跟一个h*w的邮票，邮票只能贴0，问你能不能贴满
// https://leetcode.cn/problems/stamping-the-grid/
public class Code04_StampingTheGrid {

    // 主方法
    // 根据传入的邮票尺寸，计算范围
    public static boolean possibleToStamp(int[] [] grid , int h , int w){
        int n = grid.length ;
        int m = grid[0].length ;
        int[][] sum = new int[n+1][m+1];
        // sum的外边围一圈0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i+1][j+1] = grid[i][j] ;
            }
        }
        build(sum); // sum构建前缀和数组

        // 构建差分矩阵,在这里边贴邮票
        int[][] diff = new int[n + 2][m + 2];

        // 根据邮票的长宽判断终点位置，然后确定该范围和为0才能贴
        for (int a = 1, c = a+h-1 ; c<=n ; a++ , c++){
            for (int b = 1 , d = b + w -1 ; d <= m ; b++ , d++){
                // 原始矩阵中 (a,b)左上角点
                // 根据邮票规格，h、w，算出右下角点(c,d)
                // 这个区域彻底都是0，那么:
                // sumRegion(sum, a, b, c, d) == 0
                // 那么此时这个区域可以贴邮票
                if (sumRegion(sum , a , b , c , d) == 0){
                    add(diff , a , b , c , d); // 贴邮票，该区域变为1
                }
            }
        }
        build(diff); // 进行前缀和操作，才能把全部贴邮票的范围变为1

        // 检查所有格子，原始矩阵跟差分矩阵都检查
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 原始矩阵里：grid[i][j] == 0，说明是个洞
                // 差分矩阵里：diff[i + 1][j + 1] == 0，说明洞上并没有邮票
                // 此时返回false
                if (grid[i][j] == 0 && diff[i+1][j+1] == 0){
                    return false ;
                }
            }
        }
        return true ;
    }


    // 前缀和数组
    public static void build(int[] [] m){
        for (int i =1 ; i < m.length; i++) {
            for (int j = 1; j < m[0].length; j++) {
                // 自己 加 左 加 上  减 左上
                m[i][j] += m[i-1][j] + m[i][j-1] - m[i-1][j-1] ;
            }
        }
    }

    // 计算范围和
    public static int sumRegion(int[][] sum, int a, int b, int c, int d){
        return sum[c][d] - sum[c][b-1] - sum[a-1][d] + sum[a-1][b-1] ;
    }

    // 计算二维差分 , 把该区域全变成1，相当于贴邮票
    public static void add(int[][] diff, int a, int b, int c, int d){
        diff[a][b] += 1;
        diff[c + 1] [d + 1] += 1;
        diff[c + 1] [b] -= 1 ;
        diff[a][d + 1] -= 1;
    }

}
