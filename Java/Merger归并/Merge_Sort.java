package Merger归并;


/// 归并排序：递归加排序
/// 给一个无序数组，排序
/// 时间复杂度：O（n * logN），空间复杂度 O（N） 需要辅助数组help
/// 递归与非递归
public class Merge_Sort {

    // 创建辅助数组
    public static int Max = 5001;
    public static int[] help = new int[Max] ;

    public static int[] sortArray(int[] nums) {
        if (nums.length > 1) {
            // 想测非递归版，把下面这行换成 sort02(nums) 即可
            sort01(nums , 0 ,nums.length - 1 );
        }
        return nums;
    }

    // 排序方法
    public static void merge(int[] arr , int l , int m , int r){
        // 设置俩变量指针和辅助数组下边
        int a = l ;  // 辅助数组下标
        int x = l ;  // 左半边起点
        int y = m + 1 ; // 右半边起点
        // 如果x和y没超过各个边界，就判断x跟y的数值大小，
        // 谁小谁到辅助数组中，同时该下标来到x+1位置,同时辅助数组下标也来到a+1位置
        while (x <= m && y <= r){
            help[a++] = arr[x] <= arr[y] ? arr[x++] : arr[y++];
        }
        // 当上边两个数组，有任何一个越界，另一个没越界时，把没越界后边的数直接复制在辅助数组中
        while (x <= m){
            help[a++] = arr[x++];
        }
        while (y <= r){
            help[a++] = arr[y++];
        }
        // 当辅助数组中排好序后，再一次添加到实际数组中
        for (a = l ; a <= r ; a++){
            arr[a] = help[a];
        }
    }

    // 递归版归并排序
    public static void sort01(int[] arr , int l , int r){
        // 先找最小范围
        if (l == r){
            return;
        }
        // 找中点
        int m = (l + r) / 2;
        // 递归
        sort01(arr , l , m);
        sort01(arr , m+1 , r);
        // 排序
        merge(arr , l , m , r);
    }

    // 非递归版，额外变量步长，步长从1开始每次乘以2，直到步长<=右边界，停止
    // 要求每段步长中有序
    public static void sort02(int[] arr){
        // 左右边界，以及中点m
        int n = arr.length ; // 传入的数组长度
        // 每次for循环结束后，step右移一位同时赋值给step，相当于乘2
        for (int step = 1 ; step < n ; step <<= 1){
            int l = 0, m , r ; // 每轮步长都要从0开始
            // 如果左边界小于数组长度
            while (l < n){
                m = l + step -1 ; // 中点位置
                // 如果中点+1大于等于数组最大长度,就跳出循环
                if (m + 1 >= n){
                    break;
                }
                // 选右边界,在l+两倍的步长-1 跟 最大长度-1 中 选最小
                r = Math.min( l + (step<<1) - 1 , n - 1);
                // 左右边界以及中点出来之后传入merge排序
                merge(arr , l , m , r);
                // 该步长排完序后，左边界来到中点+1位置继续循环
                l = r + 1 ;
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2, 5};
        sortArray(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
