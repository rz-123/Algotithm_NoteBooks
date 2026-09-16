package Trie_前缀树;

import java.io.*;
import java.util.HashMap;

// 给定一个数组 跟一个整数，要求返回累加和为该整数的最大长度
// https://www.nowcoder.com/practice/36fb0fd3c656480c92b569258a1223d5
public class Code02_LongestSubarraySumEqualsAim {

    public static int MAX = 100001 ;
    public static int[] arr = new int[MAX];
    public static int n , aim ;
    // key:前缀和   value是该前缀和最早出现的位置
    public static HashMap<Integer , Integer> map = new HashMap<>() ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer in = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ;
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval ;
            in.nextToken() ;
            aim = (int) in.nval ;
            for (int i = 0 ; i < n ; i++){
                in.nextToken() ;
                arr[i] = (int) in.nval ;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();

    }


    public static int compute(){
        map.clear(); // 清空map
        map.put(0,-1) ; // 前缀和0 数组为空时就存在了
        int ans = 0 ;
        for (int i = 0 , sum = 0; i < n; i++) {
            sum += arr[i]; // 计算前缀和
            // 查 sum-aim的最早出现的累加和
            if (map.containsKey(sum - aim)){
                // i-最早出现的位置
                ans = Math.max(ans , i - map.get(sum-aim)) ;
            }
            // 如果之前的map没有该累加和 就添加，如果有不做操作
            if (!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return ans ;
    }
}
