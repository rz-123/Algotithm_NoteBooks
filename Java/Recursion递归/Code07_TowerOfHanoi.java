package Recursion递归;

import java.sql.SQLOutput;
import java.util.List;

// 汉塔诺问题
// https://leetcode.cn/problems/hanota-lcci/description/
public class Code07_TowerOfHanoi {

    public static void hanoi(int n){
        if (n > 0 ){
            f(n , "左" , "中" , "右");
        }
    }
    public static void f(int i , String left , String to , String right){
        if (i == 1){
            System.out.println("圆盘1从" + left + "到" + right);
        } else {
            f(i-1 , left , right , to) ;
            System.out.println("移动圆盘" + i + "从" + left + "到" + right);
            f(i-1 , to , right , left) ;
        }
    }


    // 交换数据
//    public void hanota1(List<Integer> A, List<Integer> B, List<Integer> C) {
//        // if(A.size() > 0){
//
//        // }
//        f1(A.size() , A , B , C);
//    }
//    public List<Integer> f1(int i , List<Integer> A , List<Integer> B , List<Integer> C){
//        if(i == 0) {
//            return C ;
//        }
//        if(i == 1){
//            C.add(A.remove(A.size()-1)) ;
//            return C;
//        } else {
//            f(i - 1 , A , C , B) ;
//            C.add(A.remove(A.size() - 1)) ;
//            f(i - 1 , B , A , C) ;
//        }
//        return C ;
//    }


    public static void main(String[] args) {
        hanoi(3);
    }
}
