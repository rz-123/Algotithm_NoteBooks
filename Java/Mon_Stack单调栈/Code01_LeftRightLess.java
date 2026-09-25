package Mon_Stack单调栈;

import java.io.*;

// 给定一个数组，找出每一个i位置的左边跟右边最近且比它小的位置
//https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
public class Code01_LeftRightLess {

    public static int MAX = 1000001;
    public static int[] arr = new int[MAX] ; // 给的数组
    public static int[] stack = new int[MAX] ;  // 单调栈
    public static int[][] ans = new int[MAX][2] ; // 存放每个i未知数的左右最小且最近的位置 二维
    public static int n , r ; // n 是 数组的长度，r 是 控制栈的输入与弹出

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer in = new StreamTokenizer(br) ; // 输入
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ; // 输出
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval ;
            for (int i = 0; i < n; i++) {
                in.nextToken() ;
                arr[i] = (int) in.nval ;
            }
            compute(); // 调单调栈
            for (int i = 0; i < n; i++) {
                out.println(ans[i][0] + " " + ans[i][1]);  // 完成后将位置输出
            }
        }
        out.flush();
        out.close();
        br.close();
    }
    public static void compute() {
        r = 0 ; // 栈内为空
        int cur ; // 栈顶元素变量
        // 遍历整个数组，大压小
        for (int i = 0; i < n; i++) {
            // 如果栈顶元素 大于等于 要加入的元素
            while (r > 0 && arr[stack[r-1]] >= arr[i]){
                cur = stack[--r]; // 栈顶元素弹出
                // 然后将栈顶的位置存入ans数组，
                // 左边最近且比它小的就是现在的栈顶元素，
                ans[cur][0] = r > 0 ? stack[r-1] : -1 ; // 如果栈内没元素就返回-1
                ans[cur][1] = i ; // 右边最近 且 比它小的位置 是让它弹出来的数 位置i
            }
            stack[r++] = i ; // 加入栈内
        }
        // 当 遍历完数组，栈内还有东西 进行清算
        while (r>0){
            cur = stack[--r];
            ans[cur][0] = r > 0 ? stack[r-1] : -1 ;
            ans[cur][1] = -1 ; // 当进行清算阶段时，右边必然没有比它小的数
        }

        // 遇到重复数字 照常弹出 ，上边全部完成后进行从右往左修正
        for (int i = n-2; i >= 0 ; i--) {
            // 如果 该数的右边不等于-1（说明右边有最小值） 且 该数的右边的 等于 该位置上的数
            if (ans[i][1] != -1 && arr[ans[i][1]] == arr[i]){
                // 那么 就把 右边位置上的数的右边最小值作为该数的右边最小值
                ans[i][1] = ans[ans[i][1]][1] ;
            }
        }
    }

}
