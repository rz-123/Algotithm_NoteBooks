package Heap_Sort堆排序;

/// 堆排序：无序数组，利用完全二叉树进行排序，大根堆（所有子树的父节点都比左右俩孩子节点要大）
public class Heap_Sort {

    public static int[] sortArray(int[] nums) {
        if (nums.length > 1) {
            // heapSort1为从顶到底建堆然后排序
            // heapSort2为从底到顶建堆然后排序
            // 用哪个都可以
            // heapSort1(nums);
            heapSort2(nums);
        }
        return nums;
    }

    // 俩数交换
    public static void swap(int[] arr , int a , int b ){
        int t = arr[a] ;
        arr[a] = arr[b] ;
        arr[b] = t ;
    }

    // 向上调整大根堆，新来的i位置上的数向父节点看比较大小，如果比父节点大，就交换位置
    public static void heapInsert(int[] arr , int i){
        while (arr[i] > arr[(i - 1) / 2]){
            swap(arr , i , (i - 1) / 2);  // i位置上的数与父节点交换
            i = (i - 1) / 2 ;  // 此时i也来到父节点位置
        }
    }

    // 向下调整大根堆，新来的i位置上的数变小，向左右俩孩子看比较大小，如果比孩子小，就交换位置
    public static void heapify(int[] arr , int i , int size){
        int l = i * 2 + 1 ; // i的左孩子
        // size是当前堆大小
        while (l < size){
            /// 1、如果右孩子小于堆大小 并且 右孩子(l+1) > 左孩子，就选右孩子。
            /// 2、如果右孩子 > 堆大小，就说明i的位置上没有右孩子，所以选左孩子
            /// 3、如果右孩子 <= 左孩子，也选左孩子
            int best = l + 1 < size && arr[ l + 1 ] > arr[l] ? l + 1 : l ;
            // 左右孩子分出胜负，下边要胜的一方跟父节点进行比较
//            best = arr[best] > arr[i] ? best : i ;
//            if (best == i) {
//                break;
//            }
            if (arr[best]>arr[i]){
                swap(arr , best , i); // best和i交换位置
                i = best ; // i来到best位置
                l = i * 2 + 1; // 此时之前的左孩子来到i的下一个左孩子 继续比较
            }else {
                break;
            }
        }
    }

    // 自上到下
    // 先变成大根堆，然后0位置的数与最下边的数交换，然后size-1，断开
    // 然后现在0位置上的数向下比较
    public static void heapSort1(int[] arr){
        int n = arr.length ;
        // 变成大根堆
        for (int i = 0; i < n; i++) {
            heapInsert(arr , i);
        }
        int size = n ;
        while (size > 1) {
            swap(arr , 0 , --size); // 0位置与size-1位置交换，然后size再-1
            heapify(arr , 0 , size);  // 此时0位置的数开始向下调整
        }
    }

    // 自下到上
    public static void heapSort2(int[] arr){
        int n = arr.length ;
        // 变成大根堆
        for (int i = n -1 ; i >= 0; i--) {
            heapify(arr , i , n);
        }
        int size = n ;
        while (size > 1) {
            swap(arr , 0 , --size); // 0位置与size-1位置交换，然后size再-1
            heapify(arr , 0 , size);  // 此时0位置的数开始向下调整
        }
    }

}
