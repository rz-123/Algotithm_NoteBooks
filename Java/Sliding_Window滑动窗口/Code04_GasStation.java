package Sliding_Window滑动窗口;


// 加油站
// 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升
// 你从其中的一个加油站出发，开始时油箱为空。
// 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周
// 则返回出发时加油站的编号，否则返回 -1
// 如果存在解，则 保证 它是 唯一 的。
// 测试链接 : https://leetcode.cn/problems/gas-station/
public class Code04_GasStation {

    // 传入 一组每个加油站的油量，一组从一个加油站到另一个加油站需要的油量
    public static int canCompleteCircuit(int[] gas , int[] cost){

        int n = gas.length ;
        // l = r + 1 :如果循环中发现sum + gas[r % n] - cost[r % n]<0,
        //            那么说明l-r范围中的数值作为起点是不行的，所以直接l来到r+1位置，r来到l位置，绕环
        for (int l = 0 , r = 0 , sum ; l < n ; l = r + 1 , r = l) {
            sum = 0 ;

            // 如果 余量的累加和 + gas - cost >= 0,说明该范围可以
            // 所以 右边界要往右扩
            while (sum + gas[r % n] - cost[r % n] >= 0) {
                // 检查该窗口是否绕行了一周
                if (r-l+1 == n) {
                    return l ;  // 如果一周了，直接返回左边界作为起点
                }

                sum += gas[r % n] - cost[r % n] ;
                r++ ; // 右边界往外扩
            }
        }
        return -1 ; // 如果没有，就返回-1
    }
}
