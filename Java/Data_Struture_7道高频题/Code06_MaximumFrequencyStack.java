package Data_Struture_7道高频题;

import class106.HashFunction;

import java.util.ArrayList;
import java.util.HashMap;

// 最大频率栈
// https://leetcode.cn/problems/maximum-frequency-stack/
public class Code06_MaximumFrequencyStack {

    class FreStack{


        // 两个HashMap
        // 每层节点，遇到重复的数 新建一层
        private HashMap<Integer , ArrayList<Integer>> cntValues = new HashMap<>() ;
        private HashMap<Integer , Integer> valueTimes = new HashMap<>() ;
        // 记录最大次数变量
        private int max ;

        // 压入栈顶
        public void push(int val){
            // 加入valueTimes；value如果之前有，第二个Int每次加1
            valueTimes.put(val , valueTimes.getOrDefault(val , 0) + 1 ) ;
            int i = valueTimes.get(val); // 从valuetimes获取下标
            // 如果cntvalues中没有该数，就新建一层
            if (! cntValues.containsKey(i)){
                cntValues.put(i , new ArrayList<>()) ;
            }
            // 获取cntvalues中的i对应的列表
            ArrayList<Integer> curTimeValues = cntValues.get(i);
            curTimeValues.add(val) ; // 在列表中添加
            // 更新最大次数
            max = Math.max(max , i) ;
        }
        // 弹出出现次数最多的数，如果不是一个，就弹出最靠近栈顶的数
        public int pop(){
            // 找到出现次数最多的数所在的列表
            ArrayList<Integer> topValues = cntValues.get(max);
            // 删除最后一位  removeLast()
            int ans = topValues.removeLast();
            // 如果该列表大小为0，那就直接减去这一层
            if (topValues.size() == 0){
                cntValues.remove(max -- ) ;
            }
            int times = valueTimes.get(ans); // 获取最大值在哈希表中的计数
            if (times == 1){
                valueTimes.remove(ans) ; // 如果数值剩下一个 就直接删除该值
            } else {
                valueTimes.put(ans , times - 1) ; // 否则就让计数减1
            }
            return ans ;
        }
    }

}
