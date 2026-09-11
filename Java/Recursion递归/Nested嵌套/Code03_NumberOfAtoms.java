package Recursion递归.Nested嵌套;

import java.util.TreeMap;

// 解析含有嵌套的分子式 求每个原子的数量，按照字典序排列
// https://leetcode.cn/problems/number-of-atoms/description/
public class Code03_NumberOfAtoms {

    // 全局地址
    public static int where ;
    // 主方法
    public static String countOfAtoms(String str){
        where = 0 ;
        TreeMap<String , Integer> map = f(str.toCharArray() , 0) ;
        StringBuilder ans = new StringBuilder() ;
        for (String key : map.keySet()) {
            ans.append(key) ;
            int cnt = map.get(key) ;
            if (cnt > 1) {
                ans.append(cnt) ;  // 添加数据到ans
            }
        }
        return ans.toString() ;
    }

    // 传入数组以及起始位置
    public static TreeMap<String , Integer> f(char[] s , int i){
        TreeMap<String , Integer> ans = new TreeMap<>() ; // 总表
        StringBuilder name = new StringBuilder() ; // 之前收集的化学符号
        TreeMap<String , Integer> pre = null ; // 之前收集的有序表
        int cnt = 0 ; // 化学符号后边的数字，控制着前面的字母翻几倍
        while ( i < s.length && s[i] != ')'){
            // 如果碰到大写字母 或者 左括号，就执行fill方法
            if (s[i] >= 'A' && s[i] <= 'Z' || s[i] == '('){
                fill(ans , name , pre , cnt);
                name.setLength(0);  // 把name一次性清空
                pre = null ;
                cnt = 0 ;
                if (s[i] >= 'A' && s[i] <= 'Z'){
                    name.append(s[i++]) ;
                } else {
                    pre = f(s, i+1) ; // 遇到左括号，从i+1位置递归
                    i = where + 1 ;
                }
            } else if (s[i] >= 'a' && s[i] <= 'z'){
                name.append(s[i++]) ;
            } else { // 碰到数值时
                cnt = cnt*10 + s[i++] - '0';
            }
        }
        fill(ans , name , pre , cnt); // while循环完成后还得再执行一遍fill方法
        where = i ;
        return ans ;
    }

    // 根据cnt来对相应的化学符号进行写入
    public static void fill(TreeMap<String, Integer> ans, StringBuilder name, TreeMap<String, Integer> pre, int cnt){
        if (name.length() > 0 || pre != null){
            cnt = cnt == 0 ? 1 :cnt ; // 因为化学符号后边没数字的就说明要写入一遍
            if (name.length() > 0) {
                String key = name.toString();
                ans.put(key , ans.getOrDefault(key , 0) + cnt) ;
            } else {
                for (String key : pre.keySet()) {
                    ans.put(key , ans.getOrDefault(key ,0) + pre.get(key) * cnt) ;
                }
            }
        }
    }
}
