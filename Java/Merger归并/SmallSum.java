package Merger归并;

import java.io.*;

// 小和问题
// https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469
// 一个无序数组，从下标为0开始比较它左边的数，只要小于等于它就相加，最后把所有和相加
public class SmallSum {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.println(smallSum(0, n - 1));
        }
        out.flush();
        out.close();
    }

    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int[] help = new int[MAXN];

    public static int n;

    // 创建排序方法，比之前的merge多加一个统计功能
    public static long merge(int l , int m , int r){
        // 先统计小和，后排序
        long ans = 0 ;
        // 统计 设置指针，左半边起点i，右半边起点j
        for (int j = m + 1 , i = l , sum = 0 ; j <= r ; j++){
            // 如果i没超过中点m并且i位置的数值小于等于j位置的数值，就将i位置的的数值与Sum相加并且赋值给Sum
            // 同时i往后移一位
            while (i <= m && arr[i] <= arr[j]){
                sum += arr[i++];
            }
            // 汇总
            ans += sum ;
        }
        // 排序
        int i = l ;
        int x = l ;
        int y = m + 1 ;
        while (x <= m && y <= r){
            help[i++] = arr[x] <= arr[y] ? arr[x++] : arr[y++];
        }
        while (x <= m){
            help[i++] = arr[x++];
        }
        while (y <= r){
            help[i++] = arr[y++];
        }
        // 辅助数组赋值给原数组
        for (i = l ; i <=r ; i++){
            arr[i] = help[i];
        }
        return ans;
    }

    public static long smallSum(int l , int r){
        // 最小单元
        if (l == r){
            return 0;
        }
        int m = (l + r) >> 1 ; // 右移一位相当于除以2
        return smallSum(l , m) + smallSum(m+1 , r) + merge(l , m ,r) ;
    }

}
