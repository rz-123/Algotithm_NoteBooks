package Mod_;

// 牛吃草
// 一共有n重量的草，A牛和B牛轮流吃，每次只能吃4的幂重量的草，
// 谁能在自己回合中正好把草吃完谁赢，返回谁赢
public class Code02_EatGrass {
    // 暴力递归
    // cur:传入A或B，
    public static String f(int n , String cur){
        // 如果 cur 为A ，那 enemy 就为B
        // 如果cur 为B，enemy 就为A
        String enemy = cur.equals("A") ? "B" : "A" ;
        // 如果剩余的重量<5,谁吃谁赢
        if (n < 5){
            return (n == 0 || n == 2) ? enemy : cur ;
        }
        int pick = 1 ; // 每次吃的重量
        while (pick <= n) {
            if (f(n - pick,enemy).equals(cur)){
                return enemy ;
            }
            pick *= 4 ;
        }
        return cur ;
    }

    // 规律解法
    // % 5 == 0 或 % 5 == 2 的都是B
    public static String f2(int n ){
        if (n % 5 == 0 || n % 5 == 2) {
            return "B" ;
        } else {
            return "A" ;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(i + " : " + f(i , "A"));
        }
    }



}
