package Tree_Sort;

public class Sort_Test {

    // 交换数组中i和j位置的数
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 制造随机数组，n个数，每个数0-m之间随机
    public static int[] adamArray(int n, int m) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * m) + 1; // 不加1可能出现0，所以才加1确保0不会出现。
        }
        return arr;
    }

    // 冒泡
    // 找最大值，谁大谁往后,
    // 0-1谁大谁往后，
    // 1-2谁大谁往后......
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    // 选择排序
    // 找最小值，谁小谁往前,
    // 0-arr.length-1谁小谁往左,
    // 1-arr.length-1谁小谁往左,......
    public static void selectionSort(int[] arr) {
        // 判断边界
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // 先设置第i位置上最小
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;  // 下标交换
                }
            }
            swap(arr, i, minIndex);  // 循环一轮，进行数值交换
        }


    }

    // 插入排序
    // 0-0 位置上要求有序
    // 0-1 位置上有序......
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            // 左边的数比右边的数大就交换
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--)
                swap(arr, j, j + 1);
        }
    }

    // 对比两个数组是否相等
    public static boolean sameArray(int[] arr1, int[] arr2) {
        int n = arr1.length;
        for (int i = 0; i < n; i++) {
            if (arr1[i] != arr2[i]) {
                return true;
            }
        }
        return false;
    }

    // 拷贝相同的数组
    public static int[] copyArray(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 100; // 随机数组的最大长度
        int M = 10000;  // 每个数值随机等概率 1-M
        int times = 500; // 循环次数
        for (int i = 0; i < times; i++) {
            int n = (int) (Math.random() * N); // 随机得到数组长度
            int[] arr = adamArray(n, M);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            selectionSort(arr1);
            insertionSort(arr2);
            bubbleSort(arr3);
            if (sameArray(arr1, arr2) || sameArray(arr1, arr3)) {
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }
}
