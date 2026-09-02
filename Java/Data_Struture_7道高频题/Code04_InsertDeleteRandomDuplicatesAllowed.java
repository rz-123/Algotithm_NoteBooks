package Data_Struture_7道高频题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// 插入、删除和获取随机元素O(1)时间的结构，允许有重复数字
// https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/
public class Code04_InsertDeleteRandomDuplicatesAllowed {

    // 哈希表(value位置为HashSet) 加 数组
    class RandomizedCollection{

        // HashSet<Integer> 这里边存的是内存地址，因为数字可以重复，所以存一串
        public HashMap<Integer , HashSet<Integer>> map ;
        public ArrayList<Integer> arr ;

        public RandomizedCollection(){
            map = new HashMap<>() ;
            arr = new ArrayList<>() ;
        }

        // 添加数据
        public boolean insert(int val){
            arr.add(val) ;  // 数组中添加数据
            // 因为HashMap中的value是Set集合，所以要先创建集合
            HashSet<Integer> set = map.getOrDefault(val , new HashSet<Integer>()) ;
            set.add(arr.size() - 1) ; // 将刚才添加到数组中的数据下标加到集合中
            map.put(val , set) ;
            return set.size() == 1;// 该数据第一次插入返回true
        }

        // 删除数据
        public boolean remove(int val){
            // 里边没有 返回false
            if (! map.containsKey(val)){
                return false ;
            }
            // 还是根据值获取集合，然后进行相应的补位
            HashSet<Integer> integers = map.get(val);
            int MapIndex = integers.iterator().next(); // 在集合中选第一个数
            // 获取最后一位的数据
            int setval = arr.get(arr.size() - 1) ;
            // 如果要删除的数据等于数组中的最后一位，就不用补，直接删集合中对应的地址
            if (setval == val) {
                integers.remove(arr.size() - 1) ;
            } else {
                // 根据数组中最后一位的数据来获取hashMap中对应的集合数据
                HashSet<Integer> setvalSet = map.get(setval);
                // 该集合中添加删除数据的集合中的第一个地址
                setvalSet.add(MapIndex) ;
                // 将数组中最后一位数据调到要删除数据的位置
                arr.set(MapIndex , setval) ;
                // 最后一位的地址中删除最后一位的位置
                setvalSet.remove(arr.size() - 1) ;
                // 删除数据的集合中删除相应的地址
                integers.remove(MapIndex) ;
            }
            arr.remove(arr.size() - 1 ) ;

            // 最后判断 HashMap中的集合是否为空，为空 就删除整个数据
            if (integers.isEmpty()){
                map.remove(val) ;
            }
            return true ;
        }

        // 随机获取数据
        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }
}
