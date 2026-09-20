package Two_Pointers双指针;

// 供暖器
// 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
// 在加热器的加热半径范围内的每个房屋都可以获得供暖。
// 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置
// 请你找出并返回可以覆盖所有房屋的最小加热半径。
// 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
// 测试链接 : https://leetcode.cn/problems/heaters/

import java.util.Arrays;

public class Code06_Heaters {

    public static int findRadius(int[] house , int[] heat){
        Arrays.sort(heat);  // 排序
        Arrays.sort(house);

        int ans = 0 ;
        for (int i = 0 , j = 0; i < house.length; i++) {
            // j号供暖器到i号房屋是不是最优的？
            while (!best(house , heat , i ,  j)) {
                j++ ; // 如果不是最优的，j号供暖器往后移一位
            }
            // 如果是最优的，当前位置的距离跟ans选出最大值，更新ans
            ans = Math.max(ans , Math.abs(heat[j] - house[i])) ;
        }
        return ans ;
    }
    // 当前的houses[i]房屋由heaters[j]供暖器来供暖是最优的吗?
    public static boolean best(int[] house, int[] heaters, int i, int j){
        // 如果 j 位置为最后一个供暖器 那肯定是最优的
        // 或者
        // 当前的供暖器减去该房屋  小于 下一个供暖器减去该房屋 也是最优的
        return j == heaters.length - 1
                ||
                Math.abs(heaters[j] - house[i]) < Math.abs(heaters[j+1] - house[i]);
    }
}
