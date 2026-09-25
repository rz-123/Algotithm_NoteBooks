package Mon_Stack单调栈;

// 统计全1子矩形的数量
// 给你一个 m * n 的矩阵 mat，其中只有0和1两种值
// 请你返回有多少个 子矩形 的元素全部都是1
// 测试链接 : https://leetcode.cn/problems/count-submatrices-with-all-ones/


import java.util.Arrays;

public class Code04_CountSubmatricesWithAllOnes {

    public static int MAX = 151 ;
    public static int[] height = new int[MAX] ;
    public static int[] stack = new int[MAX] ;
    public static int r ;

    public static int numSubmat(int[] [] mat){
        int n = mat.length ; // 行
        int m = mat[0].length ;  // 列
        int ans = 0 ;
        Arrays.fill(height , 0 , m , 0);
        // 压缩数组
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            ans += countFromBottom(m) ;
        }
        return ans ;
    }


    // 传进列数
    public static int countFromBottom(int m){
        r = 0 ;
        int ans = 0;
        for (int i = 0 , left , leng , bottom; i < m; i++) {

            // 如果栈顶元素 大于等于 要加入的元素，那么就弹出栈顶元素
            while (r > 0 && height[stack[r-1]] >= height[i]) {
                int cur = stack[--r];

                // 相等不计算矩形个数
                if (height[cur] > height[i]) {
                    left = r == 0 ? -1 : stack[r-1]; // 左边最近最小值
                    leng = i - left - 1 ; // 以cur高度的下标范围
                    bottom = Math.max(left == -1 ? 0 : height[left] ,height[i] ); // 左边跟i位置选最大的一个参与计算
                    ans += (height[cur] - bottom) * leng * (leng + 1) / 2 ;
                }
            }
            stack[r++] = i ; // 压入栈
        }
        // 清空栈
        while ( r > 0 ){
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[ r - 1 ];
            int leng = m - left - 1 ;
            int down = left == -1 ? 0 : height[left]; // 留在栈内的 右边没有小的数，所以只求左边
            ans += (height[cur] - down) * leng * (leng + 1) / 2 ;
        }
        return ans ;
    }
}
