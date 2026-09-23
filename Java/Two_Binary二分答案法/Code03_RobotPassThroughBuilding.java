package Two_Binary二分答案法;

// 机器人跳跃问题
// 机器人正在玩一个古老的基于DOS的游戏
// 游戏中有N+1座建筑，从0到N编号，从左到右排列
// 编号为0的建筑高度为0个单位，编号为i的建筑的高度为H(i)个单位
// 起初机器人在编号为0的建筑处
// 每一步，它跳到下一个（右边）建筑。假设机器人在第k个建筑，且它现在的能量值是E
// 下一步它将跳到第个k+1建筑
// 它将会得到或者失去正比于与H(k+1)与E之差的能量
// 如果 H(k+1) > E 那么机器人就失去H(k+1)-E的能量值，否则它将得到E-H(k+1)的能量值
// 游戏目标是到达第个N建筑，在这个过程中，能量值不能为负数个单位
// 现在的问题是机器人以多少能量值开始游戏，才可以保证成功完成游戏
// 测试链接 : https://www.nowcoder.com/practice/7037a3d57bbd4336856b8e16a9cafd71

import java.io.*;

public class Code03_RobotPassThroughBuilding {

    public static int MAX = 100001 ;
    public static int[] arr = new int[MAX] ;
    public static int n ;  // n个数

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer in = new StreamTokenizer(br) ; // 输入
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ; // 输出
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval ; // 获取n
            int l = 0 , r = 0 ;
            for (int i = 1 ; i <= n; i++) {
                in.nextToken() ;
                arr[i] = (int)in.nval ; // 获取数组
                r = Math.max(r,arr[i]) ; // 右边界为数组最大值
            }
            out.println(compute(l , r , r)) ;
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(int l , int r , int max){
        int m , ans = -1 ; // 初始化中点和最小能量
        while (l <= r){
            m = l + ((r-l) >> 1) ;
            // 中点作为初始能量能否过关
            if (f(m,max)){
                ans = m ;
                r = m -1 ;
            } else {
                l = m + 1 ;
            }
        }
        return ans ;
    }

    // 传入中点跟最大能量值，返回能否通关
    // 一旦能量大于建筑的最高值，那么就一定能通关，后续不用跑了
    // 一但能量小于0了，就说明没通过
    public static boolean f(int mod , int max){

        for (int i = 1 ; i <= n ; i++) {
            if (mod <= arr[i]){
                mod -= arr[i] - mod ;
            } else {
                mod += mod - arr[i] ;
            }
            if (mod >= max) {
                return true ;
            }
            if (mod < 0){
                return false ;
            }
        }
        return true ;
    }

}
