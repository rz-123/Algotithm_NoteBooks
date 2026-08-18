package Dich二分;

// 二分法，时间复杂度：O(logN)
class Dichotomy {

    // 有序数组中找num返回下标
    public static int findNum(int[] arr , int num){
        // 判断边界
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0 , r = arr.length-1 , m = 0 ; // 左右边界加中点位置
        while (l <= r){
            m = (l + r) / 2; //找中点
            // m = l + ((r - l)>>1); // 同样是找中点，防止溢出
            if (arr[m] == num){
                return  m ; // 找到直接返回下标
            } else if (arr[m] < num) {
                l = m + 1; // 往右找，左边界调整
            }else {
                r = m - 1; // 往左找，右边界调整
            }
        }
        return -1;
    }


    // 有序数组中找>=num 最左位置
    public static int findLeft(int[] arr , int num){
        // 判断边界
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int index = -1;
        int l = 0 , r = arr.length-1 , m = 0 ;
        while (l <= r){
            m = (l + r) / 2 ;
            if (arr[m]>=num){
                index = m ;
                r = m - 1 ;
            }else {
                l = m + 1 ;
            }
        }
        return index;
    }

    // 有序数组中找<=num 最右位置
    public static int findRight(int[] arr , int num){
        // 判断边界
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int index = -1;
        int l = 0 , r = arr.length-1 , m = 0 ;
        while (l <= r){
            m = (l + r) / 2;
            if (arr[m] <= num){
                index = m ;
                l = m + 1;
            }else {
                r = m - 1 ;
            }
        }
        return index;
    }

    // 在无序数组（相邻两数不相等）中找峰值(中间数比两边数大)
    public static int findPack(int[] arr , int num){
        // 判断边界
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length-1;

        if (arr.length == 1){
            return 0;
        }
        // 判断左右边界是都为峰值
        if (arr[0]>arr[1]){
            return 0;
        }
        if (arr[n]>arr[n-1]){
            return n;
        }

        int l = 1 , r = n - 1 , m = 0 ;
        int index = -1 ;
        while(l <= r){
            m = (l + r) / 2;
            if (arr[m-1] > arr[m]){
                r = m - 1 ;  // 调整右边界
            }else if(arr[m] < arr[m + 1]){
                l = m + 1 ; // 调整左边界
            } else {
              index = m ; // 如果上边两个条件不满足，就说明中点位置就是峰值点
              break;
            }
        }
        return index ;
    }

    public static void main(String[] args) {

    }
}

