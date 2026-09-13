package Mod_;

import java.io.*;
import java.util.StringTokenizer;

// 消灭怪兽
// n个技能，每个技能有一个伤害，当怪兽的血量降低到一定程度，该技能有双倍伤害
// 问 最少用几个技能能消灭他
// https://www.nowcoder.com/practice/d88ef50f8dab4850be8cd4b95514bbbd
public class Code01_KillMonsterEverySkillUseOnce {

    // kill[i] → 第 i 个技能的伤害值（打多少血）
    // blood[i] → 第 i 个技能的触发阈值（怪兽血量降到这个值以下时，这个技能打双倍）
    public static int[] kill = new int[11] ;
    public static int[] blood = new int[11] ; // 血量


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StreamTokenizer in = new StreamTokenizer(br) ;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)) ;
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            int t = (int) in.nval ;
            for (int i = 0; i < t; i++) {
                in.nextToken() ;
                int n = (int) in.nval ;
                in.nextToken() ;
                int m = (int) in.nval ;
                for (int j = 0; j < n; j++) {
                    in.nextToken() ;
                    kill[j] = (int) in.nval ;
                    in.nextToken();
                    blood[j] = (int) in.nval ;
                }
                int ans = f(n , 0 , m ) ;
                out.println(ans == Integer.MAX_VALUE ? -1 : ans);
            }
        }
        out.flush();
        br.close();
        out.close();
    }

    // n:有多少技能；i:当前来到了几号技能；r:怪兽的血量
    public static int f(int n , int i , int r){
        // 如果怪兽的血量小于0 ，就返回用了多少技能
        if (r <= 0){
            return i ;
        }
        if (i == n){
            return Integer.MAX_VALUE ; // 打不死
        }
        int ans = Integer.MAX_VALUE ;  // 返回至少需要多少技能
        for (int j = i; j < n; j++) {
            swap(i , j); // 交换 位置
            // 递归，全排列，i跟j交换，然后挨个试，判断怪兽血量是否低于该技能的阈值
            // 一旦血量低于怪兽的阈值就将伤害增加两倍。
            // 选出最小值
            ans = Math.min(ans , f(n , i+1 , r - (r > blood[i] ? kill[i] : kill[i] * 2)));
            swap(i , j);  // 恢复原貌
        }
        return ans ;

    }

    // 伤害跟阈值需要同时交换，因为伤害跟阈值都是技能的两属性，必须一一对应
    public static void swap(int i , int j){
        int tmp = kill[i] ;
        kill[i] = kill[j] ;
        kill[j] = tmp ;
        tmp = blood[i];
        blood[i] = blood[j];
        blood[j] = tmp ;
    }
}
