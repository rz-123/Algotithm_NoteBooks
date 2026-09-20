package Two_Pointers双指针;

// 救生艇
// 给定数组 people
// people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit
// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit
// 返回 承载所有人所需的最小船数
// 测试链接 : https://leetcode.cn/problems/boats-to-save-people/

import java.util.Arrays;

public class Code04_BoatsToSavePeople {

    public static int numRescueBoats(int[] people , int limit){
        Arrays.sort(people); // 排序
        int ans = 0 ; // 最终返回船的数量
        int l = 0 ; // 左指针
        int r = people.length - 1 ; // 右指针
        int sum = 0 ; // 左右相加的和
        while (l <= r) {
            // 如果 左右指针位置相等时，只返回左指针上的数就行
            sum = l == r ? people[l] : people[l] + people[r] ;
            if (sum > limit){
                r-- ; // 如果俩数大于limit，那么r就单独一船，r向前移一位
            } else {
                // 否则就是两人一船，左右边界向中间靠
                l++ ;
                r-- ;
            }
            ans++ ; // ans每次加1
        }
        return ans ;
    }

}
