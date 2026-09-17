package Diff差分;


import java.io.*;

// 等差数列差分
// 一开始1~n范围上的数字都是0，一共有m个操作，每次操作为(l,r,s,e,d)
// 表示在l~r范围上依次加上首项为s、末项为e、公差为d的数列
// m个操作做完之后，统计1~n范围上所有数字的最大值和异或和
// https://www.luogu.com.cn/problem/P4231
public class Code02_ArithmeticSequenceDifference {
    // 静态变量以及静态数组

    public static int MAX = 10000005 ;
    public static long[] arr = new long[MAX] ;
    public static int n , m ;  // 1-n范围的数，进行m个操作

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer in = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ;
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval ;
            in.nextToken() ;
            m = (int) in.nval ;

            for (int i = 0 , l , r , s , e; i < m; i++) {
                in.nextToken(); l = (int) in.nval;
                in.nextToken(); r = (int) in.nval;
                in.nextToken(); s = (int) in.nval;
                in.nextToken(); e = (int) in.nval;
                // d 自己求 ，末项-首项 / 右边界 - 左边界
                // set 进行等差数列差分
                set( l , r , s , e , (e-s) / (r-l)) ;
            }
            build();  // 进行两次前缀和操作
            // 因为返回最大值 和 异或和
            long max = 0 , xor = 0 ;
            for (int i = 1 ; i < n ; i++){
                max = Math.max(max , arr[i]) ;
                xor ^= arr[i] ;
            }
            out.println(xor + " " + max);
        }
        out.flush();
        out.close();
        br.close();
    }

    // 左右边界，首项、末项、公差
    public static void set(int l , int r, int s , int e, int d){
        arr[l] += s ;
        arr[l+1] += d-s ;
        arr[r+1] -= d+e ;
        arr[r+2] += e ;
    }

    // 进行两次前缀和操作
    public static void build(){
        for (int i = 1; i <= n ; i++) {
            arr[i] += arr[i-1];
        }
        for (int i = 1; i <= n ; i++) {
            arr[i] += arr[i-1];
        }
    }

}
