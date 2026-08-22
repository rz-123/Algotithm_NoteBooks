package Random_Sort_随机排序;

// 随机数 排序 以及 荷兰国旗问题
// 一个无序数组排序，先随机一个数，小于这个数的在左，大于这个数的在右
public class RandomSort {

    // 交换数 方法
    public static void swap(int[] arr , int a , int b) {
        int tmp = arr[a] ;
        arr[a] = arr[b] ;
        arr[b] = tmp ;
    }

    // 划分 方法 , 小于等于x在左，并且保证左边的最后一个数为x，大于x在右
    public static int partition1(int[] arr , int l , int r , int x){
        // 定义变量 a-1是 <=x的区域右边界 , xn 记录x，已确保该区域的右边界最后是x
        int a = l , xn = 0 ;
        for (int i = l; i < r; i++) {
            // 当i下标的数值小于等于x时，就让a跟i 数值交换，同时a向后移一位
            // 如果=x,用xn记录
            if (arr[i] <= x) {
                swap(arr, a, i);
                // 交换完a位置的数如果=x。就记录
                if (arr[a] == x) {
                    xn = a;
                }
                a++; // a 向后移一位
            }
        }
        swap(arr , xn , a - 1);
        return a - 1 ;
    }

    // 递归 经典版
    public static void quickSort01(int[] arr , int l , int r){
        if ( l >= r ){
            return;
        }
        // 创建随机数x，随机下标才能保证随机数一定在该数组
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        int mid = partition1(arr , l , r , x) ;
        quickSort01(arr , l , mid - 1);
        quickSort01(arr , mid + 1 , r);

    }

    // 荷兰国旗问题
    // 给一个无序数组，生成随机数x，<x的在左边，>x的=在右边，=x的在中间
    public static int first, last; // 全局变量first跟last，控制左边跟右边的区域
    public static void partition2(int[] arr , int l , int r , int x){
        // 最开始first=左边界，last=右边界
        first = l ;
        last = r ;
        int i = l ; // i指针控制向下走
        while ( i <= last){ // 停止的条件
            // 如果i位置的数=x，i就往下走
            // 如果i位置上的数<x,i的数就跟first的数进行交换，同时first往下走，i也往下走
            // 如果i位置上的数>x,i的数就跟last的数交换，i不变(因为交换来的数还没比较)，last往上走一位
            if (arr[i] == x) {
                i++ ;
            } else if (arr[i] < x){
                swap(arr , first++ , i++);
            } else {
                swap(arr , i , last--);
            }
        }
    }

    public static void quickSort02(int[] arr , int l , int r){
        if (l >= r){
            return;
        }
        int x = arr[ l + (int)(Math.random() * (r - l + 1))];
        partition2(arr , l , r , x);
        int left = first; // 防止递归覆盖全局变量，先用临时变量存一下
        int right = last;
        quickSort02(arr , l , left - 1);
        quickSort02(arr , right + 1 , r);
    }
}
