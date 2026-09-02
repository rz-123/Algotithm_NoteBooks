package Data_Struture_7道高频题;

import java.util.ArrayList;
import java.util.HashMap;

// 插入、删除和获取随机元素O(1)时间的结构，不能有重复数字
// https://leetcode.cn/problems/insert-delete-getrandom-o1/
public class Code03_InsertDeleteRandom {

    // 哈希 加 数组

    class RandomizedSet{
        public HashMap<Integer , Integer> map ;
        public ArrayList<Integer> arr ;
        public RandomizedSet(){
            map = new HashMap<>();
            arr = new ArrayList<>() ;
        }
        // 添加数据
        public boolean insert(int val){
            if (map.containsKey(val)){
                return false ;  // 不允许有重复数据
            }
            map.put(val , arr.size()) ;  // key为链表长度，
            arr.add(val) ; //
            return true ;
        }
        // 删除数据
        public boolean remove(int val){
            // 如果里边没有 返回false
            if (! map.containsKey(val)) {
                return false ;
            }
            // 获取当前vale的地址
            int valIndex = map.get(val); // 在哈希表中获取当前值的地址
            int enValue = arr.get(arr.size() - 1) ; // 在链表中获取最后一位的值
            // 将最后一位的value补到要删除的位置上
            map.put(enValue , valIndex) ;
            arr.set(valIndex , enValue) ;
            map.remove( val );
            arr.remove(arr.size() - 1) ;
            return true ;
        }

        // 等概率随机获取数据
        public int getRandom(){
            return arr.get((int) (Math.random() * arr.size())) ;
        }
    }

}
