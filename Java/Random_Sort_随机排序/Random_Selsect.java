package Random_Sort_随机排序;

// 随机选择排序
// 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
// https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
public class Random_Selsect {

    public int findKthLargest(int[] nums, int k) {
        return randomizedSelect(nums, nums.length - k);
    }

    // 交换
    public static void swap(int[] arr , int a , int b){
        int tmp = arr[a] ;
        arr[a] = arr[b] ;
        arr[b] = tmp ;
    }

    // 荷兰国旗
    public static int first , last ;
    public static void partition(int[] arr , int l , int r , int x){
        first = l ;
        last = r ;
        int i = l ;

        while (i <= last){
            if (arr[i] == x){
                i++ ;
            }else if (arr[i] < x){
                swap(arr , first++ , i++);
            }else {
                swap(arr , i , last--);
            }
        }
    }
    // 找i位置上的数值是多少
    public static int randomizedSelect(int[] arr , int i){
        int ans = 0 ; // 存放i位置上的数值
        for (int l = 0 , r = arr.length - 1 ; l <= r ; ){
            partition(arr , l , r , arr[l + (int) (Math.random() * (r-l+1))]);
            if (i < first){
                r = first - 1 ;
            } else if (i > last){
                l = last + 1 ;
            } else {
                ans = arr[i];
                break;
            }
        }
        return ans ;
    }
}
