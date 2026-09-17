package Diff差分;

import java.io.*;

// 一群人落水 求落水高度
// https://www.luogu.com.cn/problem/P5026
public class Code03_WaterHeight {

    public static int MAX = 1000001 ;  // 湖泊宽度
    // 因为题目中的v最大值为10000，
    // 左侧最远影响距离为x-3 * v + 1;
    // 右侧最远影响距离为x+3 * v - 1 ;
    // x为入水点的位置，那么左右两侧可能会超过30000的规模
    public static int OFF = 30001 ;
    public static int[] arr = new int[OFF + MAX + OFF];  // 左右各加三万
    public static int n , m ;  // 朋友的数量以及湖泊的宽度


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval ; // 多少人落水
            in.nextToken() ;
            m = (int) in.nval ; // 1-m个落水的位置

            // 循环，v体积的朋友在x处落水，建四个等差数列数组
            for (int i = 0 , v , x; i < n; i++) {
                in.nextToken();v = (int)in.nval;
                in.nextToken();x = (int)in.nval;
                fall(v,x) ;
            }
            build(); // 前缀和

            //  因为 1-OFF是辅助位置，所以从OFF+1位置往后数，才是0-m真正的位置
            int start = OFF + 1 ;
            out.print(arr[start++]);
            for (int i = 2; i <= m; i++) {
                out.print(" " + arr[start++]);
            }
            out.println();
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void fall(int v, int i){
        set(i - 3 * v + 1, i - 2 * v, 1, v, 1);
        set(i - 2 * v + 1, i, v - 1, -v, -1);
        set(i + 1, i + 2 * v, -v + 1, v, 1);
        set(i + 2 * v + 1, i + 3 * v - 1, v - 1, 1, -1);
    }

    // 等差数列差分数组
    public static void set(int l , int r , int s , int e , int d){
        arr[l + OFF] += s;
        arr[l + 1 + OFF] += d - s;
        arr[r + 1 + OFF] -= d + e;
        arr[r + 2 + OFF] += e ;
    }

    // 两次前缀和
    public static void build() {
        for (int i = 1; i <= m + OFF; i++) {
            arr[i] += arr[i - 1];
        }
        for (int i = 1; i <= m + OFF; i++) {
            arr[i] += arr[i - 1];
        }
    }








}
