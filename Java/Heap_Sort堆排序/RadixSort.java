package Heap_Sort堆排序;


import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.Arrays;

// 基数排序，数组必须是非负，
// 想根据个位排序、十位排序。。。。
public class RadixSort {

    // 设置静态变量以及静态辅助数组
    public static int MAX = 50001;   // 最大值
    public static int BASE = 10;  // 数组中的数值是十进制
    public static int[] help = new int[MAX] ;  // 辅助数组
    public static int[] cunts = new int[BASE] ; // 存放每一位在数组中的数量，分区

    // 主方法
    public static int[] sortArray(int[] arr){
        if (arr.length > 1){
            int n = arr.length ;
            // 找数组最小值
            int min = arr[0] ;
            for (int i = 0; i < n; i++) {
                min = Math.min(min , arr[i]) ;
            }
            // 找最大值；
            int max = 0 ;
            for (int i = 0; i < n; i++) {
                // 数组中所有数值保证是整数
                arr[i] -= min ; // 如果有负数，那么减去一个负数 = 加上它的正数
                max = Math.max(max , arr[i]) ;
            }
            // 调用基数排序
            radixSort(arr , n , bits(max));  // bits(max) 返回最大值的位数，也就是循环多少轮
            for (int i = 0; i < n; i++) {
                arr[i] += min ;
            }
        }
        return arr ;
    }


    // 数组中的最大值有几位
    public static int bits(int maxNums){
        int ans = 0 ;
        while (maxNums > 0){
            ans ++; // 位数加一
            maxNums /= BASE ; // 每次除以十进制
        }
        return ans ;
    }

    // 核心方法，n：数组长度，bits:位数，
    public static void radixSort(int[] arr , int n , int bits){
        // 设置变量 offset每次乘10 获取个位、十位上的数
        for (int offset = 1 ; bits > 0 ; offset *= BASE , bits--){
            // 因为cunts是记录个数的，所以每进行都得清空
            for(int i = 0 ; i < BASE ; i++){
                cunts[i] = 0 ;
            }
            // 从个位开始获取单独的数值，统计词频
            for (int i = 0; i < n; i++) {
                // 模除得到的数值放在cunts对应下标的位置
                // 然后 下标对应的数值加1。
                cunts[(arr[i] / offset) % BASE] = cunts[(arr[i] / offset) % BASE] + 1;
            }
            // 前缀累加和，就是具体数值 小于等于它的 有几个
            for (int i = 1; i < BASE; i++) {
                cunts[i] += cunts[i - 1];
            }
            // 原始数组从右往左，往辅助数组help中添加数值
            for (int i = n - 1; i >= 0; i--) {
                // 先获取原始数组最右边的数值的当前位数，
                // 然后找到桶内具体的位置-1
                cunts[(arr[i] / offset) % BASE] -- ;
                // 然后将原始数组该下标的数放到辅助数组中对应下标的位置
                help[cunts[(arr[i] / offset) % BASE]] = arr[i];
            }
            // 全整完之后，将辅助数组中的数值在刷回原始数组
            for (int i = 0; i < n; i++) {
                arr[i] = help[i];

            }
        }
    }
}
