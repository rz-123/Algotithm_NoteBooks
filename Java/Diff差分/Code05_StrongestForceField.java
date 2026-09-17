package Diff差分;

import java.util.Arrays;

// 最强力场
// 给你二维数组，看看那个范围覆盖的矩形多
// https://leetcode.cn/problems/xepqZ5/description/
public class Code05_StrongestForceField {

    // 主方法 ,
    public static int fieldOfGreatestBlessing(int[][] fields){
        int n = fields.length ; // 矩阵的个数
        // 收集所有矩阵的左右边界
        long[] xs = new long[n << 1] ;
        long[] ys = new long[n << 1] ;
        for (int i = 0 , k=0 , p=0; i < n; i++) {
            long x = fields[i][0] ; // x,y为中点
            long y = fields[i][1] ;
            long r = fields[i][2] ;  // r为边长
            xs[k++] = (x << 1) - r; // 左边界
            xs[k++] = (x << 1) + r; // 右边界
            ys[p++] = (y << 1) - r; // 上边界
            ys[p++] = (y << 1) + r; // 下边界
        }
        // 排序并去重
        int sizex = sort(xs) ;
        int sizey = sort(ys) ;

        // 差分数组
        int[][] diff = new int[sizex + 2][sizey + 2] ;
        for (int i = 0 , a , b , c , d; i < n; i++) {
            long x = fields[i][0];
            long y = fields[i][1];
            long r = fields[i][2]; // 获取中心点xy轴以及边长
            // 获取编号
            a = rank(xs, (x << 1) - r, sizex);
            b = rank(ys, (y << 1) - r, sizey);
            c = rank(xs, (x << 1) + r, sizex);
            d = rank(ys, (y << 1) + r, sizey);
            add(diff , a , b , c , d); // 做这个范围差分
        }
        //
        int ans = 0 ; // 最终返回的数字
        // 在差分数组中进行前缀和操作，看哪个位置的数最大，谁就是力场最强出
        for (int i = 1; i < diff.length; i++) {
            for (int j = 1; j < diff[0].length; j++) {
                diff[i][j] += diff[i-1][j] + diff[i][j-1] - diff[i-1][j-1];
                ans = Math.max(ans,diff[i][j]);
            }
        }
        return ans;
    }

    // 将数组中的数排序 加 去重
    // 重复的数字只留1个
    public static int sort(long[] nums){
        Arrays.sort(nums); // 排序
        int size = 1 ;
        for (int i = 1; i < nums.length; i++) {
            // 去重 如果后一个数跟前一个数不相等，就存进nums
            if (nums[i] != nums[i-1]){
                nums[size++] = nums[i] ;
            }
        }
        return size ; // 返回数组的个数
    }


    // nums 有序数组，有效长度是size，0~size-1范围上无重复值
    // 已知v一定在nums[0~size-1]，返回v所对应的编号
    public static int rank(long[] nums, long v, int size) {
        int l = 0;
        int r = size - 1;
        int m, ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (nums[m] >= v) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    // 差分
    public static void add(int[][] diff, int a, int b, int c, int d) {
        diff[a][b] += 1;
        diff[c + 1][d + 1] += 1;
        diff[c + 1][b] -= 1;
        diff[a][d + 1] -= 1;
    }
}
